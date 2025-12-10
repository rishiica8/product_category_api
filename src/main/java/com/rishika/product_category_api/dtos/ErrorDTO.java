package com.rishika.product_category_api.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private LocalDateTime timestamp;
    private String errorMsg;
    private String path;

    public ErrorDTO(String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.errorMsg = message;
        this.path=path;

    }

}
