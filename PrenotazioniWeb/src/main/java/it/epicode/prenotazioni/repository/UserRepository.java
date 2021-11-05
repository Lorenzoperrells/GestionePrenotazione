package it.epicode.prenotazioni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.epicode.prenotazioni.model.User;
@Component
public interface UserRepository extends JpaRepository<User,Long>  {
	
	@Query("SELECT u FROM User u WHERE u.username=:username")
	public User findByUsername(String username);
	
	 /* Sort */
    // Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
    public List<User> findByOrderByUsernameAsc();

	public boolean existsByUsername(String username);

	public boolean existsByEmail(String email);
  

}
