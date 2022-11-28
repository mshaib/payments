package com.intuit.b2b.account;

import com.intuit.b2b.account.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> fetchAccountList() {
        return (List<Account>) accountRepository.findAll();
    }

    public Account fetchAccountByAccountId(String accountId) {
        return accountRepository.findAccountByAccountId(accountId);
    }

    public Account updateAccount(Account account, String accountId) {
        Account currentAccount = accountRepository.findAccountByAccountId(accountId);
        if (account.getAccountName() != null && !account.getAccountName().isEmpty()) {
            currentAccount.setAccountName(account.getAccountName());
        }

        if (account.getAccountNumber() != null && !account.getAccountNumber().isEmpty()) {
            currentAccount.setAccountNumber(account.getAccountNumber());
        }

        if (account.getAccountId() != null && !account.getAccountId().isEmpty()) {
            currentAccount.setAccountId(account.getAccountId());
        }

        return accountRepository.save(currentAccount);
    }

    public void deleteAccountById(String accountId) {
        accountRepository.deleteByAccountId(accountId);
    }
}

