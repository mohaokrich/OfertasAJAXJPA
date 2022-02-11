package des.alumno.ofertasapp.servicios;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import des.alumno.ofertasapp.entidades.Oferta;
import des.alumno.ofertasapp.modelo.GenericDao;
import des.alumno.ofertasapp.modelo.OfertaDao;

@Transactional
@Service
public class OfertaServiceImpl implements OfertaServicio {

	@Autowired
	OfertaDao ofertadao;

	@Autowired
	GenericDao ofertaGeneric;

	@Override
	public List<Oferta> obtenerofertaServ() {
		return ofertadao.getAllOfertas();
	}

	@Override
	public Oferta eliminarOferta(long idOferta) {
		Oferta oferta = (Oferta) ofertaGeneric.buscar(idOferta);
		if (oferta != null) {
			ofertadao.deleteById(idOferta);
			return oferta;
		}
		return null;
	}

	@Override
	public Oferta obtenerInfoOferta(long idOferta) {
		Oferta oferta = (Oferta) ofertaGeneric.buscar(idOferta);
		return oferta;
	}

	@Override
	public Oferta crearOferta(Oferta o) {
		Oferta oferta = (Oferta) ofertaGeneric.crear(o);
		if (oferta != null) {
			return oferta;
		}
		return null;
	}

	@Override
	public Oferta editarOferta(Oferta o, long id) {
		Oferta encontrarporId = (Oferta) ofertaGeneric.buscar(id);
		if (encontrarporId != null) {

			encontrarporId.setNombreOferta(o.getNombreOferta());
			encontrarporId.setPrioridadOferta(o.getPrioridadOferta());
			encontrarporId.setHiperenlaceOferta(o.getHiperenlaceOferta());
			encontrarporId.setDescripcionOferta(o.getDescripcionOferta());
			encontrarporId.setPrecioOferta(o.getPrecioOferta());

			Oferta ofertaSalvar = (Oferta) ofertaGeneric.actualizar(encontrarporId);

			return ofertaSalvar;
		}
		return null;
	}

	@Override
	public List<Oferta> filtrarOferta(String prioridad) {
		List<Oferta> oferta = ofertadao.getPorPrioridad(prioridad);
		if(oferta != null){
			return oferta;
		}
		return null;
	}
}
