package com.ang.frontui.mythread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class AngThread implements Runnable{
    private volatile int num=0;
    CountDownLatch countDownLatch = new CountDownLatch(3);

    AtomicInteger anum = new AtomicInteger(0);

    public synchronized void add(){
        System.out.println(Thread.currentThread().getName() + ",num:" + this.num++);
    }

    @Async("mailTaskExecutor")
    public void automicAdd(){
        System.out.println(Thread.currentThread().getName() + ",num:" +anum.getAndIncrement());
    }

    public  int getNum(){
        return this.num;
    }

    public static void main(String[] args) throws InterruptedException {
        AngThread angThread = new AngThread();
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque<>();
        Executor executor = new ThreadPoolExecutor(3,3,60L, TimeUnit.SECONDS,  blockingQueue);
        long start = System.currentTimeMillis();
        executor.execute(angThread);
        executor.execute(angThread);
        executor.execute(angThread);
        angThread.getCountDownLatch().await();
        System.out.println("耗时："+(System.currentTimeMillis() - start));
    }

    @Override
    public void run() {
        while (anum.get() < 1000) {
//            add();
            automicAdd();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        countDownLatch.countDown();
       return;
    }

    public CountDownLatch getCountDownLatch(){
        return countDownLatch;
    }
}
