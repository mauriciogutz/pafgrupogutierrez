package com.example.proyectospafventas.service;

import java.util.List;

import com.example.proyectospafventas.entity.Venta;


public interface VentaService {
	public List<Venta> findAll();
	public Venta findById(int id);
    public Venta create(Venta obj);
    public Venta update(Venta obj);
    public int delete(int id);
}
