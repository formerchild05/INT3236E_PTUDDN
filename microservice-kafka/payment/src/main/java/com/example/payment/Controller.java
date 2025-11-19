package com.example.payment;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class Controller {

    @PostMapping("/pay")
    public String pay(@RequestBody Map<String, Object> body) {
        Integer orderId = (Integer) body.get("orderId");
        return "Payment successful for order " + orderId;
    }
}
