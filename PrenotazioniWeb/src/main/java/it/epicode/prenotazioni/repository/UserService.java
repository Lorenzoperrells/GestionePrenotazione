package it.epicode.prenotazioni.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.prenotazioni.model.User;

@Service
public class UserService {
	@Autowired
	UserRepository ur;
	public User getById(long idUser) {
		return ur.getById(idUser);
	}
	public List<User> getAll(){	
		return ur.findAll();
	}
	public void save(User u) {
		ur.save(u);
	}
	public User getByUsername(String username) {
		return ur.findByUsername(username);
	}
	public Page<User> myFindAllUsersPageable(Pageable pageable) {
        return ur.findAll(pageable);
    }
	// Paginazione e Ordinamento
    public List<User> myFindAllUsersPageSizeSort(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        Page<User> pagedResult = ur.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<User>();
        }
    }
    /* Sort */
    // Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
 // Ordinamento
    public List<User> myFindAllUsersSorted() {
        return ur.findByOrderByUsernameAsc();
    }

   
}
