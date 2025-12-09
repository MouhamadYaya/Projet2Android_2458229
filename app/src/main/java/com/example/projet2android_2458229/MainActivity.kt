package com.example.projet2android_2458229

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.projet2android_2458229.Mesressources.theme.ThemeProjet


sealed class ecran(
    val route: String,
    val icon: ImageVector,
    val titreRes: Int,
) {
    object Home : ecran(
        route = "home",
        titreRes = R.string.home,
        icon = Icons.Default.Home
    )
    object Profile : ecran(
        route = "profile",

        titreRes = R.string.profile,
        icon = Icons.Default.Person,
    )
    object Settings : ecran(
        route = "settings",
        titreRes = R.string.settings,
        icon = Icons.Default.Settings,
    )

    companion object {
        val items = listOf(Home, Profile, Settings)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
            ImageDistanteAvancee()
        }
    }
}

@Composable
fun MyApp() {
    var isDarkTheme by remember { mutableStateOf(false) }

    ThemeProjet(darkTheme = isDarkTheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavigation(isDarkTheme = isDarkTheme, onThemeChange = { newTheme ->
                isDarkTheme = newTheme
            })
        }
    }

}

@Composable
fun ImageDistanteAvancee() {
    SubcomposeAsyncImage(
        model = "https://picsum.photos/200",
        contentDescription = "Description de l'image",
        modifier = Modifier.size(200.dp),
        contentScale = ContentScale.Fit,
        loading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        error = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Erreur de chargement")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(isDarkTheme: Boolean, onThemeChange: (Boolean) -> Unit) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) },
        bottomBar = {
            NavigationBar {
                ecran.items.forEach { ecran ->
                    NavigationBarItem(
                        icon = { Icon(ecran.icon, contentDescription =null) }, //contentDescription =  stringResource(ecran.titreRes)
                        label = { Text(stringResource(ecran.titreRes))},
                        selected = currentRoute == ecran.route,
                        onClick = {
                            navController.navigate(ecran.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = ecran.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(ecran.Home.route) { EcranAccueil() }
            composable("collection") { CollectionScreen() }
            composable("addBook") { AddBookScreen() }
            composable(ecran.Profile.route) { ProfileScreen() }
            composable(ecran.Settings.route) {
                SettingsScreenWithTheme(isDarkTheme, onThemeChange)
            }
        }
    }
}

@Composable
fun EcranAccueil(navController: NavHostController = rememberNavController()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.home),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(40.dp))
        Text(
            text = "Que voulez-vous faire?",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.height(60.dp))

        Button(
            onClick = { navController.navigate("collection") },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(80.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(stringResource(R.string.collection), style = MaterialTheme.typography.titleMedium)
        }
        Spacer(Modifier.height(60.dp))
        Button(
            onClick = { navController.navigate("addBook") },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(80.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(stringResource(R.string.add_book))
        }
    }
}

@Composable
fun CollectionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Ma collection")
    }
}

@Composable
fun AddBookScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Ajouter un livre")
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(48.dp))
        Spacer(Modifier.height(16.dp))
        Text("Écran de profil")
    }
}

@Composable
fun SettingsScreenWithTheme(isDarkTheme: Boolean, onThemeChange: (Boolean) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Icon(Icons.Default.Settings, contentDescription = null, modifier = Modifier.size(48.dp))
        Spacer(Modifier.height(16.dp))
        Text("Écran des paramètres")
        Spacer(Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Mode sombre")
            Spacer(Modifier.width(8.dp))
            Switch(checked = isDarkTheme, onCheckedChange = onThemeChange)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ImageDistanteAvancee()
}
