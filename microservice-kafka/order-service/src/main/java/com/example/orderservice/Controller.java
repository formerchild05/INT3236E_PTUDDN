package com.example.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class Controller {
    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @PostMapping("/create")
    public String createOrder() {
        Order order = new Order(123, "Nam", 250.0);

        kafkaTemplate.send("order_created", order);
        System.out.println("Order created: " + order);
        return "Order Created: " + order.getOrderId();
    }
}
