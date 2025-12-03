package ch.dulce.springjmsbridge.factory;

import com.ibm.mq.jms.MQQueue;
import com.ibm.msg.client.wmq.WMQConstants;

import lombok.Getter;
import lombok.Setter;
import org.apache.activemq.artemis.jms.bridge.DestinationFactory;

import javax.jms.Destination;

@Setter
@Getter
public class MQDestinationFactory implements DestinationFactory {

    private String destinationName;
    private int targetClient = WMQConstants.WMQ_CLIENT_NONJMS_MQ;

    @Override
    public Destination createDestination() throws Exception {
        MQQueue queue = new MQQueue(destinationName);
        queue.setTargetClient(targetClient);
        return queue;
    }
}
