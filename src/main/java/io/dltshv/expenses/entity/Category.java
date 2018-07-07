package io.dltshv.expenses.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    private String id;
    private String name;
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
