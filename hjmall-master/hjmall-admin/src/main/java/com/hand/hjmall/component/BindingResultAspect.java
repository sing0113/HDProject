package com.hand.hjmall.component;

import com.hand.hjmall.dto.UmsAdminParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
* @Description:    HibernateValidator错误结果处理切面
* @CreateDate:     2019/2/21 20:31
* @CreateUser:     SilenceTian
* @Version:        1.0
*/
@Aspect
@Component
@Order(2)
public class BindingResultAspect {
    @Pointcut("execution(public * com.hand.hjmall.controller.*.*(..))")
    public void BindingResult() {
    }

    @Around("BindingResult()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    return new UmsAdminParam.CommonResult().validateFailed(result);
                }
            }
        }
        return joinPoint.proceed();
    }
}
