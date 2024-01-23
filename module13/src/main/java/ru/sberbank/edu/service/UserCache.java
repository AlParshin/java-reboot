package ru.sberbank.edu.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserCache {
    private Map<String, UserInfo> cache = new HashMap<>();

    public UserInfo get(String id) {
        return cache.get(id);
    }

    private void create(UserInfo info) {
        cache.put(info.getLogin(), info);

    }

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {
        create(new UserInfo("admin", passwordEncoder.encode("123456"), "Одмен", "ADMIN"));
    }
}

