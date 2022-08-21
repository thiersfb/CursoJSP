package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/*Tom cat 9*/
/*
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
*/

/*Tom cat 10*/
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import connection.SingleConnectionDB;

@WebFilter("/principal/*")
public class FilterAutenticacao implements Filter {

	private static Connection connection;

	public FilterAutenticacao() {
	}

	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario"); // atributo capturado na ServletLogin

			String urlAutenticacao = req.getServletPath(); // URL que está sendo acessada

			// Validar se está logado, senão redireciona para a tela de login
			if (usuarioLogado == null && !urlAutenticacao.equalsIgnoreCase("/principal/ServletLogin")) {

				RequestDispatcher redirect = request.getRequestDispatcher("/index.jsp?url=" + urlAutenticacao); // redirecionamento para a página "index.jsp"
				request.setAttribute("msg", "Por favor realize o login !"); // "msg" é o nome do atributo passado por parâmetro no redirecionamento
				redirect.forward(request, response);
				return;
			} else {
				chain.doFilter(request, response);
			}

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			

			RequestDispatcher redirect = request.getRequestDispatcher("/error.jsp"); // redirecionamento para a página "error.jsp"
			request.setAttribute("msg", e.getMessage()); // "msg" é o nome do atributo passado por parâmetro no redirecionamento
			redirect.forward(request, response);

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionDB.getConnection();
	}

}
