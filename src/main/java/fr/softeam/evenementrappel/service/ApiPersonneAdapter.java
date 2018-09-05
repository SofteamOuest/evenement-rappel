package fr.softeam.evenementrappel.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import fr.softeam.evenementrappel.dto.Personne;
import fr.softeam.evenementrappel.exception.AppelPersonneApiException;

@Service
public class ApiPersonneAdapter {

    private RestTemplate restTemplate;
    @Value("${personne_api_uri}")
    private String PERSONNE_API_URI;

    public ApiPersonneAdapter(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Personne getPersonneParId(String idPersonne) throws AppelPersonneApiException{
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> entity = new HttpEntity<>("", headers);
            ResponseEntity<Personne> responseEntity =
                    restTemplate.exchange(PERSONNE_API_URI +"/"+idPersonne,
                            HttpMethod.GET,
                            entity,
                            Personne.class);
            return responseEntity.getBody();
        }catch(HttpClientErrorException  exception){
            throw new AppelPersonneApiException(exception.getResponseBodyAsString());
        }

    }
}
