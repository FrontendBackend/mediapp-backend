package com.mitocode.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.ForeignKey;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Signos vitales Model")
@Entity
@Table(name = "signos_vitales")
public class SignosVitales {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer idSignosVitales;

    // @NotEmpty
    @ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "fk_signosvitales_paciente"))
	private Paciente paciente;

    @Column(name = "fecha", nullable = true)
	private LocalDateTime fecha;

	@Column(name = "temperatura", nullable = true)
	private String temperatura;
		
	@Column(name = "pulso", nullable = true)
	private String pulso;
	
	@Column(name = "ritmo", nullable = true)
	private String ritmo;

    // GETTERS AND SETTERS
    public Integer getIdSignosVitales() {
        return idSignosVitales;
    }

    public void setIdSignosVitales(Integer idSignosVitales) {
        this.idSignosVitales = idSignosVitales;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public String getRitmo() {
        return ritmo;
    }

    public void setRitmo(String ritmo) {
        this.ritmo = ritmo;
    }

    
}
