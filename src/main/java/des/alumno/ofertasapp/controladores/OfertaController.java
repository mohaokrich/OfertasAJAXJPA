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
	OfertaDao modeloOfeta;

	
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
			
			modeloOfeta.crerOferta(oferta);
			return "redirect:/index";
	    }
		
		@ResponseBody
		@RequestMapping(method = RequestMethod.GET, value = "/ofertas")
		public List<Oferta> obtenerTodos() {
			return modeloOfeta.getAllOfertas();
		}

		@GetMapping("/oferta/borrar/{id}")
		public String getBorrarIdProducto(@PathVariable("id") long id) {
			modeloOfeta.borrarFila(id);
			return "redirect:/";
		}
		
		@GetMapping("/oferta/buscar")
		public String getBuscarOferta(Model modelo, @RequestParam String busqueda) {
			List<Oferta> ListaOfertasInfo = modeloOfeta.buscarOferta(busqueda);
			modelo.addAttribute("ListaOfertasInfo", ListaOfertasInfo);
			return "/perfil";
		}
		@GetMapping("/oferta/oferta{id}")
		public String getIdProducto(Model modelo, @PathVariable("id") long id) {
			Optional<Oferta> ListaOfertasInfo =  modeloOfeta.buscarPorId(id);
			Oferta p1 = ListaOfertasInfo.get();
			modelo.addAttribute("ListaOfertasInfo", p1);
			return "/perfil";
		}
}
