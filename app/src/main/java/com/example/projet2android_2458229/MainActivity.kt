package com.example.projet2android_2458229

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projet2android_2458229.ui.theme.Projet2Android_2458229Theme

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{id}") {
        fun withId(id: Int) = "detail/$id"
    }
    object Settings : Screen("settings")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Projet2Android_2458229Theme {
                AppNavigation()
            }
        }
    }
}

// just ecran vide , a implementer apres
@Composable
fun HomeScreen() {
    Text("Home Screen")
}

@Composable
fun DetailScreen(id: Int) {
    Text("Detail Screen - ID: $id")
}

@Composable
fun SettingsScreen() {
    Text("Settings Screen")
}

// Navigation test
@Composable
fun AppNavigation() {}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    Projet2Android_2458229Theme {
        HomeScreen()
    }
}
