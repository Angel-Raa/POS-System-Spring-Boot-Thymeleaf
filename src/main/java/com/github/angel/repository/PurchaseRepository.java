/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.github.angel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.angel.entity.Purchase;

/**
 *
 * @author aguero
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

}
