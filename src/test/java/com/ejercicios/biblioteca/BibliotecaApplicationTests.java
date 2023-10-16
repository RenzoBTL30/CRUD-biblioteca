package com.ejercicios.biblioteca;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ejercicios.biblioteca.model.Libro;
import com.ejercicios.biblioteca.service.LibroService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringJUnitConfig
@SpringBootTest
class BibliotecaApplicationTests {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService libroService;
    
    @Test
    public void testCrearLibro() throws Exception {
        Libro libro = new Libro();
        
        when(libroService.create(any(Libro.class))).thenAnswer(invocation -> {
            Libro libroCreado = invocation.getArgument(0);
            libroCreado.setIdlibro(1);
            return libroCreado;
        });

        mockMvc.perform(MockMvcRequestBuilders.post("/api/libro/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(libro)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idlibro").exists());
    }
    
    @Test
    public void testListarLibros() throws Exception {
    	
    	when(libroService.readAll()).thenReturn(Arrays.asList(new Libro(), new Libro()));
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/api/libro/listar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }
    
    @Test
    public void testLeerLibro() throws Exception {
        int id = 1;
                
        Libro libro = new Libro();
        libro.setIdlibro(id);
        libro.setTitulo("El delfín");
        
        when(libroService.read(id)).thenReturn(libro);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/libro/read/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idlibro").value(id));
    }
    
    
    @Test
    public void testEliminarLibro() throws Exception {
        int id = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/libro/delete/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        
        verify(libroService, times(1)).delete(id);
    }
    

    
    @Test
    public void testActualizarLibro() throws Exception {
        int id = 1;
        Libro libro = new Libro();
        libro.setIdlibro(id);

        when(libroService.read(id)).thenReturn(libro);
        when(libroService.create(any(Libro.class))).thenAnswer(invocation -> {
            Libro libroActualizado = invocation.getArgument(0);
            return libroActualizado;
        });

        mockMvc.perform(MockMvcRequestBuilders.put("/api/libro/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(libro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idlibro").value(id));
    }
    

    
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	
}
