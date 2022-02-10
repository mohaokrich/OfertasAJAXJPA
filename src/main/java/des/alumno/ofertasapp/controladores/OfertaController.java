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

	
		@ResponseBody
		@RequestMapping(method = RequestMethod.GET, value = "/ofertas")
		public List<Oferta> obtenerTodos() {
			return modeloOferta.getAllOfertas();
		}
	
		@RequestMapping(method = RequestMethod.POST, value = "/oferta/crear")
		public String obtenerDatosFormulario(@RequestParam String nombre,
											 @RequestParam String prioridad,
											 @RequestParam String descripcion,
											 @RequestParam String hiperenlace,
											 @RequestParam double precio) {
			modeloOferta.crerOferta(new Oferta(nombre,prioridad, hiperenlace,descripcion, precio));
			return "redirect:/";
	    }
		
		@RequestMapping(method = RequestMethod.POST, value = "/editar/oferta/{id}")
		public String editarOferta(@RequestParam String nombre,
											 @RequestParam String prioridad,
											 @RequestParam String descripcion,
											 @RequestParam String hiperenlace,
											 @RequestParam double precio, @PathVariable("id") long id) {
			Oferta oferta = new Oferta();
			oferta.setNombre(nombre);
			oferta.setPrioridad(prioridad);
			oferta.setDescripcion(descripcion);
			oferta.setHiperenlace(hiperenlace);
			oferta.setPrecio(precio);
			oferta.setId(id);
			modeloOferta.editarOferta(oferta);
			return "redirect:/";
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
		@ResponseBody
		@RequestMapping(method = RequestMethod.GET, value = "/oferta/filtrar", params="prioridad")
	    public List<Oferta> filtrarPrioridad(@RequestParam String prioridad) {
	        return modeloOferta.filtrarOferta(prioridad);
	    }
}
