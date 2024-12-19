package com.example.rest_api.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

public class CaseResponseDTO {
    private BigDecimal id;
    private BigDecimal documentId;

    public BigDecimal getDocumentId() {
        return documentId;
    }

    public void setDocumentId(BigDecimal documentId) {
        this.documentId = documentId;
    }

    private String status;
    private String nipPengusul;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNipPengusul() {
        return nipPengusul;
    }

    public void setNipPengusul(String nipPengusul) {
        this.nipPengusul = nipPengusul;
    }
}
