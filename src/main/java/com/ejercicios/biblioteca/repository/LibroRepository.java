package com.ejercicios.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejercicios.biblioteca.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer>{

}
