package des.alumno.ofertasapp.entidades;

import java.io.Serializable;


public class Oferta implements Serializable{
	
	//ATRIBUTOS
	private long id;
	private String nombre;
	private String fecha_publicacion;
	private String prioridad;
	private String hiperenlace;
	private String descripcion;
	private Double precio;
	
	//CONSTRUCTORES
	
	public Oferta() {
		
	}
	
	public Oferta(long id, String nombre, String fecha_publicacion, String prioridad, String hiperenlace, String descripcion, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha_publicacion = fecha_publicacion;
		this.prioridad = prioridad;
		this.hiperenlace = hiperenlace;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Oferta(String nombre, String prioridad, String hiperenlace, String descripcion, double precio) {
		super();
		this.nombre = nombre;
		this.prioridad = prioridad;
		this.hiperenlace = hiperenlace;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Oferta(long id,String prioridad, String nombre, double precio) {
		super();
		this.id = id;
		this.prioridad = prioridad;
		this.nombre = nombre;
		this.precio = precio;
	}

	//GETTERS Y SETTERS
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(String fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public String getHiperenlace() {
		return hiperenlace;
	}
	public void setHiperenlace(String hiperenlace) {
		this.hiperenlace = hiperenlace;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	//TO STRING
	@Override
	public String toString() {
		return "Oferta [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha_publicacion + ", prioridad=" + prioridad
				+ ", hiperenlace=" + hiperenlace + ", descripcion=" + descripcion + ", precio=" + precio + "]";
	}
	
	
}
 