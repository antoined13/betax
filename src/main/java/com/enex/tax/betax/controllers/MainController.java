package com.enex.tax.betax.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.lang.Iterable;

import com.enex.tax.betax.repositories.ClientRepository;
import com.enex.tax.betax.beans.Client;

@RestController
public class MainController{

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping(path="/ajclient",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addNewClient(@Valid @RequestBody Client client,BindingResult result){

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

    @GetMapping(path="/get-client-list",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Client> getClientList(){
        List<Client> returnList = new ArrayList<Client>();

        for(Client cl:clientRepository.findAll()){
            returnList.add(cl);
        }
        return returnList;
    }
}
