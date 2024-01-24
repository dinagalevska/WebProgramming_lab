//package mk.finki.ukim.mk.lab.service.impl;
//
//import mk.finki.ukim.mk.lab.model.User;
//import mk.finki.ukim.mk.lab.model.exceptions.UserNotFoundException;
//import mk.finki.ukim.mk.lab.repository.jpa.UserRepositoryJpa;
//import mk.finki.ukim.mk.lab.service.UserService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class UserServiceImpl implements UserService {
//    private final UserRepositoryJpa userRepositoryJpa;
//
//    public UserServiceImpl(UserRepositoryJpa userRepositoryJpa) {
//        this.userRepositoryJpa = userRepositoryJpa;
//    }
//
//    @Override
//    public Set<String> getAllUsernames() {
//        return userRepositoryJpa.findAll().stream()
//                .map(User::getUsername)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public List<User> findAll() {
//        return userRepositoryJpa.findAll();
//    }
//
//    @Override
//    public User findById(Long userId) {
//        return userRepositoryJpa.findById(userId).orElseThrow(UserNotFoundException::new);
//    }
//}
