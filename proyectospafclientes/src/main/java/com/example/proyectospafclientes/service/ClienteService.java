package com.example.proyectospafclientes.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.proyectospafclientes.entity.Cliente;

public interface ClienteService {
	public List<Cliente> findAll(Pageable page);
	public Cliente findById(int id);
	public Cliente findByNombre(String nombre);
	public List<Cliente> findByNombreContaining(String nombre,Pageable page);
    public Cliente create(Cliente cliente);
    public Cliente update(Cliente cliente);
    public void delete(int id);

}
