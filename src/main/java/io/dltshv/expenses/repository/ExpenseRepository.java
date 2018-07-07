package io.dltshv.expenses.repository;

import io.dltshv.expenses.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {
    @Query("{ 'categories._id': ?0 }")
    List<Expense> findAllByCategory(String categoryId);
}
