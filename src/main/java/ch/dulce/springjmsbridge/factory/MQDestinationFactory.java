package ch.dulce.springjmsbridge.factory;

import com.ibm.mq.jms.MQQueue;
import lombok.Getter;
import lombok.Setter;
import org.apache.activemq.artemis.jms.bridge.DestinationFactory;

import javax.jms.Destination;

@Setter
@Getter
public class MQDestinationFactory implements DestinationFactory {

    private String destinationName;

    @Override
    public Destination createDestination() throws Exception {
        return new MQQueue(destinationName);
    }
}
