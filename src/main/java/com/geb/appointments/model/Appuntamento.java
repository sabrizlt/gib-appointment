package com.geb.appointments.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appuntamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_e_cognome")
    private String nomeECognome;

    private String cellulare;
    private LocalDate dataAppuntamento;
    private LocalTime oraAppuntamento;
    private String barbiere;
    private String servizio; // ✅ AGGIUNGI QUESTO

    // Getters
    public Long getId() {
        return id;
    }

    public String getNomeECognome() {
        return nomeECognome;
    }

    public String getCellulare() {
        return cellulare;
    }

    public LocalDate getDataAppuntamento() {
        return dataAppuntamento;
    }

    public LocalTime getOraAppuntamento() {
        return oraAppuntamento;
    }

    public String getBarbiere() {
        return barbiere;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeECognome(String nomeECognome) {
        this.nomeECognome = nomeECognome;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public void setDataAppuntamento(LocalDate dataAppuntamento) {
        this.dataAppuntamento = dataAppuntamento;
    }

    public void setOraAppuntamento(LocalTime oraAppuntamento) {
        this.oraAppuntamento = oraAppuntamento;
    }

    public void setBarbiere(String barbiere) {
        this.barbiere = barbiere;
    }

    public String getServizio() {
        return servizio;
    }

    public void setServizio(String servizio) {
        this.servizio = servizio;
    }

    @Transient
    private Boolean overrideDuplica;

    public Boolean getOverrideDuplica() {
        return overrideDuplica;
    }

    public void setOverrideDuplica(Boolean overrideDuplica) {
        this.overrideDuplica = overrideDuplica;
    }
}
