package com.generation.progettofilm.progetto_film.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.progettofilm.progetto_film.model.Film;


public interface FilmRepository extends JpaRepository<Film,Integer>
{
    Optional<Film> findByTitolo(String titolo);
}
