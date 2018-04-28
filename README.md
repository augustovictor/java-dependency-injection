# dependency-injection-demo

## Dependency injection
Is where a needed dependency is injected by another object.

Refers to the composition of the classes. I.e., We compose classes with `DI` in mind.

Pass dependencies by constructor.

Avoid `DI` with `concrete classes`. Prefer `Interfaces` instead because:
- It allows runtime to decide which implementation to inject;
- Follows Interface segregation principle from SOLID;
- Makes code more testable;
- Spring autowires dependencies when injected through constructor;
- Use `@Qualifier` annotation on the constructor parameter to specify which interface implementation should be used if there is more than one;
    - E.g., `public ConstructorABC(@Qualifier("constructorAbcService") ABCService abcService)` 
- Use the `@Primary` annotation when you have multiple beans of the same type and you want one of them to go by default;

## Inversion of control (IoC)
Allow dependencies to be selected and injected at runtime.

The runtime of our code. I.e., Spring framework's `IoC` container.

## Profile
Spring settings at runtime that define how we wire up things