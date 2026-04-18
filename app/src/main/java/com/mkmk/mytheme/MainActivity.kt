package com.mkmk.mytheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mkmk.mytheme.ui.AppTheme

data class AppShellState(
    val serviceEnabled: Boolean = false,
    val perAppMode: Boolean = true,
    val grayscaleIntensity: Float = 0.85f,
    val selectedAppsCount: Int = 0
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AppShell()
            }
        }
    }
}

@Composable
private fun AppShell() {
    var state by remember {
        mutableStateOf(AppShellState())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "My Theme",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Lightweight per-app grayscale planner with low battery impact.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Card(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text("Accessibility Service")
                    Text(
                        text = if (state.serviceEnabled) "Enabled" else "Enable to continue onboarding",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = state.serviceEnabled,
                    onCheckedChange = { checked -> state = state.copy(serviceEnabled = checked) }
                )
            }
        }

        Card(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .toggleable(
                        value = state.perAppMode,
                        onValueChange = { state = state.copy(perAppMode = it) }
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text("Per-app Mode")
                    Text(
                        text = "Selected apps: ${state.selectedAppsCount}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = state.perAppMode,
                    onCheckedChange = null
                )
            }
        }

        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Grayscale Intensity")
                var sliderValue by remember { mutableFloatStateOf(state.grayscaleIntensity) }
                Slider(
                    value = sliderValue,
                    onValueChange = {
                        sliderValue = it
                        state = state.copy(grayscaleIntensity = it)
                    },
                    valueRange = 0.6f..1f
                )
                Text(
                    text = "${(state.grayscaleIntensity * 100).toInt()}%",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        AnimatedVisibility(
            visible = !state.serviceEnabled,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Card(Modifier.fillMaxWidth()) {
                Text(
                    text = "Next: add onboarding flow to guide service permission and battery-safe defaults.",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Text(
            text = "Status: app shell ready for selected-app list, permission onboarding, and foreground tile control.",
            style = MaterialTheme.typography.labelMedium
        )
    }
}
