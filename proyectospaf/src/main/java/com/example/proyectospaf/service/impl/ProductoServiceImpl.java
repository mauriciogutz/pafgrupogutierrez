package com.example.proyectospaf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.proyectospaf.entity.Producto;
import com.example.proyectospaf.exceptions.GeneralServiceException;
import com.example.proyectospaf.exceptions.NoDataFoundException;
import com.example.proyectospaf.exceptions.ValidateServiceException;
import com.example.proyectospaf.repository.ProductoRepository;
import com.example.proyectospaf.service.ProductoService;
import com.example.proyectospaf.validator.ProductoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoRepository repository;

	
	@Override
	@Transactional(readOnly = true)
	public Producto findById(int id) {
		try {
			return repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Producto create(Producto producto) {
		try {
			ProductoValidator.save(producto);
			Producto productoD=findByNombre(producto.getNombre());
			if(productoD!=null) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre");
			}
			
			producto.setActivo(true);
			return repository.save(producto);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	
	}

	@Override
	@Transactional
	public Producto update(Producto producto) {
		try {
			ProductoValidator.save(producto);
			Producto productodb=findById(producto.getId());
			if(productodb==null) {
				throw new ValidateServiceException("No hay un registro con ese ID");
			}
			
			Producto productodbn=findByNombre(producto.getNombre());
			if(productodbn!=null && producto.getId()!=producto.getId()) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre");
			}
			
			productodb.setNombre(producto.getNombre());
			productodb.setPrecio(producto.getPrecio());
			productodb.setCategoria(producto.getCategoria());
			return repository.save(productodb);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Producto registro = repository.findById(id).orElseThrow(()-> new NoDataFoundException("no existe un registro con ese ID"));
			repository.delete(registro);
			
		}catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		}catch (Exception e ) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
			
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
	public List<Producto> findByNombreContaining(String nombre, Pageable page) {
		try {
			return repository.findByNombreContaining(nombre,page);
			
		}catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		}catch (Exception e ) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

}
