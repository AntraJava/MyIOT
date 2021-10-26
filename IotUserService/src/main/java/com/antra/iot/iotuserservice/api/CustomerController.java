package com.antra.iot.iotuserservice.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {
    
    @GetMapping()
    public String test() {
        log.debug("Test");
        return "customer api";
    }
    
//    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
//    public Customer getCustomer(@PathVariable("uid") long id) throws CustomerException {
//        Customer Customer = customerService.findById(id);
//        if (Customer == null) {
//            throw new CustomerNotFoundException(messages.getMessage("Customer_NOT_FOUND"));
//        }
//        return Customer;
//    }
//
//    @PostMapping
//    public ResponseEntity<ResponseMessage> createCustomer(@Validated @RequestBody RegisterRequest Customer) {
//        Customer savedCustomer = customerService.saveCustomer(Customer);
//        return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.CREATED));
//    }
//
//    @RequestMapping(value = "/Customer/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @Validated @RequestBody Customer Customer){
//        Customer currentCustomer = customerService.findById(id);
//
//        if (currentCustomer == null) {
//            throw new CustomerNotFoundException(messages.getMessage("Customer_NOT_FOUND"));
//        }
//
//        currentCustomer.setName(Customer.getName());
//        currentCustomer.setAge(Customer.getAge());
//        currentCustomer.setSalary(Customer.getSalary());
//
//        customerService.updateCustomer(currentCustomer);
//        return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/Customer/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<ResponseMessage> deleteCustomer(@PathVariable("id") long id) {
//
//        Customer Customer = customerService.findById(id);
//        if (Customer == null) {
//            throw new CustomerNotFoundException(messages.getMessage("Customer_NOT_FOUND"));
//        }
//        customerService.deleteCustomerById(id);
//        return new ResponseEntity<ResponseMessage>(new ResponseMessage(messages.getMessage("Customer_DELETED"),Customer), HttpStatus.OK);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
//        ErrorResponse error = new ErrorResponse();
//        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        error.setMessage(ex.getMessage());
//        logger.error("Controller Error",ex);
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(CustomerNotFoundException.class)
//    public ResponseEntity<ErrorResponse> exceptionHandlerCustomerNotFound(Exception ex) {
//        logger.error("Cannot find Customer");
//        ErrorResponse error = new ErrorResponse();
//        error.setErrorCode(HttpStatus.NOT_FOUND.value());
//        error.setMessage(ex.getMessage());
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
}
