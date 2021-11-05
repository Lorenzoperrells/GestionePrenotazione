package it.epicode.prenotazioni.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.epicode.prenotazioni.model.Prenotazione;

@Component
public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Long>{


    @Query("SELECT p FROM Prenotazione p WHERE p.user.id= :userId AND p.dataPrenotata= :dataPrenotata")
    public List<Prenotazione> findByUtenteAndDataUtilizzo(long userId, LocalDate dataPrenotata);
    
    public List<Prenotazione> findByOrderByDataPrenotataAsc();

}

