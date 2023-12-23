package com.example.proyectospaf.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.proyectospaf.entity.Categoria;



public interface CategoriaService {
	public List<Categoria> findAll(Pageable page);
	public Categoria findById(int id);
	public Categoria findByNombre(String nombre);
	public List<Categoria> findByNombreContaining(String nombre,Pageable page);
    public Categoria create(Categoria categoria);
    public Categoria update(Categoria categoria);
    public void delete(int id);
    
    
    
}
