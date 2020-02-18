package ru.geekbrains.services;

import ru.geekbrains.entity.User;
import ru.geekbrains.entity.UserDto;
import ru.geekbrains.repository.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserService {

    @EJB
    private UserRepository userRepository;

    @TransactionAttribute
    public void merge(UserDto userDto) {
        User merged = userRepository.merge(new User(userDto));
        userDto.setId(merged.getId());
    }

    @TransactionAttribute
    public void delete(int id) {
        userRepository.delete(id);
    }

    @TransactionAttribute
    public UserDto findById(int id) {
        return new UserDto(userRepository.findById(id));
    }

    @TransactionAttribute
    public boolean existsById(int id) {
        return userRepository.findById(id) != null;
    }

    @TransactionAttribute
    public List<UserDto> getAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

}
