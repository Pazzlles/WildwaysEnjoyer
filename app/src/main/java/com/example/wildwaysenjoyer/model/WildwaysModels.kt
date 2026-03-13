package com.example.wildwaysenjoyer.model

// Enum удобен, когда набор состояний фиксирован и мы хотим безопасно переключаться между ними.
// val label: String после имени enum означает, что у каждого значения есть свое текстовое название.
enum class TrackStatus(val label: String) {
    TO_LISTEN("Прослушать"),
    LISTENED("Прослушано"),
    FAVORITE("Избранное");
    // Точка с запятой в enum нужна, потому что ниже объявлена функция.

    // Логика перехода к следующему статусу живет рядом с данными, а не размазывается по UI.
    // entries - это список всех значений enum, а ordinal - индекс текущего значения.
    fun next(): TrackStatus = entries[(ordinal + 1) % entries.size]
}

// Фильтр - это отдельная сущность, потому что у него есть вариант "Все", которого нет у статуса.
// status может быть null: это значит "не фильтровать по статусу".
enum class TrackFilter(val label: String, val status: TrackStatus?) {
    ALL("Все", null),
    TO_LISTEN("Прослушать", TrackStatus.TO_LISTEN),
    LISTENED("Прослушано", TrackStatus.LISTENED),
    FAVORITE("Избранное", TrackStatus.FAVORITE)
}

// data class автоматически получает equals(), hashCode(), toString() и copy().
data class Track(
    val id: Int,
    val title: String,
    val album: String,
    val releaseYear: Int,
    val lyrics: String,
    val status: TrackStatus
)

data class ArtistInfo(
    val name: String,
    val hometown: String,
    val formedYear: String,
    val genre: String,
    val about: String
)

// Summary - это уже не "сырые данные", а удобная сводка, подготовленная для экрана.
// Экрану проще получить готовые цифры, чем считать их внутри composable.
data class TracksSummary(
    val totalCount: Int,
    val visibleCount: Int,
    val toListenCount: Int,
    val listenedCount: Int,
    val favoriteCount: Int
) {
    // companion object похож на "статические" методы в Java.
    companion object {
        fun from(allTracks: List<Track>, visibleCount: Int): TracksSummary {
            return TracksSummary(
                totalCount = allTracks.size,
                visibleCount = visibleCount,
                // count { ... } считает элементы, для которых условие внутри лямбды вернуло true.
                toListenCount = allTracks.count { it.status == TrackStatus.TO_LISTEN },
                listenedCount = allTracks.count { it.status == TrackStatus.LISTENED },
                favoriteCount = allTracks.count { it.status == TrackStatus.FAVORITE }
            )
        }
    }
}

// UiState - это один снимок всего, что нужно экрану прямо сейчас для отрисовки.
// Такой объект удобно передавать в composable одним параметром.
data class WildwaysUiState(
    val artistInfo: ArtistInfo,
    val searchQuery: String,
    val searchExample: String,
    val selectedFilter: TrackFilter,
    val tracks: List<Track>,
    val summary: TracksSummary,
    val expandedTrackId: Int?,
    val isEmptyResult: Boolean
)
