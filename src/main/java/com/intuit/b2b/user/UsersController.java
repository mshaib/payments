package com.intuit.b2b.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.intuit.b2b.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class UsersController {

    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    @Autowired
    private UsersService usersService;

    @Autowired
    private UserAccountsService userAccountsService;

    @Autowired
    private AccountService accountsService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<String> getUserById(@PathVariable String userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ow.writeValueAsString(usersService.fetchUserByUserId(userId)));
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/users/name/{userName}")
    public ResponseEntity<String> getUserByName(@PathVariable String userName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ow.writeValueAsString(usersService.fetchUserByUserName(userName)));
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/users/{userId}/paymentMethods")
    public ResponseEntity<String> getUserPaymentMethods(@PathVariable String userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    ow.writeValueAsString(
                            userAccountsService
                                    .findAccountsByUserId(userId)
                                    .stream()
                                    .map(
                                            (accountId) -> accountsService.fetchAccountByAccountId(accountId)
                                    ).collect(Collectors.toList())
                    )
            );
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
