package com.sample.ecommerce.repository;

import com.sample.ecommerce.model.UserOrder;
import com.sample.ecommerce.model.User;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<UserOrder, Integer> {
    Iterable<UserOrder> findByUser(User user);
}
