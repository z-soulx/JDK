package test;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: JDK
 * @description: 本地线程变量-父子线程-线程池相关
 * @author: soulx
 * @create: 2020-04-29 15:36
 **/
public class ThreadLocalTest {



    // 模拟 InheritableThreadLocal 父子线程copy线程变量的线程池操作问题
    // 由于线程的变量copy只会在第一次copy父线程变量。但是线程池的线程一次创建能存在很久，这样处理不同的父线程给的task却
    // 变量环境一只没变
    @Test
    public  void ParentSubThread() throws InterruptedException {
        final InheritableThreadLocal<Span> inheritableThreadLocal = new InheritableThreadLocal<Span>();

        inheritableThreadLocal.set(new Span("xiexiexie"));
        //输出 xiexiexie
        Object o = inheritableThreadLocal.get();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("========");
                System.out.println(inheritableThreadLocal.get());;
//                inheritableThreadLocal.set(new Span("zhangzhangzhang"));
                System.out.println(inheritableThreadLocal.get().name="ssss");;
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("========");
        Span span = inheritableThreadLocal.get();
        System.out.println(span);
    }

    static class Span {
        public String name;
        public int age;
        public Span(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"name\":\"")
                    .append(name).append('\"');
            sb.append(",\"age\":")
                    .append(age);
            sb.append('}');
            return sb.toString();
        }
    }


}
