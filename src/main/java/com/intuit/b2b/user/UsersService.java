package com.intuit.b2b.user;

import com.intuit.b2b.user.entity.Users;

import java.util.List;

public interface UsersService {

    Users createUser(Users user);

    List<Users> fetchUserList();

    Users fetchUserByUserId(String userId);

    List<Users> fetchUserByUserName(String userName);

    Users updateUser(Users user, String userId);

    void deleteUserById(String userId);


}
