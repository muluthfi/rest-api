package com.example.rest_api.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table

public class KodeKasus {
    @Id
    @GeneratedValue
    @Column
    private BigDecimal id;

    // @Column
    // private String kode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kodeKasus")
    private List<Kasus> kasus= new ArrayList<>();

    @Column
    private String namaKode;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNamaKode() {
        return namaKode;
    }

    public void setNamaKode(String namaKode) {
        this.namaKode = namaKode;
    }

    // public String getKode() {
    //     return kode;
    // }

    // public void setKode(String kode) {
    //     this.kode = kode;
    // }

    public List<Kasus> getKasus() {
        return kasus;
    }

    public void setKasus(List<Kasus> kasus) {
        this.kasus = kasus;
    }

    

    


}
