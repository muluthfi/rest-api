package com.example.rest_api.core.feature.manajemenKasus;

import com.example.rest_api.controller.dto.CaseResponseDTO;
import com.example.rest_api.core.contract.repo.KasusRepo;
import com.example.rest_api.domain.Kasus;
import com.example.rest_api.domain.KodeKasus;
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

    @Autowired
    ManajemenKasusComponentImpl(KasusRepo kasusRepo, ManajemenKasusRepo manajemenKasusRepo) {
        this.kasusRepo = kasusRepo;
        this.manajemenKasusRepo = manajemenKasusRepo;
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

    public Kasus createKasus(Kasus kasus){
        kasus.setStatus(Status.DRAFT);
        kasus.setCreatedDate(new Date());
        kasus.setCreatedBy(kasus.getCreatedBy());
        kasus.setDocumentId(kasus.getDocumentId());
        kasus = kasusRepo.save(kasus);
        return kasus;
    }

    public Kasus updateKasus(Kasus kasus){
        kasus.setStatus(kasus.getStatus());
        kasus.setCreatedBy(kasus.getCreatedBy());
        kasus.setCreatedDate(kasus.getCreatedDate());
        kasus.setDocumentId(kasus.getDocumentId());
        kasus = kasusRepo.save(kasus);
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
