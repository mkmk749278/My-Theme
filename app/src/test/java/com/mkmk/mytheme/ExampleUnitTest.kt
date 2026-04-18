package com.mkmk.mytheme

import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun appShellStateDefaults() {
        val state = AppShellState()
        assertEquals(true, state.perAppMode)
        assertEquals(false, state.serviceEnabled)
        assertEquals(0, state.selectedAppsCount)
    }
}
