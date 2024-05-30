package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;


@Controller
public class RegistrationController {


    @Autowired
    public RegistrationController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/reg")
    public String registration() {
        logger.info("Сайт регистрации посещен");
        return "registration";
    }

    @PostMapping("/reg")
    public String addUser(User user, Map<String, Object> model) {
        logger.info("Пост");
        User userFromDb = userRepo.findByUsername(user.getUsername());
        logger.info("Проверка существования {}", userFromDb);
        if (userFromDb != null) {
            logger.info("Существует");
            model.put("message", "User exists!");
            return "registration";
        }
        logger.info("Не существует");
        user.setRoles(Collections.singleton(Role.USER));
        logger.info("Роль установлена");
        user.setActive(true);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        logger.info("Добавлен");
        return "redirect:/login";
    }
}