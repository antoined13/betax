package com.enex.tax.betax.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.validation.constraints.*;

@Entity
public class Client{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @NotNull
    @NotEmpty(message = "le nom est obligatoire")
    private String nom;
    private String nomConjoint;
    @NotNull
    @NotEmpty(message = "le prénom est obligatoire")
    private String prenom;
    private String prenomConjoint;
    @NotNull
    @NotEmpty(message = "le numéro national est obligatoire")
    private String numeroNational;
    private String numeroNationalConjoint;
    private boolean handicap;
    private boolean handicapConjoint;
    private String adresse;
    private String telephone;
    @Email(message = "l'email n'est pas valide")
    private String email;

    public String getNom(){
        return nom;
    }
    public void setNom(String nom){
        this.nom=nom;
    }
    public String getNomConjoint(){
        return nomConjoint;
    }
    public void setNomConjoint(String nomConjoint){
        this.nomConjoint=nomConjoint;
    }
    public String getPrenom(){
        return prenom;
    }
    public void setPrenom(String prenom){
        this.prenom=prenom;
    }
    public String getPrenomConjoint(){
        return prenomConjoint;
    }
    public void setPrenomConjoint(String prenomConjoint){
        this.prenomConjoint=prenomConjoint;
    }
    public String getNumeroNational(){
        return numeroNational;
    }
    public void setNumeroNational(String numeroNational){
        this.numeroNational=numeroNational;
    }
    public String getNumeroNationalConjoint(){
        return numeroNationalConjoint;
    }
    public void setNumeroNationalConjoint(String numeroNationalConjoint){
        this.numeroNationalConjoint=numeroNationalConjoint;
    }
    public boolean getHandicap(){
        return handicap;
    }
    public void setHandicap(boolean handicap){
        this.handicap=handicap;
    }
    public boolean getHandicapConjoint(){
        return handicapConjoint;
    }
    public void setHandicapConjoint(boolean handicapConjoint){
        this.handicapConjoint=handicapConjoint;
    }
    public String getAdresse(){
        return adresse;
    }
    public void setAdresse(String adresse){
        this.adresse=adresse;
    }
    public String getTelephone(){
        return telephone;
    }
    public void setTelephone(String telephone){
        this.telephone=telephone;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    
}