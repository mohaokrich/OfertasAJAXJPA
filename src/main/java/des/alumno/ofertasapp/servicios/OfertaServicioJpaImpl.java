package des.alumno.ofertasapp.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import des.alumno.ofertasapp.entidades.Oferta;
import des.alumno.ofertasapp.modelo.OfertasJPARepository;

@Service
public class OfertaServicioJpaImpl implements OfertaServicioJpa {

	@Autowired
	OfertasJPARepository ofertaRepJpa;
	
	@Override
	public List<Oferta> obtenerAllOfertas() {
		List<Oferta> obtenerTodas = ofertaRepJpa.findAll();
		if(obtenerTodas!=null) {
			return obtenerTodas;
		}
		return null;
	}

	@Override
	public void eliminarOferta(long idOferta) {
		ofertaRepJpa.deleteById(idOferta);
	}

	@Override
	public Oferta crearOferta(Oferta o) {
		Oferta guardarOferta = ofertaRepJpa.save(o);
		if(guardarOferta!=null) {
			return guardarOferta;
		}
		return null;
	}

	@Override
	public Oferta editarOferta(Oferta o, long idOferta) {
		Oferta encontrarporId =  ofertaRepJpa.findById(idOferta).orElse(null);
		if (encontrarporId != null) {

			encontrarporId.setNombreOferta(o.getNombreOferta());
			encontrarporId.setPrioridadOferta(o.getPrioridadOferta());
			encontrarporId.setHiperenlaceOferta(o.getHiperenlaceOferta());
			encontrarporId.setDescripcionOferta(o.getDescripcionOferta());
			encontrarporId.setPrecioOferta(o.getPrecioOferta());

			Oferta ofertaSalvar = ofertaRepJpa.save(encontrarporId);

			return ofertaSalvar;
		}
		return null;
	}

	@Override
	public List<Oferta> filtarPrioridad(String prioridadOferta) {
		List<Oferta> buscarPorPrioridad = ofertaRepJpa.findByPrioridadOferta(prioridadOferta);
		if(buscarPorPrioridad!=null) {
			return buscarPorPrioridad;
		}
		return null;
	}

	@Override
	public Oferta obtenerInfoOferta(long idOferta) {
		Oferta buscarPorId = ofertaRepJpa.findById(idOferta).orElse(null);
		if(buscarPorId!=null) {
			return buscarPorId;
		}
		return null;
	}
	
}
