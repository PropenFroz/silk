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
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Username is already taken."
                );
            }

            String role = createUserRequestDTO.getRole(); // Contoh: Anda dapat menambahkan atribut userType ke CreateUserRequestDTO.
            if (role == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Invalid role"
                );
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
    public UserModel restUpdateUser(@PathVariable("id") Long id, @Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        else {

            var userFromDTO = userMapper.updateUserModelRequestDTOToUserModel(updateUserRequestDTO);

            var userDariDatabase = userRestService.getRestUserById(id);

            if (userFromDTO.getPassword().equals(userDariDatabase.getPassword())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "New password is the same as the existing password");
            }

            return userRestService.updateRestUser(userFromDTO, id);
        }
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {

        try {
            var user = userRestService.getRestUserById(id);

            // Soft delete by updating the 'deleted' flag
            userRestService.deleteUser(user);

//            // Save the updated user to the database
//            userDb.save(user);

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
