package com.geb.appointments.controller;

import com.geb.appointments.model.Appuntamento;
import com.geb.appointments.repository.AppuntamentoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appuntamenti")
@CrossOrigin(origins = "*") // <-- accetta richieste da tutti i domini
public class AppuntamentoController {

    private final AppuntamentoRepository repository;

    public AppuntamentoController(AppuntamentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Appuntamento> getAll() {
        return repository.findAll();
    }

  @PostMapping
public ResponseEntity<?> create(@RequestBody Appuntamento appuntamento) {
    System.out.println("Ricevuto: " + appuntamento.getNomeECognome() + " - " + appuntamento.getCellulare());

    boolean override = Boolean.TRUE.equals(appuntamento.getOverrideDuplica());

    if (!override && repository.existsByDataAppuntamentoAndOraAppuntamento(
            appuntamento.getDataAppuntamento(), appuntamento.getOraAppuntamento())) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Slot già occupato");
    }

    return ResponseEntity.ok(repository.save(appuntamento));
}


    

    // Nel controller
@GetMapping("/byDate")
public List<Appuntamento> getByDate(@RequestParam("data") String data) {
    LocalDate localDate = LocalDate.parse(data);
    List<Appuntamento> appuntamenti = repository.findByDataAppuntamento(localDate);

    // 🔍 LOG per debug
    appuntamenti.forEach(a -> System.out.println(">> " + a.getOraAppuntamento()));

    return appuntamenti;
}


     @PutMapping("/{id}")
    public ResponseEntity<?> aggiorna(@PathVariable Long id, @RequestBody Appuntamento aggiornato) {
        Optional<Appuntamento> esistente = repository.findById(id);
        if (esistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Se la data o ora è cambiata, controlla conflitto
        Appuntamento originale = esistente.get();
        if ((!originale.getDataAppuntamento().equals(aggiornato.getDataAppuntamento()) ||
             !originale.getOraAppuntamento().equals(aggiornato.getOraAppuntamento())) &&
             repository.existsByDataAppuntamentoAndOraAppuntamento(
               aggiornato.getDataAppuntamento(), aggiornato.getOraAppuntamento())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Slot già occupato");
        }

        aggiornato.setId(id);
        return ResponseEntity.ok(repository.save(aggiornato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
