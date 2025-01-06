package com.example.rest_api.controller;

import com.example.rest_api.controller.dto.CaseRequestDTO;
import com.example.rest_api.controller.dto.CaseResponseDTO;
import com.example.rest_api.core.feature.manajemenKasus.ManajemenKasusComponent;
import com.example.rest_api.domain.Aktivitas;
import com.example.rest_api.domain.Kasus;
import com.example.rest_api.domain.KodeKasus;
import com.example.rest_api.domain.RiwayatKasus;
import com.example.rest_api.domain.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kasus")
public class ManajemenKasusController {
    private final ManajemenKasusComponent manajemenKasusComponent;
    @Autowired
    public ManajemenKasusController(ManajemenKasusComponent manajemenKasusComponent) {
        this.manajemenKasusComponent = manajemenKasusComponent;
    }

    @GetMapping("/")
    public ResponseEntity<List<CaseResponseDTO>> getAllbyNip(@RequestParam String nip){
        List<Kasus> daftarKasus = manajemenKasusComponent.getAllByNip(nip);
        List<CaseResponseDTO> response = new ArrayList<>();

        for(Kasus kasus : daftarKasus){
            CaseResponseDTO caseResponseDTO = new CaseResponseDTO();
            caseResponseDTO.setDocumentId(kasus.getDocumentId());
            caseResponseDTO.setId(kasus.getId());
            caseResponseDTO.setNipPengusul(kasus.getCreatedBy());
            caseResponseDTO.setStatus(kasus.getStatus().toString());
            response.add(caseResponseDTO);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CaseResponseDTO>> getAll(){
        List<Kasus> daftarKasus = manajemenKasusComponent.getAll();
        List<CaseResponseDTO> response = new ArrayList<>();
        for(Kasus kasus : daftarKasus){
            CaseResponseDTO caseResponseDTO = new CaseResponseDTO();
            caseResponseDTO.setId(kasus.getId());
            caseResponseDTO.setDocumentId(kasus.getDocumentId());
            caseResponseDTO.setNipPengusul(kasus.getCreatedBy());
            caseResponseDTO.setStatus(kasus.getStatus().toString());
            response.add(caseResponseDTO);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // bikin sebuah request DTO

    @PostMapping("/create")
    public ResponseEntity<BigDecimal> createKasus(@RequestBody CaseRequestDTO requestDTO){
        // disini ada validasi atas request
        // validasi apakah seluruh request didalam CaseRequestDTO sudah lengkap?

        Kasus kasusToSave = new Kasus.Builder()
                .create(requestDTO.getIdDokumen(), requestDTO.getNipPengusul(), requestDTO.getKodeKasus())
                .asDraft()
                .build();

        RiwayatKasus riwayatToSave = new RiwayatKasus.Builder()
            .create(kasusToSave, requestDTO.getNipPengusul())
            .asCreation()
            .build();
        
        kasusToSave = manajemenKasusComponent.createKasus(kasusToSave, riwayatToSave);

        return new ResponseEntity<>(kasusToSave.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/create/kodekasus")
    public ResponseEntity<BigDecimal> createKodeKasus(@RequestBody CaseRequestDTO requestDTO){
        KodeKasus kodeKasusToSave = new KodeKasus.Builder().create(requestDTO.getNmKodeKasus()).build();

        kodeKasusToSave = manajemenKasusComponent.createKodeKasus(kodeKasusToSave);
        return new ResponseEntity<>(kodeKasusToSave.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid")
    public ResponseEntity<BigDecimal> deleteById(@RequestParam BigDecimal id){
        Kasus kasusExist = manajemenKasusComponent.getDraftCases(id);
        Kasus newKasus = new Kasus.Builder().update(kasusExist).asDelete().build();

        RiwayatKasus riwayatToSave = new RiwayatKasus.Builder().create(newKasus, kasusExist.getCreatedBy()).asDeletion().build();

        newKasus = manajemenKasusComponent.updateKasus(id, newKasus, riwayatToSave);

        return new ResponseEntity<>(newKasus.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/submit/")
    public ResponseEntity<BigDecimal> submitById(@RequestParam BigDecimal id){
        Kasus kasusExist = manajemenKasusComponent.getDraftCases(id);
        Kasus kasusForApproval = new Kasus.Builder().update(kasusExist).asOpen().build();
        RiwayatKasus riwayatKasus = new RiwayatKasus.Builder().create(kasusForApproval, kasusExist.getCreatedBy()).asApproval().build();

        kasusForApproval = manajemenKasusComponent.updateKasus(id, kasusForApproval, riwayatKasus);

        return new ResponseEntity<>(kasusForApproval.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/getallbyid/")
    public ResponseEntity<List<CaseResponseDTO>> getAllbyId(@RequestParam BigDecimal id){
        List<Kasus> daftarKasus = manajemenKasusComponent.getAllById(id);
        List<CaseResponseDTO> response = new ArrayList<>();

        for(Kasus kasus : daftarKasus){
            CaseResponseDTO caseResponseDTO = new CaseResponseDTO();
            caseResponseDTO.setId(kasus.getId());
            caseResponseDTO.setDocumentId(kasus.getDocumentId());
            caseResponseDTO.setNipPengusul(kasus.getCreatedBy());
            caseResponseDTO.setStatus(kasus.getStatus().toString());
            response.add(caseResponseDTO);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping("/getbyid/")
    public ResponseEntity<CaseResponseDTO> getById(@RequestParam BigDecimal id){
        Kasus kasus = manajemenKasusComponent.getById(id);
        CaseResponseDTO caseResponseDTO = new CaseResponseDTO();
        caseResponseDTO.setId(kasus.getId());
        caseResponseDTO.setDocumentId(kasus.getDocumentId());
        caseResponseDTO.setNipPengusul(kasus.getCreatedBy());
        caseResponseDTO.setCreatedDate(kasus.getCreatedDate());
        caseResponseDTO.setStatus(kasus.getStatus().toString());
        return new ResponseEntity<>(caseResponseDTO, HttpStatus.OK);

    }

}
