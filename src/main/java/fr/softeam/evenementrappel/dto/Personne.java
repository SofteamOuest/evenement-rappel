package fr.softeam.evenementrappel.dto;

import java.util.ArrayList;
import java.util.List;

public class Personne {

    private String id;
    private String nom;
    private String prenom;
    private String mail_manager;
    private String mail_commercial;

    public Personne() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

	public String getMail_manager() {
		return mail_manager;
	}

	public void setMail_manager(String mail_manager) {
		this.mail_manager = mail_manager;
	}

	public String getMail_commercial() {
		return mail_commercial;
	}

	public void setMail_commercial(String mail_commercial) {
		this.mail_commercial = mail_commercial;
	}
	public List<String> getMAilRh(){
		List<String> listeMailRh= new ArrayList<>();
		listeMailRh.add("elmouddensaf@gmail.com");
		listeMailRh.add("safaelmoudden@gmail.com");
		return listeMailRh;
	}
}
