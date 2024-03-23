package com.a03.silk.restcontroller;

import com.a03.silk.dto.UserMapper;
import com.a03.silk.dto.request.CreateUserRequestDTO;
import com.a03.silk.dto.request.UpdateUserRequestDTO;
import com.a03.silk.dto.response.CreateUserResponseDTO;
import com.a03.silk.dto.response.LoginJwtResponseDTO;
import com.a03.silk.dto.response.UserResponseDTO;
import com.a03.silk.model.UserModel;
import com.a03.silk.repository.UserDb;
import com.a03.silk.restservice.UserRestService;
import com.a03.silk.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "https://silk-client.railway.internal")
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    UserRestService userRestService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDb userDb;

    @PostMapping(value = "/user/create")
    public ResponseEntity<?> restAddUser(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }
        else {
            if (userDb.existsByUsername(createUserRequestDTO.getUsername())) {
                return new ResponseEntity<>("Username yang anda masukkan sudah ada.", HttpStatus.BAD_REQUEST);
            }
            if (createUserRequestDTO.getPassword() == null || createUserRequestDTO.getPassword().length() < 8) {
                return new ResponseEntity<>("Password setidaknya harus 8 karakter.", HttpStatus.BAD_REQUEST);
            }

        
            String role = createUserRequestDTO.getRole();
            if (role == null) {
                return new ResponseEntity<>("Role tidak valid.", HttpStatus.BAD_REQUEST);
            }
            else {
                UserModel userModel =  userMapper.createUserRequestDTOToUserModel(createUserRequestDTO);
                userModel = userService.addUser(userModel, createUserRequestDTO);

                CreateUserResponseDTO createUserResponseDTO = userMapper.createUserResponseDTOToUserModel(userModel);
                createUserResponseDTO.setRole(userModel.getRole().getRole());
                return new ResponseEntity<>(createUserResponseDTO, HttpStatus.OK);
            }
        }
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> restUpdateUser(@PathVariable("id") Long id, @Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        else {

            var userFromDTO = userMapper.updateUserModelRequestDTOToUserModel(updateUserRequestDTO);

            var userDariDatabase = userRestService.getRestUserById(id);

            if (userFromDTO.getPassword().equals(userDariDatabase.getPassword())) {
                return new ResponseEntity<>("Password baru tidak boleh sama dengan password lama.", HttpStatus.BAD_REQUEST);
            }
            if (userFromDTO.getPassword()== null || userFromDTO.getPassword().length() < 8) {
                return new ResponseEntity<>("Password setidaknya harus 8 karakter.", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(userFromDTO, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {

        try {
            var user = userRestService.getRestUserById(id);

            // Soft delete by updating the 'deleted' flag
            userRestService.deleteUser(user);

            return new ResponseEntity<>("User with ID " + id + " has been soft-deleted.", HttpStatus.OK);

        } catch (NoSuchElementException e) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User with ID " + id + " not found"
            );
        }
    }

    @GetMapping("/user/all")
    public List<UserResponseDTO> getAllUser() {
        List<UserModel> users = userRestService.retrieveRestAllUser();
        List<UserResponseDTO> userDtos = new ArrayList<>();

        for (UserModel user : users) {
            UserResponseDTO userDto = new UserResponseDTO();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setUsername(user.getUsername());
            userDto.setRole(user.getRole().getRole()); // Assuming Role has a 'name' property
            userDtos.add(userDto);
        }

        return userDtos;
    }


}
