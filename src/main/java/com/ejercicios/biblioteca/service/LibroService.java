package com.ejercicios.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejercicios.biblioteca.model.Libro;
import com.ejercicios.biblioteca.repository.LibroRepository;


@Service
public class LibroService implements SLibro{
	
	@Autowired
	private LibroRepository libroRepository;

	@Override
	public Libro create(Libro l) {
		// TODO Auto-generated method stub
		return libroRepository.save(l);
	}

	@Override
	public Libro update(Libro l) {
		// TODO Auto-generated method stub
		return libroRepository.save(l);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		libroRepository.deleteById(id);
	}

	@Override
	public List<Libro> readAll() {
		// TODO Auto-generated method stub
		return libroRepository.findAll();
	}

	@Override
	public Libro read(int id) {
		// TODO Auto-generated method stub
		return libroRepository.findById(id).get();
	}

}