package com.example.rest_api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="Case")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Case {
    @Id
    private BigDecimal id;
    private BigDecimal documentId;
    private Status status;



}
