package servlets;

import java.io.IOException;
//import java.util.List;
/*
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.SingleConnectionDB;
*/

/* Tomcat 9 */
/*
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
*/

/* Tomcat 10 */
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import dao.DAOUsuarioRepository;
import models.ModelLogin;



/* O chamado Controller são as ServLets ou ServletLoginController */
@WebServlet(urlPatterns = { "/principal/ServletLogin", "/ServletLogin" }) /* Mapeamento de URL que vêm da tela */
public class ServletLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public ServletLogin() {

	}

	/* Recebe os dados pela URL em parâmetros */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("entrei no doGet");
		
		String acao = request.getParameter("acao");
		
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();
			RequestDispatcher redirect = request.getRequestDispatcher("index.jsp"); 
			redirect.forward(request, response);
		} else {
			doPost(request, response); 
		}

	}

	/* Recebe os dados enviados por um formulário */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* Parâmetro recebido precisa estar escrito igual ao form da página */
		String login = request.getParameter("txtLogin");
		String senha = request.getParameter("txtSenha");
		String url = request.getParameter("url");

		try {

			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {


				ModelLogin modelLogin = new ModelLogin();
				modelLogin.setLogin(login);
				modelLogin.setSenha(senha);
				
				
				// if(modelLogin.getLogin().equalsIgnoreCase("admin") && modelLogin.getSenha().equalsIgnoreCase("admin")) { /*simulando login*/
				if (daoLoginRepository.validarLogin(modelLogin) != null) {
					
					/************** INÍCIO CONSULTA DADOS DO USUÁRIO LOGADO PARA A SESSÂO **************/
					/*
					String sql = "SELECT * FROM TBUsuarios WHERE UPPER([Login]) LIKE UPPER('" + login + "')";
					PreparedStatement statement = connection.SingleConnectionDB.getConnection().prepareStatement(sql);
					ResultSet resultado = statement.executeQuery();
					
					while (resultado.next()) { // Percorrer as linhas de resultado do SQL 
						modelLogin.setNome(resultado.getString("nome"));
						
					}
					*/
					/************** FIM CONSULTA DADOS DO USUÁRIO LOGADO PARA A SESSÂO **************/
					
					//ModelLogin nomeUsuarioLogado = daoLoginRepository.validarLogin(modelLogin);
					//modelLogin.setNome(daoLoginRepository.validarLogin(modelLogin).toString());
					//modelLogin.setNome(nomeUsuarioLogado.getNome().toString());

					modelLogin = daoUsuarioRepository.consultaDadosUsuarioLogado(login);
					
					
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					//request.getSession().setAttribute("isAdmin", modelLogin.getIsAdmin());
					request.getSession().setAttribute("perfil", modelLogin.getPerfil());
					request.getSession().setAttribute("imagemUser", modelLogin.getFotoUser()); //revisar !?
					request.getSession().setAttribute("usuarioNome", modelLogin.getNome() + " " + modelLogin.getSobrenome()); //atributo a ser carregado na sessão para exibição do nome do usuario logado
					
					
					if (url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}

					RequestDispatcher redirect = request.getRequestDispatcher(url);
					redirect.forward(request, response);

				} else {
					RequestDispatcher redirect = request.getRequestDispatcher("/index.jsp"); // redirecionamento para a página "index.jsp"
					request.setAttribute("msg", "Informe o login e senha corretamente !"); // "msg" é o nome do atributo  passado por parâmetro no  redirecionamento
					redirect.forward(request, response);
				}

			} else {
				RequestDispatcher redirect = request.getRequestDispatcher("/index.jsp"); // redirecionamento para a página "index.jsp"
				request.setAttribute("msg", "Informe o login e senha corretamente !"); // "msg" é o nome do atributo passado por parâmetro no redirecionamento
				redirect.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirect = request.getRequestDispatcher("/error.jsp"); // redirecionamento para a página "error.jsp"
			request.setAttribute("msg", e.getMessage()); // "msg" é o nome do atributo passado por parâmetro no redirecionamento
			redirect.forward(request, response);
		}

	}

}
