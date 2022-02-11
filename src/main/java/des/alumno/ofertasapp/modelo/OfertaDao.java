package des.alumno.ofertasapp.modelo;

import java.util.List;
import java.util.Optional;

import des.alumno.ofertasapp.entidades.Oferta;

public interface OfertaDao extends GenericDao<Oferta>{
	
	List<Oferta> getAllOfertas();
	
	List<Oferta> getPorPrioridad(String prioridad);
	
	int deleteById(long id);
}
