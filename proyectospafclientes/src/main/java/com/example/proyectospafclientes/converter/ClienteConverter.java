package com.example.proyectospafclientes.converter;
import org.springframework.stereotype.Component;

import com.example.proyectospafclientes.dto.ClienteDTO;
import com.example.proyectospafclientes.entity.Cliente;
@Component
public class ClienteConverter extends AbstractConverter<Cliente,ClienteDTO> {

	@Override
	public ClienteDTO fromEntity(Cliente entity) {
		if(entity==null) return null;
		return ClienteDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.apellidos(entity.getApellidos())
				.tipodocumento(entity.getTipodocumento())
				.nrodocumento(entity.getNrodocumento())
				.direccion(entity.getDireccion())
				.build();
	}

	@Override
	public Cliente fromDTO(ClienteDTO dto) {
		if(dto==null) return null;
		return Cliente.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.apellidos(dto.getApellidos())
				.tipodocumento(dto.getTipodocumento())
				.nrodocumento(dto.getNrodocumento())
				.direccion(dto.getDireccion())
				.build();
	}

}
