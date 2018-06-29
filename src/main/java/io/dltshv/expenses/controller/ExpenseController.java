package io.dltshv.expenses.controller;

import io.dltshv.expenses.entity.Expense;
import io.dltshv.expenses.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/expense")
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/expense/{id}")
    public Expense getExpenseById(@PathVariable("id") String id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/expense/{id}")
    public ResponseEntity deleteExpenseById(@PathVariable("id") String id) {
        expenseService.deleteExpenseById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/expense")
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseService.createOrUpdateExpense(expense);
    }

    @PutMapping("/expense")
    public Expense updateExpense(@RequestBody Expense expense) {
        if (expense.getId() == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Cannot update expense without 'id'provided");
        }
        return expenseService.createOrUpdateExpense(expense);
    }

}
