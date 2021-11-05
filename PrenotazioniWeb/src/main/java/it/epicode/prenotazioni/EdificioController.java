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

import it.epicode.prenotazioni.model.Edificio;
import it.epicode.prenotazioni.repository.CittaService;
import it.epicode.prenotazioni.repository.EdificioService;

@RestController
@RequestMapping("/edificiocontroller")
public class EdificioController {
	@Autowired
	EdificioService es;
	@Autowired
	CittaService cs;
	
	
	@GetMapping("/save")
	public void save(@RequestParam  String nome,String indirizzo, String citta,String codice) {
		es.save(new Edificio(nome,indirizzo,cs.findByName(citta),codice));
	}
	@GetMapping("/edifici")
	@ResponseBody
	public List<Edificio> getAll(){
		return es.getAll();
	}
	@GetMapping("/ricercapernome/{nome}")
	@ResponseBody
	public Edificio findByName(@PathVariable String nome) {
		return es.findByName(nome);
	}
    @GetMapping(value = "/mygetalledificiopagesizesort", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Edificio>> myGetAllUserPageSizeSort(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
      List<Edificio> list = es.myFindAllEdificioPageSizeSort(page, size, sort);
      return new ResponseEntity<List<Edificio>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
    @GetMapping(value = "/mygetalledificiosortbycitta", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Edificio> myGetAllEdificioSortByCitta() {
        return es.myFindAllEdificioSorted();
    }
    @GetMapping(value="/attivaedificio")
    public String attivaEdificio(@RequestParam String edificio,String codice) {
    	Edificio e=es.findByName(edificio);
    	if(e.getCodice().equals(codice) ) {
    		e.setActive(true);
    		return "Edificio "+e.getNome()+" attivato";
    	}
    	return "Impossibile attivare l'edificio "+e.getNome();
    }
}
