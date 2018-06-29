# Rappel d'événements

Scheduler pour l'envoie de mail aux différents collaborateurs.

Ces collaborateur sont définis dans le fichier [application.yml](https://github.com/SofteamOuest/evenement-parcours-integration/blob/master/src/main/resources/application.yml) du service evenement-parcours-integration.

Les différents destinataires des mails sont : 

- Service RH 
- Service commercial 
- Manager du collaborateur concerné par l'événement

Ce sont les attributs d'un événement qui définisse la récurrence de celui-ci et sur qu'elle unités il doit se baser (jours, semaines, mois, années). 

La description d'un évenement est fait [ici](https://app.nuclino.com/MeltingPoc/General/Modle-dcf24205-0e2d-4568-84f4-5d1d1587734e).

## Spécificités technique

Il que les services suivant tournent pour que celui ci marche :
- referentiel-personne-api : https://github.com/SofteamOuest/referentiel-personnes-api
- gestion-evenement : https://github.com/SofteamOuest/gestion-evenement
- evenement-parcours-integration : https://github.com/SofteamOuest/evenement-parcours-integration

## Reste à faire

Il faut envoyer les mails personnes concernées par l'événement.








