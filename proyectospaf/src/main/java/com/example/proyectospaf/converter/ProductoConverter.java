package com.example.proyectospaf.converter;

import org.springframework.stereotype.Component;

import com.example.proyectospaf.dto.ProductoDTO;
import com.example.proyectospaf.entity.Producto;

@Component
public class ProductoConverter extends AbstractConverter<Producto,ProductoDTO>{

	@Override
	public ProductoDTO fromEntity(Producto entity) {
		if(entity==null) return null;
		return ProductoDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.precio(entity.getPrecio())
				.categoria(entity.getCategoria())
				.build();
	}

	@Override
	public Producto fromDTO(ProductoDTO dto) {
		if(dto==null) return null;
		return Producto.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.precio(dto.getPrecio())
				.categoria(dto.getCategoria())
				.build();
	}

}
