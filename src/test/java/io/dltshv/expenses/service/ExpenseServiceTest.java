package io.dltshv.expenses.service;

import io.dltshv.expenses.entity.Expense;
import io.dltshv.expenses.enums.ExpenseType;
import io.dltshv.expenses.repository.ExpenseRepository;
import org.assertj.core.data.Offset;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExpenseServiceTest {

    @Autowired
    private ExpenseRepository repo;
    @Autowired
    private ExpenseService service;

    @After
    public void tearDown() {
        repo.deleteAll();
    }

    @Test
    public void getAllExpensesByTypeTest() {

        List<Expense> expenses = asList(
                Expense.builder()
                        .amount(2.5d)
                        .type(ExpenseType.EXPENSE)
                        .build(),
                Expense.builder()
                        .amount(10d)
                        .type(ExpenseType.REVENUE)
                        .build()
        );
        repo.saveAll(expenses);

        List<Expense> expensesByType = service.getAllExpensesByType(ExpenseType.REVENUE);
        assertThat(expensesByType).hasSize(1);
        assertThat(expensesByType.get(0).getAmount()).isEqualTo(10d, Offset.offset(0.01d));
        assertThat(expensesByType.get(0).getType()).isEqualTo(ExpenseType.REVENUE);
    }

    @Test
    public void getAllExpensesByDatesIntervalTest() {
        List<Expense> expenses = asList(
                Expense.builder()
                        .amount(2.5d)
                        .date(LocalDateTime.now().minusYears(1L))
                        .build(),
                Expense.builder()
                        .amount(7d)
                        .date(LocalDateTime.now().minusDays(7L))
                        .build(),
                Expense.builder()
                        .amount(5d)
                        .date(LocalDateTime.now().minusDays(5L))
                        .build()
        );
        repo.saveAll(expenses);

        List<Expense> expensesFromInterval
                = service.getAllExpensesByDatesInterval(LocalDateTime.now().minusDays(10L), LocalDateTime.now());
        assertThat(expensesFromInterval).hasSize(2);
    }

}
