package des.alumno.ofertasapp.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ofertas")
public class Oferta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//ATRIBUTOS
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idOferta;
	
	@Column(name = "nombre")
	private String nombreOferta;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_publicacion")
	private Date fechaPublicacion;
	
	@Column(name = "prioridad")
	private String prioridadOferta;
	
	@Column(name = "hiperenlace")
	private String hiperenlaceOferta;
	
	@Column(name = "descripcion")
	private String descripcionOferta;
	
	@Column(name = "precio")
	private Double precioOferta;
	
	//CONSTRUCTORES
	
	public Oferta() {
		
	}
	//CONSTRUCTORES
	
	public Oferta(Long idOferta, String nombreOferta, Date fechaPublicacion, String prioridadOferta,
			String hiperenlaceOferta, String descripcionOferta, Double precioOferta) {
		super();
		this.idOferta = idOferta;
		this.nombreOferta = nombreOferta;
		this.fechaPublicacion = fechaPublicacion;
		this.prioridadOferta = prioridadOferta;
		this.hiperenlaceOferta = hiperenlaceOferta;
		this.descripcionOferta = descripcionOferta;
		this.precioOferta = precioOferta;
	}
	
	public Oferta(String nombreOferta, String prioridadOferta, String hiperenlaceOferta, String descripcionOferta, Double precioOferta) {
		super();
		this.nombreOferta = nombreOferta;
		this.prioridadOferta = prioridadOferta;
		this.hiperenlaceOferta = hiperenlaceOferta;
		this.descripcionOferta = descripcionOferta;
		this.precioOferta = precioOferta;
	}
	
	//GETTERS Y SETTERS
	public Long getIdOferta() {
		return idOferta;
	}
	public void setIdOferta(Long idOferta) {
		this.idOferta = idOferta;
	}

	public String getNombreOferta() {
		return nombreOferta;
	}

	public void setNombreOferta(String nombreOferta) {
		this.nombreOferta = nombreOferta;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getPrioridadOferta() {
		return prioridadOferta;
	}

	public void setPrioridadOferta(String prioridadOferta) {
		this.prioridadOferta = prioridadOferta;
	}

	public String getHiperenlaceOferta() {
		return hiperenlaceOferta;
	}

	public void setHiperenlaceOferta(String hiperenlaceOferta) {
		this.hiperenlaceOferta = hiperenlaceOferta;
	}

	public String getDescripcionOferta() {
		return descripcionOferta;
	}

	public void setDescripcionOferta(String descripcionOferta) {
		this.descripcionOferta = descripcionOferta;
	}

	public Double getPrecioOferta() {
		return precioOferta;
	}

	public void setPrecioOferta(Double precioOferta) {
		this.precioOferta = precioOferta;
	}
	
	



	
	
}
 