/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.github.angel.repository;

import java.util.Optional;

import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;

import com.github.angel.entity.User;

/**
 *
 * @author aguero
 */
@Repository
public interface UserRepository extends ListPagingAndSortingRepository<User, Long>,
        BaseJpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
