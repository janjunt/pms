package me.tt.pms.service.validation;

import me.tt.pms.core.AdviceException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: ParamValidAspect
 * @Description: 参数验证切面
 * @author: tongjianjun@itiaoling.com
 * @date 2018/7/25 13:59
 */
@Aspect
@Component
public class ParamValidAspect {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator methodValidator = factory.getValidator().forExecutables();


    @Pointcut("execution(public * me.tt.pms.service.*.*(..))")
    public void executeService(){}

    @Before("executeService()")
    public void validService(JoinPoint point) {
        Object target = point.getThis();
        Object[] args = point.getArgs();
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, args);

        List<String> validMessages = new ArrayList<>();
        for (ConstraintViolation<Object> violation : validResult) {
            validMessages.add(violation.getMessage());
        }

        if(validMessages.size() > 0){
            throw new AdviceException(String.join(System.lineSeparator(), validMessages));
        }
    }


    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object [] params){
        return methodValidator.validateParameters(obj, method, params);
    }
}