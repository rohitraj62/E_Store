package com.rohit.ElectronicStore.exceptions;

import com.rohit.ElectronicStore.dtos.ApiResponseMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        logger.info("Exception handler Invoked");
        //     ApiResponseMessage response = ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
        ApiResponseMessage response = new ApiResponseMessage(ex.getMessage(), true, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
}
//@ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String,Object>>handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
//    logger.info("Exception handler Invoked");
////    List<ObjectError> allErrors=ex.getBindingResult().getAllErrors();
//    BindingResult bindingResult = ex.getBindingResult();
//    List<ObjectError> allErrors = bindingResult.getAllErrors();
////    Map<String,Object>response=new HashMap<>();
////    allErrors.stream().forEach(objectError->
////    {
////        String message=objectError.getDefaultMessage();
////        String field=((FieldError)objectError).getField();
////        response.put(field,message);
////    });
//    Map<String, Object> response = new HashMap<>();
//
//    for (ObjectError objectError : allErrors) {
//        String message = objectError.getDefaultMessage();
//        if (objectError instanceof FieldError) {
//            String field = ((FieldError) objectError).getField();
//            response.put(field, message);
//        }
//    }
//return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
//    }
//}
