package com.example.proyectospaf.validator;

import com.example.proyectospaf.entity.Producto;
import com.example.proyectospaf.exceptions.ValidateServiceException;

public class ProductoValidator {
	public static void save(Producto producto) {
		if(producto.getNombre()==null || producto.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(producto.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
		if(producto.getPrecio()<0) {
			throw new ValidateServiceException("El precio no puede ser negativo");
		}
	}
}
