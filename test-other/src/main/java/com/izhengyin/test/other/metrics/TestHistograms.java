package com.izhengyin.test.other.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

public class TestHistograms {
	 private static final MetricRegistry metrics = new MetricRegistry();
	 private static Histogram histo = metrics.histogram(MetricRegistry.name("com.RequestHandler", "response-sizes"));
	 /**
     * 在控制台上打印输出
     */
     private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();
     /** 
      * TODO 
      * @author scutshuxue.chenxf 
      * @param args 
      * void 
      * @throws InterruptedException 
      */  
     public static void main(String[] args) throws InterruptedException {  
            // TODOAuto-generated method stub  
            reporter.start(3, TimeUnit.SECONDS);
            int i=0;  
            while(true){  
               histo.update(i++);  
               Thread.sleep(1000);  
            }  
     }  
}
