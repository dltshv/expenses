package io.dltshv.expenses.repository;

import io.dltshv.expenses.entity.Expense;
import io.dltshv.expenses.enums.ExpenseType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {

    @Query("{ 'categories._id': ?0 }")
    List<Expense> findAllByCategory(String categoryId);

    List<Expense> findAllByType(ExpenseType type);

    List<Expense> findAllByDateIsBetween(LocalDateTime start, LocalDateTime end);

    List<Expense> findAllByTypeAndDateIsBetween(ExpenseType type, LocalDateTime start, LocalDateTime end);
}
