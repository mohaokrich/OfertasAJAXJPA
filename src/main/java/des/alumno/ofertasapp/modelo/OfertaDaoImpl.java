package des.alumno.ofertasapp.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import des.alumno.ofertasapp.entidades.Oferta;

@Repository
public class OfertaDaoImpl extends GenericDaoImpl<Oferta> implements OfertaDao {

//	@Autowired
//	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Oferta> getAllOfertas() {
		Query query = this.em.createQuery("FROM Oferta");

		List<Oferta> lOfertas = query.getResultList();

		if (lOfertas != null) {
			return lOfertas;
		}

		return null;
	}

	@Override
	public List<Oferta> getPorPrioridad(String prioridad) {
		Query query = this.em.createQuery("FROM Oferta o WHERE o.prioridadOferta= :prioridad ");

		query.setParameter("prioridad", prioridad);

		List<Oferta> lOfertas = query.getResultList();

		if (lOfertas != null) {
			return lOfertas;
		}
		return null;
	}

	@Override
	public int deleteById(long id) {
		Query query = this.em.createQuery("DELETE FROM Oferta o WHERE o.idOferta= :id ");
		
		query.setParameter("id", id);
		
		return query.executeUpdate();
	}

//
//	@Override
//	public long borrarFila(long id) {
//		return jdbcTemplate.update("delete from Ofertas where id = ?", id);
//	}
//
//	@Override
//	public List<Oferta> buscarOferta(String nombre) {
//		return jdbcTemplate.query("select * from Ofertas where nombre like ?", (rs,
//				rowNum) -> new Oferta(rs.getLong("id"),rs.getString("nombre"),rs.getString("fecha_publicacion"),rs.getString("prioridad"),rs.getString("hiperenlace"),rs.getString("descripcion"), rs.getDouble("precio")), "%"+nombre+"%" );
//	}
//
//	@Override
//	public List<Oferta> filtrarOferta(String prioridad) {
//        return jdbcTemplate.query("select * from Ofertas where prioridad like ?", (rs,
//                rowNum) -> new Oferta(rs.getLong("id"),rs.getString("nombre"),rs.getString("fecha_publicacion"),rs.getString("prioridad"),rs.getString("hiperenlace"),rs.getString("descripcion"), rs.getDouble("precio")), "%"+prioridad+"%");
//	}
//

}
