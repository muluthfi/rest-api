package com.example.rest_api.controller;

import com.example.rest_api.controller.dto.CaseResponseDTO;
import com.example.rest_api.core.feature.manajemenKasus.ManajemenKasusComponent;
import com.example.rest_api.domain.Kasus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

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
            caseResponseDTO.setNipPengusul(kasus.getCreatedBy());
            caseResponseDTO.setStatus(kasus.getStatus().toString());
            response.add(caseResponseDTO);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CaseResponseDTO> createKasus(@RequestBody Kasus kasus){
        CaseResponseDTO caseResponseDTO = new CaseResponseDTO();
        kasus = manajemenKasusComponent.createKasus(kasus);
        caseResponseDTO.setDocumentId(kasus.getDocumentId());
        caseResponseDTO.setNipPengusul(kasus.getCreatedBy());
        return new ResponseEntity<>(caseResponseDTO, HttpStatus.CREATED);

    }


}
