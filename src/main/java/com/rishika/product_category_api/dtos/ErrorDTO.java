package com.rishika.product_category_api.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private LocalDateTime timestamp;
    private String errorMsg;
    private String path;
    private List<String> errors;
    public ErrorDTO(List<String> errors, String path) {
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
        this.path = path;
    }
    public ErrorDTO(String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.errorMsg = message;
        this.path=path;

    }

}
