package io.dltshv.expenses.service;

import io.dltshv.expenses.entity.Expense;
import io.dltshv.expenses.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense createOrUpdateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense getExpenseById(String id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Expense not found"));
    }

    public void deleteExpenseById(String id) {
        expenseRepository.deleteById(id);
    }

    public List<Expense> getExpensesByCategoryId(String categoryId) {
        return expenseRepository.findAllByCategory(categoryId);
    }
}
