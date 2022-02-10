package des.alumno.ofertasapp.modelo;

import java.util.List;
import java.util.Optional;

import des.alumno.ofertasapp.entidades.Oferta;

public interface OfertaDao {
	
	List<Oferta> getAllOfertas();
	int crerOferta(Oferta o);
	long borrarFila(long id);
	List<Oferta> buscarOferta(String nombre);
	Optional<Oferta> buscarPorId(long id);
	List<Oferta> filtrarOferta(String prioridad);
	//int editarOferta(Oferta o, long id);
	//Optional<Oferta> editarOferta(long id);
	//int editarOferta(Oferta oferta);
	int editarOferta(Oferta oferta);
}
