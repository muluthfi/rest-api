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
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class KodeKasus {
    @Id
    @Column
    private BigDecimal id;


//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kodeKasus")
//    private List<KodeKasus> kodeKasus= new ArrayList<>();

    @Column
    private String namaKode;
}
