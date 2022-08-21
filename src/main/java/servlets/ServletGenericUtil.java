package servlets;

import java.io.Serializable;

import dao.DAOUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import models.ModelLogin;

public class ServletGenericUtil extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	public Long getUserLogado(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();

		String usuarioLogado = (String) session.getAttribute("usuario"); // atributo capturado na ServletLogin
		
		return daoUsuarioRepository.consultaDadosUsuarioLogado(usuarioLogado).getId();
	}
	
	
	public ModelLogin getUserLogadoObject(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();

		String usuarioLogado = (String) session.getAttribute("usuario"); // atributo capturado na ServletLogin
		
		return daoUsuarioRepository.consultaDadosUsuarioLogado(usuarioLogado);
	}

}
