package com.ejercicios.biblioteca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicios.biblioteca.model.Libro;
import com.ejercicios.biblioteca.service.LibroService;


@RestController
@RequestMapping("/api/libro")
public class LibroController {
	
	@Autowired
	private LibroService libroService;
	
		@PostMapping("/crear")
		public ResponseEntity<Libro> save(@RequestBody Libro Libro) {
			try {
				Libro l = libroService.create(new Libro(Libro.getIdlibro(), Libro.getTitulo(), Libro.getFecha_lanzamiento(), Libro.getAutor(), Libro.getCategoria(), Libro.getEditorial(), Libro.getIdioma(), Libro.getPaginas(), Libro.getDescripcion()));
			    return new ResponseEntity<>(l,HttpStatus.CREATED);
			} catch (Exception e) {
				// TODO: handle exception
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		@GetMapping("/listar")
		public ResponseEntity<List<Libro>> getAllLibro(){
			try {
				List<Libro> libros = new ArrayList<Libro>();
				libros = libroService.readAll();
				if(libros.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					
				}
				return new ResponseEntity<>(libros, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		@GetMapping("/read/{idlibro}")
		public ResponseEntity<Libro> getUser(@PathVariable("idlibro") int id){
			Libro libro = libroService.read(id);
				if(libro.getIdlibro()>0) {
					return new ResponseEntity<>(libro, HttpStatus.OK);
				}else {
				
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				} 
		}
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
			try {
				libroService.delete(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		@PutMapping("/update/{id}")
		public ResponseEntity<Libro> update(@RequestBody Libro libro, @PathVariable("id") int id){
			try {
				Libro pp = libroService.read(id);
				if(pp.getIdlibro()>0) {
					pp.setTitulo(libro.getTitulo());
					pp.setFecha_lanzamiento(libro.getFecha_lanzamiento());
					pp.setAutor(libro.getAutor());
					pp.setCategoria(libro.getCategoria());
					pp.setEditorial(libro.getEditorial());
					pp.setIdioma(libro.getIdioma());
					pp.setPaginas(libro.getPaginas());
					pp.setDescripcion(libro.getDescripcion());
					
					return new ResponseEntity<>(libroService.create(pp),HttpStatus.OK);
				}else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}			

			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
}
