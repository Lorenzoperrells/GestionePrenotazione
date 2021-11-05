package it.epicode.prenotazioni;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import it.epicode.prenotazioni.model.User;
import it.epicode.prenotazioni.repository.UserService;

@RestController
@RequestMapping("/usercontroller")
public class UserController {
	@Autowired
	UserService us;
	
	@GetMapping("/save")
	public void save(@RequestParam String username, String nome, String email, String password, Boolean active) {
		us.save(new User(username,nome,email,password,active));
	}
	@GetMapping("/users")
	@ResponseBody
	public List<User> getAll(){
		return us.getAll();
	}
	@GetMapping("/ricercaperusername/{username}")
	@ResponseBody
	public User findByUsername(@PathVariable String username) {
		return us.getByUsername(username);
	}
	// Paginazione
    @GetMapping(value = "/mygetalluserspage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<User>> myGetAllUsersPage(Pageable pageable) {
        Page<User> findAll = us.myFindAllUsersPageable(pageable);
        if (findAll.hasContent()) {
            return new ResponseEntity<>(findAll, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/mygetalluserspagesizesort", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> myGetAllUserPageSizeSort(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
      List<User> list = us.myFindAllUsersPageSizeSort(page, size, sort);
      return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
    @GetMapping(value = "/mygetalluserssortbyname", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> myGetAllusersSortByName() {
        return us.myFindAllUsersSorted();
    }
}
