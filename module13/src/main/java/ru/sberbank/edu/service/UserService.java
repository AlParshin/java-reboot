package ru.sberbank.edu.service;

import ru.sberbank.edu.dao.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.edu.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService{

    @Autowired
    private final UserRepository userRepository;

    public List<UserEntity> findAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity findUserById(long id) {
        return userRepository.getById(id);
    }

    public UserEntity updateUserById(long id, String name, int age) {
        UserEntity user = userRepository.getById(id);
        user.setName(name);
        user.setAge(age);
        userRepository.save(user);
        return user;
    }

    public void add(long id, String name, int age) {
        userRepository.saveOrUpdate(id, name, age);
    }

    public void delete(long id) {
        UserEntity user = findUserById(id);
        userRepository.delete(user);
    }

}
