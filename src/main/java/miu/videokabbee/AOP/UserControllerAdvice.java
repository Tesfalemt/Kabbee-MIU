package miu.videokabbee.AOP;

//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import miu.videokabbee.ExceptionHandling.ExceptionHandling;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class UserControllerAdvice {

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
      List<String> errorMessages =ex.getBindingResult().getAllErrors().stream()
              .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

      ExceptionHandling response=new ExceptionHandling(errorMessages);

       return ResponseEntity.badRequest().body(response.toString());
     //return ResponseEntity.badRequest().body(errorMessages.toString());
   }


}
