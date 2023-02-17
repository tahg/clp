package com.revature.ecommerce.services;

import com.revature.ecommerce.exceptions.ForbiddenException;
import com.revature.ecommerce.exceptions.NetworkException;
import com.revature.ecommerce.exceptions.ResourceConflictException;
import com.revature.ecommerce.models.User;
import com.revature.ecommerce.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
//@AllArgsConstructor
public class UserService {
    private static UserRepository users;
    public UserService(UserRepository userRepository) {
        users = userRepository;
    }
    public static String signup(User user) {
        try {
            users.save(user);
        } catch (DataAccessException dae) {
            throw new ResourceConflictException(dae.getMessage());
//            throw new ResourceConflictException("User already exists.");
        }
        return "";
    }
    
    public static String login(User user) throws NetworkException {
        List<User> found = users.findByName(user.getName());
        if (!found.isEmpty()) {
            User userRecord = found.get(0);
            if (user.getPassword() != null && user.getPassword().equals(userRecord.getPassword())) return TokenService.generateToken(user.getName());
        }
        throw new ForbiddenException("Please check your credentials and try again."); 
    }
    
    public static User getUserByUsername(String name) {
        List<User> found = users.findByName(name);
        return found.isEmpty() ? null : found.get(0);
    }
}
