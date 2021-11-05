package it.epicode.prenotazioni;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.prenotazioni.model.Prenotazione;
import it.epicode.prenotazioni.repository.BusinessLogicException;
import it.epicode.prenotazioni.repository.PostazioneService;
import it.epicode.prenotazioni.repository.PrenotazioneService;
import it.epicode.prenotazioni.repository.UserService;

@RestController
@RequestMapping("/prenotazionecontroller")
public class PrenotazioneController {
	@Value("${gestioneprenotazioni.istruzioniItaliano}")
	String ita;
	@Value("${gestioneprenotazioni.istruzioniInglese}")
	String eng;
	@Autowired
	PrenotazioneService ps;
	@Autowired
	UserService us;
	@Autowired
	PostazioneService posts;
	

	 @GetMapping("/mygetlanguage")
	    public String myGetLanguage(@RequestParam String lingua) {
	    	if (lingua.equalsIgnoreCase("italiano")){
	        return ita;
	    	}
	        if (lingua.equalsIgnoreCase("inglese")) {
	        	return eng;
	        }
	        return "Errore la lingua scelta non è valida";
	    }
	 
	@GetMapping(value = "/creaprenotazione")
	@ResponseBody
	public String prenota(@RequestParam Long idUser, @RequestParam Long idPostazione, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data_prenotata) 
		throws BusinessLogicException {
	
		ps.applicaRegoleBusiness(data_prenotata);
		if (ps.userHasOtherReservationForDay(idUser,data_prenotata)) {
			throw new BusinessLogicException ("L'utente ha già prenotato per quel giorno");
		}else {
		Prenotazione p=new Prenotazione(us.getById(idUser),posts.getById(idPostazione),data_prenotata);
		ps.save(p);
		return p.toString();
		}
		
	}
		
		@GetMapping("/prenotazioni")
		@ResponseBody
		public List<Prenotazione> getAll(){
			return ps.getAll();
		}
		@GetMapping("/ricercaperid/{id}")
		@ResponseBody
		public Optional<Prenotazione> getById(@PathVariable long id) {
			return ps.getById(id);
		}
		 @GetMapping(value = "/mygetallprepage", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Page<Prenotazione>> myGetAllUsersPage(Pageable pageable) {
	        Page<Prenotazione> findAll = ps.myFindAllPrenotazioniPageable(pageable);
	        if (findAll.hasContent()) {
	            return new ResponseEntity<>(findAll, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }
	    }
		   @GetMapping(value = "/mygetallprepagesizesort", produces = MediaType.APPLICATION_JSON_VALUE)
		    public ResponseEntity<List<Prenotazione>> myGetAllUserPageSizeSort(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
		      List<Prenotazione> list = ps.myFindAllPrePageSizeSort(page, size, sort);
		      return new ResponseEntity<List<Prenotazione>>(list, new HttpHeaders(), HttpStatus.OK); 
		    }
		    @GetMapping(value = "/mygetallpresortbydata", produces = MediaType.APPLICATION_JSON_VALUE)
		    public List<Prenotazione> myGetAllPreSortByName() {
		        return ps.myFindAllPreSorted();
		    }
}

