package com.example.proyectospafventas.dto;

import java.util.Date;
import java.util.List;
import com.example.proyectospafventas.entity.VentaDetalle;
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
public class VentaDTO {
	private int idVenta;
	private int idpersona;
	private List<VentaDetalle> venta;
	private Date fecha;
	private Double total;

}
