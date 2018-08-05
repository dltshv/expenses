package io.dltshv.expenses.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.dltshv.expenses.enums.ExpenseType;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties(value = {"date"}, allowGetters = true)
public class Expense {
    @Id
    private String id;
    @NotNull
    private Double amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'hh:mm:ss")
    private LocalDateTime date;
    private String details;
    @NotNull
    private ExpenseType type;
    private List<Category> categories;
}

