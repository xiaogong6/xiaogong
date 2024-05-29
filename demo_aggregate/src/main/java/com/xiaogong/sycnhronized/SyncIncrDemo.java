package com.xiaogong.sycnhronized;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-16
 */
public class SyncIncrDemo implements Runnable{
    //共享资源(临界资源)
    static int i = 0;

    //synchronized关键字修饰实例成员方法
    public synchronized void incr(){
        i++;
    }
    @Override
    public void run() {
        for(int j=0;j<1000;j++){
            incr();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        SyncIncrDemo syncIncrDemo = new SyncIncrDemo();
        Thread t1=new Thread(syncIncrDemo);
        Thread t2=new Thread(syncIncrDemo);
        t1.start();
        t2.start();
        /**
         *join：使得放弃当前线程的执行，并返回对应的线程，例如下面代码的意思就是：
         程序在main线程中调用t1,t2线程的join方法，则main线程放弃cpu控制权，并返回
         t1,t2线程继续执行直到线程t1,t2执行完毕;
         所以结果是t1,t2线程执行完后，才到主线程执行，相当于在main线程中同步t1,t2
         线程，t1,t2执行完了，main线程才有执行的机会
         */
        t1.join();
        t2.join();
        System.out.println(i);
    }
    /**
     * 输出结果:
     * 2000
     */
}

