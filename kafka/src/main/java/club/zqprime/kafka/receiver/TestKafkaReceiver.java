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
public class TestKafkaReceiver {
    @KafkaListener( groupId = "zqprime",topics = {"quickstart-events"})
    public void listen(ConsumerRecord<String, String> consumerRecord) {
        String value = consumerRecord.value();
        log.info("收到消息：{}",value);
    }
}
