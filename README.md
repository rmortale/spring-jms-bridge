# JMS Bridge example

Simple JMS 1.1 Bridge example based on
the [JMS Bridge from Apache Artemis project](https://activemq.apache.org/components/artemis/documentation/latest/jms-bridge.html#the-jms-bridge).

Using Spring XML configuration to create a JMS Bridge between IBM MQ and Apache Artemis broker.

## IBM MQ SSL configuration

It is possible to define in the `application.yaml`
file SSLBundles which can be used by the `MQConnectionFactory` to secure the connection with SSL.

We can create a private key and a self-signed certificate with just a single command:

```bash
openssl req -newkey rsa:2048 -nodes -keyout key.key -x509 -days 365 -out key.crt
```
