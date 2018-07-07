package io.dltshv.expenses;

import io.dltshv.expenses.repository.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpensesApplicationTests {

	@Autowired
    private CategoryRepository repository;

	@Test
	public void contextLoads() {
	}

}
