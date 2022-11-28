package com.intuit.b2b.user;

import com.intuit.b2b.user.entity.UserAccounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAccountsServiceImpl implements UserAccountsService {
    @Autowired
    private UserAccountsRepo userAccountsRepository;

    public List<String> findAccountsByUserId(String userId) {
        List<UserAccounts> userAccountsList = userAccountsRepository.findUserAccountsByUserId(userId);

        return userAccountsList
                .stream().sequential()
                .map((userAccount) ->
                        userAccount.getAccountId()
                )
                .collect(Collectors.toList());
    }
}
