package com.example.rest_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table

public class Kasus {


    @Id
    @GeneratedValue
    @Column
    private BigDecimal id;

    @Column
    private BigDecimal documentId;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private String createdBy;

    @Column
    private Date createdDate;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private KodeKasus kodeKasus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kasus")
    private List<RiwayatKasus> caseHistories = new ArrayList<>();

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getDocumentId() {
        return documentId;
    }

    public void setDocumentId(BigDecimal documentId) {
        this.documentId = documentId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<RiwayatKasus> getCaseHistories() {
        return caseHistories;
    }

    public void setCaseHistories(List<RiwayatKasus> caseHistories) {
        this.caseHistories = caseHistories;
    }

    public KodeKasus getKodeKasus() {
        return kodeKasus;
    }

    public void setKodeKasus(KodeKasus kodeKasus) {
        this.kodeKasus = kodeKasus;
    }

    public static class Builder {

        private Kasus result;

        public Builder update(Kasus kasus) {
            result = kasus;
            return this;
        }

        public Builder create(BigDecimal idDokumen, String nip, BigDecimal kode) {
            result = new Kasus();
            result.setDocumentId(idDokumen);
            result.setCreatedBy(nip);
            result.setCreatedDate(new Date());

            KodeKasus kodeKasus = new KodeKasus();
            kodeKasus.setId(kode);
            result.setKodeKasus(kodeKasus);

            return this;
        }

        public Builder asDraft() {
            result.setStatus(Status.DRAFT);
            return this;
        }

        public Builder delete() {
            result.setStatus(Status.DELETED);
            return this;
        }

        public Kasus build() {
            return this.result;
        }
    }
    
}
