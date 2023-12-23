package com.example.proyectospaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.proyectospaf.converter.CategoriaConverter;
import com.example.proyectospaf.dto.CategoriaDTO;
import com.example.proyectospaf.entity.Categoria;
import com.example.proyectospaf.service.CategoriaService;
import com.example.proyectospaf.utils.WrapperResponse;


@RestController
@RequestMapping("/v1/categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	
	@Autowired 
	private CategoriaConverter converter;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll(
	@RequestParam(value="nombre",required=false,defaultValue="")String nombre,
	@RequestParam(value="offset",required = false,defaultValue="0")int pageNumber,
	@RequestParam(value="limit",required = false,defaultValue = "5") int pageSize
			){
		Pageable page = PageRequest.of(pageNumber,pageSize);
		List<Categoria> categorias;
		if (nombre==null) {
			categorias=service.findAll(page);
		}else {
			categorias=service.findByNombreContaining(nombre, page);
			
		}
		/*if (articulos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}*/
		List<CategoriaDTO> articulosDTO = converter.fromEntity(categorias);
		return new WrapperResponse(true,"Success",articulosDTO).createResponse(HttpStatus.OK);
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<WrapperResponse<CategoriaDTO>> findById(@PathVariable("id")int id){
		Categoria categoria = service.findById(id);
		
		CategoriaDTO articuloDTO= converter.fromEntity(categoria);
		return new WrapperResponse<CategoriaDTO>(true, "Success", articuloDTO).createResponse(HttpStatus.OK);
		
	}
	@PostMapping
	public ResponseEntity<CategoriaDTO> create(@RequestBody CategoriaDTO categoriaDTO){
		
		Categoria registro =  service.create(converter.fromDTO(categoriaDTO));
		CategoriaDTO registroDTO = converter.fromEntity(registro);
		return new WrapperResponse(true,"Success",registroDTO).createResponse(HttpStatus.CREATED);
		
	}
	@PutMapping(value="/{id}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable("id") int id, @RequestBody CategoriaDTO categoriaDTO){
		Categoria registro = service.update(converter.fromDTO(categoriaDTO));
		
		CategoriaDTO registroDTO = converter.fromEntity(registro);
		return new WrapperResponse(true,"Success",registroDTO).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<CategoriaDTO>delete(@PathVariable("id") int id){
		service.delete(id);
		return new WrapperResponse(true,"succes",null).createResponse(HttpStatus.OK);
	}
	
}
