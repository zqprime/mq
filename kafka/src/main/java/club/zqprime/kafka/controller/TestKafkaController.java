package club.zqprime.kafka.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zqprime
 * @date 2022/12/8 17:11
 */
@RestController
@RequestMapping(value = "/kafka")
public class TestKafkaController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping(value = "/send/{topic}/{key}/{data}")
    public Object send(@PathVariable(value = "topic") String topic, @PathVariable(value = "key") String key,@PathVariable(value = "data") String data){
        kafkaTemplate.send(topic,key,data);
        return "SUCCESS";
    }
}
