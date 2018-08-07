# Kafka 多个 Broker , 单复制测试

## 启动Broker
* step

1. 启动 Brokers
    ```
    /opt/app/kafka/bin/start-server.sh
    ```

2. 创建测试 Topic , 1 个 replication factor
    ```
    kafka-topics.sh --create --zookeeper hadoop:2181 --replication-factor 1 --partitions 3  --topic test.kafka.item3
    ```

3. 查看 Topic ，确定只有一个负责3个分区
    ```
    kafka-topics.sh --describe --zookeeper hadoop:2181 --topic test.kafka.item3
    ```
    ```
    Topic:test.kafka.item3	PartitionCount:3	ReplicationFactor:1	Configs:
       	Topic: test.kafka.item3	Partition: 0	Leader: 2	Replicas: 2	Isr: 2
       	Topic: test.kafka.item3	Partition: 1	Leader: 0	Replicas: 0	Isr: 0
       	Topic: test.kafka.item3	Partition: 2	Leader: 1	Replicas: 1	Isr: 1
    ```
## 测试点

1. 多个Producer同时写入，多个Consumer订阅,当Broker宕机后程序的表现
    * step
        1. 启动 Producer1,Producer2,Producer3, 
        2. 启动 Consumer1 , Consumer2 , Consumer3
        3. 停止 Broker1 
        4. 查看 Producer 与 Consumer 
        5. 启动 Broker 
        6. 查看 Producer 与 Consumer 
    * 结论 
        1. 当 Broker 宕机时，Producer 向该 Broker发送的消息将失败，向其它Broker发送的消息正常。 并且，Producer 会持续的向失败的Broker发送消息，不会故障转移
        2. 当 Broker 宕机时，Consumer 无法接受到消息 ，宕机 Broker 所在数据分区的消息，因为没有 Replica
        3. 当宕机的 Broker 恢复时，之前失败的Producer 发送消息恢复正常
        4. 当宕机的 Broker 恢复时，Consumer 接受消息正常，且从各分区上次提交的 offset 拉取消息,Producer发送成功的消息，Consumer 可以正确的接受,无遗漏
        