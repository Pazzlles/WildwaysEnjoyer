package com.example.wildwaysenjoyer

import com.example.wildwaysenjoyer.model.Track
import com.example.wildwaysenjoyer.model.TrackFilter
import com.example.wildwaysenjoyer.model.TrackStatus
import com.example.wildwaysenjoyer.state.WildwaysStateHolder
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WildwaysStateHolderTest {

    @Test
    fun `search finds track by title`() {
        val holder = WildwaysStateHolder(initialTracks = testTracks)

        holder.onSearchQueryChange("лёд")

        assertEquals(listOf("Лёд"), holder.uiState.tracks.map { it.title })
    }

    @Test
    fun `search finds track by status label`() {
        val holder = WildwaysStateHolder(initialTracks = testTracks)

        holder.onSearchQueryChange("избран")

        assertEquals(listOf("Травмы"), holder.uiState.tracks.map { it.title })
    }

    @Test
    fun `cycling status refreshes filtered list`() {
        val holder = WildwaysStateHolder(initialTracks = testTracks)

        holder.onFilterSelected(TrackFilter.TO_LISTEN)
        holder.onCycleTrackStatus(trackId = 1)

        assertTrue(holder.uiState.tracks.isEmpty())
        assertTrue(holder.uiState.isEmptyResult)
        assertEquals(2, holder.uiState.summary.listenedCount)
    }

    @Test
    fun `empty state is shown when nothing matches`() {
        val holder = WildwaysStateHolder(initialTracks = testTracks)

        holder.onSearchQueryChange("несуществующий трек")

        assertTrue(holder.uiState.isEmptyResult)
        assertTrue(holder.uiState.tracks.isEmpty())
    }

    private companion object {
        val testTracks = listOf(
            Track(
                id = 1,
                title = "Лёд",
                album = "Single",
                releaseYear = 2025,
                description = "Test",
                status = TrackStatus.TO_LISTEN
            ),
            Track(
                id = 2,
                title = "Ангелы",
                album = "Сумерки",
                releaseYear = 2025,
                description = "Test",
                status = TrackStatus.LISTENED
            ),
            Track(
                id = 3,
                title = "Травмы",
                album = "Сумерки",
                releaseYear = 2025,
                description = "Test",
                status = TrackStatus.FAVORITE
            )
        )
    }
}
