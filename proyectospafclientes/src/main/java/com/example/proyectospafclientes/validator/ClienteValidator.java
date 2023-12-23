package com.example.proyectospafclientes.validator;

import com.example.proyectospafclientes.entity.Cliente;
import com.example.proyectospafclientes.exceptions.ValidateServiceException;

public class ClienteValidator {
	public static void save(Cliente cliente) {
		if(cliente.getNombre()==null || cliente.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(cliente.getNombre().length()>150) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
		if(cliente.getTipodocumento()=="D") {
			if(cliente.getNrodocumento().length()!=8) {
				throw new ValidateServiceException("El dni debe tener 8 digitos");
			}
			
		}
		if(cliente.getTipodocumento()=="R") {
			if(cliente.getNrodocumento().length()!=11) {
				throw new ValidateServiceException("El ruc debe tener 11 digitos");
			}
		}
	
		
		
	}
}
