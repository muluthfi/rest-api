package com.example.rest_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.math.BigDecimal;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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


}
