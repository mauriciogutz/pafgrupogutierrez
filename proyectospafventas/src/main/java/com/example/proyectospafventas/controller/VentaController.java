package com.example.proyectospafventas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.proyectospafventas.converter.VentaConverter;
import com.example.proyectospafventas.dto.VentaDTO;
import com.example.proyectospafventas.entity.Venta;
import com.example.proyectospafventas.service.VentaService;
import com.example.proyectospafventas.utils.WrapperResponse;


@RestController
@RequestMapping("/v1/venta")
public class VentaController {
	@Autowired
	private VentaService service;
	
	@Autowired 
	private VentaConverter converter;
	
	@GetMapping
	public ResponseEntity<List<VentaDTO>> findAll(){
		List<Venta> ventas;
		ventas=service.findAll();
		List<VentaDTO> ventasDTO = converter.fromEntity(ventas);
		return new WrapperResponse(true,"Success",ventasDTO).createResponse(HttpStatus.OK);
		
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<WrapperResponse<VentaDTO>> findById(@PathVariable("id")int id){
		Venta venta = service.findById(id);
		
		VentaDTO ventaDTO= converter.fromEntity(venta);
		return new WrapperResponse<VentaDTO>(true, "Success", ventaDTO).createResponse(HttpStatus.OK);
		
	}
	@PostMapping
	public ResponseEntity<VentaDTO> create(@RequestBody VentaDTO ventaDTO){
		
		Venta registro =  service.create(converter.fromDTO(ventaDTO));
		VentaDTO registroDTO = converter.fromEntity(registro);
		return new WrapperResponse(true,"Success",registroDTO).createResponse(HttpStatus.CREATED);
		
	}

}
