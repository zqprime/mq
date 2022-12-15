package club.zqprime.kafka.listenable;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author zqprime
 * @date 2022/12/15 15:52
 */
@Component
public class BasicStreamsListenableFuture implements ListenableFutureCallback<SendResult<String, String>> {


    @Override
    public void onFailure(Throwable ex) {

    }

    @Override
    public void onSuccess(SendResult<String, String> result) {
        System.out.println("收到回调了。");
        assert result != null;
        ProducerRecord<String, String> record = result.getProducerRecord();
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.topic());
    }
}
