package it.epicode.prenotazioni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.epicode.prenotazioni.model.Postazione;
import it.epicode.prenotazioni.model.TipoPostazione;
@Component
public interface PostazioneRepository extends JpaRepository<Postazione,Long> {

	@Query("SELECT post FROM Postazione post WHERE post.edificio.citta.nome = :citta AND post.tipo = :tipo ")
   List<Postazione> findByTypeAndCity(String citta, TipoPostazione tipo);
	
	@Query("SELECT p FROM Postazione p WHERE p.codice = :codice ")
	Postazione findByCodice(String codice);
	
	public List<Postazione> findByOrderByCodiceAsc();
}
