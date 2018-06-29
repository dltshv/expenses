package io.dltshv.expenses.repository;

import io.dltshv.expenses.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
