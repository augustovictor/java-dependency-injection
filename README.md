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
- Groovy Bean Definition DSL
    - Introduced in spring 4;
    - Allows us to declare beans in groovy;
    - Borrowed from Grails;