package com.revature.ecommerce.controllers;

import com.revature.ecommerce.exceptions.NetworkException;
import com.revature.ecommerce.models.User;
import com.revature.ecommerce.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {
    
    @CrossOrigin
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String signup(@RequestBody User request, HttpServletResponse resp) {
        try {
            return UserService.signup(request);
        }  catch (NetworkException e) {
            resp.setStatus(e.getStatusCode());
            return e.getMessage();
        }
    }

    @CrossOrigin
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String login(@RequestBody User request, HttpServletResponse resp) {
        try {
            String token = UserService.login(request);
            resp.setHeader("Authorization", token);
            resp.setHeader("Access-Control-Expose-Headers", "Authorization");
            return "";
        } catch (NetworkException e) {
            resp.setStatus(e.getStatusCode());
            return e.getMessage();
        }
    }
}
