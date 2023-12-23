package com.example.proyectospaf.dto;

import com.example.proyectospaf.entity.Categoria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
	private int id;
	private String nombre;
	private double precio;
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Categoria categoria;
}
