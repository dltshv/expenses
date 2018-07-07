package io.dltshv.expenses.controller;

import io.dltshv.expenses.entity.Category;
import io.dltshv.expenses.entity.Expense;
import io.dltshv.expenses.repository.CategoryRepository;
import io.dltshv.expenses.repository.ExpenseRepository;
import io.dltshv.expenses.service.ExpenseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpenseControllerTest {

    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ExpenseService expenseService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    WebApplicationContext ctx;

    MockMvc mvc;

    @Before
    public void init() {
        mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testGetExpensebyId() throws Exception {
        Category category = Category.builder()
                .name("Food")
                .build();
        categoryRepository.save(category);
        Expense expense = Expense.builder()
                .amount(23d)
                .categories(singletonList(category))
                .build();
        String savedId = expenseRepository.save(expense).getId();
        mvc.perform(get("/expense/" + savedId))
                .andExpect(status().isOk())
                .andDo(print());

    }
}
