package com.example.wildwaysenjoyer.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.wildwaysenjoyer.data.wildwaysArtistInfo
import com.example.wildwaysenjoyer.data.wildwaysTracks
import com.example.wildwaysenjoyer.model.ArtistInfo
import com.example.wildwaysenjoyer.model.Track
import com.example.wildwaysenjoyer.model.TrackFilter
import com.example.wildwaysenjoyer.model.TracksSummary
import com.example.wildwaysenjoyer.model.WildwaysUiState

// State holder хранит состояние экрана и принимает пользовательские события.
// В Compose это хороший способ отделить логику от рисования UI.
class WildwaysStateHolder(
    // Значения по умолчанию после = позволяют создавать объект без аргументов.
    private val artistInfo: ArtistInfo = wildwaysArtistInfo,
    initialTracks: List<Track> = wildwaysTracks
) {
    // Это source of truth: исходные данные, из которых потом строится UiState.
    // private скрывает внутренние детали от внешнего кода.
    private var allTracks = initialTracks
    private var searchQuery = ""
    private var selectedFilter = TrackFilter.ALL
    private var expandedTrackId: Int? = null

    // Как только uiState меняется, composable, которые его читают, будут перерисованы.
    // by mutableStateOf(...) - это делегат свойства.
    // Он говорит Compose наблюдать за этим значением.
    // private set значит: читать uiState можно снаружи, а менять - только внутри state holder.
    var uiState by mutableStateOf(buildUiState())
        private set

    // onXxx... - распространенный стиль именования для обработчиков событий.
    fun onSearchQueryChange(query: String) {
        searchQuery = query
        // При новом поиске удобно закрыть раскрытую карточку, чтобы UI не путал пользователя.
        expandedTrackId = null
        refreshUiState()
    }

    fun onFilterSelected(filter: TrackFilter) {
        selectedFilter = filter
        expandedTrackId = null
        refreshUiState()
    }

    fun onToggleTrackDetails(trackId: Int) {
        // Если нажали на уже открытую карточку, закрываем ее. Иначе открываем новую.
        expandedTrackId = if (expandedTrackId == trackId) null else trackId
        refreshUiState()
    }

    fun onCycleTrackStatus(trackId: Int) {
        // map создает новый список на основе старого.
        // Это важный подход в Compose: вместо мутации мы часто создаем новую версию данных.
        allTracks = allTracks.map { track ->
            // Лямбда { track -> ... } означает "для каждого элемента списка выполни этот код".
            if (track.id == trackId) {
                // copy(...) удобно использовать с data class, чтобы поменять только одно поле.
                track.copy(status = track.status.next())
            } else {
                // В Kotlin if - это выражение, оно возвращает значение.
                track
            }
        }
        refreshUiState()
    }

    private fun refreshUiState() {
        // Вместо ручного обновления нескольких кусков UI мы просто собираем новый снимок состояния.
        uiState = buildUiState()
    }

    private fun buildUiState(): WildwaysUiState {
        // Производное состояние: видимые треки вычисляются из списка, поиска и фильтра.
        val visibleTracks = allTracks.filter { track ->
            // && означает логическое "И": трек должен пройти и поиск, и фильтр.
            track.matchesFilter(selectedFilter) && track.matchesSearch(searchQuery)
        }

        return WildwaysUiState(
            artistInfo = artistInfo,
            searchQuery = searchQuery,
            searchExample = allTracks.firstOrNull()?.title ?: "Название трека",
            selectedFilter = selectedFilter,
            tracks = visibleTracks,
            summary = TracksSummary.from(allTracks, visibleTracks.size),
            // takeIf вернет expandedTrackId только если условие true, иначе вернет null.
            expandedTrackId = expandedTrackId?.takeIf { expandedId ->
                // any { ... } проверяет, есть ли хотя бы один элемент, который подходит под условие.
                visibleTracks.any { it.id == expandedId }
            },
            isEmptyResult = visibleTracks.isEmpty()
        )
    }
}

@Composable
fun rememberWildwaysStateHolder(): WildwaysStateHolder {
    // remember сохраняет один и тот же объект между recomposition внутри текущей композиции.
    return remember { WildwaysStateHolder() }
}

// Это extension function: функция выглядит так, будто принадлежит Track.
// Такой стиль удобен, когда логика относится к конкретной модели.
private fun Track.matchesFilter(filter: TrackFilter): Boolean {
    return filter.status == null || status == filter.status
}

private fun Track.matchesSearch(query: String): Boolean {
    // trim() убирает пробелы по краям строки, lowercase() приводит текст к нижнему регистру.
    val normalizedQuery = query.trim().lowercase()
    if (normalizedQuery.isBlank()) {
        return true
    }

    // По твоему концепту ищем и по названию, и по статусу.
    return title.lowercase().contains(normalizedQuery) ||
        status.label.lowercase().contains(normalizedQuery)
}
