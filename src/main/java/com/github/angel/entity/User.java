package com.github.angel.entity;

import java.util.Collection;
import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author aguero
 */
@Entity(name = "User")
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class User implements  UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "fk_role_id", insertable = true, updatable = true)
    private Long roleId;
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long")
    @Column(unique = true, nullable = false)
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    private String username;
    @JsonIgnore
    @NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false)
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    private String password;
    @ManyToOne(targetEntity = Role.class, cascade =  CascadeType.ALL, fetch =  FetchType.EAGER)
    @JoinColumn(name = "fk_role_id", insertable =  false, updatable = false, referencedColumnName = "role_id")
    private Role role;

   
    public User() {}

    public User(Long userId,
            @NotBlank(message = "Username is required") @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long") String username,
            @NotBlank(message = "Password is required") String password, Role role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     if(role == null ) return Collections.emptyList();
     if(role.getAuthorities() == null) return Collections.emptyList();
     return role.getAuthorities()
             .getPermissions()
             .stream().map(Enum::name)
             .map(SimpleGrantedAuthority::new)
             .toList();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

   
    

   

}
