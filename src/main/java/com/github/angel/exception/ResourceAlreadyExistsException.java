/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.exception;

/**
 *
 * @author aguero
 */
public class ResourceAlreadyExistsException extends RuntimeException{

    public ResourceAlreadyExistsException(String message){
        super(message);
    }

}
