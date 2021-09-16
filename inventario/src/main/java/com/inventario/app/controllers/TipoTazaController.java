package com.inventario.app.controllers;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.app.entity.Taza;
import com.inventario.app.entity.TipoTaza;
import com.inventario.app.service.TipoTazaService;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/inventario/tipo-taza")
public class TipoTazaController {
	
	@Autowired
	private TipoTazaService TipoTazaService;
	
	
	//Create new cup
	@PostMapping

	public  ResponseEntity<?> create (@RequestBody TipoTaza tipoTaza){
		return ResponseEntity.status(HttpStatus.CREATED).body(TipoTazaService.save(tipoTaza));
	}
	
	//read cup
	@GetMapping("/{id}")
	public ResponseEntity<?> read (@PathVariable Long id){
		Optional<TipoTaza> oTipoTaza = TipoTazaService.findById(id);
		if(!oTipoTaza.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oTipoTaza);
	}
	
	@GetMapping("")
	public List<TipoTaza> readAll (){
		List<TipoTaza> tipoTaza = StreamSupport
				.stream(TipoTazaService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return tipoTaza;
	}
	
	@PutMapping("editar-tipo-taza/{id}")
	public ResponseEntity<?> update (@RequestBody TipoTaza tazaDetails, @PathVariable (value = "id") Long id){
		Optional<TipoTaza> oTipoTaza = TipoTazaService.findById(id);
		
		if(!oTipoTaza.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		oTipoTaza.get().setTipo_taza(tazaDetails.getTipo_taza());
		return ResponseEntity.status(HttpStatus.CREATED).body(TipoTazaService.save(oTipoTaza.get()));
	}
	
}
