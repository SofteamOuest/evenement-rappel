package fr.softeam.evenementrappel;

import fr.softeam.evenementrappel.mail.EmailServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EvenementRappelApplicationTests {

	@Autowired
	private EmailServiceImpl emailService;

	@Test
	public void contextLoads() {
		String to = "paul-jakez.boceno@softeam.fr";
		String sujet = "Rappel point accueil";
		String message = "Le point d'accueil du callaborateur XX est le dd/MM/yyyy";
		emailService.sendSimpleMessage(to,sujet,message);
	}

}
