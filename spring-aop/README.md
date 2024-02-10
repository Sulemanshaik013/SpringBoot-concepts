## ASPECT ORIENTED PROGRAMMING

Aspect oriented programming, in simple terms AOP, is a programming paradigm (mostly boring for me) that allows to modularize the cross-cutting concerns in the application.

Before moving in to AOP let us understand concern and cross-cutting concerns.

A concern is a behaviour that we want to implement in our application. Cross-cutting concerns are non-functional requirements used at multiple layers of application.

Some of the cross-cutting concerns are logging, security, data validation, performance monitoring, exception handling etc.,

These problems can also be solved through object-oriented programming by using inheritance and delegation. I mean, we can have a separate class for Logging and functional classes must implement or call these methods which makes them complex.

With AOP, we can remove these patterns by maintaining a separate module so that it can be inserted at compile time or when class is loaded or when a method is called, which gives more control over the program flow.

In Object oriented programming modularity of an application can be achieved by classes where as In Aspect oriented programming modularity of an application is achieved by aspects.

_ **AOP Terminologies** _

- ASPECT: An aspect is a common concern that cuts across multiple classes.
- JOINPOINT: It is a point during the execution of program where aspect can be inserted, such as execution of method or handling an exception.
- ADVICE: Advice is an action to be performed at a particular joinpoint. Different types of Advices are

  - @Before – advice that runs before a method execution.
  - @After – advice that runs after the method execution.
  - @AfterReturning – advice that runs when a method executes successfully.
  - @AfterThrowing – advice that runs when an exception is thrown.
  - @Around – advice that wraps around a method execution.

- POINTCUT: Pointcut is an expression that specifies where an advice needs to be applied.
- TARGET: Target is the object on which advice will be applied.
- PROXY: Proxy is the object which is created by applying advice on the target object.
- WEAVING: It is the process of applying aspects to the target object to create a new proxied object.

**Pointcut expressions**

Pointcut expressions starts with pointcut designators (PCD).

1. execution – Matches a joinpoint methods signature.
      ```
    Ex: - @Pointcut("execution(public void com.learning.spring.service.getMethod())") 
    ```

1. within – Matches all joinpoints in a class, package, or sub-package.
      ```
      Ex: - @Pointcut("within(com.learning.spring.service.\*)")
      ```
1. @annotation – Used to match a joinpoint method annotated with given annotation

      ```
     Ex: - @Pointcut("@annotation(LogExit)")
     ```

We can also combine these expressions by using logical operators &&(and), ||(or) and !(not).

An example class :

```
@Aspect
@Component
public class LoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(com.learning.spring.service..*)")
	public void entryAndExitPointcut() {
	}

	@Pointcut("within(com.learning.spring.service..*) || within(com.learning.spring.util..*) || @annotation(LogExit) ")
	public void exceptionsPointcut() {
	}

	@Pointcut("within(com.learning.spring.controller..*)")
	public void executionTimePointcut() {
	}

	@Around(value = "entryAndExitPointcut()")
	public Object logEntryAndExit(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// Method entry and exit logic
		return proceedingJoinPoint;
	}

	@AfterThrowing(value = "exceptionsPointcut()", throwing = "e")
	public void logExceptions(JoinPoint joinPoint, Throwable e) throws Throwable {
		// Exception logic
	}

	@AfterReturning(value = "within(com.learning.spring.controller..*)", returning = "object")
	public void logRetuningValues(JoinPoint joinPoint, Object object) throws Throwable {
		// logic
	}

	@Around(value = "executionTimePointcut()")
	public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// Execution time logic
		return proceedingJoinPoint;
	}
}
```
