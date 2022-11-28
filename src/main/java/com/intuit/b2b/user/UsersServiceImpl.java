package com.intuit.b2b.user;

import com.intuit.b2b.user.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepo userRepository;

    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    public List<Users> fetchUserList() {
        return (List<Users>) userRepository.findAll();
    }

    public Users fetchUserByUserId(String userId) {
        return userRepository.findUserByUserId(userId);
    }

    public List<Users> fetchUserByUserName(String userName) {
        return userRepository.findUsersStartsWithUserName(userName);
    }

    public Users updateUser(Users user, String userId) {
        Users currentUser = userRepository.findUserByUserId(userId);
        if (user.getUserName() != null && !user.getUserName().isEmpty()) {
            currentUser.setUserName(user.getUserName());
        }

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            currentUser.setEmail(user.getEmail());
        }

        if (user.getUserId() != null && !user.getUserId().isEmpty()) {
            currentUser.setUserId(user.getUserId());
        }

        return userRepository.save(currentUser);
    }

    public void deleteUserById(String userId) {
        userRepository.deleteByUserId(userId);
    }


}
