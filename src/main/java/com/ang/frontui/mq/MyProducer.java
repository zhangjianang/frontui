package com.ang.frontui.mq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class MyProducer {
    public static String groupName = "ang_sync";
    private static String asyncGroupName = "ang_async";
    public static String nameAddr = "www.zja.com:9876";
    public static String sendTopic = "angTopic";
    public static String asyncTopic = "angAsyncTopic";
    private static String sendTag = "TagA";

    public static void main(String[] args) throws Exception {
//        syncProduce();
        asyncProduce();
//        oneway();
    }

    public static void syncProduce() throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer(groupName);
        // Specify name server addresses.
        producer.setNamesrvAddr(nameAddr);
        //Launch the instance.
        producer.start();
        for (int i = 100; i < 120; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message(sendTopic,
                    sendTag,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

    public static void asyncProduce() throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer(asyncGroupName);
        // Specify name server addresses.
        producer.setNamesrvAddr(nameAddr);
        //Launch the instance.
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(2);
        int messageCount =20;
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);

        for (int i = 0; i < messageCount; i++) {
            final int index = i;
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message(asyncTopic,
                    sendTag,
                    "OrderID_188",
                    ("Hello world"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d OK %s %n", index,
                            sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await(5, TimeUnit.SECONDS);
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }


    public static void oneway() throws Exception{
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        // Specify name server addresses.
        producer.setNamesrvAddr(nameAddr);
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 10; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message(sendTopic,
                    sendTag,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            producer.sendOneway(msg);

        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
