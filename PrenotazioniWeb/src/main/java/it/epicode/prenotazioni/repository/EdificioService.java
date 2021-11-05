package it.epicode.prenotazioni.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.prenotazioni.model.Edificio;

@Service
public class EdificioService {
	@Autowired
	EdificioRepository er;

	public List<Edificio> getAll(){
		return er.findAll();
	}
	public Edificio findByName(String name){
		return er.findByName(name);
	}
	public void save(Edificio e) {
		er.save(e);
	}
    /* Sort */
    // Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
 // Ordinamento
    public List<Edificio> myFindAllEdificioSorted() {
        return er.findByOrderByCittaAsc();
    }
	// Paginazione e Ordinamento
    public List<Edificio> myFindAllEdificioPageSizeSort(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        Page<Edificio> pagedResult = er.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Edificio>();
        }
    }
}
