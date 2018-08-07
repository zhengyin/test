# Kafka 多个 Broker , 多复制测试，多分区测试

## 启动Broker
* step

1. 启动 Brokers
    ```
    /opt/app/kafka/bin/start-server.sh
    ```

2. 创建测试 Topic , 1 个 replication factor
    ```
    kafka-topics.sh --create --zookeeper hadoop:2181 --replication-factor 3 --partitions 8  --topic test.kafka.item4
    ```

3. 查看 Topic ，确定分区与复制因子的数量
    ```
    kafka-topics.sh --describe --zookeeper hadoop:2181 --topic test.kafka.item4
    ```
    ```
    Topic:test.kafka.item4	PartitionCount:8	ReplicationFactor:3	Configs:
        Topic: test.kafka.item4	Partition: 0	Leader: 0	Replicas: 0,1,2	Isr: 0,1,2
        Topic: test.kafka.item4	Partition: 1	Leader: 1	Replicas: 1,2,3	Isr: 1,2,3
        Topic: test.kafka.item4	Partition: 2	Leader: 2	Replicas: 2,3,4	Isr: 2,3,4
        Topic: test.kafka.item4	Partition: 3	Leader: 3	Replicas: 3,4,0	Isr: 3,4,0
        Topic: test.kafka.item4	Partition: 4	Leader: 4	Replicas: 4,0,1	Isr: 4,0,1
        Topic: test.kafka.item4	Partition: 5	Leader: 0	Replicas: 0,2,3	Isr: 0,2,3
        Topic: test.kafka.item4	Partition: 6	Leader: 1	Replicas: 1,3,4	Isr: 1,3,4
        Topic: test.kafka.item4	Partition: 7	Leader: 2	Replicas: 2,4,0	Isr: 2,4,0
    ```
## 测试点

1. 当Broker宕机后程序的表现
    * step
        1. 启动 Producer
        2. 启动 Consumer
        3. 停止 Broker1 
        4. 查看 Producer 与 Consumer 
        5. 启动 Broker 
        6. 查看 Producer 与 Consumer 
        7. 同时停止 Broker1 , Broker2
        8. 查看 Producer 与 Consumer 
        9. 同时停止 Broker1 , Broker2 , Broker3
        10. 恢复其中任意一个 Broker 
        11. 查看 Producer 与 Consumer 
    * 结论 
        1. 当 Broker 宕机时，Producer 与 Consumer 会切换到其余可用的Broker,消息读写正常. 切换取决于 [ISR] 中follower的ID 
        2. 当 Broker 宕机数超过了 replication-factor 中设置数量时 ，Producer 与 Consumer 无法工作
        3. 当宕机的 Broker 恢复时，之前失败的Producer 与 Consumer 消息读写恢复