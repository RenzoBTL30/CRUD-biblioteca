package com.ejercicios.biblioteca.service;

import java.util.List;

import com.ejercicios.biblioteca.model.Libro;

public interface SLibro {
	Libro create(Libro l);
	Libro update(Libro l);
	void delete(int id);
	List<Libro> readAll();
	Libro read(int id);
}
