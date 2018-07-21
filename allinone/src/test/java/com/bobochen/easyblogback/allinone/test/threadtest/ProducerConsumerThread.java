package com.bobochen.easyblogback.allinone.test.threadtest;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerThread {
    @Test
    public void syncTest(){
        try {

                new Thread(()->Worker.setWork()).start();
                Thread.sleep(1000);
           for (int i=0;i<=2;i++){

                new Thread(()->Worker.getWork()).start();
            }
            while(true){
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



class Worker{
    public static List<WorkContext> worklist=new ArrayList<>();
    public static int num=0;
    public static Object worklistlock = new Object();
    public static void setWork(){
        while(true) {
            synchronized (worklistlock) {
                try {

                    num = num + 1;
                    worklist.add(new WorkContext(num, num + "name"));
                    System.out.println(Thread.currentThread().getName() + " 生产" + num + " " + worklist.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getWork(){
        while(true) {
            synchronized (worklistlock) {
                try {

                    if (worklist.size() > 0) {
                        WorkContext wc = worklist.get(0);
                        worklist.remove(wc);
                        System.out.println(Thread.currentThread().getName() + " 消费" + wc.getId() + " " + worklist.size());
                    } else {
                        System.out.println(Thread.currentThread().getName() + " 没有东西消费" + " " + worklist.size());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



class Worker2{
    public static List<WorkContext> worklist=new ArrayList<>();
    public static int num=0;
    public static Object worklistlock = new Object();
    public static void setWork(){
        while(true) {
            synchronized (worklistlock) {
                try {

                    num = num + 1;
                    worklist.add(new WorkContext(num, num + "name"));
                    System.out.println(Thread.currentThread().getName() + " 生产" + num + " " + worklist.size());
                    worklistlock.notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getWork(){
        while(true) {
            synchronized (worklistlock) {
                try {

                    if (worklist.size() > 0) {
                        WorkContext wc = worklist.get(0);
                        worklist.remove(wc);
                        System.out.println(Thread.currentThread().getName() + " 消费" + wc.getId() + " " + worklist.size());
                    } else {
                        worklistlock.wait();
                        System.out.println(Thread.currentThread().getName() + " 没有东西消费" + " " + worklist.size());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

@Data
@AllArgsConstructor
class WorkContext{
    private int id;
    private String name;
}


