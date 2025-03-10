package com.example.rest_api.core.contract.repo;

import com.example.rest_api.domain.Kasus;
import com.example.rest_api.domain.KodeKasus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface KasusRepo extends JpaRepository<Kasus, BigDecimal> {
    //JPA
    List<Kasus> findAllByCreatedBy(String createdBy);
    @Query("select a from Kasus a where a.createdBy = ?1 ")
    List<Kasus> findAllByCreatedByHql(String createdBy);
    @NativeQuery("SELECT * FROM KASUS WHERE createdBy = ?1 ")
    List<Kasus> findAllByCreatedBySql (String createdBy);
    List<Kasus> findAll();
    List<Kasus> findAllById(BigDecimal id);
    Kasus getById(BigDecimal id);
    void deleteById(BigDecimal id);

    @Query("SELECT a FROM Kasus a join a.kodeKasus b WHERE  a.documentId =?1 AND a.status != 'DELETED' and b.id =?2 ")
    Kasus getActiveCasesByDocId(BigDecimal documentId, BigDecimal kode);

    @Query("SELECT a FROM Kasus a WHERE a.documentId =?1 and a.status !='DELETED'")
    Kasus getCaseByDocId(BigDecimal documentId);

    // select * from kasus a join kode_kasus b on a.kode = b.kode where b.kode = kode

    



}
