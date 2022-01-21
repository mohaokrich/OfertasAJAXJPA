package des.alumno.ofertasapp.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import des.alumno.ofertasapp.entidades.Oferta;
import des.alumno.ofertasapp.modelo.OfertaDao;

public class OfertaServiceImpl implements OfertaServicio{

	@Autowired
	OfertaDao ofertadao;
	
	@Override
	public Oferta crearOfertaServ(Oferta o) {
        try {
            Integer resultado = ofertadao.crerOferta(o);
            return o;
        } catch (DataAccessException dae) {

            return null;
        }
    }
    @Override
    public List<Oferta> obtenerofertaServ() {
        return ofertadao.getAllOfertas();
    }
	
}

