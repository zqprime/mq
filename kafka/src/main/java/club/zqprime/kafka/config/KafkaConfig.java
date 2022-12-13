package club.zqprime.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author zqprime
 * @date 2022/12/8 17:07
 */
@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("quickstart-events")
                .partitions(3)
                .replicas(2)
                .build();
    }


}
