package com.izhengyin.test.es;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.w3c.dom.ranges.Range;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2018/9/17 下午3:46
 */
public class QueryApi {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level","TRACE");

        TransportClient client = null;
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", "elk").build();
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("hadoop-worker1"), 9192))
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("hadoop-worker2"), 9192))
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("hadoop-worker3"), 9192));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        test2(client);


        client.close();
    }


    private static void test2(TransportClient client){
        QueryBuilder rqb = new RangeQueryBuilder("@timestamp").gt(1533713301000L).lte(1533713401000L);
        QueryBuilder bqb = new BoolQueryBuilder().
                must(rqb);
        System.out.println(bqb.toString());
        SearchResponse response = client.prepareSearch("ods-events-2018.08.08")
                .setQuery(bqb)
                .setExplain(false)
                .execute()
                .actionGet();
        System.out.println(JSON.toJSON(response));
    }

    private static void test1(TransportClient client){

        QueryBuilder tqb1 = new TermQueryBuilder("application","application1");
        QueryBuilder tqb2 = new TermQueryBuilder("client","client1");
        QueryBuilder tqb3 = new TermQueryBuilder("pv",19);
        QueryBuilder rqb = new RangeQueryBuilder("dateMinute").lt(System.currentTimeMillis()).gt(1532850794950L);
        QueryBuilder bqb = new BoolQueryBuilder().
                must(tqb1).
                must(tqb2).
                must(tqb3).
                must(rqb);
        AggregationBuilder ab = new SumAggregationBuilder("sum_pv").field("pv");



        System.out.println(bqb.toString());


        SearchResponse response = client.prepareSearch("biz-monitor")
                .setQuery(bqb)
                .addAggregation(ab)
                .setFrom(0).setSize(5).setExplain(false)
                .execute()
                .actionGet();

        Sum sum = response.getAggregations().get("sum_pv");

        System.out.println(sum.getValue());

        SearchHit[] searchHits = response.getHits().getHits();
        for(SearchHit searchHit : searchHits){
            System.out.println(JSON.toJSON(searchHit));
        }


    }


}
