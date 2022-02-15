package des.alumno.ofertasapp.controladores;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		
		//OBTENER TODAS LAS OFERTAS
		@ResponseBody
		@RequestMapping(method = RequestMethod.GET, value = "/ofertas")
		public List<Oferta> obtenerTodos() {
			return modeloServicio.obtenerofertaServ();
		}
		
		//OBTENER OFERTA POR ID
		@ResponseBody
		@RequestMapping(method = RequestMethod.GET, value = "/oferta/oferta{id}")
		public Oferta getIdProducto(Model modelo, @PathVariable("id") long id) {
			return modeloServicio.obtenerInfoOferta(id);
		}
		
		//CREAR OFERTA
		@RequestMapping(method = RequestMethod.POST, value = "/oferta/crear")
		public ResponseEntity<Oferta> obtenerDatosFormulario(@RequestBody Oferta oferta) {
			
			//fecha------
			java.util.Date date = new java.util.Date();
			oferta.setFechaPublicacion(date);
			//fecha------
			
			Oferta crearOferta = modeloServicio.crearOferta(oferta);
			
			ResponseEntity<Oferta> resp = new ResponseEntity<Oferta>(crearOferta, HttpStatus.OK);
			return resp;
	    }
		//EDITAR OFERTA
		@ResponseBody
		@PutMapping(value = "/editar/oferta/{id}")
		public ResponseEntity<Oferta> editarOferta(@RequestBody Oferta oferta, @PathVariable("id") long id) {
			Oferta editarOferta = modeloServicio.editarOferta(oferta, id);
			ResponseEntity<Oferta> resp = new ResponseEntity<Oferta>(editarOferta, HttpStatus.OK);
			return resp;
	    }
		//BORRAR OFERTA
		@GetMapping("/oferta/borrar/{id}")
		public ResponseEntity<Oferta> getBorrarIdProducto(@PathVariable("id") Long id) {
			Oferta ofertaborrar = modeloServicio.eliminarOferta(id);
			ResponseEntity<Oferta> resp = new ResponseEntity<Oferta>(ofertaborrar, HttpStatus.OK);
			return resp;
		}
		//FILTRAR OFERTA
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
