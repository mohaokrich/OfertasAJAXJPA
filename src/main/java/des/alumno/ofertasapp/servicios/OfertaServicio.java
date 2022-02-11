package des.alumno.ofertasapp.servicios;

import java.util.List;


import des.alumno.ofertasapp.entidades.Oferta;

public interface OfertaServicio {
	
	 List<Oferta> obtenerofertaServ();
	 
	 Oferta eliminarOferta(long idOferta);
	 
	 Oferta obtenerInfoOferta (long idOferta);
	 
	 Oferta crearOferta (Oferta o);
	 
	 Oferta editarOferta(Oferta o, long id);
	 
	 List<Oferta> filtrarOferta(String prioridad);

}
