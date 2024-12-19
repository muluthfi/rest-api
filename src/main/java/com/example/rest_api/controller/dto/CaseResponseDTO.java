package com.example.rest_api.controller.dto;

import com.example.rest_api.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

public class CaseResponseDTO {
    private BigDecimal id;
    private BigDecimal documentId;
    private Date createdDate;




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
    public BigDecimal getDocumentId() {
        return documentId;
    }

    public void setDocumentId(BigDecimal documentId) {
        this.documentId = documentId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
