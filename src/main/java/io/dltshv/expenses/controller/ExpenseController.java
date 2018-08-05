package io.dltshv.expenses.controller;

import io.dltshv.expenses.entity.Expense;
import io.dltshv.expenses.enums.ExpenseType;
import io.dltshv.expenses.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/expense")
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping(path = "/expense", params = "type")
    public List<Expense> getAllExpensesByType(@RequestParam ExpenseType type) {
        return expenseService.getAllExpensesByType(type);
    }

    @GetMapping(path = "/expense", params = {"start", "end"})
    public List<Expense> getAllExpensesByDatesInterval(@RequestParam LocalDateTime start,
                                                       @RequestParam LocalDateTime end) {
        return expenseService.getAllExpensesByDatesInterval(start, end);
    }

    @GetMapping(path = "/expense", params = {"type", "start", "end"})
    public List<Expense> getAllExpensesByTypeAndDatesInterval(@RequestParam ExpenseType type,
                                                              @RequestParam LocalDateTime start,
                                                              @RequestParam LocalDateTime end) {
        return expenseService.getAllExpensesByTypeAndDatesInterval(type, start, end);
    }

    @GetMapping("/expense/{id}")
    public Expense getExpenseById(@PathVariable("id") String id) {
        return expenseService.getExpenseById(id);
    }

    @GetMapping("/expenses-by-category/{categoryId}")
    public List<Expense> getExpenseByCategory(@PathVariable("categoryId") String categoryId) {
        return expenseService.getExpensesByCategoryId(categoryId);
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
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Cannot update expense without 'id' provided");
        }
        return expenseService.createOrUpdateExpense(expense);
    }

}
