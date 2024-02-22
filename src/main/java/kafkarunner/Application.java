package kafkarunner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.EmbeddedKafkaZKBroker;

@SpringBootApplication
public class Application implements CommandLineRunner, ApplicationListener<ApplicationStartedEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Environment environment;

    @Value("${kafka.topic:topic}")
    String kafkaTopic;
    @Value("${kafka.port:9092}")
    int kafkaPort;

    public Application(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public EmbeddedKafkaBroker embeddedKafkaBroker() {
        EmbeddedKafkaZKBroker embeddedKafkaZKBroker = new EmbeddedKafkaZKBroker(1, false, kafkaTopic);
        embeddedKafkaZKBroker.kafkaPorts(kafkaPort);
        return embeddedKafkaZKBroker;
    }

    @Override
    public void run(String... args) throws Exception {
        Thread.currentThread().join();
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        String kafkaBootstrapServers = environment.getProperty("spring.kafka.bootstrap-servers");
        logger.info("Kafka started on {} with topic: {}", kafkaBootstrapServers, kafkaTopic);
    }
}
