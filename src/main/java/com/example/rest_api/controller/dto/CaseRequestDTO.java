package com.example.rest_api.controller.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CaseRequestDTO {
    private BigDecimal idDokumen;
    private BigDecimal kodeKasus;
    private String nipPengusul;
    private String nmKodeKasus;
}
