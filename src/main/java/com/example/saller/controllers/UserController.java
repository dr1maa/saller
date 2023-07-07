package com.example.saller.controllers;

import com.example.saller.models.User;
import com.example.saller.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller

@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal,
                          Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("email")) {
                FieldError emailError = bindingResult.getFieldError("email");
                model.addAttribute("errorMessage","Неверный Email");
            }
            if (bindingResult.hasFieldErrors("phoneNumber")) {
                FieldError phoneNumberError = bindingResult.getFieldError("phoneNumber");
                model.addAttribute("errorMessage", "Неверный формат номера телефона");
            }
            if (bindingResult.hasFieldErrors("name")) {
                FieldError nameError = bindingResult.getFieldError("name");
                model.addAttribute("errorMessage", "Ошибка в имени");
            }
            if (bindingResult.hasFieldErrors("password")) {
                FieldError passwordError = bindingResult.getFieldError("password");
                model.addAttribute("errorMessage", "Пароль слишком длинный");
            }
            return "registration";
        }
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        model.addAttribute("products", user.getProducts());
        return "user-info";
    }
}