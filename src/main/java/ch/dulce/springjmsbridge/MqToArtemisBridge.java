package ch.dulce.springjmsbridge;

import ch.dulce.springjmsbridge.factory.ArtemisDestinationFactory;
import ch.dulce.springjmsbridge.factory.ArtemisFactoryFactory;
import ch.dulce.springjmsbridge.factory.MQDestinationFactory;
import ch.dulce.springjmsbridge.factory.MQFactoryFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.jms.bridge.QualityOfServiceMode;
import org.apache.activemq.artemis.jms.bridge.impl.JMSBridgeImpl;

@Slf4j
@Getter
@Setter
@ToString
public class MqToArtemisBridge {

    private MQFactoryFactory sourceFactory;
    private ArtemisFactoryFactory targetFactory;
    private String sourceQueueName;
    private String targetQueueName;
    private String bridgeName;
    private int maxRetries = -1;
    private long failureRetryInterval = 5000;
    private int maxBatchSize = 100;
    private long maxBatchWaitTime = 5000;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private JMSBridgeImpl jmsBridge;

    public void start() throws Exception {
        createBridge();
        log.info("Starting MqToArtemisBridge {}", this);
        jmsBridge.start();
    }

    public void stop() throws Exception {
        log.info("Stoping MqToArtemisBridge");
        jmsBridge.stop();
    }

    private void createBridge() {
        jmsBridge = new JMSBridgeImpl();
        jmsBridge.setBridgeName(bridgeName);
        jmsBridge.setSourceConnectionFactoryFactory(sourceFactory);
        jmsBridge.setTargetConnectionFactoryFactory(targetFactory);
        MQDestinationFactory sf = new MQDestinationFactory();
        sf.setDestinationName(sourceQueueName);
        jmsBridge.setSourceDestinationFactory(sf);
        ArtemisDestinationFactory df = new ArtemisDestinationFactory();
        df.setDestinationName(targetQueueName);
        jmsBridge.setTargetDestinationFactory(df);
        jmsBridge.setFailureRetryInterval(failureRetryInterval);
        jmsBridge.setMaxBatchSize(maxBatchSize);
        jmsBridge.setMaxBatchTime(maxBatchWaitTime);
        jmsBridge.setMaxRetries(maxRetries);
        jmsBridge.setQualityOfServiceMode(QualityOfServiceMode.DUPLICATES_OK);
    }
}
