package com.generation.progettofilm.progetto_film.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.generation.progettofilm.progetto_film.dto.FilmDTO;
import com.generation.progettofilm.progetto_film.model.Film;

@Mapper
public interface FilmMapper 
{

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    @Mapping(source = "dob",target = "dob", dateFormat = "yyyy-MM-dd")
    FilmDTO filmToFilmDTO(Film film);


    @Mapping(source = "dob",target = "dob", dateFormat = "yyyy-MM-dd")
    Film filmDTOToFilm(FilmDTO filmDTO);
}

