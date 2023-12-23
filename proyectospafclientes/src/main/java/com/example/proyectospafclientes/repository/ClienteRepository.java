package com.example.proyectospafclientes.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proyectospafclientes.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	public Cliente findByNombre(String nombre);
	public Cliente findByNrodocumento(String nrodocumento);
	public List<Cliente> findByNombreContaining(String nombre,Pageable page);

}
