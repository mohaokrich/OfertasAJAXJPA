package des.alumno.ofertasapp.modelo;

import java.util.Map;

public interface GenericDao<T> {
	
	long contarTodos(Map<String, Object> params);

	T crear(T t);

	void borrar(Object id);

	T buscar(Object id);

	T actualizar(T t);

}
