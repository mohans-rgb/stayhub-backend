package com.StayHub.StayHub.Advice;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;


@Data
@Builder
public class ApiError {

     private HttpStatus httpStatus;

     private String errorMessage;

     private List<String> subErrors;
}
