package com.generation.progettofilm.progetto_film.auth.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_info")
public class UserAdditionalInfo 
{

    @Id
    @Column(name = "user_id")
    private int id;

    private String nome;
    private String cognome;
    private int eta;
    private LocalDate dob;
    private String indirizzo;


    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
