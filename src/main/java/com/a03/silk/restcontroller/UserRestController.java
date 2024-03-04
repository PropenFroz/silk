package com.a03.silk.restcontroller;

import com.a03.silk.dto.UserMapper;
import com.a03.silk.dto.request.CreateUserRequestDTO;
import com.a03.silk.dto.response.CreateUserResponseDTO;
import com.a03.silk.dto.response.UserResponseDTO;
import com.a03.silk.model.UserModel;
import com.a03.silk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping(value = "user/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreateUserResponseDTO addUser(@RequestBody CreateUserRequestDTO createUserRequestDTO){
        try{
            UserModel userModel =  userMapper.createUserRequestDTOToUserModel(createUserRequestDTO);
            userModel = userService.addUser(userModel, createUserRequestDTO);

            CreateUserResponseDTO createUserResponseDTO = userMapper.createUserResponseDTOToUserModel(userModel);
            createUserResponseDTO.setRole(userModel.getRole().getRole());
            return createUserResponseDTO;

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "gak bisa");
        }
    }
}
