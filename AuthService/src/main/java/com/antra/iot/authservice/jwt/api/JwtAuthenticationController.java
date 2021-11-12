package com.antra.iot.authservice.jwt.api;

import com.antra.iot.authservice.jwt.JwtRequest;
import com.antra.iot.authservice.jwt.JwtResponse;
import com.antra.iot.authservice.jwt.JwtTokenUtil;
import com.antra.iot.authservice.pojo.Customer;
import com.antra.iot.authservice.service.CustomerValidationService;
import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class JwtAuthenticationController {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomerValidationService cvs;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());
        log.info("User try to login: " + authenticationRequest.getUsername());
        Customer userDetails = cvs.verifyLogin(authenticationRequest);
        userDetails.setUsername(authenticationRequest.getUsername());
        userDetails.setId(userDetails.getId());
        log.info("User logged in {}", userDetails);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/validate")
    public ResponseEntity<Customer> validateToken(@RequestBody JwtToken request) {
        if (jwtTokenUtil.validateToken(request.getToken())) {
            Customer customer = new Customer();
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(request.getToken());
            customer.setId(claims.getSubject());
            customer.setEmail(claims.get("email", String.class));
            customer.setName(claims.get("name", String.class));
            customer.setUsername(claims.get("username", String.class));
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.status(401).build();
    }

}

@Data
class JwtToken{
    String token;
}
