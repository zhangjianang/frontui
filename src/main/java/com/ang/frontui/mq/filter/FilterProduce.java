package com.ang.frontui.mq.filter;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

import static com.ang.frontui.mq.MyProducer.groupName;
import static com.ang.frontui.mq.MyProducer.nameAddr;

public class FilterProduce {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        // Specify name server addresses.
        producer.setNamesrvAddr(nameAddr);

        producer.start();

        for(int i=0;i<10;i++) {
            Message msg = new Message("angTopic",
                    "TagA",
                    ("filter Rocket " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
// Set some properties.

            msg.putUserProperty("a", String.valueOf(i));

            SendResult sendResult = producer.send(msg);
            System.out.println(i+" 发送结果：" + sendResult);
        }
        producer.shutdown();
    }
}
