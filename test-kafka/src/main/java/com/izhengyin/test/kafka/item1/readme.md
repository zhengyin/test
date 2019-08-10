# Kafka 单 Broker ,单分区 测试

## 启动Broker
* step

1. 启动 Borker-0 
    ```
    /opt/app/kafka/bin/start-server-0.sh 
    ```

2. 创建测试 Topic
    ```
    kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1  --topic test.kafka.topic.item1
    ```

3. 查看 Topic ，确定只有一个单节点
    ```
    kafka-topics.sh --describe --zookeeper hadoop:2181 --topic test.kafka.item1
    ```
## 测试点

1. 单 consumer 挂掉后重连，重新接受消息的情况
    * step
        1. 启动 Producer 
        2. 启动 Consumer  
        3. 停止 Consumer 记录最后一条消息的offset
        4. 重启 Consumer 比对offset
    * 结论 : 消息可以在宕机处从新接受，期间可能产生从复消息，取决于 COMMIT 最后一条消息的 offset ，如设置 Auto Commit 从复消息产生取决于最短消息频率与Interval时间
    
2. 同group , 多 consumer 当其中一个 consumer 挂掉后重连，重新接受消息的情况
    * step
        1. 启动 Producer 
        2. 启动 Consumer1 , Consumer2   
        3. 停止 Consumer1 记录最后一条消息的offset
        4. 查看 Consumer2 比对offset
    * 结论 : 同上
    
3. 不同group , 多 consumer 当其中一个 consumer 挂掉后重连，重新接受消息的情况
    * step
        1. 启动 Producer 
        2. 启动 Consumer1 , Consumer2   
        3. 停止 Consumer1 记录最后一条消息的offset
        4. 重启 Consumer1 比对offset 
    * 不同group,多个 Consumer 会各种维护一个 offset ,相互间不受干扰 


