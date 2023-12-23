package com.example.proyectospafventas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.proyectospafventas.entity.Venta;
import com.example.proyectospafventas.exceptions.GeneralServiceException;
import com.example.proyectospafventas.exceptions.NoDataFoundException;
import com.example.proyectospafventas.exceptions.ValidateServiceException;
import com.example.proyectospafventas.repository.VentaRepository;
import com.example.proyectospafventas.service.VentaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VentaServiceImpl implements VentaService {
	@Autowired
	private  VentaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Venta> findAll() {
		try {
			return repository.findAll();
			
		} catch (NoDataFoundException e ) {
			log.info(e.getMessage(),e);
			throw e;
		}catch (Exception e ) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Venta findById(int id) {
		try {
			Venta registro = repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		}catch (Exception e ) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Venta create(Venta venta) {
		try {
			
			venta.setEstado(true);
			Venta registro = repository.save(venta);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		}catch (Exception e ) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	public Venta update(Venta obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
