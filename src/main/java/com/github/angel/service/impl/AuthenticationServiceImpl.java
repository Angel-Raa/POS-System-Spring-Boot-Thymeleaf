/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.angel.dto.Login;
import com.github.angel.dto.Register;
import com.github.angel.entity.Authorities;
import com.github.angel.entity.Role;
import com.github.angel.entity.User;
import com.github.angel.exception.ResourceAlreadyExistsException;
import com.github.angel.repository.RoleRepository;
import com.github.angel.repository.UserRepository;
import com.github.angel.service.AuthenticationService;

/**
 *
 * @author aguero
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthenticationServiceImpl(UserRepository repository, PasswordEncoder encoder,
            AuthenticationManager authenticationManager, RoleRepository roleRepository) {
        this.repository = repository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }

    @Override
    public void logout() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }

    @Override
    public void login(Login login) {
        String username = login.getUsername();
        String password = login.getPassword();
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        authentication = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Transactional
    @Override
    public void register(Register register) {
        String username = register.getUsername();
        String password = register.getPassword();
        if (repository.existsByUsername(username)) {
            throw new ResourceAlreadyExistsException("");
        } else {
            User user = new User();
            Role role = new Role();
            role.setAuthorities(Authorities.ASSOCIATE);
            roleRepository.persist(role);
            user.setPassword(encoder.encode(password));
            user.setRoleId(role.getRoleId());
            user.setUsername(username);
            repository.persist(user);
        }

    }

}
