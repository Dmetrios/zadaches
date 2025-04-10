package com.example.demo.annotations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.*;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

@Slf4j
@Component
public class ProfilingAnnotationBeanPostProcessor implements BeanPostProcessor {
    private ConcurrentHashMap<String, Class> map = new ConcurrentHashMap<>();
    private ProfilingController profilingController = new ProfilingController();

    public ProfilingAnnotationBeanPostProcessor() throws Exception {
        ManagementFactory.getPlatformMBeanServer().registerMBean(profilingController, new ObjectName("profiling", "name", "controller"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if(beanClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = map.get(beanName);
        if(beanClass != null) {
            if(beanClass.getInterfaces().length > 0) {
                return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String result = getResultString(method, args);
                        if (profilingController.isEnabled()) {
                            long start = System.currentTimeMillis();
                            log.info(method.getName() + " - start; " + result + "Profiling: " + start);
                            Object retVal = Optional.ofNullable(method.invoke(bean, args)).orElse("");
                            long end = System.currentTimeMillis();
                            log.info(method.getName() + " - end; " + retVal + "Profiling: " + end);
                            return retVal;
                        }
                        else {
                            log.info(method.getName() + " - start; " + result);
                            Object retVal = Optional.ofNullable(method.invoke(bean, args)).orElse("");
                            log.info(method.getName() + " - end; " + retVal);
                            return retVal;
                        }
                    }
                });
            }
            else {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(beanClass);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                        String result = getResultString(method, args);
                        if (profilingController.isEnabled()) {
                            long start = System.currentTimeMillis();
                            log.info(method.getName() + " - start; " + result + "Profiling: " + start);
                            Object retVal = Optional.ofNullable(method.invoke(bean, args)).orElse("");
                            long end = System.currentTimeMillis();
                            log.info(method.getName() + " - end; " + retVal + "Profiling: " + end);
                            return retVal;
                        }
                        else {
                            log.info(method.getName() + " - start; " + result);
                            Object retVal = Optional.ofNullable(method.invoke(bean, args)).orElse("");
                            log.info(method.getName() + " - end; " + retVal);
                            return retVal;
                        }
                    }
                });
                return enhancer.create();
            }
        }
        return bean;
    }

    private String getResultString(Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        Parameter[] parameters = method.getParameters();
        String result = IntStream.range(0, parameters.length)
                .mapToObj(i -> parameters[i].getName() + " = " + args[i])
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
        return result;
    }
}
