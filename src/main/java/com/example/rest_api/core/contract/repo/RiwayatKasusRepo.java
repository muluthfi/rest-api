package com.example.rest_api.core.contract.repo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rest_api.domain.RiwayatKasus;

public interface RiwayatKasusRepo extends JpaRepository<RiwayatKasus, BigDecimal>{
    //Melihat Semua Histori Kasus
    List<RiwayatKasus> findAll();
    //Melihat Semua Histori Kasus berdasarkan Id
    List<RiwayatKasus> findAllById(BigDecimal id);

}
