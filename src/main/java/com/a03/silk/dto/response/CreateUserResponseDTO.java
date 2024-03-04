package com.a03.silk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponseDTO {
    public Long id;
    private String username;
    private String name;
    private String role;
}
