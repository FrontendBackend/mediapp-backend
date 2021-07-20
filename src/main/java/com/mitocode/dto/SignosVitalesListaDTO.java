package com.mitocode.dto;

import javax.validation.constraints.NotNull;

import com.mitocode.model.SignosVitales;

public class SignosVitalesListaDTO {
    
    @NotNull
	private SignosVitales signosVitales;

    public SignosVitales getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(SignosVitales signosVitales) {
        this.signosVitales = signosVitales;
    }
}
