package com.example.projet2android_2458229.Mesressources
import com.example.projet2android_2458229.R

data class Livre(
    val id: Int,
    val titre: String,
    val auteur: String,
    val couverture: Int
)

fun getLivres(titre: String? = null): List<Livre> =
    if (titre == null)
        getLivresExemples()
    else
        getLivresExemples().filter {
            it.titre.lowercase().contains(titre.lowercase())
        }

fun getLivresExemples() = listOf(
    Livre(1, "Harry Potter à l'école des sorciers", "J. K. Rowling", R.drawable.images),
    Livre(2, "Le Seigneur des anneaux : La communauté de l'anneau", "J. R. R. Tolkien", R.drawable.ic_launcher_background),
    Livre(3, "1984", "George Orwell", R.drawable.ic_launcher_background),
    Livre(4, "Le Petit Prince", "Antoine de Saint-Exupéry", R.drawable.ic_launcher_background)
)
