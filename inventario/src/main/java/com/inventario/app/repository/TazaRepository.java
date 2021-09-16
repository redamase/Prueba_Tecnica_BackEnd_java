package com.inventario.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inventario.app.entity.Taza;

@Repository
public interface TazaRepository extends JpaRepository<Taza, Long>{

	@Query ("select t from Taza t WHERE t.eliminado = 0")
	public List<Taza> findByNotEliminated();
	
	
}
