package com.example.rest_api.core.feature.manajemenKasus;
import com.example.rest_api.domain.Kasus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ManajemenKasusComponent {

    List<Kasus> getAllByNip(String nip);
    List<Kasus> getAll();
    List<Kasus> getAllById(BigDecimal id);
    Kasus getById(BigDecimal id);
    Kasus createKasus(Kasus kasus);
    void deleteById(BigDecimal id);

    Kasus updateKasus(BigDecimal id, Kasus kasus);


}
