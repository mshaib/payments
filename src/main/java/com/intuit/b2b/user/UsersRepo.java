package com.intuit.b2b.user;

import com.intuit.b2b.user.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends CrudRepository<Users, Long> {

    Users findUserByUserId(String userId);

    void deleteByUserId(String userId);

    @Query("select u from Users u where u.userName LIKE CONCAT(:username,'%')")
    List<Users> findUsersStartsWithUserName(@Param("username") String username);
}
