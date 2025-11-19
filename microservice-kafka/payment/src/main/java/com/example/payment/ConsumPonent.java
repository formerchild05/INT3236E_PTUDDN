package com.example.payment;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumPonent {

    @KafkaListener(topics = "order_created", groupId = "payment-group")
    public void listen(Order order) {
        System.out.println("PaymentService received order: " + order);

        // xử lý thanh toán
        System.out.println("Processing payment for orderId=" + order.getOrderId() +
                ", user=" + order.getUser() +
                ", amount=" + order.getAmount());
    }

}
