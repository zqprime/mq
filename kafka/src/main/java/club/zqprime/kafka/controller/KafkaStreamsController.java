package club.zqprime.kafka.controller;

import club.zqprime.kafka.listenable.BasicStreamsListenableFuture;
import club.zqprime.kafka.qo.StreamQo.BasicStreamQo;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zqprime
 * @date 2022/12/15 10:13
 */
@RestController
@RequestMapping("/kafka/streams")
public class KafkaStreamsController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;
    @Resource
    private BasicStreamsListenableFuture basicStreamsListenableFuture;

    @PostMapping(value = "/testKafkaStreams")
    public void testKafkaStreams(@RequestBody BasicStreamQo basicStreamQo){
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(basicStreamQo.getTopic(), basicStreamQo.getKey(), basicStreamQo.getData());
        send.addCallback(basicStreamsListenableFuture);
    }
}
