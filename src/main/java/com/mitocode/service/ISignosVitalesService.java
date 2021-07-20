package com.mitocode.service;

import com.mitocode.dto.SignosVitalesListaDTO;
import com.mitocode.model.SignosVitales;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISignosVitalesService extends ICRUD<SignosVitales, Integer>{
    
    public SignosVitales registrarSignosVitalesTransaccional(SignosVitalesListaDTO dto);

    Page<SignosVitales> listarPageable(Pageable pageable);
}
