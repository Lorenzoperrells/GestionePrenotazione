package it.epicode.prenotazioni.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.epicode.prenotazioni.model.Edificio;


@Component
public interface EdificioRepository extends JpaRepository<Edificio,Long> {
	@Query("SELECT e FROM Edificio e WHERE e.nome=:nome")
	public Edificio findByName(String nome);
	
	public List<Edificio> findByOrderByCittaAsc();
}
