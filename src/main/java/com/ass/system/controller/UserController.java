package com.ass.system.controller;

import com.ass.system.model.user.AppUser;
import com.ass.system.model.user.UserDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserDaoImpl dao;

    public UserController(UserDaoImpl dao) {
        this.dao = dao;
    }


    @GetMapping
    public List<AppUser> getUsers() {
        return dao.findAll();
    }

    @GetMapping(("/{userId}"))
    public AppUser getUserById(@PathVariable int userId) {
        try {
            return dao.findById(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/register")
    public String displayCreateUserForm(Model model) {
        AppUser user = new AppUser();
        model.addAttribute("createdUser", user);
        return "pages/user/register";
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") AppUser appUser, BindingResult result) throws SQLException {
        AppUser existing = dao.findByEmail(appUser.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account with that email");
        }
        if (result.hasErrors()) {
            System.out.println("Errors: " + result.getAllErrors());
            return "pages/user/register";
        }
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        appUser.setPassword(bCrypt.encode(appUser.getPassword()));
        dao.save(appUser);
        return "pages/user/regsuccess";

    }

    @PostMapping("/update/{userId}")
    public void updateUser(@PathVariable int userId, AppUser user) {
        try {
            dao.update(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/delete/{userId}")
    public void deleteUser(@PathVariable int userId) {
        try {
            dao.delete(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
