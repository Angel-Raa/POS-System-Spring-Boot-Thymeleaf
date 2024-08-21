/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.github.angel.repository;

import java.util.List;

import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.github.angel.entity.Role;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;

/**
 *
 * @author aguero
 */
@Repository
public interface RoleRepository extends ListPagingAndSortingRepository<Role, Long>,  BaseJpaRepository<Role, Long>{

}
