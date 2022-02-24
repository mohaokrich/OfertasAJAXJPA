package des.alumno.ofertasapp.controladores;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import des.alumno.ofertasapp.entidades.Oferta;
import des.alumno.ofertasapp.servicios.OfertaServicioJpa;

@Controller
public class OfertaController {
	
	@Autowired
	OfertaServicioJpa modeloJPA;
		
		//OBTENER TODAS LAS OFERTAS
		@ResponseBody
		@RequestMapping(method = RequestMethod.GET, value = "/ofertas")
		public List<Oferta> obtenerTodos() {
			return modeloJPA.obtenerAllOfertas();
		}
		
		//OBTENER OFERTA POR ID
		@ResponseBody
		@RequestMapping(method = RequestMethod.GET, value = "/oferta/oferta{id}")
		public Oferta getIdProducto(Model modelo, @PathVariable("id") long id) {
			return modeloJPA.obtenerInfoOferta(id);
		}
		
		//CREAR OFERTA
		@RequestMapping(method = RequestMethod.POST, value = "/oferta/crear")
		public ResponseEntity<Oferta> obtenerDatosFormulario(@RequestBody Oferta oferta) {
			
			//fecha------
			java.util.Date date = new java.util.Date();
			oferta.setFechaPublicacion(date);
			//fecha------
			
			Oferta crearOferta = modeloJPA.crearOferta(oferta);
			
			ResponseEntity<Oferta> resp = new ResponseEntity<Oferta>(crearOferta, HttpStatus.OK);
			return resp;
	    }
		//EDITAR OFERTA
		@ResponseBody
		@PutMapping(value = "/editar/oferta/{id}")
		public ResponseEntity<Oferta> editarOferta(@RequestBody Oferta oferta, @PathVariable("id") long id) {
			Oferta editarOferta = modeloJPA.editarOferta(oferta, id);
			ResponseEntity<Oferta> resp = new ResponseEntity<Oferta>(editarOferta, HttpStatus.OK);
			return resp;
	    }
		//BORRAR OFERTA
		@GetMapping("/oferta/borrar/{id}")
		public void getBorrarIdProducto(@PathVariable("id") Long id) {
			modeloJPA.eliminarOferta(id);
		}
		//FILTRAR OFERTA
		@ResponseBody
		@RequestMapping(method = RequestMethod.GET, value = "/oferta/filtrar", params="prioridad")
	    public List<Oferta> filtrarPrioridad(@RequestParam String prioridad) {
	        return (List<Oferta>) modeloJPA.filtarPrioridad(prioridad);
	    }
		
}
