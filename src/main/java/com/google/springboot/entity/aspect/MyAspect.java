package com.google.springboot.entity.aspect;

import com.google.springboot.entity.ResponseResult;
import com.google.springboot.entity.request.OrgOperationRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * classes annotated with @Aspect may have methods and fields just like any other class,they may
 * also contain pointcut,advice and introduction declaration
 *
 * Aspect programming is a way for adding behavior to existing code without modifying
 * that code
 *
 * The spring Framework's AOP functionality is normally used in conjunction with the spring IoC container.
 * Aspects are configured using normal bean definition syntax
 *
 * your code never invokes the advice methods of an aspect class.spring does it for you based on you configuration
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
     *      ' @target(org.springframework.transaction.annotation.Transactional):
     *            any join point (method execution only in Spring AOP) where the target object has an @Transactional annotation:
     *      ' @within(org.springframework.transaction.annotation.Transactional):
     *            any join point (method execution only in Spring AOP) where the declared type of the target object has an @Transactional annotation
     *      ' @annotation(org.springframework.transaction.annotation.Transactional):
     *            any join point (method execution only in Spring AOP) where the executing method has an @Transactional annotation
     *      ' @args(com.xyz.security.Classified):
     *            any join point (method execution only in Spring AOP) which takes a single parameter, and where the runtime type of the argument passed has the @Classified annotation
     */
    @Pointcut("execution(* com.google.springboot.web.AOPController.transfer(java.lang.String,java.util.Map))")
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
     * any join point(method execution in Spring AOP) which takes a single parameter,and where the argument passed at
     * runtime is Serializable
     */
    @Pointcut("args(java.io.Serializable)")
    public void argsDesigonator() {}


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
        //for(Object o : joinPoint.getArgs()) {
         //   System.out.println(o);
        //}
        //System.out.println(joinPoint.toString());//execution(ResponseResult com.google.springboot.web.AOPController.transfer(String,Map))
        //System.out.println(joinPoint.getKind()); // method-execution
        //System.out.println(joinPoint.getSignature().getDeclaringTypeName()); //com.google.springboot.web.AOPController
        //System.out.println(joinPoint.getSignature().getName()); // transfer
        //System.out.println(joinPoint.getSignature().toString()); //ResponseResult com.google.springboot.web.AOPController.transfer(String,Map)
        //System.out.println(joinPoint.toLongString());
        //Object thisTarget = joinPoint.getThis(); // com.google.springboot.web.AOPController
        //Object target = joinPoint.getTarget();
        System.out.println("===================before transfer() method call=====================");
    }

    /**
     * The first parameter of the advice method must be of type ProceedingJoinPoint,within the body of the advice,calling proceed() on
     * the ProceedingJoinPoint causes the underlying method to execute,the proceed() method may also be called passing in an Object[]-the values
     * in the array will be used as the arguments to the method execution when it proceeds.
     */
    @Around("execution(* com.google.springboot.web.AOPController.aroundAdvice(..)) && args(name)")
    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint,String name) {
        System.out.println("name is " + name);
        ResponseResult result;
        try {
            /**
             * If 'this()' was used in the pointcut for binding, it must be passed first in proceed(..).
             *
             * If 'target()' was used in the pointcut for binding, it must be passed next in proceed(..) -
             * it will be the first argument to proceed(..) if this() was not used for binding.
             *
             * Finally come all the arguments expected at the join point, in the order they are supplied at the join point.
             */

            /**
             * proceed() will actually call aroundAdvice() method
             */
            result = (ResponseResult) proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after proceed method..........");
    }

    /**
     * the actual OrgOperationRequest object is available to the advice via the orgOperationRequest parameter
     * @param orgOperationRequest
     */
    @Pointcut("execution(* com.google.springboot.web.AOPController.parameterTest(..)) && args(orgOperationRequest,..)")
    public void parameterPointCut(OrgOperationRequest orgOperationRequest) {}

    /**
     * in-place point cut expression
     * @param orgOperationRequest
     */
    @Before(value = "execution(* com.google.springboot.web.AOPController.parameterTest(..)) && args(orgOperationRequest,name)",argNames = "orgOperationRequest,name")
    public void parameterTest(OrgOperationRequest orgOperationRequest,String name) {
        System.out.println("====================parameter====================================");
        System.out.println("name is " + name);
        System.out.println(orgOperationRequest.getUserIds().toString().replace("[","").replace("]",""));
    }
}
