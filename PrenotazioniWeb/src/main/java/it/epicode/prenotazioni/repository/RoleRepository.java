package it.epicode.prenotazioni.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.epicode.prenotazioni.model.Role;
import it.epicode.prenotazioni.model.RoleType;
@Component
public interface RoleRepository extends JpaRepository<Role,Long>{

	Optional<Role> findByRoletype(RoleType roletype);

}
