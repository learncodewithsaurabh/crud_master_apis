package com.codewithsaurabh.crud_master_apis.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
private String message;
private boolean success;
private Object data;
}
