package io.dltshv.expenses.controller;

import io.dltshv.expenses.repository.CategoryRepository;
import io.dltshv.expenses.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResetController {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    @GetMapping("/reset")
    public ResponseEntity reset() {
        categoryRepository.deleteAll();
        expenseRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

}
