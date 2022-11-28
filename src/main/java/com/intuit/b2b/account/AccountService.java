package com.intuit.b2b.account;

import com.intuit.b2b.account.entity.Account;

import java.util.List;

public interface AccountService {

    Account createAccount(Account account);

    List<Account> fetchAccountList();

    Account fetchAccountByAccountId(String accountId);

    Account updateAccount(Account account, String accountId);

    void deleteAccountById(String accountId);
}
