package des.alumno.ofertasapp.modelo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import des.alumno.ofertasapp.entidades.Oferta;

public interface OfertasJPARepository extends JpaRepository<Oferta, Long> {
	List<Oferta> findByPrioridad(String Prioridad);
}
