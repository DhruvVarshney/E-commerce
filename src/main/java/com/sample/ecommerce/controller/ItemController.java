package com.sample.ecommerce.controller;

import com.sample.ecommerce.model.Item;
import com.sample.ecommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for Item.
 *
 * Created Dhruv.
 */
@Controller
@RequestMapping(path="/item")
public class ItemController {
    @Autowired

    private ItemService itemService;

    @GetMapping(path="/{itemId}") // Map ONLY GET Requests
    public @ResponseBody
    ResponseEntity<Item> findItem(@PathVariable(value ="itemId" ) Integer itemId) {
       return itemService.findItem(itemId);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Item> createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @PostMapping(path="/{itemId}")
    public @ResponseBody ResponseEntity<Item> updateItem(@PathVariable(value = "itemId") Integer itemId,@RequestBody Item item) {
        return itemService.updateItem(itemId,item);
    }

    @DeleteMapping(path="/{itemId}")
    public @ResponseBody
    ResponseEntity<Item> deleteItem(@PathVariable(value ="itemId" ) Integer itemId) {
        return itemService.deleteItem(itemId);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Item> findAllItems(){
        return itemService.getAllItems();
    }
}