package com.a03.silk.restservice;

import com.a03.silk.dto.request.UserRequestDTO;
import com.a03.silk.dto.response.UserResponseDTO;

public interface AdminRestService {


    String getToken(String username, String name);
}
