# JMS Bridge example

Simple JMS 1.1 Bridge example based on
the [JMS Bridge from Apache Artemis project](https://activemq.apache.org/components/artemis/documentation/latest/jms-bridge.html#the-jms-bridge).

Using Spring XML configuration to create a JMS Bridge between IBM MQ and Apache Artemis broker.

The configuration is done in the config directory. In this manner when building a Docker image
we can easily override the configuration files without the need to rebuild the image.
The config directory contains the following files:

- `application.yaml` - Spring Boot application configuration file
- `factories.xml` - Spring XML configuration file defining the JMS connection factories
- `bridge.xml` - Spring XML configuration file defining the JMS Bridge

## IBM MQ SSL configuration

It is possible to define in the `application.yaml`
file SSLBundles which can be used by the `MQConnectionFactory` to secure the connection with SSL.

We can create a private key and a self-signed certificate with just a single command:

```bash
openssl req -newkey rsa:2048 -nodes -keyout key.key -x509 -days 365 -out key.crt
```

To run the IBM MQ server with SSL enabled we can use the following `docker run` command:

```shell
docker run --name mqtls --env LICENSE=accept \
    --env MQ_QMGR_NAME=QM1 \
    --env MQ_APP_PASSWORD=passw0rd \
    --env MQ_ADMIN_PASSWORD=passw0rd \
    --volume /local/dir/with/above-created/ssl-keys:/etc/mqm/pki/keys/mykey \
    --publish 1415:1414 \
    --publish 9444:9443 \
    --detach \
    icr.io/ibm-messaging/mq:latest
```
Put the created openssl keys `key.key` and `key.crt` into the `/local/dir/with/above-created/ssl-keys` directory.

Next import the self-signed certificate into a Java KeyStore file:

```bash
keytool -keystore clientkey.jks -storetype jks -importcert -file key.crt -alias server-certificate
```

Use the created `clientkey.jks` truststore file in the `application.yaml` configuration
to enable SSL connection to IBM MQ server.
