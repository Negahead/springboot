package com.google.springboot.entity.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * classes annotated with @Aspect may have methods and fields just like any other class,they may
 * also contain pointcut,advice and introduction declaration
 *
 * Aspect programming is a way for adding behavior to existing code without modifying
 * that code
 */
@Component
@Aspect
public class MyAspect {
    /**
     * spring AOP only supports method execution join points for spring beans,so you can think
     * of a pointcut as matching the execution of methods on spring beans
     */


    /**
     *  The following example defines a pointcut named 'anyOldTransfer' that will match the execution of
     *  any method named 'transfer'
     *
     *      execution:
     *          matching method execution join points
     *      *    for return matches any return type
     *      (..) matches zero or more parameters
     *      ()   matches a method that takes no parameters
     *      (*)  matches a method that takes one parameter of any type
     *
     *
     *      execution(public * *(..)) : the execution of any public method with zero of more parameters
     *      execution(* set*(..))  : the execution of any method with a name beginning with "set"
     *      execution(* com.xyz.service.AccountService.*(..)) : the execution of any method defined by the AccountService class
     *      execution(* com.xyz.service.*.*(..)) : the execution of any method defined in the service package
     *      execution(* com.xyz.service..*.*(..)) : the execution of any method defined in the service package or a sub-package
     *
     *      within(com.xyz.service.*) : any join point (method execution only in Spring AOP) within the service package
     *
     *      this(com.xyz.service.AccountService) : any join point (method execution only in Spring AOP) where the proxy implements the AccountService interface:
     *                                             where this isinstanceof AccountService is true
     *
     *      target(com.xyz.service.AccountService) :  any join point (method execution only in Spring AOP) where the target object implements the AccountService interface:
     *                                              if you are calling a method on an object,and that object is instance
     *                                              of AccountService
     *
     */
    @Pointcut("execution(* com.google.springboot.web.AOPController.transfer(..))")
    private void anyOldTransfer() {
    }

    /**
     * when a matched method execution returns normally,this executes after the @After advice
     */
    @AfterReturning("execution(* com.google.springboot.web.AOPController.transfer(..))")
    public void afterReturningInplace() {
        System.out.println("==========after returning of method transfer================");
    }

    /**
     * A join point is in the web layer if the method is defined in a
     * type in the com.google.springboot.web packages or any sub-package under that
     *
     * in contrast to execution,this is more like a generic method find.
     */
    @Pointcut("within(com.google.springboot.web..*)")
    public void inWebLayer() {}


    /**
     * when the method execution exits
     */
    @After("inWebLayer()")
    public void afterInWebLayer() {
        System.out.println("=====================after any join point in web layer==========================");
    }

    @After("target(com.google.springboot.service.AOPService)")
    public void thisPointCut() {
        System.out.println("=====================target in of type AOPService==========================");
    }

    /**
     *  this advice is executed before the join-point
     */
    @Before("com.google.springboot.entity.aspect.MyAspect.anyOldTransfer()")
    public void beforeAdvice(JoinPoint joinPoint) {
        for(Object o : joinPoint.getArgs()) {
            System.out.println(o);
        }
        System.out.println(joinPoint.toString());//execution(ResponseResult com.google.springboot.web.AOPController.transfer(String,Map))
        System.out.println(joinPoint.getKind()); // method-execution
        System.out.println(joinPoint.getSignature().getDeclaringTypeName()); //com.google.springboot.web.AOPController
        System.out.println(joinPoint.getSignature().getName()); // transfer
        System.out.println(joinPoint.getSignature().toString()); //ResponseResult com.google.springboot.web.AOPController.transfer(String,Map)
        System.out.println(joinPoint.toLongString());
        Object thisTarget = joinPoint.getThis(); // com.google.springboot.web.AOPController
        Object target = joinPoint.getTarget();
        System.out.println("===================before transfer() method call=====================");
    }
}
