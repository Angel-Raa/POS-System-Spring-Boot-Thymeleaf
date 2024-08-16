/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.configuration;

import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;

/**
 *
 * @author aguero
 */
@EnableJpaRepositories(
    basePackages = "com.github.angel.repository",
    value = {"io.hypersistence.utils.spring.repository", "com.github.angel.repository"},
    repositoryBaseClass = BaseJpaRepositoryImpl.class
)
@Configuration
public class JpaConfiguration {


}
