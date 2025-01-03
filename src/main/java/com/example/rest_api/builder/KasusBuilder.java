package com.example.rest_api.builder;
import java.math.BigDecimal;

import com.example.rest_api.domain.Kasus;

public class KasusBuilder {

    private Kasus result;

    public KasusBuilder create(BigDecimal idDokumen, String nip, BigDecimal kode){
        result = new Kasus();
        
        return this;
    }
    
}
