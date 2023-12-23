package com.example.proyectospaf.converter;

import org.springframework.stereotype.Component;

import com.example.proyectospaf.dto.CategoriaDTO;
import com.example.proyectospaf.entity.Categoria;
@Component
public class CategoriaConverter extends AbstractConverter<Categoria,CategoriaDTO>{

	@Override
	public CategoriaDTO fromEntity(Categoria entity) {
		if(entity==null) return null;
		return CategoriaDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.descripcion(entity.getDescripcion())
				.build();
	}

	@Override
	public Categoria fromDTO(CategoriaDTO dto) {
		if(dto==null) return null;
		return Categoria.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.descripcion(dto.getDescripcion())
				.build();
	}
	
}
