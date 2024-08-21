/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.controller.auth;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.angel.dto.Login;
import com.github.angel.dto.Register;
import com.github.angel.service.AuthenticationService;

import jakarta.validation.Valid;

/**
 *
 * @author aguero
 */
@Controller
@RequestMapping("/auth")

public class AuthenticationController {
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String login(final Model model, @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout, final RedirectAttributes attributes) {

        if (error != null) {
            model.addAttribute("message", "Los datos ingresados no son correctos, por favor vuelva a intentarlo.");
            model.addAttribute("messageType", "error");
        }
        if (logout != null) {
            model.addAttribute("message", "Ha cerrado sesión exitosamente.");
            model.addAttribute("messageType", "success");
        }
        model.addAttribute("login", new Login());
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@Valid Login login, final BindingResult result, final Model model,
            final RedirectAttributes attributes) {
        if (result.hasErrors()) {
            // Recopila los errores de validación y los agrega al modelo
            Map<String, Object> errores = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage));
            model.addAttribute("errors", errores);
            model.addAttribute("login", login);
            return "auth/login";
        }
        service.login(login);
        model.addAttribute("login", login);
        attributes.addAttribute("message", "user authenticated successfully");
        return "redirect:/";

    }

    @GetMapping("/register")
    public String register(final Model model) {
        model.addAttribute("register", new Register());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid Register register, final BindingResult result, final Model model,
            final RedirectAttributes attributes) {
        if (result.hasErrors()) {
            // Recopila los errores de validación y los agrega al modelo
            Map<String, Object> errores = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage));
            model.addAttribute("errors", errores);
            model.addAttribute("register", register);
            return "auth/register";
        }

        service.register(register);
        model.addAttribute("register", register);
        return "redirect:/auth/login";
    }

    @GetMapping("/logout")
    public String logout(final Model model) {
        return "redirect:/auth/login?logout=true";
    }
}
