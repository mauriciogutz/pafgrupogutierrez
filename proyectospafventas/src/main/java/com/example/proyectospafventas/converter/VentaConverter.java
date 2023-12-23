package com.example.proyectospafventas.converter;
import org.springframework.stereotype.Component;

import com.example.proyectospafventas.dto.VentaDTO;
import com.example.proyectospafventas.entity.Venta;
@Component
public class VentaConverter extends AbstractConverter<Venta,VentaDTO> {

	@Override
	public VentaDTO fromEntity(Venta entity) {
		if ( entity == null) return null;
		return VentaDTO.builder()
						.idVenta(entity.getIdVenta())
						.idpersona(entity.getIdpersona())
						.fecha(entity.getFecha())
						.total(entity.getTotal())
						.venta(entity.getVenta())
						.build();
	}

	@Override
	public Venta fromDTO(VentaDTO dto) {
		if ( dto == null) return null;
		return Venta.builder()
				.idVenta(dto.getIdVenta())
				.idpersona(dto.getIdpersona())
				.fecha(dto.getFecha())
				.total(dto.getTotal())
				.venta(dto.getVenta())
				.build();
	}

}
