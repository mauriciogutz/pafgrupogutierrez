package com.example.proyectospaf.validator;

import com.example.proyectospaf.entity.Categoria;
import com.example.proyectospaf.exceptions.ValidateServiceException;

public class CategoriaValidator {
	public static void save(Categoria categoria) {
		if(categoria.getNombre()==null || categoria.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(categoria.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
	}
}
