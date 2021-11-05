package it.epicode.prenotazioni.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.prenotazioni.model.Citta;

@Service
public class CittaService {
	@Autowired
	CittaRepository cr;
	
	public List<Citta> getAll(){
		return cr.findAll();
	}
	public void save(Citta c) {
		cr.save(c);
	}
	public Citta findByName(String nome) {
		return cr.findByName(nome);
	}

	// Paginazione e Ordinamento
    public List<Citta> myFindAllCittaPageSizeSort(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        Page<Citta> pagedResult = cr.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Citta>();
        }
    }
    /* Sort */
    // Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
 // Ordinamento
    public List<Citta> myFindAllCittaSorted() {
        return cr.findByOrderByNomeAsc();
    }
}
