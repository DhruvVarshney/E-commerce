package com.sample.ecommerce.service;

import com.sample.ecommerce.model.Item;
import com.sample.ecommerce.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepository;

    public ResponseEntity<Item> findItem(Integer itemId){
        Optional<Item> byId = itemRepository.findById(itemId);
        if(byId.isPresent()) {
            return new ResponseEntity<Item>(byId.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Item>( HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Item> createItem(Item item) {
        if(!validateItemCreate(item))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(item.getSellingPrice() == null) item.setSellingPrice(item.getSellingPrice());
        return new ResponseEntity<>(itemRepository.save(item),HttpStatus.OK);
    }

    public Iterable<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Iterable<Item> getItemByCategory(String category){
        return itemRepository.findByCategory(category);
    }
    public ResponseEntity<Item> deleteItem(Integer id){
        Optional<Item> byId = itemRepository.findById(id);
        if(!byId.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        itemRepository.delete(byId.get());
        return new ResponseEntity<>(byId.get(),HttpStatus.OK);
    }

    private boolean validateItemCreate(Item item){
        if(item.getId() != null) return false;
        if(item.getName() == null || item.getImage() == null) return false;
        if(item.getQuantity() == null || item.getQuantity() <0 ) return false;
        if(item.getMrp() == null || item.getMrp() < 0 ) return false;
        return true;
    }

    public ResponseEntity<Item> updateItem(Integer id, Item item) {
        Optional<Item> foundItem = itemRepository.findById(id);
        if(!foundItem.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item updateItem = foundItem.get();
        if(item.getName() != null)
            updateItem.setName(item.getName());
        if(item.getImage() != null)
            updateItem.setImage(item.getImage());
        if(item.getDetails() != null)
            updateItem.setDetails(item.getDetails());
        if(item.getQuantity() != null ){
            if(item.getQuantity() < 0 )return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            updateItem.setQuantity(item.getQuantity());
        }
        if(item.getCategory() != null){
            updateItem.setCategory(item.getCategory());
        }
        if(item.getSeller() != null){
            updateItem.setSeller(item.getSeller());
        }
        if(item.getSellingPrice() != null){
            if(item.getSellingPrice() < 0 )return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            updateItem.setSellingPrice(item.getSellingPrice());
        }
        if(item.getMrp() != null){
            if(item.getMrp() < 0 )return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            updateItem.setMrp(item.getMrp());
        }
        if(updateItem.getSellingPrice() > updateItem.getMrp()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        itemRepository.save(updateItem);
        return new ResponseEntity<>(updateItem,HttpStatus.OK);
    }
}
