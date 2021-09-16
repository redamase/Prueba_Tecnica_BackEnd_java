package com.inventario.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.app.entity.Taza;
import com.inventario.app.repository.TazaRepository;

@Service
public class TazaServiceImpl implements TazaService{

	@Autowired
	private TazaRepository tazaRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Taza> findAll() {
		return tazaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Taza> findAll(Pageable pageable) {
		return tazaRepository.findAll(pageable);
	}
	

	@Override
	@Transactional(readOnly = true)
	public Optional<Taza> findById(Long id) {
		return tazaRepository.findById(id);
	}

	@Override
	@Transactional
	public Taza save(Taza taza) {
		return tazaRepository.save(taza);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		tazaRepository.deleteById(id);
	}
	
	
	@Override
	@Transactional(readOnly = true)
    public List<Taza> findByNotEliminated() {

        List<Taza> tazas = tazaRepository.findByNotEliminated();
        return tazas;
    }
	

}
