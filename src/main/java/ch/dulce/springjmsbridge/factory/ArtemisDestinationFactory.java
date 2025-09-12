package ch.dulce.springjmsbridge.factory;

import lombok.Getter;
import lombok.Setter;
import org.apache.activemq.artemis.jms.bridge.DestinationFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;

import javax.jms.Destination;

@Getter
@Setter
public class ArtemisDestinationFactory implements DestinationFactory {

    private String destinationName;

    @Override
    public Destination createDestination() throws Exception {
        return new ActiveMQQueue(destinationName);
    }
}
