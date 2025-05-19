package com.example.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User create(User user, String requesterRole) {
        if ("ADMIN".equals(requesterRole)) {
            return userRepository.save(user);
        }

        if (requesterRole == null || requesterRole.isBlank()) {
            user.setRole(com.example.userservice.Role.USER);
            return userRepository.save(user);
        }
        throw new RuntimeException("Недостаточно прав для создания пользователя");
    }

    public void delete(Long id, String requesterRole, Long requesterId) {
        if ("ADMIN".equals(requesterRole)) {
            userRepository.deleteById(id);
        } else if ("USER".equals(requesterRole) && requesterId != null && id.equals(requesterId)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Недостаточно прав для удаления");
        }
    }

    public User update(Long id, User userData) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(userData.getName());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setRole(userData.getRole());
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        return userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Неверный email или пароль"));
    }
}