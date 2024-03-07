package com.a03.silk.controller;

import java.util.ArrayList;
import java.util.List;

import com.a03.silk.dto.request.CreateUserRequestDTO;
import com.a03.silk.dto.request.UserRequestDTO;
import com.a03.silk.dto.response.CreateUserResponseDTO;
import com.a03.silk.dto.response.UserResponseDTO;
import com.a03.silk.restcontroller.UserRestController;
import com.a03.silk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    UserRestController userRestController;

    @GetMapping("/user/add")
    public String formAddUser(Model model) {
        UserRequestDTO userDTO = new UserRequestDTO();
        List<String> roles = new ArrayList<>();
        roles.add("Admin");
        roles.add("User");
        model.addAttribute("listRole", roles);
        model.addAttribute("userDTO", userDTO);
        return "form-add-user";
    }

}
