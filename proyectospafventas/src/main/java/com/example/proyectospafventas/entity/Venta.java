package com.example.proyectospafventas.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "venta")
@EntityListeners(AuditingEntityListener.class)
public class Venta {
	@jakarta.persistence.Id
	@Column(name="idVenta",columnDefinition="smallint")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVenta;
	
	@Column(name="idpersona",columnDefinition="smallint")
	private int idpersona;
	
	@Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idVent")
    private List<VentaDetalle> venta;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(name="total", nullable = false)
	private Double total;
	
	@Column(name="created_at",nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(name="updated_at",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	@Column(name="estado",nullable=false)
	private boolean estado;
	
	public Venta(){
		venta = new ArrayList<>();
    	
    }
}
