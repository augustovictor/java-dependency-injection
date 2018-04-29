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
- `@Service`: The class has an operation offered as an interface that stands alone in the model, with no encapsulated state;
    - We place a service inside the controller. The service communicates to the database through a repository;

## Spring scan
Spring scans the application looking for classes annotated with spring annotations;

Spring starts scanning components from the package where the class with `@SpringBootApplication` is located;

To configure spring to scan other packages as well we can add the following annotation below:
```
@SpringBootApplication`: `@ComponentScan(basePackages= = { <root.package.name>, "augustovictor.services" })
```

Ps: When setting this option we overwrite the spring default package scan so we have to inform all of them.