package com.antra.iot.iotuserservice.api;

import com.antra.iot.iotuserservice.api.pojo.ErrorResponse;
import com.antra.iot.iotuserservice.api.pojo.RegisterRequest;
import com.antra.iot.iotuserservice.api.pojo.ResponseMessage;
import com.antra.iot.iotuserservice.api.pojo.UsernamePasswordRequest;
import com.antra.iot.iotuserservice.exception.CustomerException;
import com.antra.iot.iotuserservice.service.CustomerService;
import com.antra.iot.iotuserservice.vo.CustomerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping()
    public String test() {
        log.debug("Test");
        return "customer api";
    }
    @PostMapping
    public ResponseEntity<ResponseMessage<CustomerVO>> createCustomer(@Validated @RequestBody RegisterRequest customer) {
        CustomerVO savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(new ResponseMessage<>(HttpStatus.CREATED), HttpStatus.CREATED);
    }
    @PostMapping("/verify")
    public ResponseEntity<ResponseMessage<CustomerVO>> verifyCustomer(@Validated @RequestBody UsernamePasswordRequest request) {
        CustomerVO res = customerService.verifyCustomer(request);
        if (res == null) {
            return new ResponseEntity<>(new ResponseMessage<>(HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseMessage<>(HttpStatus.OK, res), HttpStatus.OK);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getFieldError().getField() + " " + ex.getFieldError().getDefaultMessage());
        log.error("Controller Error",ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorResponse> customerExceptionHandler(CustomerException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getReason());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
