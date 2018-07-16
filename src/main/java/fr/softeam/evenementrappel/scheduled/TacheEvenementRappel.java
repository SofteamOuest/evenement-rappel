package fr.softeam.evenementrappel.scheduled;

import fr.softeam.evenementrappel.dto.EvenementRappel;
import fr.softeam.evenementrappel.dto.Personne;
import fr.softeam.evenementrappel.exception.AppelPersonneApiException;
import fr.softeam.evenementrappel.exception.EvenementRappelException;
import fr.softeam.evenementrappel.mail.EmailServiceImpl;
import fr.softeam.evenementrappel.service.ApiGroupePersonneAdapter;
import fr.softeam.evenementrappel.service.ApiPersonneAdapter;
import fr.softeam.evenementrappel.service.EvenementParcoursIntegrationAdapter;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@Component
public class TacheEvenementRappel {

	private ApiGroupePersonneAdapter apiGroupePersonneAdapter;

	private EvenementParcoursIntegrationAdapter evenementParcoursIntegrationAdapter;

	private EmailServiceImpl emailService;

	private ApiPersonneAdapter apiPersonneAdapter;

	private static final Logger logger = LoggerFactory.getLogger(TacheEvenementRappel.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	public TacheEvenementRappel(ApiGroupePersonneAdapter apiGroupePersonneAdapter,
			EvenementParcoursIntegrationAdapter evenementParcoursIntegrationAdapter, EmailServiceImpl emailService,
			ApiPersonneAdapter apiPersonneAdapter) {
		this.apiGroupePersonneAdapter = apiGroupePersonneAdapter;
		this.evenementParcoursIntegrationAdapter = evenementParcoursIntegrationAdapter;
		this.emailService = emailService;
		this.apiPersonneAdapter = apiPersonneAdapter;
	}

	 @Scheduled(cron="0 0 6 * * *")
	public void sendRappel() throws EvenementRappelException, AppelPersonneApiException {
		logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
		List<EvenementRappel> evenementRappelList = evenementParcoursIntegrationAdapter.getEvenementsARappeler();
		for (EvenementRappel evenementRappel : evenementRappelList) {
			Personne personne = apiPersonneAdapter.getPersonneParId((evenementRappel.getIdPersonne()));
			for (String groupe : evenementRappel.getDestinataires()) {
				switch (groupe) {
				case "rh":
					for (String personneRh : personne.getMAilRh()) {
						sendMailParGroup(evenementRappel, personneRh, personne.getNom(), personne.getPrenom());
					}
					break;
				case "manager":
					sendMailParGroup(evenementRappel, personne.getMail_manager(), personne.getNom(),
							personne.getPrenom());
					break;
				case "commerce":
					sendMailParGroup(evenementRappel, personne.getMail_commercial(), personne.getNom(),
							personne.getPrenom());
					break;
				}
			}
		}
	}

	private void sendMailParGroup(EvenementRappel evenementRappel, String mail, String nom, String prenom) {
		String message = "L'évènement " + evenementRappel.getInformationEvenement().getNom() + " du collaborateur "
				+ prenom + " " + nom + " est le " + evenementRappel.getInformationEvenement().getDateEvenement() + ".";
		emailService.sendSimpleMessage(mail, "Rappel évènement", message);
	}

}
