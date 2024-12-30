package com.myecom.myapp.advice;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"timestamp", "status", "error", "message", "path"})
public class ApiError {

  private String path;
  private int status;
  private HttpStatus error;
  private String message;
  private long timestamp;
}
