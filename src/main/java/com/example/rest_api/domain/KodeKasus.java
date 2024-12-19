package com.example.rest_api.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class KodeKasus {
    @Id
    @Column
    private BigDecimal kode;

    @Column
    private String name;
}
