package it.epicode.prenotazioni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.prenotazioni.repository.CittaRepository;
import it.epicode.prenotazioni.repository.EdificioRepository;
import it.epicode.prenotazioni.repository.PostazioneRepository;
import it.epicode.prenotazioni.repository.PrenotazioneRepository;
import it.epicode.prenotazioni.repository.RoleRepository;
import it.epicode.prenotazioni.repository.UserRepository;


@Component
public class PrenotazioniCRUD implements CommandLineRunner{
	@Autowired
	EdificioRepository er;
	@Autowired
	PostazioneRepository pr;
	@Autowired
	UserRepository ur;
	@Autowired
	CittaRepository cr;
	@Autowired
	RoleRepository rr;
	@Autowired
	PrenotazioneRepository prenotazioneRepository;
	
	@Override
	public void run(String... args) throws Exception {
//		User u1=new User(null,"lorenzoperrells","lorenzoperrella@gmail.com","1234","lorenzo","Perrella");
//		Role user= new Role(RoleType.ROLE_USER);
//		Role admin=new Role(RoleType.ROLE_ADMIN);
//		rr.save(user);
//		rr.save(admin);
//		u1.getRoles().add(user);
//		u1.getRoles().add(admin);	
//		ur.save(u1);
//		Citta c1=new Citta("Roma");
//		Edificio e1=new Edificio("Ama","via del corso",c1,"a2345678");
//		Edificio e2=new Edificio("Acea","via dei mille",c1,"8765432a");
//		Postazione p1=new Postazione("001","Deposito",TipoPostazione.PRIVATO,10,e1);
//		Prenotazione pre1=new Prenotazione(u1,p1,LocalDate.of(2021, 10, 28));
//		cr.save(c1);
//		er.save(e1);
//		er.save(e2);
//		pr.save(p1);
//		prenotazioneRepository.save(pre1);
//		System.out.println(prenotazioneRepository.findById((long) 1).toString());
	}
	

}
