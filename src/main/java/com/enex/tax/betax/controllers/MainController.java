package com.enex.tax.betax.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.validation.Valid;

import com.enex.tax.betax.repositories.ClientRepository;
import com.enex.tax.betax.beans.Client;

@RestController
public class MainController{

    @Autowired
    private ClientRepository clientRepository;

    //ADAPTER CETTE METHOD POUR RENVOYER JSON ET PAS STRING et en faire une CRUD
    @PostMapping(path="/ajclient",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addNewClient(@Valid @RequestBody Client client,BindingResult result){
        
        String response;
        if(result.hasErrors()){
            response = "{\"result\": false,\"message\": \""+result.getFieldError().getDefaultMessage()+"\"}";
        } else {
            //VERIFIER SI CLIENT PAS DEJA EXISTANT
            clientRepository.save(client);
            response = "{\"result\": true,\"message\": \"Le client a été enregistré dans la base de donnée.\"}";
        }
        return response;
        
    }
    @GetMapping(path="/clients",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ArrayList<String,String> (){

        
        
    }

}
