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

    public Kasus createKasus(Kasus kasus, RiwayatKasus riwayatKasus){
        kasus.setStatus(Status.DRAFT);
        kasus.setCreatedDate(new Date());
        kasus.setCreatedBy(kasus.getCreatedBy());
        kasus.setDocumentId(kasus.getDocumentId());
        riwayatKasus.setCreatedBy(kasus.getCreatedBy());
        riwayatKasus.setCreatedDate(new Date());
        // riwayatKasus.setKasus(Kasus.getI;
        riwayatKasus.setAktivitas(Aktivitas.CREATION);
        kasus = kasusRepo.save(kasus);
        riwayatKasus = riwayatKasusRepo.save(riwayatKasus);
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
