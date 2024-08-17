/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.utils;

/**
 *
 * @author aguero
 */

public class PageableUtils {
    private int number;
    private boolean current;
    public PageableUtils() {
    }
    public PageableUtils(int number, boolean current) {
        this.number = number;
        this.current = current;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public boolean isCurrent() {
        return current;
    }
    public void setCurrent(boolean current) {
        this.current = current;
    }
    

}
