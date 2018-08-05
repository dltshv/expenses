package io.dltshv.expenses.service;

import io.dltshv.expenses.entity.Expense;
import io.dltshv.expenses.enums.ExpenseType;
import io.dltshv.expenses.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public List<Expense> getAllExpensesByType(ExpenseType type) {
        return expenseRepository.findAllByType(type);
    }

    public List<Expense> getAllExpensesByDatesInterval(LocalDateTime start, LocalDateTime end) {
        return expenseRepository.findAllByDateIsBetween(start, end);
    }

    public List<Expense> getAllExpensesByTypeAndDatesInterval(ExpenseType type, LocalDateTime start, LocalDateTime end) {
        return expenseRepository.findAllByTypeAndDateIsBetween(type, start, end);
    }

    public Expense createOrUpdateExpense(Expense expense) {
        expense.setDate(LocalDateTime.now());
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
