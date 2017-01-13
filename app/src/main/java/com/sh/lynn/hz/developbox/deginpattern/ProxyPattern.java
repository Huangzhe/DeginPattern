package com.sh.lynn.hz.developbox.deginpattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理模式：为其他对象提供一种代理以控制对这个对象的访问。
 * 代理模式的三个角色：
 * 1.Subject 抽象主题角色  可以是抽象类也可以是接口，
 * 是一个最普通的业务类型定义，无特殊要求。
 * 2.RealSubject 具体主题角色
 * 3.Proxy代理主题角色
 * Created by hyz84 on 16/12/14.
 */

public class ProxyPattern {

    public interface Subject {
        public void method();
    }

    public class RealSubject implements Subject {
        @Override
        public void method() {

        }
    }

    public class Proxy implements Subject {
        private Subject mSubject = null;

        public Proxy() {
            this.mSubject = new Proxy();
        }

        public Proxy(Object... objects) {
        }

        @Override
        public void method() {
            before();
            mSubject.method();
            after();
        }

        private void before() {
        }

        private void after() {
        }
    }
    /**********动态代理***************/
    /**
     * InvocationHandler 接口，通过这个接口，所有方法都由该Handler来处理，
     * 即所有被代理的方法都由InvocationHandler接管实际处理任务。
     * 动态代理是根据被代理的接口生成所有的方法，也就是说给定一个接口，动态代理会宣传
     * “我已经实现该接口下所有方法了”
     */
    public class MyInvocationHandler implements InvocationHandler {

        //被代理实类
        Object target = null;

        public MyInvocationHandler(Object _obj) {
            this.target = _obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
            Object result = method.invoke(this.target, objects);
            return result;
        }
    }

    //动态代理类
    public static class DynamicProxy<T> {
        public static <T> T newProxyInstance(ClassLoader loader, Class<T>[] interfaces, InvocationHandler h) {
            return (T) java.lang.reflect.Proxy.newProxyInstance(loader, interfaces, h);
        }
    }

//    public static class SubjectDynamicProxy extends DynamicProxy {
//        public static <T> T newProxyInstance(Subject subject) {
//            ClassLoader cl = subject.getClass().getClassLoader();
//            Class<?>[] classes = subject.getClass().getInterfaces();
//            InvocationHandler mHandler = new MyInvocationHandler(subject);
//            return newProxyInstance(cl, classes, mHandler);
//        }
//    }
//
//    //场景类
//    public class Client {
//        Subject mSubject = new RealSubject();
//        Subject proxy = SubjectDynamicProxy.newProxyInstance(mSubject);
//        proxy.method();
//    }
}
