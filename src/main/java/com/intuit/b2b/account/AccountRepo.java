package com.intuit.b2b.account;

import com.intuit.b2b.account.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends CrudRepository<Account, Long> {
    Account findAccountByAccountId(String accountId);

    void deleteByAccountId(String accountId);
}
