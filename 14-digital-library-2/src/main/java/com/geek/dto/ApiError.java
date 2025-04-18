package com.geek.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//setter & getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
private String errorMsg;
private String status;

}
