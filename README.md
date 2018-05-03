# dependency-injection-demo

## Dependency injection
Is where a needed dependency is injected by another object.

Refers to the composition of the classes. I.e., We compose classes with `DI` in mind.

Pass dependencies by constructor.
- We can set dependencies through constructors, setters or properties (spring will use reflection in this case (DO NOT DO THIS));

Avoid `DI` with `concrete classes`. Prefer `Interfaces` instead because:
- It allows runtime to decide which implementation to inject;
- Follows Interface segregation principle from SOLID;
- Makes code more testable;
- Spring autowires dependencies when injected through constructor;
- Use `@Qualifier` annotation on the constructor parameter to specify which `bean` should be used if there is more than one;
    - E.g., `public ConstructorABC(@Qualifier("constructorAbcService") ABCService abcService)` 
- Use the `@Primary` annotation when you have multiple beans of the same type and you want one of them to go by default;

## Inversion of control (IoC)
Allow dependencies to be selected and injected at runtime.
The runtime env (or framework) injects dependencies;

The runtime of our code. I.e., Spring framework's `IoC` container.

## Profile
Spring settings at runtime that define how we wire up things

## Lifecycle
The interfaces we can implement to make use of bean lifecycle are:
- InitializingBean
- DisposableBean

The annotations to access Spring Bean Lifecycle are:
- @PostConstruct
- PreDestroy

## Spring Configuration options
- Xml based
    - Introduced in spring framework 2.0;
    - Common in legacy spring applications;
    - Still supported in spring framework 5.x
    - The config file should be placed within `resources` folder;
    - The config file should be imported through the `@ImportResource("classpath:config-file.xml")` above the `@SpringBootApplication` annotation;
        - xml attribute example: `<bean name="chuckNorrisQuotes" class="guru.springframework.norris.chuck.ChuckNorrisQuotes" />`
        - The class should be the one to be imported;
- Annotation based
    - Introduced in version 3;
    - Picked up via 'Component Scans';
    - Refers to class level annotations;
        - `@Controller`, `@Service`, `@Component`, `@Repository`;
- Java based (Preferred)
    - Introduced in version 3;
    - Uses java classes to define Spring Beans;
        - Annotation used: `@Configuration`;
    - Beans are declared with `@Bean` annotation;
        - Very useful when we want to load an external class as a `Bean` so it can be autowired;
- Groovy Bean Definition DSL
    - Introduced in spring 4;
    - Allows us to declare beans in groovy;
    - Borrowed from Grails;
    
## Spring stereotypes
Definition: It is a fixed general image or set of characteristics which represent a particular type of person or thing;

Spring stereotypes are used to define Spring Beans in the Spring context;
- `@Component` (root node in the hierarchy): The class will be created as a Bean;
- `@Controller`: The class has the role of a Spring MVC Controller;
- `@RestController`: Extends `@Controller` and adds `@ResponseBody`;
- `@Repository`(data layer): The class has a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects;
    - When using this annotation spring will catch specific platform exceptions and re-throw them as spring exceptions; 
- `@Service`: The class has an operation offered as an interface that stands alone in the model, with no encapsulated state;
    - We place a service inside the controller. The service communicates to the database through a repository;

## Spring scan
Spring scans the application looking for classes annotated with spring annotations;

Spring starts scanning components from the package where the class with `@SpringBootApplication` is located;

To configure spring to scan other packages as well we can add the following annotation below:
```
@SpringBootApplication
@ComponentScan(basePackages= = { <root.package.name>, "augustovictor.services" })
```

Ps: When setting this option we overwrite the spring default package scan so we have to inform all of them.

## Bean Factory configuration
It is a good practice to keep related configurations in one centralized class;

Use the `@Configuration` annotation;

[Example](https://github.com/springframeworkguru/spring5-di-demo/blob/factory-beans/src/main/java/guru/springframework/config/GreetingServiceConfig.java)

## Spring boot

Is just a configuration wrapper around the Spring MVC Framework;

## Spring Bean Scopes
To declare a scope we add the `@Scope` annotation;

In xml configuration based we use the `scope` attribute in the `bean` tag;

Singleton (Default): Only one instance of the bean is created in the IoC container;

Prototype: A new instance is created each time the bean is requested;

Request: A single instance per http request. Only valid in the context of a web-aware Spring ApplicationContext;

Session: A single instance per http session; Only valid in the context of a web-aware Spring ApplicationContext;

Global-session: Only in a context of portlets;

Application: Bean is scoped to the lifecycle of a ServletContext; Only valid in a context of a web-aware;

Web-socket: Bean is scoped to the lifecycle of a Web-socket; Only valid in a context of a web-aware;

Custom-scope: Spring Scopes are extensible and we can create our owns by implementing the interface `Scope`;

It is not possible to overwrite the scopes `Singleton` and `Prototype`; 

The `@SpringBootApplication` includes 3 other annotations:
- `@Configuration`;
- `@EnableAutoConfiguration`;
- `@ComponentScan`;

To display an auto-configuration report from spring boot: Start the command line parameter `--debug`;

To disable specific spring boot configuration classes pass the class name to he `exclude` parameter of `@EnableAutoConfiguration` annotations;

# External properties

[DOCS](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html)

A good example of this is a database credentials file;

Create a `datasource.properties` in resources folder;

## Environment variables (OS properties)
We can add env variables through intellij under `Run > Edit configurations > Environment`

To make use of an env variable we should declarate an `@Autowire` Environment variable and access values through `env.getProperty("propertyName")`

## Profile Properties (Spring boot 4+)

Spring active profiles

Spring boot configuration files

If we create a file `applicatio-<profile>.properties` it will overwrite the `application.properties` values;


## JPA

### Annotations
- `@Lob`: Large object (Blob or clob);
- `@Enumerated`: Consider whether you want to store the value as ordinal or string.
    - Ordinal values change when we add new elements between values in our enum;
    - Ordinal is default;

### Relationships
The owner side of a Many to Many relationship is defined by the `mappedBy` attribute (the child entity property that would reference the parent).

### Fetch types

- Lazy: Data is not required until referenced;
    - OneToMany
    - ManyToMany
- Eager: Data is required upfront;
    - OneToOne
    - ManyToOne

### Cascade types

Control how state changes are cascated from parent to child objects;

- Persist: Save operations will cascade to related entities;
- Merge: Related entities are merged when owning entity is merged;
- Refresh: Related entities are refreshed when the owning entity is refreshed;
- Remove: All related entities are removed when owning entity is deleted;
- Detach: Detaches all related entities if a manual detach occurs;
    - No longer associated with the hibernate session. So we cannot access lazy associations anymore;
- All: Applies all the above cascade options;

Ps: By default no operations are cascated;

### Embeddable Types

JPA/Hibernate support.

Used to define a common set of properties. E.g.: an order might have a billing address, and a shipping address;

### Inheritance

Relational DB does not have the concept of inheritance;

- MappedSuperclass: Entities inherit from a superclass. A table is NOT created for the superclass;
- Single Table (Hibernate default): One table is used for all subclasses;
- Joined Table: Base class and subclasses have their own tables. Fetchin subclass entities require a join to the parent table;
- Table per class: Each subclass has its own table;

### Create and update timestamps

JPA supports `@PrePersist` and `@PreUpdate` via JPA lifecycle callbacks.

Hibernate provides `@CreationTimestamp` and `@UpdateTimestamp` (Preferred way).

### Hibernate DDL auto

It controls what DDL operations Hibernate will perform on startup;

DDL - Data Definition Language

DML - Data Manupulation Language

Hibernate property is set by the spring property `spring.jpa.hibernate.ddl-auto`

The options are:
- none: No DDL activity from hibernate; Does not verify the db structure neither;
- validate (use in production): Fail to startup if anything is missing (table or column); 
- update: Automatically update db schema based on JPA model;
- create: Automatically create the database;
- create-drop: Drop after application finishes;

Spring boot will use create-drop for embedded databases (hsql, h2, derby) or none for other dbs;

Data (seeds) can be loaded from an `import.sql` file (This is a hibernate feature);
- The file must be located on the root of the classpath;
- This will be executed only if Hibernate's ddl-auto property is set to `create` or `create-drop`;

### Spring JDBC

Spring's datasource initializer via spring boot will by default load `schema.sql` and `data.sql` from the root o the classpath.

Spring boot will also load from `schema-${platform}.sql` and `data-${platform}.sql` (set `spring.datasource.platform` in properties first).

These configs may conflict with Hibernate's DDL auto property (choose spring approach only); If you have to use both, set hibernate's ddl-auto option to either none or validate;

To reference the tables in scripts we use the lower_snake_case

### Spring Data JPA

We can make use of query methods

### Lombok

The objective is to get rid of java boilerplate;

Lombok resolves this by reading annotations and converting them into actual methods when the code is compiled;

Annotations
- @Getter: Generate getters for all properties;
- @Setter: Generate setter for all non-final properties;
- @ToString: Generates a string of classname, and each field separated by commas;
    - We can set it to include field names as well;
    - Also call the super toString
- @EqualsAndHashCode: Generates implementations of `equals(Object other)` and `hashCode()`
    - By default will use all non-static, non-transient properties;
    - Can optionally exclude specific properties;
- @NoArgsConstructor: Will cause compiler error if there are final fields;
    - Can optionally force, which will initialize final fields with `0` / false / null;
- @RequiredArgsConstructor: Generates a constructor for all fields that are final or marked `@NonNull``
    - Will throw a NullPointerException if any `@NonNull` fields are null;
- @Data: Generates typical boilerplate code for POJOs;
    - Combines @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
    - No constructor is generated if you have already declared one;
- @Value: The immutable variant of @Data
    - All fields are made private and final by default;
- @NonNull: Set on parameter of method or constructor and a NullPointerException will be thrown if parameter is null
- @Builder: Implements the builder pattern for object creation
- @SneakyThrows: Throw checked exceptions without declaring in calling method's throw clause;
- @Syncronized: A safer implementation of java's synchronized
- @Log: Creates a java util logger
- Slf4j: Creates a SLF4J logger

Setup
- Add maven dependency
```
groupId: org.projectlombok 
artifact: lombok
```

### Testing

Unit tests

- Code under test: Code we're testing
- Test fixture: Fixed state of a set of objects used as a baseline for running tests.
    - This includes: Input data, mock objects, loading db with known data, etc;

- Ideal test coverage is within 70%-80%;
- Should be unity and execute very fast;
- Should not have external dependencies (No db, no spring context, etc);

Integration test

- Designed to test behaviors between objects and parts of the overall system;
- Much marger scope;
- Can include spring context, db, message brokers, etc;
- will run much slower than unit tests;

Functional tests (End-to-end)

- Test a running application;
- Application is live, likely deployed in a known env;
- Functional touch points are tested (using a web driver, calling web services, sending/receiving messages, etc);

TDD: Write tests first, which wil fail, then code to fix the test;

BDD: Builds on TDD and specifies that tests of any unit of software should be specified in terms of desired behavior or the unit;

Often implemented with DSLs to create natural language tests (JBehave, Cucumber, Spock(preferred))

Example of tests: "Given ...", "When...", "Then..."

Mock: Fake implementation of a classe used for testing. Like a test double.

Spy: Partial mock, allowing us to override select methods of a real class;

Testing goals:
- Have the majority of the tests as unit tests;
- Try to test specific business logic in unit tests;
- Use integration tests to test interactions;
- Think of a pyramid. Base is unit tests, middle is integration tests, and top is functional tests;

Test scope dependencies
- Using spring-boot-starter-test (default from spring initializr will load the following dependencies):
    - JUnit
    - Spring Test and Spring Boot Test: Utilities and integration tests support for spring boot apps;
    - AssertJ
    - Hamcrest: Lib for matcher objects;
    - Mockito
    - JSONassert: Json assertion lib;
    - JSONPath: XPath for JSON;

JUnit annotations
- @Test
- @Before
- @After
- @BeforeClass
- @AfterClass
- @Ignore
- @Test(expected = Exception.class)
- @Test(timeout = 10) (10 = seconds)

Spring Boot annotations
- @RunWith(SpringRuner.class)
- @SpringBootTest
- @TestConfiguration: Specifies a spring config for the test
- @MockBean (Uses mockito)
- @SpyBean (Uses mockito)
- @JsonTest
- @WebMvcTest: Used to test web context without a full http server (Tomcat)
- @DataJpaTest: Used to test data layer with embedded database;
- @JdbcTest: Like @DataJpaTest but does not configure entity manager. We would be testing agains a mysql for exemple;
- @DataMongoTest: Configures an embedded MongoDB for testing;
- @RestClientTest: Creates a mock server for testing rest clients;
- @AutoConfigureRestDocks: Allows us to use Spring Rest Docs in tests, creating API docs;
- @BootStrapWith: Configures how TestContext is bootstrapped;
- @ContextConfiguration: Directs Spring on how to configure the context for the test;
- @ContextHierarchy: Creates hierarchy with @ContextConfiguration
- @ActiveProfiles: Set which spring profiles are active for the profile;
- 