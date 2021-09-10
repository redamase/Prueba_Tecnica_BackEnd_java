package com.inventario.app.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taza")

public class Taza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50)
	private String tipo_taza;
	private String color;
	private String dimensiones;
	private String capacidad;
	private String modelo;
	private String material;
	private Long cantidad_total;
	static Long cantidad_disponible;
	private float precio_compra;
	private float precio_venta;
	private float precio_final_compra;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTipo_taza() {
		return tipo_taza;
	}
	public void setTipo_taza(String tipo_taza) {
		this.tipo_taza = tipo_taza;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDimensiones() {
		return dimensiones;
	}
	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}
	public String getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public Long getCantidad_total() {
		return cantidad_total;
	}
	public void setCantidad_total(Long cantidad_total) {
		this.cantidad_total = cantidad_total;
	}
	public static Long getCantidad_disponible() {
		return cantidad_disponible;
	}
	public static void setCantidad_disponible(Long cantidad_disponible) {
		Taza.cantidad_disponible = cantidad_disponible;
	}
	public float getPrecio_compra() {
		return precio_compra;
	}
	public void setPrecio_compra(float precio_compra) {
		this.precio_compra = precio_compra;
	}
	public float getPrecio_venta() {
		return precio_venta;
	}
	public void setPrecio_venta(float precio_venta) {
		this.precio_venta = precio_venta;
	}
	public float getPrecio_final_compra() {
		return precio_final_compra;
	}
	public void setPrecio_final_compra(float precio_final_compra) {
		this.precio_final_compra = precio_final_compra;
	}

}
