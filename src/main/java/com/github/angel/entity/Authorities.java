/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */

package com.github.angel.entity;

import java.util.List;

import com.github.angel.utils.Permission;

/**
 *
 * @author aguero
 */

public enum Authorities {
    ADMINISTRATOR(
        List.of(
            Permission.DELETE,
            Permission.DISABLE,
            Permission.READ,
            Permission.UPDATE,
            Permission.WRITE
        )
    ),
    ASSOCIATE(
        List.of(
            Permission.DISABLE,
            Permission.WRITE,
            Permission.READ,
            Permission.UPDATE
        )
    );

    private final List<Permission> permissions;

    private Authorities(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }
    
}
