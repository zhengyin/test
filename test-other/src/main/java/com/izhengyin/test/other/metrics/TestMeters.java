package com.izhengyin.test.other.metrics;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class TestMeters {
	/**
     * 实例化一个registry，最核心的一个模块，相当于一个应用程序的metrics系统的容器，维护一个Map
     */
    private static final MetricRegistry metrics = new MetricRegistry();

    /**
     * 在控制台上打印输出
     */
//    private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();

    final static Graphite graphite = new Graphite(new InetSocketAddress("127.0.0.1", 18125));
    final static GraphiteReporter reporter = GraphiteReporter.forRegistry(metrics)
                                                      .prefixedWith("192.168.1.88")
                                                      .convertRatesTo(TimeUnit.SECONDS)
                                                      .convertDurationsTo(TimeUnit.MILLISECONDS)
                                                      .filter(MetricFilter.ALL)
                                                      .build(graphite);
 
    /*
    
    private static final Slf4jReporter reporter = Slf4jReporter.forRegistry(metrics)
            .outputTo(LoggerFactory.getLogger("com.example.metrics"))
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build();
            */
    /**
     * 实例化一个Meter
     */
    private static final Meter requests = metrics.meter(MetricRegistry.name("Gateway", "request"));

    public static void handleRequest() {
    	requests.mark();
        
        
    }

    public static void main(String[] args) throws InterruptedException {
        reporter.start(1, TimeUnit.SECONDS);
        while(true){
            handleRequest();
            Thread.sleep(10);
        }
    }
}
