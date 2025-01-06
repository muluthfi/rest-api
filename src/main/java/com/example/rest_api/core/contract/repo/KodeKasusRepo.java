package com.example.rest_api.core.contract.repo;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rest_api.domain.KodeKasus;

public interface KodeKasusRepo extends JpaRepository<KodeKasus, BigDecimal>{

}
