package des.alumno.ofertasapp.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import des.alumno.ofertasapp.entidades.Oferta;
import des.alumno.ofertasapp.modelo.OfertaDao;

@Controller
public class OfertaController {
	
	@Autowired
	OfertaDao modeloOferta;

	
		@PostMapping("/index")
		public String obtenerDatosFormulario(Model modelo, @RequestParam String nombre,
														   @RequestParam String prioridad,
														   @RequestParam String descripcion,
														   @RequestParam String hiperenlace,
														   @RequestParam double precio) {
			Oferta oferta = new Oferta();
			oferta = new Oferta(null,null,null,null,0);
			
			oferta.setNombre(nombre);
			oferta.setPrioridad(prioridad);
			oferta.setDescripcion(descripcion);
			oferta.setHiperenlace(hiperenlace);
			oferta.setPrecio(precio);
			
			modeloOferta.crerOferta(oferta);
			return "redirect:/index";
	    }
		
		@ResponseBody
		@RequestMapping(method = RequestMethod.GET, value = "/ofertas")
		public List<Oferta> obtenerTodos() {
			return modeloOferta.getAllOfertas();
		}

		@GetMapping("/oferta/borrar/{id}")
		public String getBorrarIdProducto(@PathVariable("id") long id) {
			modeloOferta.borrarFila(id);
			return "redirect:/";
		}
		
		@GetMapping("/oferta/buscar")
		public String getBuscarOferta(Model modelo, @RequestParam String busqueda) {
			List<Oferta> ListaOfertasInfo = modeloOferta.buscarOferta(busqueda);
			modelo.addAttribute("ListaOfertasInfo", ListaOfertasInfo);
			return "/perfil";
		}
		@ResponseBody
		@RequestMapping(method = RequestMethod.GET, value = "/oferta/oferta{id}")
		public Optional<Oferta> getIdProducto(Model modelo, @PathVariable("id") long id) {
			return modeloOferta.buscarPorId(id);
		}
}
