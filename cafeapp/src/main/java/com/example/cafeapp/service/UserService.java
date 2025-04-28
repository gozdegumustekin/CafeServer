package com.example.cafeapp.service;

import com.example.cafeapp.model.User;
import com.example.cafeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor injection (tavsiye edilen yöntem)
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Kullanıcı kaydı
    public User registerUser(User user) {
        // Aynı telefon numarasıyla daha önce kayıt yapılmış mı?
        if (userRepository.findByPhone(user.getPhone()).isPresent()) {
            throw new RuntimeException("Bu telefon numarasıyla zaten bir kullanıcı var.");
        }

        // (Gelişmiş projelerde burada şifre hashing yapılmalı)
        return userRepository.save(user);
    }

    // Kullanıcı girişi
    public User loginUser(String phone, String password) {
        Optional<User> userOpt = userRepository.findByPhone(phone);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Telefon numarası bulunamadı.");
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Şifre yanlış.");
        }

        return user;
    }
}
