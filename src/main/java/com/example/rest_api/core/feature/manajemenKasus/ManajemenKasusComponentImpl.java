package com.example.rest_api.core.feature.manajemenKasus;

import com.example.rest_api.controller.dto.CaseResponseDTO;
import com.example.rest_api.core.contract.repo.KasusRepo;
import com.example.rest_api.core.contract.repo.RiwayatKasusRepo;
import com.example.rest_api.domain.Aktivitas;
import com.example.rest_api.domain.Kasus;
import com.example.rest_api.domain.KodeKasus;
import com.example.rest_api.domain.RiwayatKasus;
import com.example.rest_api.domain.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
class ManajemenKasusComponentImpl implements ManajemenKasusComponent{

    private final KasusRepo kasusRepo;
    private final ManajemenKasusRepo manajemenKasusRepo;
    private final RiwayatKasusRepo riwayatKasusRepo;

    @Autowired
    ManajemenKasusComponentImpl(KasusRepo kasusRepo, ManajemenKasusRepo manajemenKasusRepo, RiwayatKasusRepo riwayatKasusRepo) {
        this.kasusRepo = kasusRepo;
        this.manajemenKasusRepo = manajemenKasusRepo;
        this.riwayatKasusRepo = riwayatKasusRepo;
    }

    @Override
    public List<Kasus> getAllByNip(String nip) {
        return kasusRepo.findAllByCreatedBy(nip);
    }

    public List<Kasus> getAll(){
        return kasusRepo.findAll();
    }

    @Override
    public Kasus getById(BigDecimal id) {
        return kasusRepo.getById(id);
    }

    @Override
    public Kasus getActiveCasesByDocId(BigDecimal documentId, BigDecimal kode) {
        return kasusRepo.getActiveCasesByDocId(documentId, kode);
    }

    public Kasus createKasus( Kasus kasus, RiwayatKasus riwayatKasus){  
        // check apakah dokumen id exist
        // 1. get kasus by id dokumen
        Kasus existingKasus = kasusRepo.getActiveCasesByDocId(kasus.getDocumentId(),kasus.getKodeKasus().getId());
        if (existingKasus != null) {
            throw new RuntimeException("Dokumen sudah direkam");
        } 
        // if exixst, then throw exception as runtime with message id dokumen sudah ada
        // save kasus  

        // ada kmeungkinan, kode kasus yg dikirim sama user itu tidak valid/tidak terdaftar
        // kcek dulu apakah kode kasus ada atau engga, di get dulu
        // kalo ada, di assign ulang ke kasusnya
        
        kasusRepo.save(kasus);

        riwayatKasusRepo.save(riwayatKasus);
        
        return kasus;
    }

    public Kasus updateKasus(BigDecimal id, Kasus kasus){
        Kasus kasusExist = kasusRepo.getById(id);
        if(kasusExist.getId() == id){
            kasus.setStatus(kasus.getStatus());
            kasus.setCreatedBy(kasus.getCreatedBy());
            kasus.setCreatedDate(kasusExist.getCreatedDate());
            kasus.setDocumentId(kasus.getDocumentId());
            kasus = kasusRepo.save(kasus); 
        }
        return kasus;
    }   

    public List<Kasus> getAllById(BigDecimal id){
        return kasusRepo.findAllById(id);
    }

    @Override
    public void deleteById(BigDecimal id) {
        kasusRepo.deleteById(id);
    }
}
