package com.sample.ecommerce.service;

//import com.sample.ecommerce.model.Item;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.ecommerce.model.User;
import com.sample.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    public ResponseEntity<User> findUser(String emailId){
        Optional<User> byId = userRepository.findById(emailId);
        if(byId.isPresent()) {
            return new ResponseEntity<User>(byId.get(), HttpStatus.OK);
        }
        return new ResponseEntity<User>( HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<User> createUser(User user) {
        if(!validateUserCreate(user))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(userRepository.save(user),HttpStatus.OK);
    }

    private boolean validateUserCreate(User user) {
        if(user.getEmail() == null || user.getName() == null)
            return false;
        return true;
    }
}
