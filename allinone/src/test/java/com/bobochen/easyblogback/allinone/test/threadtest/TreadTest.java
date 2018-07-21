package com.bobochen.easyblogback.allinone.test.threadtest;

import org.junit.Test;

import java.io.Console;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TreadTest {


    @Test
    public void thread1Test() {
        try {


            FutureTask<Integer> ftask=new FutureTask<Integer>(()->LamadaThreadMethod.method3());
            new Thread(ftask,"t5").start();
            try {
                System.out.println("Future result:"+ftask.get(1, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            new Thread(new Thread1(),"t1").start();
            new Thread(new Thread1(),"t2").start();
            new Thread(()->LamadaThreadMethod.method1(),"t3").start();
            new Thread(()->LamadaThreadMethod.method2(),"t4").start();

            while(true){
                Thread.sleep(60*60*1000);
            }
        }catch (Exception e){

        }
    }


}

class Thread1 implements Runnable {




    @Override
    public void run() {
        try {
            Thread.sleep(8000);
            System.out.println(Thread.currentThread().getName()+"延时8s执行");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class  LamadaThreadMethod{
    public static void method1(){
        try {
            Thread.sleep(8000);
            System.out.println(Thread.currentThread().getName()+"method1 延时8s执行");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void method2(){
        try {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName()+"method2 延时3s执行");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int method3(){
        try {
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName()+"method3 延时10s执行");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return 33;
        }
    }
}
