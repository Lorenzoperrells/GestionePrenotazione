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

import it.epicode.prenotazioni.model.Citta;
import it.epicode.prenotazioni.repository.CittaService;

@RestController
@RequestMapping("/cittacontroller")
public class CittaController {
	@Autowired
	CittaService cs;
	
	@GetMapping("/save")
	public void save(@RequestParam String nome) {
		cs.save(new Citta(nome));
	}
	@GetMapping("/citta")
	@ResponseBody
	public List<Citta> getAll(){
		return cs.getAll();
	}
	@GetMapping("/ricercapernome/{nome}")
	@ResponseBody
	public Citta findByName(@PathVariable String nome) {
		return cs.findByName(nome);
	}
	 @GetMapping(value = "/mygetallcittapagesizesort", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Citta>> myGetAllUserPageSizeSort(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
	      List<Citta> list = cs.myFindAllCittaPageSizeSort(page, size, sort);
	      return new ResponseEntity<List<Citta>>(list, new HttpHeaders(), HttpStatus.OK); 
	    }
	    @GetMapping(value = "/mygetallcittasortbyname", produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<Citta> myGetAllusersSortByName() {
	        return cs.myFindAllCittaSorted();
	    }

}
