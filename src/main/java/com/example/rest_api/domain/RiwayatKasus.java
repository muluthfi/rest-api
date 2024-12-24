package com.example.rest_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Entity
@Table
public class RiwayatKasus {

    @Id
    @GeneratedValue
    @Column
    private BigDecimal id;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Kasus kasus;

    @Enumerated(EnumType.STRING)
    @Column
    private Aktivitas aktivitas;

    @Column
    private String createdBy;

    @Column
    private Date createdDate;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Kasus getKasus() {
        return kasus;
    }

    public void setKasus(Kasus kasus) {
        this.kasus = kasus;
    }

    public Aktivitas getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(Aktivitas aktivitas) {
        this.aktivitas = aktivitas;
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

    public static class Builder {

        private RiwayatKasus result;

        public Builder create(Kasus kasus, String nip) {
            result = new RiwayatKasus();

            result.setCreatedBy(nip);
            result.setCreatedDate(new Date());
            result.setKasus(kasus);

            return this;
        }

        public Builder asCreation() {
            result.setAktivitas(Aktivitas.CREATION);
            return this;
        }

        public RiwayatKasus build() {
            return result;
        }
    }
}
