package com.a03.silk.restservice;

import com.a03.silk.dto.TokenDTO;
import com.a03.silk.dto.request.LoginRequestDTO;
import com.a03.silk.dto.request.UserRequestDTO;
import com.a03.silk.dto.response.UserResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AdminRestServiceImpl implements AdminRestService {

    @Override
    public String getToken(String username, String name) {
//        var body = new LoginRequestDTO(username, name);
//
//        var response = this.webClient
//                .post()
//                .uri("/api/auth/login-jwt-webadmin")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(body)
//                .retrieve()
//                .bodyToMono(TokenDTO.class)
//                .block();
//
//        var token = response.getToken();

        return "token";
    }



}
