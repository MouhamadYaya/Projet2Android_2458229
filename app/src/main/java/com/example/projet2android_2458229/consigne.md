# Projet Android - Session Automne 2025

Développez une application Android complète individuellement ou en équipe de 2 personnes qui démontre votre maîtrise des
concepts modernes de développement mobile.

## Exigences techniques

**Interface utilisateur**

- Interface entièrement construite avec  Jetpack Compose
- Thème *Material 3* personnalisé avec des couleurs, typographies et formes cohérentes
- Possibilité de facilement passer d'un thème sombre à un thème clair, et vice-versa, dans l'application
- Support multilingue (minimum 3 langues), avec le français obligatoire comme langue par défaut
- Possibilité de changer de langue dans l'application
- Interface adaptative pour différentes tailles d'écrans et orientations

**Navigation**

- Implémenter au moins 3 écrans principaux distincts
- Navigation Compose avec une barre de navigation inférieure et/ou un menu latéral et/ou un menu dans la barre
  supérieure
- Gestion appropriée de la pile de navigation

**Ressources**

- Utilisation d'images et/ou d'icônes stockées dans les ressources
- Fichier(s) de configuration pour stocker les paramètres de l'application
- Support de la persistance des données entre les sessions

**Architecture et code**

- Organisation en paquets logiques
- Documentation complète du code avec `KDoc`
- Gestion de l'état avec `remember` ou `rememberSaveable`, `mutableStateOf`, ...
- Utilisation des bonnes pratiques de Jetpack Compose

## Livrables

**Dépôt Git**

- Nom du projet significatif incluant les numéros d'étudiants ou le nom d'équipe
- `README.md` détaillé contenant :
    - Noms et numéros des membres de l'équipe
    - Description détaillée de l'application
    - Images de la maquette et/ou du prototype
    - Guide d'utilisation
    - Captures d'écran des fonctionnalités principales
    - Instructions d'installation et de configuration

**Code source**

- Fichiers Kotlin bien organisés et documentés
- Ressources (images, _strings_, etc.) correctement structurées
- *Commits* réguliers et messages descriptifs

## Suggestions de projets

1. **Gestionnaire de collection**
    - Gestion d'une collection personnelle (livres, films, jeux, etc.)
    - Catalogage avec images
    - Filtres et recherche
    - Statistiques et visualisations

2. **Application de recettes**
    - Catalogue de recettes avec photos
    - Conversion des unités de mesure
    - Liste d'épicerie générée
    - Favoris et catégories

3. **Jeu éducatif**
    - Quiz multilingue
    - Système de points et niveaux
    - Tableau des scores
    - Progression sauvegardée

4. **Planificateur d'activités**
    - Création et gestion d'événements
    - Rappels et notifications
    - Vue calendrier
    - Partage d'activités

**L'application choisie doit démontrer une complexité supérieure au premier projet tout en restant réalisable dans
le temps imparti.**

## Grille d'évaluation (Remise préliminaire) (15 points)

### Dépôt Git et fichier `README.md` (5 points)

- Nom du projet significatif incluant les numéros d'étudiants ou le nom d'équipe
- Dépôt partagé correctement avec l'utilisateur `profdenis` sur GitHub ou GitLab
- Contenu du fichier `README.md` :
    - Noms et numéros des membres de l'équipe
    - Description détaillée de l'application
    - Images de la maquette et/ou du prototype

### Maquette et/ou prototype (10 points)

- Au moins une des 2 options suivantes :
    - Maquette d'interface utilisateur (images insérées dans le markdown) pour toutes les fenêtres de l'application
    - Prototype de l'application avec les composants non-fonctionnels
        - Pour cette option, vous devez inclure des captures d'écran de toutes les fenêtres dans le README
            - les captures d'écran peuvent être prises sur un téléphone, un émulateur ou dans la prévisualisation de
              fonctions composables
        - et inclure le code du prototype dans le dépôt Git

## Grille d'évaluation (Remise finale) (100 points)

### Fonctionnalités et Interface (40 points)

**Interface utilisateur (15 points)**

- Qualité générale de l'interface Compose
- Thème Material 3 cohérent et bien implémenté
- Adaptation aux différentes tailles d'écran et orientations

**Navigation et Structure (15 points)**

- Implémentation correcte de la navigation Compose
- Minimum 3 écrans fonctionnels et pertinents
- Menu/barre de navigation bien intégré(e)

**Internationalisation et Ressources (10 points)**

- Support de 3 langues ou plus, dont le français
- Utilisation appropriée des ressources images/icônes
- Gestion des fichiers de configuration

### Code et Architecture (35 points)

**Qualité du code (20 points)**

- Organisation claire en paquets
- Documentation du code (commentaires, KDoc)
- Respect des conventions Kotlin et bonnes pratiques Compose
- Gestion appropriée de l'état

**Architecture du projet (15 points)**

- Séparation des responsabilités
- Réutilisation des composants
- Gestion des erreurs et cas limites

### Gestion de projet (15 points)

- Dépôt Git : _commits_ réguliers et messages pertinents
- `README.md` complet et bien structuré
- Répartition équitable des tâches visible dans les _commits_

### Créativité et Complexité (10 points)

- Originalité du projet
- Complexité appropriée

### Pénalités possibles

- Retard (-10% par jour)
- Fonctionnalités manquantes (-5 points par exigence non respectée)
- Bugs critiques (-5 points par bug majeur)
- Documentation manquante ou insuffisante (-5 points)
- Erreur importante en termes de structure de code et algorithme utilisé (-5 points)

### Bonus possibles (maximum 10 points)

- Tests unitaires et/ou d'interface
- Animations et transitions sophistiquées
- Support d'accessibilité
- Intégration pertinente d'APIs externes

**Total** : 100 points (+ possibilité de bonus)

## Remises

Créez un projet nommé soit `Projet_1234567_12345678`, en n'oubliant pas de remplacer `1234567` et `1234568`
par vos numéros de DA, soit selon le nom de votre application, mais pas de mauvais noms comme `ProjetNative` ou `Test`
ou quelque chose du genre. Le nom du dépôt Git et le nom du dossier de projet doivent être correctement nommés.
Votre projet doit être placé dans un dépôt Git, sur GitHub ou GitLab, et partagé avec
l'utilisateur `profdenis`, en tant que collaborateur ou développeur. Votre dépôt ne doit pas être
public. Il n'y aura pas de remise sur Léa pour ce projet. Votre dépôt git sera cloné
après la date de remise. Assurez-vous que votre _commit_ final a bien été poussé avant la limite.

**Date de la remise préliminaire** : 17 novembre (avant minuit) pour le _commit_ dans le dépôt Git, et le 18 ou le 20
novembre en classe pour la présentation.

**Date de la remise finale** :  18 décembre (avant minuit) pour le _commit_ dans le dépôt Git, et le 19 décembre en
classe pour la présentation. L'horaire de la journée des présentations finales sera partagée au début du mois de
décembre.
