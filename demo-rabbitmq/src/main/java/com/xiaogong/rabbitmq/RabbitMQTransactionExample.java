package com.xiaogong.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-17
 */
public class RabbitMQTransactionExample {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // 启用事务
            channel.txSelect();

            String exchangeName = "my_exchange";
            String routingKey = "my_routing_key";

            try {
                // 发送第一条消息
                String message1 = "Transaction Message 1";
                channel.basicPublish(exchangeName, routingKey, null, message1.getBytes());

                // 发送第二条消息
                String message2 = "Transaction Message 2";
                channel.basicPublish(exchangeName, routingKey, null, message2.getBytes());

                // 模拟一个错误
                // int x = 1 / 0;

                // 提交事务（如果没有发生错误）
                channel.txCommit();

                System.out.println("Transaction committed.");
            } catch (Exception e) {
                // 发生错误，回滚事务
                channel.txRollback();
                System.err.println("Transaction rolled back.");
            }
        }
    }

}
