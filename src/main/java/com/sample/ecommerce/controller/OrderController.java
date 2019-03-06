package com.sample.ecommerce.controller;

import com.sample.ecommerce.model.UserOrder;
import com.sample.ecommerce.service.OrderService;
import com.sample.request.PlaceOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/order")
public class OrderController {
    @Autowired private OrderService orderService;

    @PostMapping(path = "/place")
    public @ResponseBody
    ResponseEntity<UserOrder> placeOrder(@RequestBody PlaceOrderRequest request) {
        return orderService.placeOrder(request);
    }

    @GetMapping(path = "/emailId/{emailId}")
    public @ResponseBody
    ResponseEntity<Iterable<UserOrder>> getOrders(@PathVariable(value = "emailId") String emailId) {
        return orderService.getOrders(emailId);
    }
}
