package com.intuit.b2b.user;

import com.intuit.b2b.user.entity.UserAccounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountsRepo extends CrudRepository<UserAccounts, Long> {
    List<UserAccounts> findUserAccountsByUserId(String userId);
}
