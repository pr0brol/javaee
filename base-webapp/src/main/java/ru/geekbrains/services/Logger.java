package ru.geekbrains.services;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

public class Logger implements Serializable {
    @AroundInvoke
    public Object printLog(InvocationContext ctx) throws Exception{
        System.out.println("Вызван метод " + ctx.getMethod().getName());
        return ctx.proceed();
    }
}
