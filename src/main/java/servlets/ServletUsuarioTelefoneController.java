package servlets;

import java.io.IOException;
import java.util.List;

import dao.DAOUsuarioRepository;
import dao.DAOUsuarioTelefoneRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ModelLogin;
import models.ModelTelefone;

@WebServlet("/ServletUsuarioTelefoneController")
public class ServletUsuarioTelefoneController extends ServletGenericUtil {
	
	private static final long serialVersionUID = 1L;
    
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	private DAOUsuarioTelefoneRepository daoUsuarioTelefoneRepository = new DAOUsuarioTelefoneRepository();
	
    public ServletUsuarioTelefoneController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String acao = request.getParameter("acao");
			

			if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				String idFone = request.getParameter("id");
				String user_id = request.getParameter("userid");
								
				ModelLogin modelLogin = daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(user_id));
				List<ModelTelefone> modelTelefones = daoUsuarioTelefoneRepository.consultaUsuarioTelefonesList(modelLogin.getId());
				ModelTelefone modelUserTelefones = daoUsuarioTelefoneRepository.consultaDadosTelefoneUsuarioPorId(Long.parseLong(idFone));

				request.setAttribute("modelLogin", modelLogin);
				request.setAttribute("modelTelefones", modelTelefones);
				request.setAttribute("modelUserTelefones", modelUserTelefones);
				request.setAttribute("msg", "Telefone em edição");
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				
				return;
			}
			
			if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluir")) {
				String idFone = request.getParameter("id");
				String user_id = request.getParameter("userid");
				
				daoUsuarioTelefoneRepository.excluirUsuarioTelefone(Long.parseLong(idFone));
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(user_id));
				
				List<ModelTelefone> modelTelefones = daoUsuarioTelefoneRepository.consultaUsuarioTelefonesList(modelLogin.getId());

				request.setAttribute("modelTelefones", modelTelefones);
				request.setAttribute("modelLogin", modelLogin);
				request.setAttribute("msg", "Excluído com sucesso");
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				
				return;
			}
			
			/*
			if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluirComAjax")) {
				String idFone = request.getParameter("id");
				String user_id = request.getParameter("userid");
				
				daoUsuarioTelefoneRepository.excluirUsuarioTelefone(Long.parseLong(idFone));

				ModelLogin modelLogin = daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(user_id));
				List<ModelTelefone> modelTelefones = daoUsuarioTelefoneRepository.consultaUsuarioTelefonesList(modelLogin.getId());

				request.setAttribute("modelTelefones", modelTelefones); 	// mantém os dados na tela
				request.setAttribute("modelLogin", modelLogin);
				response.getWriter().write("Excluído com sucesso!");
				
				return;
			}
			*/
			
			String iduser = request.getParameter("iduser");	//parâmetro recebido na url
			if(iduser != null && !iduser.isEmpty())
			{
				ModelLogin modelLogin = daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(iduser));
				
				List<ModelTelefone> modelTelefones = daoUsuarioTelefoneRepository.consultaUsuarioTelefonesList(modelLogin.getId());

				request.setAttribute("modelTelefones", modelTelefones);
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			} else {
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaListaUsuario();
				request.setAttribute("modelLogins", modelLogins); 	// mantém os dados na tela
				//request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina());
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String msg = "";
		try {

			String iduser = request.getParameter("id");		//nome do componente html enviado na requisição
			String numero = request.getParameter("numero");
			String tel_id = request.getParameter("tel_id");
			
			ModelTelefone modelTelefone = new ModelTelefone();
			modelTelefone.setId(tel_id != null && !tel_id.isEmpty() ? Long.parseLong(tel_id) : null);
			modelTelefone.setNumero(numero);
			modelTelefone.setUsuario_id(daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(iduser)));
			modelTelefone.setCriado_por(super.getUserLogadoObject(request));
			
			/* daoUsuarioTelefoneRepository.gravaTelefone(modelTelefone); */
			
			if(daoUsuarioTelefoneRepository.validarTelefoneEmUso(modelTelefone.getNumero())) { //VALIDA SE NUMERO EXISTE NA BASE DE DADOS
				msg = "Telefone já existe no banco de dados. Nenhuma alteração realizada !";
			} else {
				if(modelTelefone.isNovo()) {
					/*** Registra novo usuário ***/ 
					msg = "Novo telefone registrado com sucesso !";
				} else {
					/*** Atualiza Usuário Existente ***/ 
					msg = "Telefone atualizado com sucesso !";
				}
				daoUsuarioTelefoneRepository.gravaTelefone(modelTelefone);
			}
			
			List<ModelTelefone> modelTelefones = daoUsuarioTelefoneRepository.consultaUsuarioTelefonesList(Long.parseLong(iduser));

			ModelLogin modelLogin = daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(iduser));
			
			if (tel_id != null && !tel_id.isEmpty()) {
				ModelTelefone modelUserTelefones = daoUsuarioTelefoneRepository.consultaDadosTelefoneUsuarioPorId(Long.parseLong(tel_id));
				request.setAttribute("modelUserTelefones", modelUserTelefones);
			}
			
			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("modelTelefones", modelTelefones);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
