package des.alumno.ofertasapp.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String getIndex () {
		return "index";
	}
	
	@GetMapping("/perfil")
	public String getPerfil () {
		return "perfil";
	}	
	
}
