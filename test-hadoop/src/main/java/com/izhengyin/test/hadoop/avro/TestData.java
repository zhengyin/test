package com.izhengyin.test.hadoop.avro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.FileReader;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019/4/26 2:31 PM
 */
public class TestData {
    private static final String data = "[{\n" +
            "\t\"itemId\": 69321,\n" +
            "\t\"userId\": 1,\n" +
            "\t\"bizType\": false,\n" +
            "\t\"catId\": 1000000000000000,\n" +
            "\t\"myCatId\": 0,\n" +
            "\t\"itemName\": \"中国通俗文艺（81。1创刊号柯蓝主编）\",\n" +
            "\t\"author\": \"\",\n" +
            "\t\"press\": \"文化艺术\",\n" +
            "\t\"price\": \"10.0\",\n" +
            "\t\"yearsGroup\": false,\n" +
            "\t\"pubDate\": \"0000-00-00\",\n" +
            "\t\"years\": 6000000000,\n" +
            "\t\"discount\": 100,\n" +
            "\t\"number\": 1,\n" +
            "\t\"isNewBook\": false,\n" +
            "\t\"quality\": 90,\n" +
            "\t\"isOnSale\": false,\n" +
            "\t\"beginSaleTime\": 1396930757,\n" +
            "\t\"endSaleTime\": 2114352000,\n" +
            "\t\"oriPrice\": \"0.0\",\n" +
            "\t\"itemSn\": \"\",\n" +
            "\t\"isbn\": \"\",\n" +
            "\t\"isRelatedISBN\": false,\n" +
            "\t\"isSyncISBN\": false,\n" +
            "\t\"booklibId\": 0,\n" +
            "\t\"imgUrl\": \"\",\n" +
            "\t\"bgImgUrl\": \"\",\n" +
            "\t\"isBuildIndex\": false,\n" +
            "\t\"isDelete\": false,\n" +
            "\t\"certifyStatus\": \"2\",\n" +
            "\t\"reCertifyStatus\": false,\n" +
            "\t\"repeatMd5\": \"99cb17e7610b2c1dac81fcdaef4fdd85\",\n" +
            "\t\"approach\": false,\n" +
            "\t\"isDraft\": false,\n" +
            "\t\"productArea\": 1002000000,\n" +
            "\t\"bearShipping\": \"1\",\n" +
            "\t\"isUseMould\": 1,\n" +
            "\t\"mouldId\": 0,\n" +
            "\t\"weight\": 0.0,\n" +
            "\t\"weightPiece\": 0.0,\n" +
            "\t\"isPreSale\": false,\n" +
            "\t\"preSaleNum\": 0,\n" +
            "\t\"isFlashSale\": false,\n" +
            "\t\"startFlashSaleTime\": 0,\n" +
            "\t\"endFlashSaleTime\": 0,\n" +
            "\t\"limitBuyNum\": 0,\n" +
            "\t\"addTime\": 1054801971,\n" +
            "\t\"updateTime\": 1556184727000,\n" +
            "\t\"__dmlType\": \"DELETE\",\n" +
            "\t\"__dmlTs\": 1556252260872,\n" +
            "\t\"__partition\": 10,\n" +
            "\t\"__offset\": 210\n" +
            "}, {\n" +
            "\t\"itemId\": 74836,\n" +
            "\t\"userId\": 416,\n" +
            "\t\"bizType\": false,\n" +
            "\t\"catId\": 1000000000000000,\n" +
            "\t\"myCatId\": 0,\n" +
            "\t\"itemName\": \"盛世魂-大唐玄宗时代\",\n" +
            "\t\"author\": \"赵剑敏\",\n" +
            "\t\"press\": \"三联\",\n" +
            "\t\"price\": \"8.0\",\n" +
            "\t\"yearsGroup\": false,\n" +
            "\t\"pubDate\": \"1995-08-02\",\n" +
            "\t\"years\": 1003000000,\n" +
            "\t\"discount\": 100,\n" +
            "\t\"number\": 1,\n" +
            "\t\"isNewBook\": false,\n" +
            "\t\"quality\": 90,\n" +
            "\t\"isOnSale\": false,\n" +
            "\t\"beginSaleTime\": 1396930758,\n" +
            "\t\"endSaleTime\": 2114352000,\n" +
            "\t\"oriPrice\": \"0.0\",\n" +
            "\t\"itemSn\": \"\",\n" +
            "\t\"isbn\": \"\",\n" +
            "\t\"isRelatedISBN\": false,\n" +
            "\t\"isSyncISBN\": false,\n" +
            "\t\"booklibId\": 0,\n" +
            "\t\"imgUrl\": \"\",\n" +
            "\t\"bgImgUrl\": \"\",\n" +
            "\t\"isBuildIndex\": false,\n" +
            "\t\"isDelete\": false,\n" +
            "\t\"certifyStatus\": \"2\",\n" +
            "\t\"reCertifyStatus\": false,\n" +
            "\t\"repeatMd5\": \"d2f41fade2217cfa200b180467b0dc62\",\n" +
            "\t\"approach\": false,\n" +
            "\t\"isDraft\": false,\n" +
            "\t\"productArea\": 1002000000,\n" +
            "\t\"bearShipping\": \"1\",\n" +
            "\t\"isUseMould\": 1,\n" +
            "\t\"mouldId\": 0,\n" +
            "\t\"weight\": 0.0,\n" +
            "\t\"weightPiece\": 0.0,\n" +
            "\t\"isPreSale\": false,\n" +
            "\t\"preSaleNum\": 0,\n" +
            "\t\"isFlashSale\": false,\n" +
            "\t\"startFlashSaleTime\": 0,\n" +
            "\t\"endFlashSaleTime\": 0,\n" +
            "\t\"limitBuyNum\": 0,\n" +
            "\t\"addTime\": 1055350564,\n" +
            "\t\"updateTime\": 1486084863000,\n" +
            "\t\"__dmlType\": \"DELETE\",\n" +
            "\t\"__dmlTs\": 1556252260872,\n" +
            "\t\"__partition\": 10,\n" +
            "\t\"__offset\": 210\n" +
            "}, {\n" +
            "\t\"itemId\": 148499,\n" +
            "\t\"userId\": 416,\n" +
            "\t\"bizType\": false,\n" +
            "\t\"catId\": 1000000000000000,\n" +
            "\t\"myCatId\": 0,\n" +
            "\t\"itemName\": \"语林采英\",\n" +
            "\t\"author\": \"秦牧\",\n" +
            "\t\"press\": \"上海文艺\",\n" +
            "\t\"price\": \"6.0\",\n" +
            "\t\"yearsGroup\": false,\n" +
            "\t\"pubDate\": \"1978-05-03\",\n" +
            "\t\"years\": 1005000000,\n" +
            "\t\"discount\": 100,\n" +
            "\t\"number\": 1,\n" +
            "\t\"isNewBook\": false,\n" +
            "\t\"quality\": 90,\n" +
            "\t\"isOnSale\": false,\n" +
            "\t\"beginSaleTime\": 1396930758,\n" +
            "\t\"endSaleTime\": 2114352000,\n" +
            "\t\"oriPrice\": \"0.0\",\n" +
            "\t\"itemSn\": \"\",\n" +
            "\t\"isbn\": \"\",\n" +
            "\t\"isRelatedISBN\": false,\n" +
            "\t\"isSyncISBN\": false,\n" +
            "\t\"booklibId\": 0,\n" +
            "\t\"imgUrl\": \"\",\n" +
            "\t\"bgImgUrl\": \"\",\n" +
            "\t\"isBuildIndex\": false,\n" +
            "\t\"isDelete\": false,\n" +
            "\t\"certifyStatus\": \"2\",\n" +
            "\t\"reCertifyStatus\": false,\n" +
            "\t\"repeatMd5\": \"ac3d569d0699a427b56b89f200271c0f\",\n" +
            "\t\"approach\": false,\n" +
            "\t\"isDraft\": false,\n" +
            "\t\"productArea\": 1002000000,\n" +
            "\t\"bearShipping\": \"1\",\n" +
            "\t\"isUseMould\": 1,\n" +
            "\t\"mouldId\": 0,\n" +
            "\t\"weight\": 0.0,\n" +
            "\t\"weightPiece\": 0.0,\n" +
            "\t\"isPreSale\": false,\n" +
            "\t\"preSaleNum\": 0,\n" +
            "\t\"isFlashSale\": false,\n" +
            "\t\"startFlashSaleTime\": 0,\n" +
            "\t\"endFlashSaleTime\": 0,\n" +
            "\t\"limitBuyNum\": 0,\n" +
            "\t\"addTime\": 1062348346,\n" +
            "\t\"updateTime\": 1486084863000,\n" +
            "\t\"__dmlType\": \"DELETE\",\n" +
            "\t\"__dmlTs\": 1556252260872,\n" +
            "\t\"__partition\": 10,\n" +
            "\t\"__offset\": 210\n" +
            "}, {\n" +
            "\t\"itemId\": 187670,\n" +
            "\t\"userId\": 416,\n" +
            "\t\"bizType\": false,\n" +
            "\t\"catId\": 4000000000000000,\n" +
            "\t\"myCatId\": 0,\n" +
            "\t\"itemName\": \"红色娘子军主旋律乐谱\",\n" +
            "\t\"author\": \"\",\n" +
            "\t\"press\": \"人民\",\n" +
            "\t\"price\": \"5.0\",\n" +
            "\t\"yearsGroup\": false,\n" +
            "\t\"pubDate\": \"1970-12-01\",\n" +
            "\t\"years\": 1006000000,\n" +
            "\t\"discount\": 100,\n" +
            "\t\"number\": 1,\n" +
            "\t\"isNewBook\": false,\n" +
            "\t\"quality\": 80,\n" +
            "\t\"isOnSale\": false,\n" +
            "\t\"beginSaleTime\": 1396930758,\n" +
            "\t\"endSaleTime\": 2114352000,\n" +
            "\t\"oriPrice\": \"0.0\",\n" +
            "\t\"itemSn\": \"\",\n" +
            "\t\"isbn\": \"\",\n" +
            "\t\"isRelatedISBN\": false,\n" +
            "\t\"isSyncISBN\": false,\n" +
            "\t\"booklibId\": 0,\n" +
            "\t\"imgUrl\": \"\",\n" +
            "\t\"bgImgUrl\": \"\",\n" +
            "\t\"isBuildIndex\": false,\n" +
            "\t\"isDelete\": false,\n" +
            "\t\"certifyStatus\": \"2\",\n" +
            "\t\"reCertifyStatus\": false,\n" +
            "\t\"repeatMd5\": \"2b0a3b74b270261fb043c91b71c28e11\",\n" +
            "\t\"approach\": false,\n" +
            "\t\"isDraft\": false,\n" +
            "\t\"productArea\": 1002000000,\n" +
            "\t\"bearShipping\": \"1\",\n" +
            "\t\"isUseMould\": 1,\n" +
            "\t\"mouldId\": 0,\n" +
            "\t\"weight\": 0.0,\n" +
            "\t\"weightPiece\": 0.0,\n" +
            "\t\"isPreSale\": false,\n" +
            "\t\"preSaleNum\": 0,\n" +
            "\t\"isFlashSale\": false,\n" +
            "\t\"startFlashSaleTime\": 0,\n" +
            "\t\"endFlashSaleTime\": 0,\n" +
            "\t\"limitBuyNum\": 0,\n" +
            "\t\"addTime\": 1065370491,\n" +
            "\t\"updateTime\": 1486084863000,\n" +
            "\t\"__dmlType\": \"DELETE\",\n" +
            "\t\"__dmlTs\": 1556252260872,\n" +
            "\t\"__partition\": 10,\n" +
            "\t\"__offset\": 210\n" +
            "}, {\n" +
            "\t\"itemId\": 187671,\n" +
            "\t\"userId\": 416,\n" +
            "\t\"bizType\": false,\n" +
            "\t\"catId\": 4000000000000000,\n" +
            "\t\"myCatId\": 0,\n" +
            "\t\"itemName\": \"智取威虎山主旋律乐谱\",\n" +
            "\t\"author\": \"\",\n" +
            "\t\"press\": \"人民\",\n" +
            "\t\"price\": \"5.0\",\n" +
            "\t\"yearsGroup\": false,\n" +
            "\t\"pubDate\": \"1970-08-01\",\n" +
            "\t\"years\": 1006000000,\n" +
            "\t\"discount\": 100,\n" +
            "\t\"number\": 1,\n" +
            "\t\"isNewBook\": false,\n" +
            "\t\"quality\": 80,\n" +
            "\t\"isOnSale\": false,\n" +
            "\t\"beginSaleTime\": 1396930758,\n" +
            "\t\"endSaleTime\": 2114352000,\n" +
            "\t\"oriPrice\": \"0.0\",\n" +
            "\t\"itemSn\": \"\",\n" +
            "\t\"isbn\": \"\",\n" +
            "\t\"isRelatedISBN\": false,\n" +
            "\t\"isSyncISBN\": false,\n" +
            "\t\"booklibId\": 0,\n" +
            "\t\"imgUrl\": \"\",\n" +
            "\t\"bgImgUrl\": \"\",\n" +
            "\t\"isBuildIndex\": false,\n" +
            "\t\"isDelete\": false,\n" +
            "\t\"certifyStatus\": \"2\",\n" +
            "\t\"reCertifyStatus\": false,\n" +
            "\t\"repeatMd5\": \"65862bc9da0ba869f77f61b3981eb82b\",\n" +
            "\t\"approach\": false,\n" +
            "\t\"isDraft\": false,\n" +
            "\t\"productArea\": 1002000000,\n" +
            "\t\"bearShipping\": \"1\",\n" +
            "\t\"isUseMould\": 1,\n" +
            "\t\"mouldId\": 0,\n" +
            "\t\"weight\": 0.0,\n" +
            "\t\"weightPiece\": 0.0,\n" +
            "\t\"isPreSale\": false,\n" +
            "\t\"preSaleNum\": 0,\n" +
            "\t\"isFlashSale\": false,\n" +
            "\t\"startFlashSaleTime\": 0,\n" +
            "\t\"endFlashSaleTime\": 0,\n" +
            "\t\"limitBuyNum\": 0,\n" +
            "\t\"addTime\": 1065370492,\n" +
            "\t\"updateTime\": 1486084863000,\n" +
            "\t\"__dmlType\": \"DELETE\",\n" +
            "\t\"__dmlTs\": 1556252260872,\n" +
            "\t\"__partition\": 10,\n" +
            "\t\"__offset\": 210\n" +
            "}, {\n" +
            "\t\"itemId\": 225328,\n" +
            "\t\"userId\": 1,\n" +
            "\t\"bizType\": false,\n" +
            "\t\"catId\": 5000000000000000,\n" +
            "\t\"myCatId\": 0,\n" +
            "\t\"itemName\": \"领导者媒介形象设计（硬精）\",\n" +
            "\t\"author\": \"龙永枢等编\",\n" +
            "\t\"press\": \"社会科学文献\",\n" +
            "\t\"price\": \"8.0\",\n" +
            "\t\"yearsGroup\": false,\n" +
            "\t\"pubDate\": \"1997-09-01\",\n" +
            "\t\"years\": 1003000000,\n" +
            "\t\"discount\": 100,\n" +
            "\t\"number\": 1,\n" +
            "\t\"isNewBook\": false,\n" +
            "\t\"quality\": 90,\n" +
            "\t\"isOnSale\": false,\n" +
            "\t\"beginSaleTime\": 1396930758,\n" +
            "\t\"endSaleTime\": 2114352000,\n" +
            "\t\"oriPrice\": \"0.0\",\n" +
            "\t\"itemSn\": \"\",\n" +
            "\t\"isbn\": \"\",\n" +
            "\t\"isRelatedISBN\": false,\n" +
            "\t\"isSyncISBN\": false,\n" +
            "\t\"booklibId\": 0,\n" +
            "\t\"imgUrl\": \"\",\n" +
            "\t\"bgImgUrl\": \"\",\n" +
            "\t\"isBuildIndex\": false,\n" +
            "\t\"isDelete\": false,\n" +
            "\t\"certifyStatus\": \"2\",\n" +
            "\t\"reCertifyStatus\": false,\n" +
            "\t\"repeatMd5\": \"20115a5a47c08146d4ef5fbfbc8084ae\",\n" +
            "\t\"approach\": false,\n" +
            "\t\"isDraft\": false,\n" +
            "\t\"productArea\": 1002000000,\n" +
            "\t\"bearShipping\": \"1\",\n" +
            "\t\"isUseMould\": 1,\n" +
            "\t\"mouldId\": 0,\n" +
            "\t\"weight\": 0.0,\n" +
            "\t\"weightPiece\": 0.0,\n" +
            "\t\"isPreSale\": false,\n" +
            "\t\"preSaleNum\": 0,\n" +
            "\t\"isFlashSale\": false,\n" +
            "\t\"startFlashSaleTime\": 0,\n" +
            "\t\"endFlashSaleTime\": 0,\n" +
            "\t\"limitBuyNum\": 0,\n" +
            "\t\"addTime\": 1069024745,\n" +
            "\t\"updateTime\": 1556184727000,\n" +
            "\t\"__dmlType\": \"DELETE\",\n" +
            "\t\"__dmlTs\": 1556252260872,\n" +
            "\t\"__partition\": 10,\n" +
            "\t\"__offset\": 210\n" +
            "}, {\n" +
            "\t\"itemId\": 248982,\n" +
            "\t\"userId\": 416,\n" +
            "\t\"bizType\": false,\n" +
            "\t\"catId\": 1000000000000000,\n" +
            "\t\"myCatId\": 0,\n" +
            "\t\"itemName\": \"劳伦斯\",\n" +
            "\t\"author\": \"克默德\",\n" +
            "\t\"press\": \"三联\",\n" +
            "\t\"price\": \"3.0\",\n" +
            "\t\"yearsGroup\": false,\n" +
            "\t\"pubDate\": \"1986-12-01\",\n" +
            "\t\"years\": 1004000000,\n" +
            "\t\"discount\": 100,\n" +
            "\t\"number\": 1,\n" +
            "\t\"isNewBook\": false,\n" +
            "\t\"quality\": 90,\n" +
            "\t\"isOnSale\": false,\n" +
            "\t\"beginSaleTime\": 1396930758,\n" +
            "\t\"endSaleTime\": 2114352000,\n" +
            "\t\"oriPrice\": \"0.0\",\n" +
            "\t\"itemSn\": \"\",\n" +
            "\t\"isbn\": \"\",\n" +
            "\t\"isRelatedISBN\": false,\n" +
            "\t\"isSyncISBN\": false,\n" +
            "\t\"booklibId\": 0,\n" +
            "\t\"imgUrl\": \"\",\n" +
            "\t\"bgImgUrl\": \"\",\n" +
            "\t\"isBuildIndex\": false,\n" +
            "\t\"isDelete\": false,\n" +
            "\t\"certifyStatus\": \"2\",\n" +
            "\t\"reCertifyStatus\": false,\n" +
            "\t\"repeatMd5\": \"d1577064562d367a56dc157360cc7403\",\n" +
            "\t\"approach\": false,\n" +
            "\t\"isDraft\": false,\n" +
            "\t\"productArea\": 1002000000,\n" +
            "\t\"bearShipping\": \"1\",\n" +
            "\t\"isUseMould\": 1,\n" +
            "\t\"mouldId\": 0,\n" +
            "\t\"weight\": 0.0,\n" +
            "\t\"weightPiece\": 0.0,\n" +
            "\t\"isPreSale\": false,\n" +
            "\t\"preSaleNum\": 0,\n" +
            "\t\"isFlashSale\": false,\n" +
            "\t\"startFlashSaleTime\": 0,\n" +
            "\t\"endFlashSaleTime\": 0,\n" +
            "\t\"limitBuyNum\": 0,\n" +
            "\t\"addTime\": 1071095130,\n" +
            "\t\"updateTime\": 1486084863000,\n" +
            "\t\"__dmlType\": \"DELETE\",\n" +
            "\t\"__dmlTs\": 1556252260872,\n" +
            "\t\"__partition\": 10,\n" +
            "\t\"__offset\": 210\n" +
            "}, {\n" +
            "\t\"itemId\": 249000,\n" +
            "\t\"userId\": 416,\n" +
            "\t\"bizType\": false,\n" +
            "\t\"catId\": 1000000000000000,\n" +
            "\t\"myCatId\": 0,\n" +
            "\t\"itemName\": \"京剧剧本 恶虎村 斩经堂 四郎探母 连环套\",\n" +
            "\t\"author\": \"《戏剧艺术》>《上海戏剧》编辑部\",\n" +
            "\t\"press\": \"编辑部\",\n" +
            "\t\"price\": \"6.0\",\n" +
            "\t\"yearsGroup\": false,\n" +
            "\t\"pubDate\": \"1979-07-01\",\n" +
            "\t\"years\": 1004000000,\n" +
            "\t\"discount\": 100,\n" +
            "\t\"number\": 1,\n" +
            "\t\"isNewBook\": false,\n" +
            "\t\"quality\": 80,\n" +
            "\t\"isOnSale\": false,\n" +
            "\t\"beginSaleTime\": 1396930758,\n" +
            "\t\"endSaleTime\": 2114352000,\n" +
            "\t\"oriPrice\": \"0.0\",\n" +
            "\t\"itemSn\": \"\",\n" +
            "\t\"isbn\": \"\",\n" +
            "\t\"isRelatedISBN\": false,\n" +
            "\t\"isSyncISBN\": false,\n" +
            "\t\"booklibId\": 0,\n" +
            "\t\"imgUrl\": \"\",\n" +
            "\t\"bgImgUrl\": \"\",\n" +
            "\t\"isBuildIndex\": false,\n" +
            "\t\"isDelete\": false,\n" +
            "\t\"certifyStatus\": \"2\",\n" +
            "\t\"reCertifyStatus\": false,\n" +
            "\t\"repeatMd5\": \"f07dab770aea22c8ca3662190cfac3d6\",\n" +
            "\t\"approach\": false,\n" +
            "\t\"isDraft\": false,\n" +
            "\t\"productArea\": 1002000000,\n" +
            "\t\"bearShipping\": \"1\",\n" +
            "\t\"isUseMould\": 1,\n" +
            "\t\"mouldId\": 0,\n" +
            "\t\"weight\": 0.0,\n" +
            "\t\"weightPiece\": 0.0,\n" +
            "\t\"isPreSale\": false,\n" +
            "\t\"preSaleNum\": 0,\n" +
            "\t\"isFlashSale\": false,\n" +
            "\t\"startFlashSaleTime\": 0,\n" +
            "\t\"endFlashSaleTime\": 0,\n" +
            "\t\"limitBuyNum\": 0,\n" +
            "\t\"addTime\": 1071095156,\n" +
            "\t\"updateTime\": 1486084863000,\n" +
            "\t\"__dmlType\": \"DELETE\",\n" +
            "\t\"__dmlTs\": 1556252260872,\n" +
            "\t\"__partition\": 10,\n" +
            "\t\"__offset\": 210\n" +
            "}, {\n" +
            "\t\"itemId\": 310206,\n" +
            "\t\"userId\": 416,\n" +
            "\t\"bizType\": false,\n" +
            "\t\"catId\": 14000000000000000,\n" +
            "\t\"myCatId\": 0,\n" +
            "\t\"itemName\": \"广>告心理-广告人对消费行为的心理把握（龙媒广告选书）\",\n" +
            "\t\"author\": \"马谋超\",\n" +
            "\t\"press\": \"中国物价\",\n" +
            "\t\"price\": \"12.0\",\n" +
            "\t\"yearsGroup\": false,\n" +
            "\t\"pubDate\": \"1997-01-01\",\n" +
            "\t\"years\": 1003000000,\n" +
            "\t\"discount\": 100,\n" +
            "\t\"number\": 1,\n" +
            "\t\"isNewBook\": false,\n" +
            "\t\"quality\": 100,\n" +
            "\t\"isOnSale\": false,\n" +
            "\t\"beginSaleTime\": 1396930759,\n" +
            "\t\"endSaleTime\": 2114352000,\n" +
            "\t\"oriPrice\": \"24.0\",\n" +
            "\t\"itemSn\": \"\",\n" +
            "\t\"isbn\": \"\",\n" +
            "\t\"isRelatedISBN\": false,\n" +
            "\t\"isSyncISBN\": false,\n" +
            "\t\"booklibId\": 0,\n" +
            "\t\"imgUrl\": \"\",\n" +
            "\t\"bgImgUrl\": \"\",\n" +
            "\t\"isBuildIndex\": false,\n" +
            "\t\"isDelete\": false,\n" +
            "\t\"certifyStatus\": \"2\",\n" +
            "\t\"reCertifyStatus\": false,\n" +
            "\t\"repeatMd5\": \"d3702d442e641c18643b04fc279de965\",\n" +
            "\t\"approach\": false,\n" +
            "\t\"isDraft\": false,\n" +
            "\t\"productArea\": 1002000000,\n" +
            "\t\"bearShipping\": \"1\",\n" +
            "\t\"isUseMould\": 1,\n" +
            "\t\"mouldId\": 0,\n" +
            "\t\"weight\": 0.0,\n" +
            "\t\"weightPiece\": 0.0,\n" +
            "\t\"isPreSale\": false,\n" +
            "\t\"preSaleNum\": 0,\n" +
            "\t\"isFlashSale\": false,\n" +
            "\t\"startFlashSaleTime\": 0,\n" +
            "\t\"endFlashSaleTime\": 0,\n" +
            "\t\"limitBuyNum\": 0,\n" +
            "\t\"addTime\": 1075742324,\n" +
            "\t\"updateTime\": 1486084863000,\n" +
            "\t\"__dmlType\": \"DELETE\",\n" +
            "\t\"__dmlTs\": 1556252260872,\n" +
            "\t\"__partition\": 10,\n" +
            "\t\"__offset\": 210\n" +
            "}, {\n" +
            "\t\"itemId\": 323959,\n" +
            "\t\"userId\": 416,\n" +
            "\t\"bizType\": false,\n" +
            "\t\"catId\": 4000000000000000,\n" +
            "\t\"myCatId\": 0,\n" +
            "\t\"itemName\": \"纸上沙龙（综合艺术期刊创刊号 铜版纸彩印）\",\n" +
            "\t\"author\": \"侯素平主编\",\n" +
            "\t\"press\": \"编辑部\",\n" +
            "\t\"price\": \"10.0\",\n" +
            "\t\"yearsGroup\": false,\n" +
            "\t\"pubDate\": \"2002-12-01\",\n" +
            "\t\"years\": 1002000000,\n" +
            "\t\"discount\": 100,\n" +
            "\t\"number\": 1,\n" +
            "\t\"isNewBook\": false,\n" +
            "\t\"quality\": 90,\n" +
            "\t\"isOnSale\": false,\n" +
            "\t\"beginSaleTime\": 1396930760,\n" +
            "\t\"endSaleTime\": 2114352000,\n" +
            "\t\"oriPrice\": \"0.0\",\n" +
            "\t\"itemSn\": \"\",\n" +
            "\t\"isbn\": \"\",\n" +
            "\t\"isRelatedISBN\": false,\n" +
            "\t\"isSyncISBN\": false,\n" +
            "\t\"booklibId\": 0,\n" +
            "\t\"imgUrl\": \"\",\n" +
            "\t\"bgImgUrl\": \"\",\n" +
            "\t\"isBuildIndex\": false,\n" +
            "\t\"isDelete\": false,\n" +
            "\t\"certifyStatus\": \"2\",\n" +
            "\t\"reCertifyStatus\": false,\n" +
            "\t\"repeatMd5\": \"acad805b8fab22ef9f4ed84e80de7d6d\",\n" +
            "\t\"approach\": false,\n" +
            "\t\"isDraft\": false,\n" +
            "\t\"productArea\": 1002000000,\n" +
            "\t\"bearShipping\": \"1\",\n" +
            "\t\"isUseMould\": 1,\n" +
            "\t\"mouldId\": 0,\n" +
            "\t\"weight\": 0.0,\n" +
            "\t\"weightPiece\": 0.0,\n" +
            "\t\"isPreSale\": false,\n" +
            "\t\"preSaleNum\": 0,\n" +
            "\t\"isFlashSale\": false,\n" +
            "\t\"startFlashSaleTime\": 0,\n" +
            "\t\"endFlashSaleTime\": 0,\n" +
            "\t\"limitBuyNum\": 0,\n" +
            "\t\"addTime\": 1076700043,\n" +
            "\t\"updateTime\": 1486084863000,\n" +
            "\t\"__dmlType\": \"DELETE\",\n" +
            "\t\"__dmlTs\": 1556252260872,\n" +
            "\t\"__partition\": 10,\n" +
            "\t\"__offset\": 210\n" +
            "}]";

    private static final String _schema = "{\"doc\":\"Sqoop import of QueryResult\",\"fields\":[{\"name\":\"itemId\",\"type\":[\"null\",\"long\"]},{\"name\":\"userId\",\"type\":[\"null\",\"long\"]},{\"name\":\"bizType\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"catId\",\"type\":[\"null\",\"long\"]},{\"name\":\"myCatId\",\"type\":[\"null\",\"long\"]},{\"name\":\"itemName\",\"type\":[\"null\",\"string\"]},{\"name\":\"author\",\"type\":[\"null\",\"string\"]},{\"name\":\"press\",\"type\":[\"null\",\"string\"]},{\"name\":\"price\",\"type\":[\"null\",\"string\"]},{\"name\":\"yearsGroup\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"pubDate\",\"type\":[\"null\",\"string\"]},{\"name\":\"years\",\"type\":[\"null\",\"long\"]},{\"name\":\"discount\",\"type\":[\"null\",\"int\"]},{\"name\":\"number\",\"type\":[\"null\",\"int\"]},{\"name\":\"isNewBook\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"quality\",\"type\":[\"null\",\"int\"]},{\"name\":\"isOnSale\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"beginSaleTime\",\"type\":[\"null\",\"int\"]},{\"name\":\"endSaleTime\",\"type\":[\"null\",\"int\"]},{\"name\":\"oriPrice\",\"type\":[\"null\",\"string\"]},{\"name\":\"itemSn\",\"type\":[\"null\",\"string\"]},{\"name\":\"isbn\",\"type\":[\"null\",\"string\"]},{\"name\":\"isRelatedISBN\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"isSyncISBN\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"booklibId\",\"type\":[\"null\",\"long\"]},{\"name\":\"imgUrl\",\"type\":[\"null\",\"string\"]},{\"name\":\"bgImgUrl\",\"type\":[\"null\",\"string\"]},{\"name\":\"isBuildIndex\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"isDelete\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"certifyStatus\",\"type\":[\"null\",\"string\"]},{\"name\":\"reCertifyStatus\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"repeatMd5\",\"type\":[\"null\",\"string\"]},{\"name\":\"approach\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"isDraft\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"productArea\",\"type\":[\"null\",\"long\"]},{\"name\":\"bearShipping\",\"type\":[\"null\",\"string\"]},{\"name\":\"isUseMould\",\"type\":[\"null\",\"int\"]},{\"name\":\"mouldId\",\"type\":[\"null\",\"long\"]},{\"name\":\"weight\",\"type\":[\"null\",\"float\"]},{\"name\":\"weightPiece\",\"type\":[\"null\",\"float\"]},{\"name\":\"isPreSale\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"preSaleNum\",\"type\":[\"null\",\"int\"]},{\"name\":\"isFlashSale\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"startFlashSaleTime\",\"type\":[\"null\",\"int\"]},{\"name\":\"endFlashSaleTime\",\"type\":[\"null\",\"int\"]},{\"name\":\"limitBuyNum\",\"type\":[\"null\",\"int\"]},{\"name\":\"addTime\",\"type\":[\"null\",\"int\"]},{\"name\":\"updateTime\",\"type\":[\"null\",\"long\"]},{\"name\":\"__dmlType\",\"type\":[\"null\",\"string\"]},{\"name\":\"__dmlTs\",\"type\":[\"null\",\"long\"]},{\"name\":\"__partition\",\"type\":[\"null\",\"long\"]},{\"name\":\"__offset\",\"type\":[\"null\",\"long\"]}],\"name\":\"AutoGeneratedSchema\",\"type\":\"record\"}";

    public static void main(String[] args) throws IOException ,InterruptedException{
        List<Map<String,Object>> maps = JSON.parseObject(data,new TypeReference<List<Map<String,Object>>>(){});
        DatumWriter<GenericRecord> userDatumWriter = new GenericDatumWriter<GenericRecord>();
        Schema schema = Schema.parse(_schema);

        Map<String,Class> fieldTypeMap = generateFieldTypeMap(_schema);
        File file = new File("/tmp/2.avro");
        if(file.exists()){
            file.delete();
        }
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(userDatumWriter);
        if(file.exists()){
            dataFileWriter.appendTo(file);
        }else{
            dataFileWriter.create(schema, file);
        }
        for (Map<String,Object> map : maps){
            GenericRecord genericRecord = new GenericData.Record(schema);
            for (String key : map.keySet()){
                Object v = map.get(key);
                if(!fieldTypeMap.get(key).equals(v.getClass())){
                    if(fieldTypeMap.get(key).equals(Long.class) && v.getClass().equals(Integer.class)){
                        v = ((Integer)v).longValue();
                    }
                }
                if(v.getClass().equals(BigDecimal.class)){
                    v = ((BigDecimal)v).floatValue();
                }
                genericRecord.put(key,v);
            }
            System.out.println(genericRecord.toString());
            dataFileWriter.append(genericRecord);
            dataFileWriter.flush();
            TimeUnit.SECONDS.sleep(1);
        }
        dataFileWriter.close();



    }

    private static Map<String,Class> generateFieldTypeMap(String schema){
        Map<String,Class> fieldTypeMap =  new HashMap<>();
        AvroSchemaBean avroSchemaBean = JSON.parseObject(schema,new TypeReference<AvroSchemaBean>(){});
        List<AvroSchemaBean.Fields> fields = avroSchemaBean.getFields();
        for (AvroSchemaBean.Fields field : fields){
            List<String> type = field.getType();
            if(type.contains("string")){
                fieldTypeMap.put(field.getName(),String.class);
            }
            if(type.contains("int")){
                fieldTypeMap.put(field.getName(),Integer.class);
            }
            if(type.contains("long")){
                fieldTypeMap.put(field.getName(),Long.class);
            }
            if(type.contains("float") ){
                fieldTypeMap.put(field.getName(),Float.class);
            }
            if(type.contains("double")){
                fieldTypeMap.put(field.getName(),Double.class);
            }
            if(type.contains("boolean")){
                fieldTypeMap.put(field.getName(),Boolean.class);
            }
            if(type.contains("map")){
                fieldTypeMap.put(field.getName(),Map.class);
            }
        }
        return fieldTypeMap;
    }

}
