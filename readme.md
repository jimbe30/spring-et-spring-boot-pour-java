### 05 - Initialisation du projet Spring Boot

[Initialisation du projet demo](https://start.spring.io/)

![](./img/spring-initializr.png)

Générer le projet et le dézipper dans le répertoire Git principal qui a été créé préalablement

Puis importer le projet maven dans eclipse (import Git -> Smart Import)

Créer la page d'accueil `index.html` dans `/src/main/resources/static`

Exécuter le projet : `mvnw spring-boot:run`

Et voir le résultat sur http://localhost:8080/

### 09 - Programmation par contrat pour résoudre le problème de la flexibilité

Les dépendances font désormais référence à des interfaces plutôt qu'à des classes concrètes et ne sont pas instanciées directement par les objets qui en ont besoin.

Ceci permet d'assouplir les contraintes entre les composants des différentes couches logicielles : couplage faible pour plus de flexibilité.

Les interfaces définissent de manière abstraite la façon dont les composants communiquent : ce sont les contrats.

Un composant central a pour responsabilité l'instanciation des objets concrets et leur injection dans les objets qui en ont besoin : c'est l'injection de dépendances.

### 14 - Affectation de valeur à un composant Spring

![](./img/applicationContext_01.png)

L'attribut `value` de la balise `<property>` permet d'affecter une valeur à un attribut de bean géré par le conteneur Spring.

Les types d'objets que Spring est capable de convertir automatiquement sont :
- les types primitifs (int, long, ...)
- String
- Properties
- URL
- File
- Class

### 15 - Injection automatique par nom ou par type

![](./img/applicationContext_02.png)

On peut utiliser l'attribut `default-autowire` de la balise `<beans>` ou  l'attribut `autowire` de la balise `<bean>` pour injecter automatiquement les références de beans en tant que dépendances sans avoir à les écrire dans le fichier de configuration.

Les valeurs courantes de ces attributs sont `byName` ou `byType`

### 17 - Affectation de propriété par annotation @Value

On peut affecter aux attributs de beans une valeur configurée dans un fichier de propriétés

`applicationContext.xml`

![](./img/applicationContext_03.png)

Fichier `application.properties`

```properties
invoice.prefix = PREF
invoice.lastNumber = 184
```

Fichier java

```java
public class InvoiceServicePrefix implements InvoiceServiceInterface {	
	@Value("${invoice.lastNumber}")
	private long lastNumber;	
	@Value("${invoice.prefix}")
	private String prefix;
	
```


### 20 - Se passer du fichier XML

Grâce aux classes de configuration, il est possible de déplacer toute la config XML dans une classe java annotée `@Configuration`

Les beans sont instanciés par cette classe

```java
@Configuration
@ComponentScan("com.mycompany.invoise")
@PropertySource("classpath:application.properties")
public class AppConfig {
	@Bean
	InvoiceControllerInterface invoiceController() {
		return new InvoiceControllerWeb();
	}
	@Bean
	InvoiceServiceInterface invoiceService() {
		return new InvoiceServicePrefix();
	}
	@Bean
	InvoiceRepositoryInterface invoiceRepository() {
		return new InvoiceRepositoryDatabase();
	}
}
```

Le contexte Spring est alors donné par 

```java
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
```

Les dépendances sont injectées par l'annotation `Autowired` dans les beans qui en ont besoin

```java
public class InvoiceServicePrefix implements InvoiceServiceInterface {
	...	
	@Autowired
	private InvoiceRepositoryInterface invoiceRepository;
    ...
```



### 21 - Classes de configuration plus en détail

Il est possible de placer l'annotation `@configuration` sur n'importe quelle classe, y compris celle qui contient la méthode `main()`

Et d'avoir autant de classes de configuration qu'on le souhaite

```java
@Configuration
@ComponentScan("com.mycompany.invoise")
public class App {
	public static void main(String[] args) {		
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		InvoiceControllerInterface invoiceController = context.getBean(InvoiceControllerInterface.class);
		invoiceController.createInvoice();		
	} 
}


@Configuration
@PropertySource("classpath:application.properties")
public class AppConfigPropertySource {}


@Configuration
public class AppConfigBeanFactory {	
	@Bean
	InvoiceControllerInterface invoiceController() {
		return new InvoiceControllerWeb();
	}	
	@Bean
	InvoiceServiceInterface invoiceService() {
		return new InvoiceServicePrefix();
	}	
	@Bean
	InvoiceRepositoryInterface invoiceRepository() {
		return new InvoiceRepositoryDatabase();
	}
}
```



### 22 - Spring boot - @SpringBootApplication

Spring Boot nécessite au minimum la dépendance suivante dans le fichier `pom.xml`

```xml
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
            <version>2.2.4.RELEASE</version>
        </dependency>
```



Dans la classe principale `App`, l'annotation `@SpringBootApplication` combine (et remplace donc) différentes annotations telles que `@configuration`, `@ComponentScan` et `@PropertySource("classpath:application.properties")` 

La méthode statique `run()` de la classe `SpringApplication` renvoie un objet `ApplicationContext`

```java
@SpringBootApplication
public class App {
	public static void main(String[] args) {		
		ApplicationContext context = SpringApplication.run(App.class);
		InvoiceControllerInterface invoiceController = context.getBean(InvoiceControllerInterface.class);
		invoiceController.createInvoice();		
	}
}
```



### 24 - Spring boot - Déployer l'application

Spring Boot fournit le plugin `spring-boot-maven-plugin` qui permet de créer un jar exécutable lors de la phase package, avec toutes les dépendances. 

Fichier  `pom.xml`

```xml
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
```



### 26 - Spring boot web

Spring Boot fournit le starter `spring-boot-starter-web` qui permet de créer une application web avec un serveur Tomcat embarqué.

`pom.xml`

```xml
	<dependencies>
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>    
	</dependencies>
```



Par défaut, Spring Boot cherche dans le répertoire `classpath:/static` le contenu web statique (pages HTML) à servir aux utilisateurs :

- Le port par défaut est 8080
- Le nom de contexte est vide
- La ressource web par défaut est `index.html`



### 27 - Spring auto configuration

L'annotation `@SpringBootApplication` inclut également l'annotation `@EnableAutoConfiguration` .

Elle indique à Spring de chercher dans les librairies du projet les classes de configuration qui fournissent les composants et valeurs de propriétés par défaut.

Ces valeurs par défaut  peuvent être surchargées par configuration.

Les propriétés concernées sont listées ici : https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html

 `application.properties`

```properties
server.port = 80
server.servlet.context-path = /invoice
```



Pour remplacer le serveur web, il faut exclure la dépendance vers celui par défaut et rajouter celle vers celui voulu

`pom.xml`

```xml
	<dependencies>
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
            	<exclusion>
            		<groupId>org.springframework.boot</groupId>
            		<artifactId>spring-boot-starter-tomcat</artifactId>
            	</exclusion>
            </exclusions>
		</dependency>		
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-undertow</artifactId>
		</dependency>       
	</dependencies>
```



### 30 - Spring MVC - contrôleur frontal `DispatcherServlet` et contrôleurs `@Controller`

Spring MVC est un framework web (structure de composants logiciels sous-jacente à une application et qui définit son architecture).

> Doc de référence : https://docs.spring.io/spring-framework/docs/current/reference/html/web.html



Il fait appel au contrôleur central `DispatcherServlet` qui intercepte toutes les requêtes HTTP qui lui sont destinées :

- Avec Spring Boot, `DispatcherServlet` est initialisé grâce à l'auto configuration (classe `DispatcherServletAutoConfiguration`) 

- Dans une webapp standard au niveau servlet 3 et +, il l'est en étendant la classe `AbstractDispatcherServletInitializer`  ou `AbstractAnnotationConfigDispatcherServletInitializer`



Par défaut le chemin des requêtes interceptées est ` / ` : toutes les requêtes sont interceptées.

Ce chemin est modifiable avec la propriété `spring.mvc.servlet.path`



Le contrôleur frontal `DispatcherServlet` délègue le traitement des requêtes à des contrôleurs simples : ce sont les classes annotées `@Controller`

Le choix du contrôleur et de la méthode à invoquer provient de la configuration du chemin via l'annotation `@RequestMapping`

Exemple pour le chemin `/home` (càd l'URL http://localhost/invoice/home)

```java
@Controller
public class InvoiceControllerWeb implements InvoiceControllerInterface {	
	...
	@RequestMapping("/home")
	public String displayHome() {
		System.out.println("Invocation de InvoiceControllerWeb.displayHome()");
		return "";
	}
...
```



Une fois le traitement effectué, le contrôleur frontal renvoie ou redirige l'utilisateur vers une vue  



### 32 - Spring MVC - vues et modèle

2 façons de présenter des vues dont le contenu est dynamique :

- soit générer le code HTML directement sur le serveur et l'envoyer au client (JSP, Thymeleaf)
- soit envoyer une page HTML statique et laisser le client remplir le contenu dynamique avec javascript et des requêtes ajax



#### Vue renvoyée par le contrôleur 

Lorsque le retour d'une méthode `@RequestMapping` du contrôleur est de type `String`, le framework cherche une vue du même nom (par défaut avec l'extension .html).

Il est alors courant de valoriser les données du modèle via l'objet `HttpServletRequest` passé en paramètre. 

```java
	@RequestMapping("/home")
	public String displayHome(HttpServletRequest request) {
		System.out.println("Invocation de InvoiceControllerWeb.displayHome()");
		request.setAttribute("invoices", invoiceService.getInvoiceList());
		return "index"; // nom de la vue recherchée par Spring avec extension .html
	}
```



Dans le cas où le retour n'est pas de type `String`, Spring recherche une vue du même nom que le chemin donné dans l'annotation `@RequestMapping`

L'objet en retour peut être ajouté au modèle via l'annotation `@ModelAttribute`

```java
	@RequestMapping("/index")   // nom de la vue recherchée par Spring 
	@ModelAttribute("invoices")
	public List<Invoice> index() {
		System.out.println("Invocation de InvoiceControllerWeb.index()");
		List<Invoice> invoiceList = invoiceService.getInvoiceList();
		return invoiceList;
	}
```



Les données ajoutées au modèle par les méthodes `@RequestMapping` du contrôleur sont ensuite disponibles pour la vue renvoyée 



#### Thymeleaf pour générer des vues côté serveur

C'est un moteur de template qui permet d'ajouter à une page HTML statique du contenu dynamique grâce à des balises et attributs personnalisés :

- Par défaut, Spring Boot cherche les fichiers associés dans le répertoire `classpath:/templates/`

- Le namespace `th` doit être ajouté en entête du fichier `html`

  ```html
  <html lang="en" xmlns:th="http://www.thymeleaf.org">
  ```

- Et la dépendance correspondante dans le `pom.xml` :

  ```xml
  		<dependency>
  			<groupId>org.springframework.boot</groupId>
  			<artifactId>spring-boot-starter-thymeleaf</artifactId>
  		</dependency>
  ```



Thymeleaf permet un affichage dynamique des données fournies au modèle par le contrôleur

Exemple dans `classpath:/templates/index.html`

```html
    <table>
    	<tr th:each="invoice: ${invoices}">
    		<td><span th:text="${invoice.number}"></span></td>
    		<td><span th:text="${invoice.customerName}"></span></td>
    	</tr>    
    </table>
```


### 34 - Spring MVC - `Model`, `ModelAndView` et paramètres d'URL


#### Thymeleaf - Syntaxe d'URL avec paramètre dans le chemin

Une URL qui commence par un ` / ` est relative au contexte de la page en cours.
Exemple : si le chemin de contexte est /invoice, alors l'URL ci-dessous envoie la requête vers /localhost/invoice/{number}
Où {number} est un paramètre valorisé ensuite  

```html
	<a th:href="@{/{number} (number=${invoice.number})}" th:text="${invoice.number}"></a>
```


#### Spring - Lecture du paramètre d'URL et définition explicite de la vue et du modèle


- Soit on utilise un paramètre `Model` en paramètre de la méthode du `@Controller` et on renvoie le nom de la vue

```java
	@RequestMapping("/index")
	public String index(Model model) {
		System.out.println("Invocation de InvoiceControllerWeb.index()");
		List<Invoice> invoiceList = invoiceService.getInvoiceList();
		model.addAttribute( "invoices", invoiceList);
		return "index";
	}
```

- Soit on renvoie un objet `ModelAndView` qui contient à la fois le nom de la vue et les attributs du modèle

```java
	@RequestMapping("/{id}")
	public ModelAndView displayInvoice(@PathVariable("id") String invoiceNumber) {
		System.out.println("Invocation de InvoiceControllerWeb.displayInvoice()");
		Invoice invoice = invoiceService.getInvoice(invoiceNumber);
		return new ModelAndView("invoice-details", "invoice", invoice);
	}
```

L'annotation `@PathVariable("id")` permet de lire et de transférer la valeur du paramètre d'URL vers le paramètre correspondant dans la méthode 
