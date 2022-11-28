package com.intuit.b2b.user;

import java.util.List;

public interface UserAccountsService {

    List<String> findAccountsByUserId(String userId);
}
