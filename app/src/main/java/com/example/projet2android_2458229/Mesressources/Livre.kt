package com.example.projet2android_2458229.data
import com.example.projet2android_2458229.R

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
            auteur = "J. K. Rowling",
            type = "Roman",
            annee = 1997,
            imageResource = R.drawable.ic_launcher_background,
            tags = listOf("fantasy", "magie")
        ),
        Livre(
            id = 2,
            titre = "Le Seigneur des Anneaux",
            auteur = "J. R. R. Tolkien",
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
        )
    )
}
