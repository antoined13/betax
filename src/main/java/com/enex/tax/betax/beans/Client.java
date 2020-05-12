package com.enex.tax.betax.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.validation.constraints.*;

//import java.util.Date;

@Entity
public class Client{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @NotEmpty(message = "Le numéro national est obligatoire")
    @Pattern(message = "Le numéro national doit contenir uniquement 11 chiffres",regexp="\\d{11}")
    private String nn;
    @NotEmpty(message = "Le nom est obligatoire")
    private String name;
    @NotEmpty(message = "Le prénom est obligatoire")
    private String firstname;
    @Email(message = "L'adresse email n'est pas valide")
    private String mail;
    private String adress;
    private String phoneNumber;
    /*private LocalDate dateLastUpdate = LocalDate.now();
    private boolean reqUpdate = false;*/
    /*private boolean c100166;
    private boolean c100265;
    private boolean c100364;
    private boolean c100463;
    private boolean c101057;
    private boolean c101849;
    private boolean c101948;*/

    public int getId(){
        return id;
    }
    public void setId(int i){
        this.id = i;
    }
    public String getNN(){
        return nn;
    }
    public void setNN(String n){
        this.nn = n;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String n){
        this.name = n;
    }
    public String getFirstname(){
        return this.firstname;
    }
    public void setFirstname(String n){
        this.firstname = n;
    }
    public String getMail(){
        return mail;
    }
    public void setMail(String n){
        this.mail = n;
    }
    public String getAdress(){
        return adress;
    }
    public void setAdress(String n){
        this.adress = n;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String n){
        this.phoneNumber = n;
    }

    
}