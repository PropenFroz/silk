package com.a03.silk.restcontroller;

import com.a03.silk.dto.UserMapper;
import com.a03.silk.dto.request.LoginJwtRequestDTO;
import com.a03.silk.dto.request.LoginRequestDTO;
import com.a03.silk.dto.response.LoginJwtResponseDTO;
import com.a03.silk.security.jwt.JwtUtils;
import com.a03.silk.service.RoleService;
import com.a03.silk.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthRestController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        try{
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwtToken = jwtUtils.generateJwtToken(loginRequest.getUsername());

            return new ResponseEntity<>(Collections.singletonMap("token", jwtToken), HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("status", false);
            responseMap.put("message", "Username atau password salah");
            return new ResponseEntity<>(responseMap, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            // Mendapatkan informasi waktu kedaluwarsa token
            Date expirationDate = jwtUtils.extractExpiration(jwtToken);

            // Membuat token kedaluwarsa
            Date expiredTokenDate = new Date();

            // Set expiredTokenDate ke waktu yang sudah lewat
            expiredTokenDate.setTime(System.currentTimeMillis() - 1000);

            // Jika token belum kedaluwarsa, atur waktu kedaluwarsanya menjadi waktu yang sudah lewat
            if (expirationDate.after(expiredTokenDate)) {
                // Atur waktu kedaluwarsa token yan


                jwtUtils.setExpiration(expiredTokenDate, jwtToken);
            }
        }

        return ResponseEntity.ok(Collections.singletonMap("message", "Logout berhasil"));
    }

}
