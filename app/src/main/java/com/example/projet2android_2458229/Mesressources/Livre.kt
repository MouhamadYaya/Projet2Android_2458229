package com.example.projet2android_2458229.data
import android.content.Context
import android.util.Log
import com.example.projet2android_2458229.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

data class Livre(
    val id: Int,
    val titre: String,
    val auteur: String,
    val type: String,
    val annee: Int? = null,

    val langue: String = "Français",
    val imageResource: Int ,
    val tags: List<String> = emptyList()
)

fun getLivre(titre: String? = null, auteur: String? = null): List<Livre> =
    getSampleLivre()
        .filter { livre ->
            (titre == null || livre.titre.lowercase().contains(titre.lowercase())) &&
                    (auteur == null || livre.auteur.lowercase().contains(auteur.lowercase()))
        }

fun getSampleLivre(): List<Livre> {
    return listOf(
        Livre(
            id = 1,
            titre = "Harry Potter à l'école des sorciers",
            auteur = "Francois",
            type = "Roman",
            annee = 1997,
            imageResource = R.drawable.ic_launcher_background,
            tags = listOf("fantasy", "magie")
        ),
        Livre(
            id = 2,
            titre = "Le Seigneur des Anneaux",
            auteur = "Tolkien",
            type = "Roman",
            annee = 1954,

            imageResource = R.drawable.ic_launcher_background,
            tags = listOf("fantasy", "aventure")
        ),
        Livre(
            id = 3,
            titre = "1984",
            auteur = "George Orwell",
            type = "Roman",
            annee = 1949,

            imageResource = R.drawable.ic_launcher_background,
            tags = listOf("politique", "dystopie")
        ),
        Livre(
            id = 4,
            titre = "Le Petit Prince",
            auteur = "Antoine de Saint-Exupéry",
            type = "Conte",
            annee = 1943,

            imageResource = R.drawable.ic_launcher_background,
            tags = listOf("philosophie", "classique")
        ),
        Livre(
            id = 5,
            titre = "L'homme riche",
            auteur = "papy",
            type = "Conte",
            annee = 1943,

            imageResource = R.drawable.ic_launcher_background,
            tags = listOf("philosophie", "classique")
        )

    )
}
fun Context.saveToFile(Livres: List<Livre>, filename: String) {
    try {
        val file = File(this.filesDir, filename)
        file.writeText(Gson().toJson(Livres))
    } catch (e: Exception) {
        Log.e("FileIO", "Erreur lors de l'écriture du fichier", e)
    }
}
fun Context.readLivreFromFile(filename: String): List<Livre> {
    val file = File(this.filesDir, filename)
    return if (file.exists()) {
        try {
            val jsonString = file.readText()
            Log.i("jsonString", jsonString)

            val playerListType = object : TypeToken<List<Livre>>() {}.type
            Gson().fromJson(jsonString, playerListType)
        } catch (e: Exception) {
            Log.e("FileIO", "Erreur lors de la lecture du fichier", e)
            emptyList()
        }
    } else {
        emptyList()
    }
}
