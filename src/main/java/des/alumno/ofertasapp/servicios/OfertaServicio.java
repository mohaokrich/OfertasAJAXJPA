package des.alumno.ofertasapp.servicios;

import java.util.List;

import des.alumno.ofertasapp.entidades.Oferta;

public interface OfertaServicio {
	
	public Oferta crearOfertaServ(Oferta o);
	public List<Oferta> obtenerofertaServ();
	
}
