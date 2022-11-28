package com.intuit.b2b.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.intuit.b2b.account.AccountService;
import com.intuit.b2b.account.entity.Account;
import com.intuit.b2b.user.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UsersService usersService;

    @MockBean
    private UserAccountsService userAccountsService;

    @MockBean
    private AccountService accountsService;

    private final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


    @Test
    public void getUsersByIdShouldReturnUserAsJson() throws JsonProcessingException {
        Users mockUser = new Users(1, "1234", "name", "name@email.com");
        when(usersService.fetchUserByUserId(any())).thenReturn(mockUser);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/v1/users/1234", String.class)).isEqualTo(ow.writeValueAsString(mockUser));
    }


    @Test
    public void getUserByNameShouldReturnUsersStartingWithUserName() throws JsonProcessingException {
        Users mockUser = new Users(1, "1234", "name", "name@email.com");
        when(usersService.fetchUserByUserName(any())).thenReturn(Arrays.asList(mockUser));
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/v1/users/name/na", String.class)).isEqualTo(ow.writeValueAsString(Arrays.asList(mockUser)));
    }

    @Test
    public void getUserPaymentMethodsShouldReturnUsersAccountObjects() throws JsonProcessingException {
        Account mockAccount = new Account(1, "bank", "111", "888999", "000");
        when(userAccountsService.findAccountsByUserId(any())).thenReturn(Arrays.asList("111"));
        when(accountsService.fetchAccountByAccountId(any())).thenReturn(mockAccount);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/v1/users/1234/paymentMethods", String.class)).isEqualTo(ow.writeValueAsString(Arrays.asList(mockAccount)));
    }

}
