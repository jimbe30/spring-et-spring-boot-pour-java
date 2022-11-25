### 05 - Initialisation du projet Spring Boot

[Initialisation du projet demo](https://start.spring.io/)

![](./img/spring-initializr.png)

Générer le projet et le dézipper dans le répertoire Git principal qui a été créé préalablement

Puis importer le projet maven dans eclipse (import Git -> Smart Import)

Créer la page d'accueil `index.html`dans `/src/main/resources/static`

Exécuter le projet : `mvnw spring-boot:run`

Et voir le résultat sur http://localhost:8080/

### 09 - Programmation par contrat pour résoudre le problème de la flexibilité

Les dépendances font désormais référence à des interfaces plutôt qu'à des classes concrètes et ne sont pas instanciées directement par les objets qui en ont besoin.

Ceci permet d'assouplir les contraintes entre les composants des différentes couches logicielles : couplage faible pour plus de flexibilité.

Les interfaces définissent de manière abstraite la façon dont les composants communiquent : ce sont les contrats.

Un composant central a pour responsabilité l'instanciation des objets concrets et leur injection dans les objets qui en ont besoin : c'est l'injection de dépendances.



