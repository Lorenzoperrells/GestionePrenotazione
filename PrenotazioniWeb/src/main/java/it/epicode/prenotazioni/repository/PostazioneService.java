package it.epicode.prenotazioni.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.prenotazioni.model.Postazione;
import it.epicode.prenotazioni.model.TipoPostazione;


@Service
public class PostazioneService {
	@Autowired
	private PostazioneRepository pr;
	
	public List<Postazione> findPostazioneByCittaETipo(String citta,TipoPostazione tipo) {
		return pr.findByTypeAndCity(citta,tipo);
	}
	public Postazione getById(long id) {
		return pr.getById(id);
	}
	public List<Postazione> getAll() {
		return pr.findAll();
	}
	public void save(Postazione p) {
		pr.save(p);
	}
	public Postazione getByCodice(String codice) {
		return pr.findByCodice(codice);
	}
	   /* Sort */
    // Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
 // Ordinamento
    public List<Postazione> myFindAllPostazioneSorted() {
        return pr.findByOrderByCodiceAsc();
    }
	// Paginazione e Ordinamento
    public List<Postazione> myFindAllUsersPageSizeSort(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        Page<Postazione> pagedResult = pr.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Postazione>();
        }
    }
}
