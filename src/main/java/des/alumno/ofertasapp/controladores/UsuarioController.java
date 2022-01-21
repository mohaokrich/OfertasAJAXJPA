package des.alumno.ofertasapp.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {
	
	@GetMapping("/crearUsuario")
	public String createUser(HttpSession session, @RequestParam(value = "usuario", required = true) String usuario) {
		session.setAttribute("usuario", usuario);
		return "redirect:index";
	}
	@GetMapping("/cerrarSession")
	public String cerrasSession(HttpSession session) {
		session.removeAttribute("usuario");
		session.invalidate();
		return "redirect:index";
	}
	
}
