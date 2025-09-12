package ch.dulce.springjmsbridge.factory;

import lombok.Getter;
import lombok.Setter;
import org.apache.activemq.artemis.jms.bridge.ConnectionFactoryFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.ConnectionFactory;

@Getter
@Setter
public class ArtemisFactoryFactory implements ConnectionFactoryFactory {

  private String brokerUrl;
  private String username;
  private String password;

  @Override
  public ConnectionFactory createConnectionFactory() throws Exception {
    ActiveMQConnectionFactory factory =
        new ActiveMQConnectionFactory(brokerUrl, username, password);
    factory.setUseTopologyForLoadBalancing(false);
    return factory;
  }
}
