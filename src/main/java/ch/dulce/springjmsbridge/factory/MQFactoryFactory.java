package ch.dulce.springjmsbridge.factory;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import lombok.Getter;
import lombok.Setter;
import org.apache.activemq.artemis.jms.bridge.ConnectionFactoryFactory;
import org.springframework.boot.ssl.SslBundle;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.util.StringUtils;

import javax.jms.ConnectionFactory;

@Getter
@Setter
public class MQFactoryFactory implements ConnectionFactoryFactory {

    private String username;
    private String password;
    private String host;
    private int port;
    private String channel;
    private String queueManager;
    private String sslCipherSuite;
    private String sslBundleName;
    private SslBundles sslBundles;

    @Override
    public ConnectionFactory createConnectionFactory() throws Exception {
        MQConnectionFactory cf = new MQConnectionFactory();
        cf.setHostName(host);
        cf.setPort(port);
        cf.setChannel(channel);
        cf.setQueueManager(queueManager);
        cf.setTransportType(WMQConstants.WMQ_CM_CLIENT);
        cf.setStringProperty(WMQConstants.USERID, username);
        cf.setStringProperty(WMQConstants.PASSWORD, password);
        if (StringUtils.hasText(sslCipherSuite)) {
            cf.setSSLCipherSuite(sslCipherSuite);
        }
        if (StringUtils.hasText(sslBundleName)) {
            SslBundle bundle = sslBundles.getBundle(sslBundleName);
            cf.setSSLSocketFactory(bundle.createSslContext().getSocketFactory());
        }
        return cf;
    }


}
