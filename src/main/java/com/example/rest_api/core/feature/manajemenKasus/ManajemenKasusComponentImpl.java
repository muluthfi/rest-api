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

    public Kasus createKasus(Kasus kasus){
        kasus.setStatus(Status.DRAFT);
        kasus.setCreatedDate(new Date());
        kasus.setCreatedBy(kasus.getCreatedBy());
        kasus.setDocumentId(kasus.getDocumentId());
        kasus = kasusRepo.save(kasus);
        return kasus;
    }
}
