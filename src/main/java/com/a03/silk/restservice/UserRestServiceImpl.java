package com.a03.silk.restservice;

import com.a03.silk.model.UserModel;
import com.a03.silk.repository.RoleDb;
import com.a03.silk.repository.UserDb;
import com.a03.silk.security.jwt.JwtUtils;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserRestServiceImpl implements UserRestService {

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

    @Autowired
    private UserDb userDb;

    @Autowired
    private RoleDb roleDb;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public List<UserModel> retrieveRestAllUser() {
        return userDb.findAll();
    }

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserModel getRestUserById(Long id) {
        for (UserModel user : retrieveRestAllUser()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public UserModel getRestUserByUsername(String username) {
        for (UserModel user : retrieveRestAllUser()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void saveRestUser(UserModel user)
    {
        userDb.save(user);
    }

    @Override
    public String encryptPassword(String plainPassword) {
        String encryptedPassword = passwordEncoder.encode(plainPassword);
        // Lakukan enkripsi password di sini, seperti menggunakan BCryptPasswordEncoder

        return encryptedPassword;
    }

    @Override
    public boolean verifyPassword(String plainPassword, String encodedPassword) {
        return passwordEncoder.matches(plainPassword, encodedPassword);
    }

    @Override
    public UserModel updateRestUser(UserModel userFromDTO, Long id) {
        UserModel user = getRestUserById(id);

        if (user != null){
            user.setName(user.getName());
            user.setUsername(user.getUsername());

            user.setPassword(encryptPassword(userFromDTO.getPassword()));

            userDb.save(user);
        }
        return user;
    }

    @Override
    public UserModel deleteUser(UserModel user) {
        userDb.delete(user);

//        entityManager.flush();
        return user;
    }




}
