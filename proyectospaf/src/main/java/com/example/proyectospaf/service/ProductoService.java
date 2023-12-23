package com.example.proyectospaf.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.proyectospaf.entity.Producto;

public interface ProductoService {
	public List<Producto> findAll(Pageable page);
	public Producto findById(int id);
	public Producto findByNombre(String nombre);
	public List<Producto> findByNombreContaining(String nombre,Pageable page);
    public Producto create(Producto producto);
    public Producto update(Producto producto);
    public void delete(int id);
}
