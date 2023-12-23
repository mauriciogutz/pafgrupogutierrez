package com.example.proyectospafventas.entity;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="detalleVenta")
@EntityListeners(AuditingEntityListener.class)
public class VentaDetalle {
	
	
	@Id
	@Column(name="idDetalleVenta",columnDefinition="smallint")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalleVenta;
	
	@Column(name="precio", nullable = false)
	private Double precio;
	
	@Column(name="cantidad",columnDefinition="smallint")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Double cantidad;
	
	@Column(name="subTotal", nullable = true)
	private Double subTotal;
	
	@Column(name="idproducto",columnDefinition="smallint")
	private int idproducto;

	@Column(name="created_at",nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(name="updated_at",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	@Column(name="activo",nullable=false)
	private boolean activo;
	
	public Double getSubTotal(){
        if (this.precio >0  && this.cantidad >0 ){
            return this.cantidad * this.precio;
        }else {
            return (double) 0;
        }
    }
    public VentaDetalle(){
        this.cantidad=(double)0;
        this.precio=(double) 0;
    }

}
