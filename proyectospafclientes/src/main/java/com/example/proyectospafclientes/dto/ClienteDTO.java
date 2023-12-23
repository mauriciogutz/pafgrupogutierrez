package com.example.proyectospafclientes.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTO {
	
		private Integer id;
		private String tipodocumento;
		private String nrodocumento;
		private String nombre;
		private String apellidos;
		private String direccion;
	

}
