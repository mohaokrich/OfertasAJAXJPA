package des.alumno.ofertasapp.modelo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import des.alumno.ofertasapp.entidades.Oferta;

@Repository
public class OfertaDaoImpl implements OfertaDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Oferta> getAllOfertas() { 
		return jdbcTemplate.query("SELECT * from Ofertas", 
				(rs, rowNum) ->new Oferta(rs.getLong("id"),rs.getString("nombre"),rs.getString("fecha_publicacion"),rs.getString("prioridad"),rs.getString("hiperenlace"),rs.getString("descripcion"), rs.getDouble("precio")));
	}

	@Override
	public int crerOferta(Oferta o) {
		return jdbcTemplate.update("INSERT INTO Ofertas(nombre,prioridad,hiperenlace,descripcion,precio) values(?,?,?,?,?)",o.getNombre(),o.getPrioridad(),o.getHiperenlace(),o.getDescripcion(),o.getPrecio());
	}

	@Override
	public long borrarFila(long id) {
		return jdbcTemplate.update("delete from Ofertas where id = ?", id);
	}

	@Override
	public List<Oferta> buscarOferta(String nombre) {
		return jdbcTemplate.query("select * from Ofertas where nombre like ?", (rs,
				rowNum) -> new Oferta(rs.getLong("id"),rs.getString("nombre"),rs.getString("fecha_publicacion"),rs.getString("prioridad"),rs.getString("hiperenlace"),rs.getString("descripcion"), rs.getDouble("precio")), "%"+nombre+"%" );
	}

	@Override
	public Optional<Oferta> buscarPorId(long id) {
		return jdbcTemplate.queryForObject("select * from Ofertas where id = ?", new Object[] { id }, (rs,
				rowNum) -> Optional.of(new Oferta(rs.getLong("id"),rs.getString("nombre"),rs.getString("fecha_publicacion"),rs.getString("prioridad"),rs.getString("hiperenlace"),rs.getString("descripcion"), rs.getDouble("precio"))));
	}

	@Override
	public List<Oferta> filtrarOferta(String prioridad) {
        return jdbcTemplate.query("select * from Ofertas where prioridad like ?", (rs,
                rowNum) -> new Oferta(rs.getLong("id"),rs.getString("nombre"),rs.getString("fecha_publicacion"),rs.getString("prioridad"),rs.getString("hiperenlace"),rs.getString("descripcion"), rs.getDouble("precio")), "%"+prioridad+"%");
	}

	@Override
	public int editarOferta(Oferta oferta) {
		final String update_query = "UPDATE Ofertas SET nombre = ?, prioridad = ?, hiperenlace = ?, descripcion = ?, precio = ?  where id = ?";
		return jdbcTemplate.update(update_query, oferta.getNombre(), oferta.getPrioridad(), oferta.getHiperenlace(), oferta.getDescripcion(), oferta.getPrecio(), oferta.getId()); 
	}

}
