# Embedded Kafka Runner

Simple app that runs Kafka locally using spring-kafka-test.

## Why?

* Configuration-free
* Kafka downloaded through Maven
* Useful in restricted environments without Docker or alternatives

## How to run

```sh
./run-kafka.sh
```
This will start Kafka on port 9092 with a topic `topic`.

To change the port or topic, use the environment variables `KAFKA_PORT` and `KAFKA_TOPIC`:
```sh
KAFKA_PORT=12345 KAFKA_TOPIC=test-topic ./run-kafka.sh
```

## How to stop

Press Ctrl+C.