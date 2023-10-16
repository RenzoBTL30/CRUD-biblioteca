package com.ejercicios.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="libro")
public class Libro {
	 @Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
	 private int idlibro;
	 
	 @Column(name="titulo")
	 private String titulo;
	 
	 @Column(name="fecha_lanzamiento")
	 private String fecha_lanzamiento;
	 
	 @Column(name="autor")
	 private String autor;
	 
	 @Column(name="categoria")
	 private String categoria;
	 
	 @Column(name="editorial")
	 private String editorial;
	 
	 @Column(name="idioma")
	 private String idioma;
	 
	 @Column(name="paginas")
	 private int paginas;
	 
	 @Column(name="descripcion")
	 private String descripcion;
	 
}
