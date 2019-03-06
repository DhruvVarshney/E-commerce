package com.sample.ecommerce.repository;

import com.sample.ecommerce.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called itemRepository
// CRUD refers Create, Read, Update, Delete

public interface ItemRepo extends CrudRepository<Item, Integer> {

    Iterable<Item> findByCategory(String category);

}