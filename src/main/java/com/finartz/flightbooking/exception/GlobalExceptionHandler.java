package com.finartz.flightbooking.exception;

import com.finartz.flightbooking.domain.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto handleValidationException(ValidationException e) {
        ErrorDto errorDto = getErrorOutput(e);

        return errorDto;
    }

    @ExceptionHandler(FlightBookingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto handleFlightBookingException(ValidationException e) {
        ErrorDto errorOutput = getErrorOutput(e);

        return errorOutput;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public ErrorDto handleAuthenticationException(IllegalArgumentException e) {
        ErrorDto errorOutput = getErrorOutput(e);

        return errorOutput;
    }

    private ErrorDto getErrorOutput(Exception e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrrorMessage(e.getMessage());
        return errorDto;
    }
}
