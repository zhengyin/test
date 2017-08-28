# Kafka 单 Broker , 多分区 测试

## 启动Broker
* step

1. 启动 Borker-0 
    ```
    /opt/app/kafka/bin/start-server-0.sh 
    ```

2. 创建测试 Topic
    ```
    kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3  --topic test.kafka.topic.item2
    ```

3. 查看 Topic ，确定只有一个单节点
    ```
    kafka-topics.sh --describe --zookeeper hadoop:2181 --topic test.kafka.item2
    ```
## 测试点

1. 同group , 多 consumer 当其中一个 consumer 挂掉后重连，重新接受消息的情况
    * step
        1. 启动 Producer 
        2. 启动 Consumer1 , Consumer2 , Consumer3
        3. 停止 Consumer1 
        4. 查看 Consumer2 , Consumer3 是否延续接受了后续消息，中间是否有遗漏
        5. 停止 Consumer2 
        6. 查看 Consumer3 是否延续接受了后续消息，中间是否有遗漏
    * 结论 
        1. 当只有一个 Consumer 时 ，会同时接受 3 个分区的消息,消息连续
        2. 当有两个 Consumer 时，会由一个 Consumer 去接受一个分区的消息，另一个 Consumer 同时接受其余两个分区的消息
        3. 当 Consumer 数等于分区数 (本例中为3)，会由一个 Consumer 负责去接受一个分区的消息
        4. 当 Consumer 数大于分区数(n)，只有 n 个Consumer在接受消息,其余处于等待状态，当活跃的Consumer宕机后顶上 


