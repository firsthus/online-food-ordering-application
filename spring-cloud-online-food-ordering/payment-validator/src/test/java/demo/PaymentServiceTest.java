package demo;


import demo.model.OrderInfo;
import demo.model.OrderStatus;
import demo.model.PaymentInfo;
import demo.service.PaymentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = PaymentValidatorApplication.class)
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RestTemplate restTemplate;
            //= new RestTemplate();

    private PaymentInfo paymentInfo;
    boolean isValid;
    private MockRestServiceServer mockServer;

    @Before
    public void setup() throws Exception{
        mockServer = MockRestServiceServer.createServer(restTemplate);
        paymentInfo = new PaymentInfo("1234123412341234","02/2012","893","orderId1");
        isValid = paymentService.validateInfo(paymentInfo);
        System.out.println("In setup isValid = " + isValid);
    }

    @Test
    public void whenProcess_expectOk() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderStatus(OrderStatus.paid);
        orderInfo.setOrderId("orderId1");
        HttpEntity<OrderInfo> entity = new HttpEntity<>(orderInfo);

        String uri = "http://localhost:9002/restaurant/order/";
        String requestUri = uri + orderInfo.getOrderId();
        System.out.println("in test requestUri is:" + requestUri);

        mockServer
                .expect(requestTo(requestUri))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.orderStatus").value("paid"))
                .andRespond(withNoContent());

        boolean isValid = paymentService.processPaymentInfo(paymentInfo);
        if(isValid) {
            Map<String, Object> uriVariables = new HashMap<> ();
            uriVariables.put("orderStatus","paid");
            // restTemplate.setMessageConverters(Arrays.<HttpMessageConverter<?>>asList(new MappingJackson2HttpMessageConverter()));
            // ResponseEntity responseEntity = restTemplate.exchange(requestUri, HttpMethod.PUT, new HttpEntity<>(orderInfo), String.class, uriVariables);
            // assertThat(HttpStatus.NO_CONTENT).isEqualTo(responseEntity.getStatusCode());
            mockServer.verify();
        }

    }
}
