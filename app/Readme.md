# Clean Architecture
Ce ReadMe vous permettra de comprendre comme fonctionne l'architecture de ce projet. Ce projet utilise la clen architecture,

## Couche Domain

La couche domain contient la logique métier de l'application, elle ne doit dépendre d'aucune autre couche (comme data ou ui), mais les autres couches peuvent en dépendre.

### Models
Les modèles dans la couche domain sont les entités métiers de cette application. Elles représentent les objets les plus proches du domaine métier avec leurs propriétés.

### Repositories
Les repositories dans le domain sont des interfaces qui définissent des contrats pour accéder aux données. Ils sont une abstraction sur les sources de données (comme les API, les bases de données, etc.), mais sans en connaître l'implémentation.

### DI
Dans la partie domain, la sous-partie di est utilisée pour gérer l'injection de dépendances de manière déclarative. Cela permet d'injecter les dépendances (comme les repositories) dans les différentes parties de votre application, sans que la couche domain soit dépendante de ces implémentations.

## Couche Data

La couche data dans une architecture Clean Architecture est responsable de la gestion et de la manipulation des données de l'application. Elle se charge de la récupération, de la transformation, et du stockage des données depuis différentes sources (comme les API, les bases de données locales, etc.).

### Repositories

Les repositories dans la couche data sont les implémentations concrètes des interfaces définies dans le domain. Chaque repository est responsable de fournir les données à partir des sources configurées (API, base de données, cache, etc.).

### Remote
La sous-couche remote contient tout ce qui concerne la récupération des données à distance, généralement via des APIs ou des services Web.

### Local
La sous-couche local gère les données locales de l'application, que ce soit à travers une base de données, un cache, ou le stockage partagé.

### DI
La sous-couche di dans la couche data contient la configuration d'injection des dépendances pour cette couche. C'est ici que sont définis quels repositories, local et remote data sources doivent être injectés dans l'application.

## Couche UI

La couche UI (User Interface) dans une architecture Clean Architecture est responsable de tout ce qui concerne l'affichage de l'application et l'interaction avec l'utilisateur. Elle représente l’interface visible et interactive que l’utilisateur utilise pour interagir avec l’application. Contrairement aux couches plus basses (comme domain ou data), la couche UI se concentre sur l'expérience utilisateur.

### Composables
Les composables dans Jetpack Compose sont les blocs de construction de l’interface utilisateur. Ils permettent de définir l’apparence de chaque partie de l'UI sous forme de fonctions réutilisables.

### Theme
La sous-couche theme est responsable de la gestion du style et de la cohérence visuelle de l'application. Elle définit les couleurs, les typographies, et les styles visuels utilisés dans toute l'application.

### Navigation.kt
La sous-couche navigation.kt gère la navigation entre les différents écrans de l'application. Avec Jetpack Compose, la navigation est souvent gérée via la bibliothèque Navigation Compose.

### ViewModel
Les ViewModels sont des composants clé dans l'architecture de la couche UI. Ils sont responsables de maintenir l'état de l'interface utilisateur et d'interagir avec la couche domain pour récupérer et fournir les données.

### Couche Features
Dans une architecture Clean Architecture, la couche features est généralement une manière d'organiser le code de façon modulaire et découplée par fonctionnalités ou cas d'utilisation spécifiques. L'idée derrière l'organisation en features est de rendre le projet plus lisible et maintenable, surtout lorsque l'application devient plus complexe. Cela permet de regrouper toutes les composantes liées à une fonctionnalité spécifique dans un même module ou dossier.


