package com.antra.iot.authservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> {
    HttpStatus statusCode;
    T data;

    public ResponseMessage(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
