package com.geb.appointments.repository;

import com.geb.appointments.model.Appuntamento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppuntamentoRepository extends JpaRepository<Appuntamento, Long> {
    List<Appuntamento> findByDataAppuntamento(LocalDate dataAppuntamento);
    boolean existsByDataAppuntamentoAndOraAppuntamento(LocalDate data, LocalTime ora);
}


