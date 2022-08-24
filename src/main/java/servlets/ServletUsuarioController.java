package servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
//import java.util.Date;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import beandto.BeanDtoGraficoSalarioUser;

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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import dao.DAOUsuarioRepository;
import models.ModelLogin;
import util.ReportUtil;

@MultipartConfig
@WebServlet( urlPatterns = { "/ServletUsuarioController"})
//public class ServletUsuarioController extends HttpServlet {
public class ServletUsuarioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;	
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	
	public ServletUsuarioController() {
	}
	 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String acao = request.getParameter("acao");
			
			if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String idUser = request.getParameter("id");
				
				daoUsuarioRepository.excluirUsuario(idUser);
				
				request.setAttribute("msg", "Exclu�do com sucesso!");
				//request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(super.getUserLogado(request)));
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina());
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				String idUser = request.getParameter("id");
				
				daoUsuarioRepository.excluirUsuario(idUser);

				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaListaUsuario();
				//List<ModelLogin> modelLogins = daoUsuarioRepository.consultaListaUsuario(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins); 	// mant�m os dados na tela
				
				response.getWriter().write("Exclu�do com sucesso!");
				
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				String nomeBusca = request.getParameter("nomeBusca");
				
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca);
				//List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca, super.getUserLogado(request));
				
			    ObjectMapper mapper = new ObjectMapper();
				 
				String json = mapper.writeValueAsString(dadosJsonUser);
				
				response.addHeader("totalPagina", "" + daoUsuarioRepository.pesquisaUsuarioListTotalPaginaPaginacao(nomeBusca /*, super.getUserLogado(request)*/));
				response.getWriter().write(json);
			
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjaxPage")) {	
				
				String nomeBusca = request.getParameter("nomeBusca");
				String pagina = request.getParameter("pagina");
				
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.pesquisaUsuarioListPaginada(nomeBusca, Integer.parseInt(pagina));
				
			    ObjectMapper mapper = new ObjectMapper();
				 
				String json = mapper.writeValueAsString(dadosJsonUser);
				
				response.addHeader("totalPagina", "" + daoUsuarioRepository.pesquisaUsuarioListTotalPaginaPaginacao(nomeBusca /*, super.getUserLogado(request)*/));
				response.getWriter().write(json);
				
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				String idUser = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(idUser));
				//ModelLogin modelLogin = daoUsuarioRepository.consultaDadosUsuarioPorId(idUser, super.getUserLogado(request));
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaListaUsuario();
				//List<ModelLogin> modelLogins = daoUsuarioRepository.consultaListaUsuario(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins); 	// mant�m os dados na tela
				
				request.setAttribute("msg", "Usuário em edição");
				request.setAttribute("modelLogin", modelLogin); 	// mant�m os dados na tela
				//request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(super.getUserLogado(request)));
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina());
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaListaUsuario();
				//List<ModelLogin> modelLogins = daoUsuarioRepository.consultaListaUsuario(super.getUserLogado(request));
				
				
				//request.setAttribute("msg", "Lista de usu�rios");
				request.setAttribute("modelLogins", modelLogins); 	// mant�m os dados na tela
				//request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(super.getUserLogado(request)));
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina());
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {
				String idUser = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(idUser));
				//ModelLogin modelLogin = daoUsuarioRepository.consultaDadosUsuarioPorId(idUser, super.getUserLogado(request));
				
				if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
					response.setHeader("Content-Disposition", "attachment;filename=profile_img_"+ modelLogin.getLogin() + "." + modelLogin.getFotoUserExtensao());
					response.getOutputStream().write(new Base64().decodeBase64(modelLogin.getFotoUser().split("\\,")[1]));
				}
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
				Integer offset = Integer.parseInt(request.getParameter("pagina"));
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaListaPaginadaUsuario(/*this.getUserLogado(request),*/ offset);
				
				request.setAttribute("modelLogins", modelLogins); 	// mant�m os dados na tela
				//request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina());
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioUser")) {
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				if ((dataInicial == null || dataInicial.isEmpty()) && (dataFinal == null || dataFinal.isEmpty())) {
					request.setAttribute("relatorioUser", daoUsuarioRepository.consultaUsuarioListRelatorio());
				} else {
					request.setAttribute("relatorioUser", daoUsuarioRepository.consultaUsuarioListRelatorio(dataInicial, dataFinal));
				}
				
				request.setAttribute("dataInicial", dataInicial);
				request.setAttribute("dataFinal", dataFinal);
				request.getRequestDispatcher("principal/reluser.jsp").forward(request, response); // redirecionamento para a p�gina "error.jsp"				
			
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioPDF") ||acao.equalsIgnoreCase("imprimirRelatorioExcel")) {
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				List<ModelLogin> modelLogins = null;
				
				if ((dataInicial == null || dataInicial.isEmpty()) && (dataFinal == null || dataFinal.isEmpty())) {
					modelLogins = daoUsuarioRepository.consultaUsuarioListRelatorio();
				} else {
					modelLogins = daoUsuarioRepository.consultaUsuarioListRelatorio(dataInicial, dataFinal);
				}
				
				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("PARAM_SUB_REPORT", request.getServletContext().getRealPath("relatorio") + File.separator);
				
				byte[] relatorio = null;
				String extensao = "";
				
				if (acao.equalsIgnoreCase("imprimirRelatorioPDF")) {
					extensao = "pdf";
					relatorio = new ReportUtil().geraRelatorioPDF(modelLogins, "rel-user-jsp", params, request.getServletContext());
				} else if (acao.equalsIgnoreCase("imprimirRelatorioExcel")) {
					extensao = "xls";
					relatorio = new ReportUtil().geraRelatorioExcel(modelLogins, "rel-user-jsp", params, request.getServletContext());
				}
				
				
				response.setHeader("Content-Disposition", "attachment;filename=relatorio_usuario." + extensao);
				response.getOutputStream().write(relatorio);
			
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("graficoSalario")) {
				
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				if ((dataInicial == null || dataInicial.isEmpty()) && (dataFinal == null || dataFinal.isEmpty())) {
					BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = daoUsuarioRepository.montarGraficoMediaSalario(/*super.getUserLogado(request)*/);

				    ObjectMapper mapper = new ObjectMapper();
					 
					String json = mapper.writeValueAsString(beanDtoGraficoSalarioUser);

					response.getWriter().write(json);

				} else {
					BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = daoUsuarioRepository.montarGraficoMediaSalario(dataInicial, dataFinal);

				    ObjectMapper mapper = new ObjectMapper();
					 
					String json = mapper.writeValueAsString(beanDtoGraficoSalarioUser);

					response.getWriter().write(json);
				}
				
			
				
			} else {

				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaListaUsuario();
				//List<ModelLogin> modelLogins = daoUsuarioRepository.consultaListaUsuario(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins); 	// mantem os dados na tela

				//request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina());
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher redirect = request.getRequestDispatcher("/error.jsp"); // redirecionamento para a p�gina "error.jsp"
			request.setAttribute("msg", e.getMessage()); // "msg" � o nome do atributo passado por par�metro no redirecionamento
			redirect.forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String msg = "";
		try {
			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String sobrenome = request.getParameter("sobrenome");
			String email = request.getParameter("email");
			String cpf = request.getParameter("cpf");
			String rg = request.getParameter("rg");
			String dtnascimento = request.getParameter("dataNascimento");
			String rendaMensal = request.getParameter("rendamensal");
			
			rendaMensal = rendaMensal.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
			
			String statusId = request.getParameter("radioStatus").equals("ativo") ? "1" : "0";
			String perfil = request.getParameter("perfil");
			String genero = request.getParameter("radioGenero").equals("M") ? "M" : "F";
			//String fotoUser = request.getParameter("fotoembase64");
			/*
			String cep = request.getParameter("cep");
			String logradouro = request.getParameter("logradouro");
			String numero = request.getParameter("numero");
			String complemento = request.getParameter("complemento");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String uf = request.getParameter("uf");
			*/
			
			/*String statusId = "";
			
			if(request.getParameter("radioType") != null){
				if(request.getParameter("radioType").equals("ativo")) {
					statusId = "1";
				}
				if(request.getParameter("radioType").equals("inativo")) {
					statusId = "0";
				}
			}*/
						
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setNome(nome);
			modelLogin.setSobrenome(sobrenome);
			modelLogin.setEmail(email);
			
			//Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(dtnascimento)));
			//modelLogin.setDtNascimento(new Date(new SimpleDateFormat("dd/mm/yyyy").parse(dtnascimento).getTime()));
			
			modelLogin.setDtNascimento(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dtnascimento))));
			modelLogin.setRendamensal(Double.valueOf(rendaMensal));
			//modelLogin.setDtNascimento(dataNascimento);
			modelLogin.setCpf(cpf);
			modelLogin.setRg(rg);
			//modelLogin.setDtNascimento(Date.parse(dtnascimento));
			modelLogin.setStatusId(statusId);
			modelLogin.setPerfil(perfil);
			modelLogin.setGenero(genero);
			
			/*
			modelLogin.setCep(cep);
			modelLogin.setLogradouro(logradouro);
			modelLogin.setNumero(numero);
			modelLogin.setComplemento(complemento);
			modelLogin.setBairro(bairro);
			modelLogin.setCidade(cidade);
			modelLogin.setUf(uf);
			*/
			
			if (ServletFileUpload.isMultipartContent(request)) {
				Part part = request.getPart("fileFoto"); //Pega a foto da tela atrav�s do atributo name
				
				if (part.getSize() > 0) {
					byte[] foto = IOUtils.toByteArray(part.getInputStream());	//Converte a imagem para byte
					String imagemBase64 = "data:image/"+ part.getContentType().split("\\/")[1] + ";base64," + new Base64().encodeBase64String(foto);
					//System.out.println(imagemBase64);
					
					modelLogin.setFotoUser(imagemBase64);
					modelLogin.setFotoUserExtensao(part.getContentType().split("\\/")[1]);
				}
			}
			
			
			if (daoUsuarioRepository.validarLoginEmUso(modelLogin.getLogin()) && modelLogin.getId() == null) { //VALIDA SE LOGIN EXISTE NA BASE DE DADOS
				msg = "Login j� em uso por outro usu�rio !";
			} else {
				if(modelLogin.isNovo()) {
					/*** Registra novo usu�rio ***/ 
					msg = "Novo usu�rio registrado com sucesso !";
				} else {
					/*** Atualiza Usu�rio Existente ***/ 
					msg = "Dados do usuário atualizados com sucesso !";
				}
				//modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));
			}
			
			
			List<ModelLogin> modelLogins = daoUsuarioRepository.consultaListaUsuario();
			//List<ModelLogin> modelLogins = daoUsuarioRepository.consultaListaUsuario(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins); 	// mant�m os dados na tela
			
			request.setAttribute("msg", msg);
			request.setAttribute("modelLogin", modelLogin); 	// mant�m os dados na tela
			//request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(super.getUserLogado(request)));
			request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina());
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			
		
		} catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher redirect = request.getRequestDispatcher("/error.jsp"); // redirecionamento para a p�gina "error.jsp"
			request.setAttribute("msg", e.getMessage()); // "msg" � o nome do atributo passado por par�metro no redirecionamento
			redirect.forward(request, response);
		}
		
	}

}
