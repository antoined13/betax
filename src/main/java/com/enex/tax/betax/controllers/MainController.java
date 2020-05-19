package com.enex.tax.betax.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.util.*;
import java.lang.Iterable;
import javax.mail.*;
import java.lang.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import org.apache.commons.lang3.RandomStringUtils;

import com.enex.tax.betax.repositories.ClientRepository;
import com.enex.tax.betax.beans.Client;

@RestController
public class MainController{

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping(path="/ajclient",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addNewClient(@Valid @RequestBody Client client,BindingResult result){
        //IMPLEMENTER RESPONSEENTITY
        String response;
        if(result.hasErrors()){
            response = "{\"result\": false,\"message\": \""+result.getFieldError().getDefaultMessage()+"\"}";
        } else {
            //VERIFIER SI CLIENT PAS DEJA EXISTANT
            clientRepository.save(client);
            response = "{\"result\": true,\"message\": \"Vos données ont bien été envoyées.\"}";
        }
        return response;
        
    }

    @PostMapping(path="/update-client",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateClient(@Valid @RequestBody Client receivedClient){
        //On vérifie que le client existe déjà
        if(clientRepository.existsById(receivedClient.getId())){
            clientRepository.save(receivedClient);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @GetMapping(path="/get-client-list",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Client> getClientList(){
        List<Client> returnList = new ArrayList<Client>();

        for(Client cl:clientRepository.findAll()){
            returnList.add(cl);
        }
        return returnList;
    }

    @GetMapping(path="/send-update-mail/{nn}")
    public void sendUpdateMail(@PathVariable String nn){
        //IMPLEMENTER RESPONSEENTITY
        Client cl = clientRepository.findByNumeroNational(nn);
        //A SECURISER
        //GERER LE CAS OU IL N Y A PAS D EMAIL ATTACHE AU CLIENT
        //GERER LE CAS OU LA DEMANDE A DEJA ETE ENVOYEE

        //A SECURISER
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        cl.setUpdateRequestPass(RandomStringUtils.random( 30, characters ));
        clientRepository.save(cl);

        final String username = "betax.terracompta@gmail.com";
        final String password = "azertyp-e13";

        final String email = cl.getEmail();

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("fiduterracompta@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("TerraCompta - déclaration fiscale");
            message.setText("Bonjour,"
                    + "\n\n Afin de réaliser votre déclaration fiscale j'ai besoin que vous me communiquiez certaines informations."
                    + "\n\n Vous trouverez ci-dessous un lien et un mot de passe vous permettant de mettre à jour votre dossier virtuel:"
                    + "\n\n http://localhost:8080/updateforms/updateform.html?nn="+nn+"&pass="+cl.getUpdateRequestPass()
                    + "\n\n ");

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(path="/get-client/{nn}/{pass}")
    public Client getClient(@PathVariable String nn, @PathVariable String pass){

        try{
            Client cl = clientRepository.findByNumeroNational(nn);
            if(cl.getUpdateRequestPass().equals(pass)){
                cl.setUpdateRequestPass(null);
                clientRepository.save(cl);
                return cl;
            } else {
                throw new IllegalArgumentException();
            }
        }catch(NullPointerException e){
            throw new IllegalArgumentException();
        }
        
    }

    @GetMapping(path="/delete-client/{nn}")
    public void deleteClient(@PathVariable String nn){
        clientRepository.deleteById(clientRepository.findByNumeroNational(nn).getId());
    }
}
