package com.lxt.thread;

public class CountTest {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final CountTest test = new CountTest();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<100;j++)
                        test.increase();
                };
            }.start();
        }

//        while(Thread.activeCount()>1){
//            Thread.yield();
//        }

        System.out.println(test.inc);
    }
}
