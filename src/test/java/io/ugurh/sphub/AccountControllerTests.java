package io.ugurh.sphub;

import io.ugurh.sphub.account.Account;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 6.05.2023 - 16:58
 */


public class AccountControllerTests extends AbstractTest {

    private static final String BASE_URL = "http://localhost:8081/api/accounts";
    private static final MediaType MEDIA_TYPE = MediaType.APPLICATION_JSON;
    @Autowired
    protected MockMvc mvc;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getAllAccounts() throws Exception {
        String url = BASE_URL + "/findAll?page=0&size=10&sortBy=username";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get(url)
                        .accept(MEDIA_TYPE))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Account[] accounts = mapFromJson(content, Account[].class);
        assertTrue(accounts.length > 0);
    }

    @Test
    public void createAccount() throws Exception {
        String uri = "/api/accounts/create";
        Account account = new Account("user1", "password1", "user1@gmail.com");

        String inputJson = mapToJson(account);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void deleteProduct() throws Exception {
        String uri = "/api/accounts/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "success");
    }

}
