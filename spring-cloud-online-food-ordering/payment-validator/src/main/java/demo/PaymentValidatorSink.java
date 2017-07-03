package demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import demo.model.PaymentInfo;
import demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
@EnableBinding(Sink.class)
@Slf4j
public class PaymentValidatorSink {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PaymentService paymentService;

    //@HystrixCommand(fallbackMethod = "updatePaymentInfoFallback")
    @ServiceActivator(inputChannel = Sink.INPUT)
    public void updatePaymentInfos(String input) throws Exception {
        PaymentInfo payload = this.objectMapper.readValue(input, PaymentInfo.class);
	    //log.info("Payload received: " + payload);
        boolean isValid = paymentService.processPaymentInfo(payload);
        log.info("Payment info processed, valid payment is" + isValid);
    }

    public void updatePaymentInfoFallback(String input){
        log.info("Unable to receive or validate paymentinfo, this is the fallback method\n" + "input is" + input );
    }
}
