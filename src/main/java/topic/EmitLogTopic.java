package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class EmitLogTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv)                   throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        Message message = new Message();
        message.level = "kern.toto";
        message.content = "Run. Run. Or it will explode";

        channel.basicPublish(EXCHANGE_NAME,  message.level, null, message.content.getBytes());
        System.out.println(" [x] Sent '" +  message.level + "':'" + message.content + "'");

        connection.close();
    }

    public static final class Message {
        private String level;
        private String content;
    }

}