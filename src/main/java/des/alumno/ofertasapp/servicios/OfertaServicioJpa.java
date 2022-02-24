package des.alumno.ofertasapp.servicios;

import java.util.List;

import des.alumno.ofertasapp.entidades.Oferta;

public interface OfertaServicioJpa {
	List<Oferta> obtenerAllOfertas();
	void eliminarOferta(long idOferta);
	Oferta crearOferta(Oferta o);
	Oferta editarOferta(Oferta o, long idOferta);
	List <Oferta> filtarPrioridad(String prioridad);
	Oferta obtenerInfoOferta(long idOferta);
}
