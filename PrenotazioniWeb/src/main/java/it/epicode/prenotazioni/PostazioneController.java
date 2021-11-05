package it.epicode.prenotazioni;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import it.epicode.prenotazioni.model.Postazione;
import it.epicode.prenotazioni.model.TipoPostazione;
import it.epicode.prenotazioni.repository.EdificioService;
import it.epicode.prenotazioni.repository.PostazioneService;

@RestController
@RequestMapping("/postazionecontroller")
public class PostazioneController {
	@Autowired
	PostazioneService service;
	@Autowired
	EdificioService es;
	
	@GetMapping("/cercapostazione")
	public List<Postazione> findAvailableByTypeAndCity(@RequestParam String citta,@RequestParam TipoPostazione tipo) {
		return service.findPostazioneByCittaETipo(citta,tipo);
		
	}
	@GetMapping("/save")
	public void save(@RequestParam String codice,String descrizione, TipoPostazione tipo, Integer max, String edificio) {
		service.save(new Postazione(codice,descrizione,tipo,max,es.findByName(edificio)));
	}
	@GetMapping("/postazioni")
	@ResponseBody
	public List<Postazione> getAll(){
		return service.getAll();
	}
	@GetMapping("/ricercapercodice/{codice}")
	@ResponseBody
	public Postazione findByName(@PathVariable String codice) {
		return service.getByCodice(codice);
	}
    @GetMapping(value = "/mygetallpostssortbycodice", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Postazione> myGetAllPostazioneSortByCodice() {
        return service.myFindAllPostazioneSorted();
    }
    @GetMapping(value = "/mygetallpostspagesizesort", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Postazione>> myGetAllUserPageSizeSort(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
      List<Postazione> list = service.myFindAllUsersPageSizeSort(page, size, sort);
      return new ResponseEntity<List<Postazione>>(list, new HttpHeaders(), HttpStatus.OK); 
    }

}
