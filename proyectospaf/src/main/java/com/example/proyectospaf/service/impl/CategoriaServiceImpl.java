package com.example.proyectospaf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.proyectospaf.entity.Categoria;
import com.example.proyectospaf.exceptions.GeneralServiceException;
import com.example.proyectospaf.exceptions.NoDataFoundException;
import com.example.proyectospaf.exceptions.ValidateServiceException;
import com.example.proyectospaf.repository.CategoriaRepository;
import com.example.proyectospaf.service.CategoriaService;
import com.example.proyectospaf.validator.CategoriaValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoriaServiceImpl implements CategoriaService{
	@Autowired
	private  CategoriaRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll(Pageable page) {
		
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
	public Categoria findById(int id) {
		
		try {
			Categoria registro = repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
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
	@Transactional(readOnly = true)
	public Categoria findByNombre(String nombre) {
		
		try {
			Categoria registro = repository.findByNombre(nombre);
			return registro;
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
	public List<Categoria> findByNombreContaining(String nombre, Pageable page) {
		try {
			return repository.findByNombreContaining(nombre, page);
			
		}catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		}catch (Exception e ) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Categoria create(Categoria categoria) {
		try {
			CategoriaValidator.save(categoria);
			if(repository.findByNombre(categoria.getNombre()) != null) {
				throw new ValidateServiceException("existe un registro con el nombre");
				
			}
			categoria.setActivo(true);
			Categoria registro = repository.save(categoria);
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
	public Categoria update(Categoria categoria) {
		try {
			CategoriaValidator.save(categoria);
			Categoria registro=repository.findById(categoria.getId()).orElseThrow(()->new NoDataFoundException("no existe REgistro con ese ID"));
			Categoria registroD=repository.findByNombre(categoria.getNombre());
			if( registroD != null && registroD.getId()!= categoria.getId()) {
				throw new ValidateServiceException("existe un registro con el nombre");
				
			}
			registro.setNombre(categoria.getNombre());
			registro.setDescripcion(categoria.getDescripcion());
			repository.save(registro);
			return registro;
			
		}catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		}catch (Exception e ) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Categoria registro = repository.findById(id).orElseThrow(()-> new NoDataFoundException("no existe un registro con ese ID"));
			repository.delete(registro);
			
		}catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		}catch (Exception e ) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
		
	}

}
