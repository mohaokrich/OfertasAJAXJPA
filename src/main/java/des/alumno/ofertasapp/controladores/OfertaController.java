package des.alumno.ofertasapp.controladores;

import java.time.LocalDateTime;
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
import des.alumno.ofertasapp.modelo.GenericDao;
import des.alumno.ofertasapp.modelo.OfertaDao;
import des.alumno.ofertasapp.servicios.OfertaServicio;

@Controller
public class OfertaController {
	
	@Autowired
	OfertaDao modeloOferta;
	
	@Autowired
	OfertaServicio modeloServicio;
	
	@Autowired
	GenericDao modeloGenericDao;
	
		@ResponseBody
		@RequestMapping(method = RequestMethod.GET, value = "/ofertas")
		public List<Oferta> obtenerTodos() {
			return modeloServicio.obtenerofertaServ();
		}
		@ResponseBody
		@RequestMapping(method = RequestMethod.GET, value = "/oferta/oferta{id}")
		public Oferta getIdProducto(Model modelo, @PathVariable("id") long id) {
			return modeloServicio.obtenerInfoOferta(id);
		}
		@RequestMapping(method = RequestMethod.POST, value = "/oferta/crear")
		public String obtenerDatosFormulario(@RequestParam String nombre,
											 @RequestParam String prioridad,
											 @RequestParam String descripcion,
											 @RequestParam String hiperenlace,
											 @RequestParam double precio) {
			Oferta oferta = new Oferta();
			oferta.setNombreOferta(nombre);
			oferta.setPrioridadOferta(prioridad);
			oferta.setDescripcionOferta(descripcion);
			oferta.setHiperenlaceOferta(hiperenlace);
			oferta.setPrecioOferta(precio);
			
			//fecha------
			 java.util.Date date = new java.util.Date();           
			//fecha-------
		    
			oferta.setFechaPublicacion(date);
			modeloServicio.crearOferta(oferta);
			return "redirect:/";
	    }
		
		@RequestMapping(method = RequestMethod.POST, value = "/editar/oferta/{id}")
		public String editarOferta(@RequestParam String nombre,
											 @RequestParam String prioridad,
											 @RequestParam String descripcion,
											 @RequestParam String hiperenlace,
											 @RequestParam double precio, @PathVariable("id") long id) {
			
			Oferta oferta = new Oferta();
			
			oferta.setNombreOferta(nombre);
			oferta.setPrioridadOferta(prioridad);
			oferta.setDescripcionOferta(descripcion);
			oferta.setHiperenlaceOferta(hiperenlace);
			oferta.setPrecioOferta(precio);
			
			modeloServicio.editarOferta(oferta,id);
			return "redirect:/";
	    }
		
		@GetMapping("/oferta/borrar/{id}")
		public String getBorrarIdProducto(@PathVariable("id") Long id) {
			modeloServicio.eliminarOferta(id);
			return "redirect:/";
		}
		@ResponseBody
		@RequestMapping(method = RequestMethod.GET, value = "/oferta/filtrar", params="prioridad")
	    public List<Oferta> filtrarPrioridad(@RequestParam String prioridad) {
	        return (List<Oferta>) modeloServicio.filtrarOferta(prioridad);
	    }
//		@GetMapping("/oferta/buscar")
//		public String getBuscarOferta(Model modelo, @RequestParam String busqueda) {
//			List<Oferta> ListaOfertasInfo = modeloOferta.buscarOferta(busqueda);
//			modelo.addAttribute("ListaOfertasInfo", ListaOfertasInfo);
//			return "/perfil";
//		}
//		}
		
		
}
