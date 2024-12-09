package br.com.user.productsore.usersApi.service;

import br.com.user.productsore.usersApi.domain.user.User;
import br.com.user.productsore.usersApi.exception.UserNotFoundException;
import br.com.user.productsore.usersApi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User get(UUID id) {
        return findUserById(id);
    }

    public void delete(UUID id) {
        User user = findUserById(id);

        userRepository.delete(user);
    }

    public User findUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
