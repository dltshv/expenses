package io.dltshv.expenses.repository;

import io.dltshv.expenses.entity.Category;
import io.dltshv.expenses.entity.Expense;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpenseRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    public void testInsert() {
        Category category = Category.builder()
                .name("Food")
                .build();
        String savedCategoryId = categoryRepository.save(category).getId();
        Expense expense = Expense.builder()
                .amount(23d)
                .categories(singletonList(category))
                .build();
        String savedExpenseId = expenseRepository.save(expense).getId();

        List<Expense> expenses = expenseRepository.findAllByCategory(savedCategoryId);
        assertThat(expense).isNotNull();
    }

}
