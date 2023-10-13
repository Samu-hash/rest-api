package com.ternova.restapi.restapi.service.database;

import lombok.Getter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Getter
public class DynamicInterfaceInvoker<T> {

    private final T target;

    public DynamicInterfaceInvoker(Class<T> interfaceClass) {
        this.target = createDynamicProxy(interfaceClass);
    }

    @SuppressWarnings("unchecked")
    private T createDynamicProxy(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            // Invocar el método en la implementación real
                            return method.invoke(target, args);
                        } catch (Throwable e) {
                            // Manejar la excepción o imprimir información de depuración
                            e.printStackTrace();
                            throw e; // Puedes re-lanzar la excepción si es necesario
                        }
                    }
                }
        );
    }
}
