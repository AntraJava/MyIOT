package com.antra.iot.authservice.jwt.api;

import com.antra.iot.authservice.jwt.JwtRequest;
import com.antra.iot.authservice.jwt.JwtResponse;
import com.antra.iot.authservice.jwt.JwtTokenUtil;
import com.antra.iot.authservice.jwt.UserDetails;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JwtAuthenticationController {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());

        UserDetails userDetails = new UserDetails();
        userDetails.setUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody JwtToken request) {
        if (jwtTokenUtil.validateToken(request.getToken())) {
            return ResponseEntity.ok("Valid");
        }
        return ResponseEntity.status(401).body("Invalid");
    }

}

@Data
class JwtToken{
    String token;
}