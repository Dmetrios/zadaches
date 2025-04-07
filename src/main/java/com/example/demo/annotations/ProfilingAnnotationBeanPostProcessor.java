package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

@Slf4j
public class ProfilingAnnotationBeanPostProcessor implements BeanPostProcessor {
    HashMap<String, Class> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if(beanClass.isAnnotationPresent(Profiling.class)) {
            System.out.println("ProfilingAnnotationBeanPostProcessor");
            map.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = map.get(beanName);
        if(beanClass != null) {
            System.out.println("ProfilingAnnotationBeanPostProcessor2");
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("ProfilingAnnotationBeanPostProcessor3");
                    System.out.println(method.isAnnotationPresent(Profiling.class));
                    if(method.isAnnotationPresent(Profiling.class)) {
                        log.info(method.getName() + " - start", (Object) method.getParameters());
                        Object retVal = method.invoke(bean, args);
                        log.info(method.getName() + " - end", (Object) method.getParameters());
                        return retVal;
                    }
                    else {
                        return method.invoke(bean, args);
                    }
                }
            });
        }
        return bean;
    }
}
