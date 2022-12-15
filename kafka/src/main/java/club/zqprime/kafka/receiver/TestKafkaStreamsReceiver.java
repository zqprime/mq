package club.zqprime.kafka.receiver;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zqprime
 * @date 2022/12/8 17:08
 */
@Slf4j
@Component
public class TestKafkaStreamsReceiver {

    @KafkaListener( groupId = "zqprime",topics = {"streamTopic"})
    public void streamTopic(ConsumerRecord<String, String> consumerRecord) {
        String value = consumerRecord.value();
        log.info("streamTopic收到消息：{}",value);
    }
    @KafkaListener( groupId = "zqprime",topics = {"tableTopic"})
    public void tableTopic(ConsumerRecord<String, String> consumerRecord) {
        String value = consumerRecord.value();
        log.info("tableTopic收到消息：{}",value);
    }
    @KafkaListener( groupId = "zqprime",topics = {"orderTopic"})
    public void orderTopic(ConsumerRecord<String, String> consumerRecord) {
        String value = consumerRecord.value();
        log.info("orderTopic收到消息：{}",value);
    }
}
