package club.zqprime.kafka.qo.StreamQo;

import lombok.Data;

/**
 * @author zqprime
 * @date 2022/12/15 15:50
 */
@Data
public class BasicStreamQo {
    private String topic;
    private String key;
    private String data;
}
