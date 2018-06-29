package io.dltshv.expenses.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @Id
    private String id;
    private Double amount;
    private LocalDateTime date;
    private String details;
    private List<Category> categories;
}
