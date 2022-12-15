package club.zqprime.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zqprime
 * @date 2022/12/14 17:18
 */
@Configuration
@EnableKafkaStreams
public class StreamConfig {

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>(5);
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "study-test");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "162.14.97.85:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class);
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);
        return new KafkaStreamsConfiguration(props);
    }


    @Bean
    public NewTopic streamTopic() {
        return TopicBuilder.name("streamTopic")
                .partitions(3)
                .replicas(2)
                .build();
    }

    @Bean
    public NewTopic tableTopic() {
        return TopicBuilder.name("tableTopic")
                .partitions(3)
                .replicas(2)
                .build();
    }
    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder.name("orderTopic")
                .partitions(3)
                .replicas(2)
                .build();
    }

    @Bean
    @DependsOn({"streamTopic","tableTopic","orderTopic"})
    public KStream<String, String> kStream(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder.stream("streamTopic");
//        stream.map(KeyValue::new).to("tableTopic", Produced.with(Serdes.String(), new JsonSerde<>()));
        stream.to("tableTopic");
//        stream.filter((k, v) -> {
//            BigDecimal orderAmt = v.getOrderAmt();
//            return orderAmt.compareTo(new BigDecimal(1)) < 0;
//        }).to("orderTopic", Produced.with(Serdes.String(), new JsonSerde<>()));
        return stream;
    }
}