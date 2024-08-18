/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.exception;

/**
 *
 * @author aguero
 */
public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String msg){
        super(msg);
    }

}
