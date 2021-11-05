package it.epicode.prenotazioni.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.prenotazioni.model.Prenotazione;

@Service
public class PrenotazioneService {
	@Value("${exception.lessthantwodays")
	private String lessThanTwoDays;
	@Autowired
	PrenotazioneRepository preRepository;
	
	
	public void save(Prenotazione p) {
		preRepository.save(p);
	}
    public boolean diffInDaysLessThan(int numDays, LocalDate firstDate, LocalDate secondDate) {
        LocalDate numDaysBefore = secondDate.minusDays(numDays);
        return firstDate.isAfter(numDaysBefore);
    }
    
    public void applicaRegoleBusiness(LocalDate data_prenotata)throws BusinessLogicException{
        if (diffInDaysLessThan(2,LocalDate.now(),data_prenotata)) {
            throw new BusinessLogicException(lessThanTwoDays);
            
        }
    }
        public boolean userHasOtherReservationForDay(Long idUser, LocalDate date) {
            List<Prenotazione> result=preRepository.findByUtenteAndDataUtilizzo(idUser, date);
            if(result.isEmpty()) {
                return false;
            }
            return true;
		
	}
        public Optional<Prenotazione> getById(long id) {
        	return preRepository.findById(id);
        }
        
        public List<Prenotazione> getAll(){
        	return preRepository.findAll();
        }
        public Page<Prenotazione> myFindAllPrenotazioniPageable(Pageable pageable) {
            return preRepository.findAll(pageable);
        }
    	// Paginazione e Ordinamento
        public List<Prenotazione> myFindAllPrePageSizeSort(Integer page, Integer size, String sort) {
            Pageable paging = PageRequest.of(page, size, Sort.by(sort));
            Page<Prenotazione> pagedResult = preRepository.findAll(paging);
            if (pagedResult.hasContent()) {
                return pagedResult.getContent();
            } else {
                return new ArrayList<Prenotazione>();
            }
        }
        /* Sort */
        // Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
     // Ordinamento
        public List<Prenotazione> myFindAllPreSorted() {
            return preRepository.findByOrderByDataPrenotataAsc();
        }
}
