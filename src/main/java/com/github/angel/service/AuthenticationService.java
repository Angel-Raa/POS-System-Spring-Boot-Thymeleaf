/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.github.angel.service;

import com.github.angel.dto.Login;
import com.github.angel.dto.Register;

/**
 *
 * @author aguero
 */
public interface AuthenticationService {
    void logout();
    void login(Login login);
    void register(Register register);
    
    


}
