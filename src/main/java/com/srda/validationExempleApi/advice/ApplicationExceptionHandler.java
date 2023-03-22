package com.srda.validationExempleApi.advice;

import com.srda.validationExempleApi.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

//Attention on ne peut pas l'appeler exceptionHandler la classe car elle existe déjà.
@RestControllerAdvice
public class ApplicationExceptionHandler {



    //On veut retourner la réponse comme étant une "BAD request"
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //J'utilise une exception de spring web que je mets en paramètre dans une méthode qui va la gèrer.
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception){
        Map<String, String> errorMap=new HashMap<>();
        //je prend l'exception, pour chaque champ d'erreur je le mets dans la map.
        exception.getBindingResult().getFieldErrors().forEach(
                error -> {
                    errorMap.put(error.getField(),error.getDefaultMessage());
                }
        );
        return errorMap;
    }

    //Je lui dis que si j'ai cette excption.class dans le controller, je délègue cette requête à cette méthode.
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//internal server error donnera le code 500
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String , String>handleBusinessExeption(UserNotFoundException exception){
        Map<String, String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return errorMap;
    }

}
