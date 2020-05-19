package com.enex.tax.betax.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.validation.constraints.*;
import lombok.Setter;
import lombok.Getter;

@Entity
@Getter
@Setter
public class Client{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @NotNull
    @NotEmpty(message = "le nom est obligatoire")
    private String nom;
    @NotNull
    @NotEmpty(message = "le prénom est obligatoire")
    private String prenom;
    @NotNull
    @NotEmpty(message = "le numéro national est obligatoire")
    private String numeroNational;
    private boolean handicap;
    private String adresse;
    private String telephone;
    @Email(message = "l'email n'est pas valide")
    private String email;
    private String updateRequestPass;
    private EtatCivil etatCivil;

    
}