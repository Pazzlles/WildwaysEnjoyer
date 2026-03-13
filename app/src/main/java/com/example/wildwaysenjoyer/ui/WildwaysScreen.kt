package com.example.wildwaysenjoyer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wildwaysenjoyer.model.ArtistInfo
import com.example.wildwaysenjoyer.model.Track
import com.example.wildwaysenjoyer.model.TrackFilter
import com.example.wildwaysenjoyer.model.TrackStatus
import com.example.wildwaysenjoyer.model.TracksSummary
import com.example.wildwaysenjoyer.model.WildwaysUiState
import com.example.wildwaysenjoyer.state.WildwaysStateHolder
import com.example.wildwaysenjoyer.state.rememberWildwaysStateHolder
import com.example.wildwaysenjoyer.ui.theme.WildwaysEnjoyerTheme

// @Composable помечает функцию, которая может рисовать UI в Compose.
@Composable
fun WildwaysRoute(
    // Значение по умолчанию позволяет вызывать Route без аргументов.
    stateHolder: WildwaysStateHolder = rememberWildwaysStateHolder()
) {
    // Route связывает state holder и экран: отсюда в UI уходят данные и callbacks.
    // Запись stateHolder::onSearchQueryChange - это ссылка на функцию.
    // Мы передаем сам обработчик, а не вызываем его прямо здесь.
    WildwaysScreen(
        uiState = stateHolder.uiState,
        onSearchQueryChange = stateHolder::onSearchQueryChange,
        onFilterSelected = stateHolder::onFilterSelected,
        onToggleTrackDetails = stateHolder::onToggleTrackDetails,
        onCycleTrackStatus = stateHolder::onCycleTrackStatus
    )
}

@Composable
// @OptIn нужен, когда мы сознательно используем experimental API библиотеки.
@OptIn(ExperimentalMaterial3Api::class)
fun WildwaysScreen(
    uiState: WildwaysUiState,
    onSearchQueryChange: (String) -> Unit,
    onFilterSelected: (TrackFilter) -> Unit,
    onToggleTrackDetails: (Int) -> Unit,
    onCycleTrackStatus: (Int) -> Unit,
    // Аргумент по умолчанию Modifier = Modifier делает функцию гибкой и удобной для переиспользования.
    modifier: Modifier = Modifier
) {
    // Сам экран stateless: он не хранит бизнес-состояние, а только рисует пришедший UiState.
    // Scaffold - готовый контейнер Material 3 для типового экрана с topBar, bottomBar и контентом.
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Wildways Track Watchlist",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Курышева А. – Б9124-09.03.03пикд(3)",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        // Для небольшого учебного списка достаточно обычного Column + verticalScroll.
        // Modifier в Compose обычно собирается цепочкой: каждый вызов добавляет новое поведение.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            ArtistHeroCard(artistInfo = uiState.artistInfo)
            SearchSection(
                query = uiState.searchQuery,
                searchExample = uiState.searchExample,
                onQueryChange = onSearchQueryChange
            )
            SummaryCard(summary = uiState.summary)
            FiltersRow(
                selectedFilter = uiState.selectedFilter,
                onFilterSelected = onFilterSelected
            )
            TracksSection(
                uiState = uiState,
                onToggleTrackDetails = onToggleTrackDetails,
                onCycleTrackStatus = onCycleTrackStatus
            )
        }
    }
}

@Composable
private fun ArtistHeroCard(artistInfo: ArtistInfo) {
    // Локальная val внутри функции помогает сократить длинные обращения к MaterialTheme.colorScheme.
    val colors = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colors.primaryContainer,
                        colors.secondaryContainer
                    )
                ),
                shape = RoundedCornerShape(28.dp)
            )
            .padding(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = artistInfo.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${artistInfo.hometown} • c ${artistInfo.formedYear} • ${artistInfo.genre}",
                style = MaterialTheme.typography.bodyMedium,
                color = colors.onSurfaceVariant
            )
            Text(
                text = artistInfo.about,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun SearchSection(
    query: String,
    searchExample: String,
    onQueryChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Поиск",
                style = MaterialTheme.typography.titleMedium
            )
            // Здесь TextField полностью управляется снаружи:
            // value приходит из state holder, а изменения уходят через callback.
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text("Название или статус") },
                placeholder = { Text("Например: $searchExample или избранное") }
            )
            if (query.isNotBlank()) {
                // Лямбда onClick = { onQueryChange("") } очистит поле поиска.
                TextButton(
                    onClick = { onQueryChange("") },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Сбросить поиск")
                }
            }
        }
    }
}

@Composable
private fun SummaryCard(summary: TracksSummary) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "Сводка по коллекции",
                style = MaterialTheme.typography.titleMedium
            )
//            Text(
//                text = "В библиотеке ${summary.totalCount} треков, а на экране сейчас " +
//                    "${summary.visibleCount}.",
//                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.onSurfaceVariant
//            )
//            HorizontalDivider()
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                SummaryBadge(
                    label = "Прослушать",
                    value = summary.toListenCount,
                    // weight(1f) делит доступную ширину между элементами Row поровну.
                    modifier = Modifier.weight(1f)
                )
                SummaryBadge(
                    label = "Прослушано",
                    value = summary.listenedCount,
                    modifier = Modifier.weight(1f)
                )
            }
            SummaryBadge(
                label = "Избранное",
                value = summary.favoriteCount,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun SummaryBadge(
    label: String,
    value: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun FiltersRow(
    selectedFilter: TrackFilter,
    onFilterSelected: (TrackFilter) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = "Фильтр по статусу",
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // entries.forEach { ... } перебирает все значения enum TrackFilter по очереди.
            TrackFilter.entries.forEach { filter ->
                FilterChip(
                    selected = filter == selectedFilter,
                    // Здесь мы создаем маленькую лямбду, которая при клике передаст выбранный фильтр вверх.
                    onClick = { onFilterSelected(filter) },
                    label = { Text(filter.label) }
                )
            }
        }
    }
}

@Composable
private fun TracksSection(
    uiState: WildwaysUiState,
    onToggleTrackDetails: (Int) -> Unit,
    onCycleTrackStatus: (Int) -> Unit
) {
    // Здесь хорошо видно идею UI = f(state): либо рисуем empty state, либо рисуем список.
    // В Compose обычный Kotlin if просто выбирает, какую ветку UI построить.
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Треки",
            style = MaterialTheme.typography.titleMedium
        )
        if (uiState.isEmptyResult) {
            EmptyResultCard(
                query = uiState.searchQuery,
                selectedFilter = uiState.selectedFilter
            )
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                // forEach проходит по готовому списку треков и рисует карточку для каждого.
                uiState.tracks.forEach { track ->
                    TrackCard(
                        track = track,
                        isExpanded = uiState.expandedTrackId == track.id,
                        // Здесь лямбда "запоминает" track.id и передает его обработчику при клике.
                        onToggleTrackDetails = { onToggleTrackDetails(track.id) },
                        onCycleTrackStatus = { onCycleTrackStatus(track.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyResultCard(
    query: String,
    selectedFilter: TrackFilter
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Ничего не найдено",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Попробуй очистить поиск или сменить фильтр. Сейчас запрос: " +
                    if (query.isBlank()) "пустой" else "\"$query\"",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Активный фильтр: ${selectedFilter.label}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun TrackCard(
    track: Track,
    isExpanded: Boolean,
    onToggleTrackDetails: () -> Unit,
    onCycleTrackStatus: () -> Unit
) {
    // Карточка не хранит свое состояние через remember.
    // Она получает все извне, поэтому ее проще тестировать и переиспользовать.
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = track.title,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "${track.album} • ${track.releaseYear}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                StatusBadge(status = track.status)
            }

            if (isExpanded) {
                // Описание показываем условно: UI просто следует за текущим состоянием.
                Text(
                    text = track.lyrics,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = onToggleTrackDetails,
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
                ) {
                    Text(
                        // Kotlin if можно использовать прямо внутри выражения для выбора текста.
                        text = if (isExpanded) "Скрыть текст песни" else "Открыть текст песни",
                        textAlign = TextAlign.Center
                    )
                }
                FilledTonalButton(
                    onClick = onCycleTrackStatus,
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "Сменить на ${track.status.next().label.lowercase()}",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun StatusBadge(status: TrackStatus) {
    // when в Kotlin может возвращать значение.
    // Здесь мы сразу получаем пару цветов и распаковываем ее в две переменные.
    val (containerColor, contentColor) = when (status) {
        TrackStatus.TO_LISTEN -> {
            MaterialTheme.colorScheme.secondaryContainer to
                MaterialTheme.colorScheme.onSecondaryContainer
        }

        TrackStatus.LISTENED -> {
            MaterialTheme.colorScheme.tertiaryContainer to
                MaterialTheme.colorScheme.onTertiaryContainer
        }

        TrackStatus.FAVORITE -> {
            MaterialTheme.colorScheme.primaryContainer to
                MaterialTheme.colorScheme.onPrimaryContainer
        }
    }

    Surface(
        shape = CircleShape,
        color = containerColor,
        contentColor = contentColor
    ) {
        Text(
            text = status.label,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

// @Preview позволяет увидеть composable в Android Studio без запуска на устройстве.
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WildwaysScreenPreview() {
    WildwaysEnjoyerTheme {
        WildwaysScreen(
            uiState = WildwaysStateHolder().uiState,
            onSearchQueryChange = {},
            onFilterSelected = {},
            onToggleTrackDetails = {},
            onCycleTrackStatus = {}
        )
    }
}
