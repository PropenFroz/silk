package com.a03.silk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserRequestDTO {

    private String name;
    private String username;
    private String password;
    private String currentPassword;



}
