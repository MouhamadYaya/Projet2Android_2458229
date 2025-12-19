package com.example.projet2android_2458229

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.projet2android_2458229.Mesressources.theme.ThemeProjet
import com.example.projet2android_2458229.data.Livre
import com.example.projet2android_2458229.data.filterLivres
import com.example.projet2android_2458229.data.getLivre
import com.example.projet2android_2458229.data.getSampleLivre
import com.example.projet2android_2458229.data.readLivreFromFile
import com.example.projet2android_2458229.data.saveToFile


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
            Bibliotheque()
        }
    }
}

@Composable
fun Bibliotheque() {
    var isDarkTheme by remember { mutableStateOf(false) }
    ThemeProjet(darkTheme = isDarkTheme) {
        AppNavigation(
            isDarkTheme = isDarkTheme, onThemeChange = {
                isDarkTheme = it
            }
        )
    }
}

@Composable
fun ImageDistanteAvancee() {
    SubcomposeAsyncImage(
        model = "https://picsum.photos/200",
        contentDescription = "Description de l'image",
        modifier = Modifier.size(70.dp),
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
                        icon = {
                            Icon(
                                ecran.icon,
                                contentDescription = null
                            )
                        },
                        label = { Text(stringResource(ecran.titreRes)) },
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
            composable(ecran.Home.route) { EcranAccueil(navController = navController) }
            composable("collection") { EcranDeCollection(navController = navController) }
            composable("ajoutlivre") { EcranAjoutDeLivre(navController = navController) }
            composable(ecran.Profile.route) { EcranDeProfil() }
            composable(ecran.Settings.route) {
                EcranParametreAvecTheme(isDarkTheme, onThemeChange)
            }
        }
    }
}

@Composable
fun EcranAccueil(navController: NavHostController) {
    val orientation = LocalConfiguration.current.orientation

    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.home),
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(14.dp))

            Text(
                text = stringResource(R.string.what_do_you_want),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(25.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(22.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { navController.navigate("collection") },
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        stringResource(R.string.collection),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Button(
                    onClick = { navController.navigate("ajoutlivre") },
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        stringResource(R.string.add_book),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    } else {
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
                stringResource(R.string.what_do_you_want),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )


            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate("collection") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(80.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    stringResource(R.string.collection),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate("ajoutlivre") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(80.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(stringResource(R.string.add_book))
            }
        }
    }
}


@Composable
fun EcranDeCollection(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            stringResource(R.string.collection), modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        LivreListWithSearch(
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = { navController.navigate("ajoutlivre") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                stringResource(R.string.add_book),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun EcranAjoutDeLivre(navController: NavHostController) {
    var titre by rememberSaveable { mutableStateOf("") }
    var auteur by rememberSaveable { mutableStateOf("") }
    var TypeDeLivre by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    val fichierJson = stringResource(R.string.livre_json)
    val orientation = LocalConfiguration.current.orientation

    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = titre,
                    onValueChange = {
                        titre = it
                                    },
                    label = { Text(stringResource(R.string.add_book_title)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(5.dp))

                TextField(
                    value = auteur,
                    onValueChange = { auteur = it },
                    label = { Text(stringResource(R.string.add_book_author)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (titre.isNotEmpty() && auteur.isNotEmpty()) {
                            val livreList = context.readLivreFromFile(fichierJson).toMutableList()
                            livreList.add(
                                Livre(
                                    id = livreList.size + 1,
                                    titre = titre,
                                    auteur = auteur,
                                    type = "Typedefaut",
                                    imageResource = R.drawable.ic_launcher_background
                                )
                            )
                            context.saveToFile(livreList, fichierJson)
                            navController.popBackStack()
                        }
                    },
                    enabled = titre.isNotEmpty() && auteur.isNotEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp)
                ) {
                    Text(stringResource(R.string.add_book))
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(R.string.add_book),
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(32.dp))

            TextField(
                value = titre,
                onValueChange = {
                    titre = it
                },
                label = {
                    Text("Titre du livre")
                },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(16.dp))

            TextField(
                value = auteur,
                onValueChange = {
                    auteur = it
                },
                label = {
                    Text("Auteur")
                },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = {
                    if (!titre.isEmpty() && !auteur.isEmpty()) {
                        val livreList = context.readLivreFromFile(fichierJson).toMutableList()
                        val nouveauLivre = Livre(
                            id = livreList.size + 1,
                            titre = titre,
                            auteur = auteur,
                            type = "Typedefaut",
                            imageResource = R.drawable.ic_launcher_background
                        )

                        livreList.add(nouveauLivre)

                        context.saveToFile(livreList, fichierJson)
                        navController.popBackStack()
                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                enabled = !titre.isEmpty() && !auteur.isEmpty()
            ) {
                Text("Ajouter le livre")
            }

        }
    }
}

@Composable
fun EcranDeProfil() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.profile_title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))
        Text(
            stringResource(R.string.profile_status),
            style = MaterialTheme.typography.bodyLarge
        )

    }
}

@Composable
fun EcranParametreAvecTheme(isDarkTheme: Boolean, onThemeChange: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Icon(Icons.Default.Settings, contentDescription = null, modifier = Modifier.size(48.dp))
        Spacer(Modifier.height(16.dp))
        Text(
            "Écran des paramètres",
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                stringResource(R.string.dark_mode)
            )

            Spacer(Modifier.width(8.dp))
            Switch(checked = isDarkTheme, onCheckedChange = onThemeChange)
        }
        ImageDistanteAvancee()
    }

}

@Composable
fun LivreListWithSearch(modifier: Modifier = Modifier) {
    var searchTitre by rememberSaveable { mutableStateOf("") }
    var searchAuteur by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {

            TextField(
                value = searchTitre,
                label = { Text("Titre") },
                onValueChange = { searchTitre = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
            )

            TextField(
                value = searchAuteur,
                label = { Text("Auteur") },
                onValueChange = { searchAuteur = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            )
        }
        val context = LocalContext.current
        var LivreList = context.readLivreFromFile(stringResource(R.string.livre_json))
        if (LivreList.isEmpty()) {
            LivreList = getSampleLivre()
            context.saveToFile(LivreList, stringResource(R.string.livre_json))
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filterLivres(LivreList, searchTitre, searchAuteur)) { livre ->
                LivreCard(livre)
            }
        }
    }
}

@Composable
fun LivreCard(livre: Livre, modifier: Modifier = Modifier) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Card(
        border = BorderStroke(1.dp, colorResource(R.color.purple_500)),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { isExpanded = !isExpanded }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = livre.imageResource),
                contentDescription = livre.titre,
                modifier = Modifier.size(50.dp)
            )

            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(text = livre.titre, fontWeight = FontWeight.Bold)
                Text(text = livre.auteur)
                Text(text = "Type: ${livre.type}")
            }
        }

        if (isExpanded) {
            Text(
                text = "ID du livre : ${livre.id}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

/**
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCollectionScreen() {
CollectionScreen()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAddBookScreen() {
EcranAjoutDeLivre()
}

 */




