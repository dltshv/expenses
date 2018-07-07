package io.dltshv.expenses.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"date"}, allowGetters = true)
public class Expense {
    @Id
    private String id;
    private Double amount;
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'hh:mm:ss")
    private LocalDateTime date;
    private String details;
    private List<Category> categories;
}
