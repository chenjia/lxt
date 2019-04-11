package com.lxt.ms.designpattern.proxy.simpleproxy;

/**
 * Created by Tom.
 */
public class RealSubject implements Subject{
    @Override
    public void request() {
        System.out.println("real service is called.");
    }
}
