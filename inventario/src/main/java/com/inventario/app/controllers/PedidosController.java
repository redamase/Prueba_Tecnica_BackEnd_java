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

import com.inventario.app.entity.Pedidos;
import com.inventario.app.entity.Taza;
import com.inventario.app.service.PedidosService;
import com.inventario.app.service.TazaService;


@CrossOrigin
@RestController
@RequestMapping(path = "/api/inventario/pedidos")
public class PedidosController {
	
	@Autowired
	private PedidosService pedidosService;
	@Autowired
	private TazaService tazaService;
	//private TazaServiceImpl tazaServiceImplm;
	//Create new order
	@PostMapping

	public  ResponseEntity<?> create (@RequestBody Pedidos pedidos){
		if(pedidos.getCantidad_total() >=10 && pedidos.getTipo_taza().equals("Calidad Alta")) {
			pedidos.setCantidad_regalo(3);
			pedidos.setTipo_taza_regalo("Calidad Baja");
		}
		else if(pedidos.getCantidad_total() >=10 && pedidos.getTipo_taza().equals("Calidad Baja")) {
			pedidos.setCantidad_regalo(2);
			pedidos.setTipo_taza_regalo("Calidad Baja");
		}
		else if(pedidos.getCantidad_total() < 10 ) {
			pedidos.setCantidad_regalo(0);
			pedidos.setTipo_taza_regalo("");
		}
			
		Optional<Taza> oTaza = tazaService.findById((Long) pedidos.getId_taza());
		if(!oTaza.isPresent()) {
			return ResponseEntity.notFound().build();
		}else  {
			if(pedidos.getCantidad_total() < oTaza.get().getCantidad_disponible()) {
				long total = oTaza.get().getCantidad_disponible() - pedidos.getCantidad_total();
				oTaza.get().setCantidad_disponible(total);
				tazaService.save(oTaza.get());
				return ResponseEntity.status(HttpStatus.CREATED).body(pedidosService.save(pedidos));
			}else {
				System.out.println("Se paso de pedido");
				return ResponseEntity.status(406).body("No puede sobrepasar la cantidad de tazas disponibles");
			}
			
		}
		
		
		//return ResponseEntity.status(HttpStatus.CREATED).body(pedidosService.save(pedidos));
	}
	
	
	//read order
	@GetMapping("/{id}")
	public ResponseEntity<?> read (@PathVariable Long id){
		Optional<Pedidos> oPedidos = pedidosService.findById(id);
		if(!oPedidos.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oPedidos);
	}
	
	@GetMapping("")
	public List<Pedidos> readAll (){
		List<Pedidos> pedidos = StreamSupport
			.stream(pedidosService.findAll().spliterator(), false)
			.collect(Collectors.toList());
		return pedidos;
	}
	
	@PutMapping("editar-pedido/{id}")
	public ResponseEntity<?> update (@RequestBody Pedidos pedidosDetails, @PathVariable (value = "id") Long id){
		Optional<Pedidos> oPedidos = pedidosService.findById(id);
		
		if(!oPedidos.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		oPedidos.get().setCantidad_regalo(pedidosDetails.getCantidad_regalo());
		oPedidos.get().setCantidad_total(pedidosDetails.getCantidad_total());
		oPedidos.get().setTipo_taza_regalo(pedidosDetails.getDimensiones());
		oPedidos.get().setPrecio_total(pedidosDetails.getPrecio_total());
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidosService.save(oPedidos.get()));
	}
}
