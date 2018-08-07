package com.izhengyin.test.other.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.Random;

public class TestTimers {
	/**
     * 实例化一个registry，最核心的一个模块，相当于一个应用程序的metrics系统的容器，维护一个Map
     */
    private static final MetricRegistry metrics = new MetricRegistry();

    /**
     * 在控制台上打印输出
     */
    private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();


    private final static Timer responses = metrics.timer(MetricRegistry.name(TestTimers.class, "request"));

	public static void main(String[] args) throws InterruptedException {
		Random rn = new Random();  
		while(true){  
			Timer.Context context = responses.time();
            Thread.sleep(rn.nextInt(1000));  
            context.stop();  
        }  
	}
}
