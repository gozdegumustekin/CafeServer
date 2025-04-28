package com.example.cafeapp.controller;

import com.example.cafeapp.model.User;
import com.example.cafeapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Flutter'dan gelen isteklere izin verir (güvenlik için geliştirilebilir)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // KAYIT İSTEĞİ
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // GİRİŞ İSTEĞİ
    @PostMapping("/login")
    public User loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest.getPhone(), loginRequest.getPassword());
    }

    // Giriş için ayrı bir sınıf: sadece phone & password alıyoruz
    public static class LoginRequest {
        private String phone;
        private String password;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
