package com.sample.ecommerce.service;

import com.sample.ecommerce.model.Item;
import com.sample.ecommerce.model.UserOrder;
import com.sample.ecommerce.model.OrderDetails;
import com.sample.ecommerce.model.User;
import com.sample.ecommerce.repository.ItemRepo;
import com.sample.ecommerce.repository.OrderRepo;
import com.sample.ecommerce.repository.UserRepo;
import com.sample.request.PlaceOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    @Autowired private ItemRepo itemRepo;
    @Autowired private UserRepo userRepo;
    @Autowired private OrderRepo orderRepo;


    public ResponseEntity<UserOrder> placeOrder(PlaceOrderRequest request){

        if(request.getEmailId() == null || request.getItemIdAndQuantity().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = userRepo.findById(request.getEmailId());
        if(!user.isPresent()){
            System.out.println("User not found ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserOrder userOrder = new UserOrder();
        userOrder.setUser(user.get());
        userOrder.setAmount(request.getAmount());
        userOrder.setDate(new Date());
        List<OrderDetails> orderDetailList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : request.getItemIdAndQuantity().entrySet()) {
            Integer itemId = entry.getKey();
            Integer quantity = entry.getValue();
            Optional<Item> item = itemRepo.findById(itemId);
            if(!item.isPresent() || item.get().getQuantity() < quantity){
                System.out.println("Item not found or quantity not available ");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            OrderDetails details = new OrderDetails();
            details.setItem(item.get());
            details.setQuantity(quantity);
            details.setUserOrder(userOrder);
            orderDetailList.add(details);
        }
        userOrder.setOrderDetails(orderDetailList);
        orderRepo.save(userOrder);

        return new ResponseEntity<>(userOrder,HttpStatus.OK);
    }

    public ResponseEntity<Iterable<UserOrder>> getOrders(String emailId) {
        Optional<User> user = userRepo.findById(emailId);
        if(!user.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(orderRepo.findByUser(user.get()),HttpStatus.OK);
    }
}
