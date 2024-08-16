/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.github.angel.repository;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.github.angel.entity.Purchase;

/**
 *
 * @author aguero
 */
@Repository
public interface PurchaseRepository extends ListPagingAndSortingRepository<Purchase, Long>, BaseJpaRepository<Purchase, Long> {

}
