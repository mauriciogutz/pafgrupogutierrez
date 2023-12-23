package com.example.proyectospafclientes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.proyectospafclientes.entity.Cliente;
import com.example.proyectospafclientes.exceptions.GeneralServiceException;
import com.example.proyectospafclientes.exceptions.NoDataFoundException;
import com.example.proyectospafclientes.exceptions.ValidateServiceException;
import com.example.proyectospafclientes.repository.ClienteRepository;
import com.example.proyectospafclientes.service.ClienteService;
import com.example.proyectospafclientes.validator.ClienteValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteServiceImpl  implements ClienteService{
	
	@Autowired
	private  ClienteRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll(Pageable page) {
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
	public Cliente findById(int id) {
		try {
			Cliente registro = repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
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
	public Cliente findByNombre(String nombre) {
		try {
			Cliente registro = repository.findByNombre(nombre);
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
	public List<Cliente> findByNombreContaining(String nombre, Pageable page) {
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
	public Cliente create(Cliente cliente) {
		try {
			ClienteValidator.save(cliente);
			if(repository.findByNombre(cliente.getNombre()) != null) {
				throw new ValidateServiceException("existe un registro con el nombre");
				
			}
			cliente.setActivo(true);
			Cliente registro = repository.save(cliente);
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
	public Cliente update(Cliente cliente) {
		try {
			ClienteValidator.save(cliente);
			Cliente registro=repository.findById(cliente.getId()).orElseThrow(()->new NoDataFoundException("no existe Registro con ese ID"));
			Cliente registroD=repository.findByNrodocumento(cliente.getNrodocumento());
			if( registroD != null && registroD.getId()!= cliente.getId()) {
				throw new ValidateServiceException("existe un registro con el nombre");
			}
			registro.setNombre(cliente.getNombre());
			registro.setApellidos(cliente.getApellidos());
			registro.setDireccion(cliente.getDireccion());
			registro.setNrodocumento(cliente.getNrodocumento());
			registro.setTipodocumento(cliente.getTipodocumento());
			
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
			Cliente registro = repository.findById(id).orElseThrow(()-> new NoDataFoundException("no existe un registro con ese ID"));
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
