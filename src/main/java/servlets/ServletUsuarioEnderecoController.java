package servlets;

import java.io.IOException;
import java.util.List;

import dao.DAOUsuarioRepository;
import dao.DAOUsuarioEnderecoRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ModelLogin;
import models.ModelTelefone;
import models.ModelEndereco;

@WebServlet("/ServletUsuarioEnderecoController")
public class ServletUsuarioEnderecoController extends ServletGenericUtil {

	private static final long serialVersionUID = 1L;
    
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	private DAOUsuarioEnderecoRepository daoUsuarioEnderecoRepository = new DAOUsuarioEnderecoRepository();

    public ServletUsuarioEnderecoController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * try { String iduser = request.getParameter("iduser"); if(iduser != null &&
		 * !iduser.isEmpty()) { ModelLogin modelLogin =
		 * daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(iduser));
		 * 
		 * request.setAttribute("usuario", modelLogin);
		 * request.getRequestDispatcher("principal/telefone.jsp").forward(request,
		 * response); } else { List<ModelLogin> modelLogins =
		 * daoUsuarioRepository.consultaListaUsuario();
		 * request.setAttribute("modelEnderecos", modelLogins); // mantém os dados na
		 * tela request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina());
		 * request.getRequestDispatcher("principal/usuario.jsp").forward(request,
		 * response); } } catch(Exception e) { e.printStackTrace(); }
		 */
		
		try {
			
			String acao = request.getParameter("acao");
			
			if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				String idEndereco = request.getParameter("id");
				String user_id = request.getParameter("userid");
								
				ModelLogin modelLogin = daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(user_id));
				List<ModelEndereco> modelEnderecos = daoUsuarioEnderecoRepository.consultaUsuarioEnderecoList(modelLogin.getId());
				ModelEndereco modelUserEnderecos = daoUsuarioEnderecoRepository.consultaDadosEnderecoUsuarioPorId(Long.parseLong(idEndereco));

				request.setAttribute("modelLogin", modelLogin);
				request.setAttribute("modelEnderecos", modelEnderecos);
				request.setAttribute("modelUserEnderecos", modelUserEnderecos);
				request.setAttribute("msg", "Endereço em edição");
				request.getRequestDispatcher("principal/endereco.jsp").forward(request, response);
				
				return;
			}
			
			if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluir")) {
				String idEndereco = request.getParameter("id");
				String user_id = request.getParameter("userid");
				
				daoUsuarioEnderecoRepository.excluirUsuarioEndereco(Long.parseLong(idEndereco));
				//daoUsuarioTelefoneRepository.excluirUsuarioTelefone(Long.parseLong(idFone));
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(user_id));
				
				List<ModelEndereco> modelEnderecos = daoUsuarioEnderecoRepository.consultaUsuarioEnderecoList(modelLogin.getId());

				request.setAttribute("modelEnderecos", modelEnderecos);
				request.setAttribute("modelLogin", modelLogin);
				request.setAttribute("msg", "Excluído com sucesso");
				request.getRequestDispatcher("principal/endereco.jsp").forward(request, response);
				
				return;
			}
			
			/*
			if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluirComAjax")) {
				String idFone = request.getParameter("id");
				String user_id = request.getParameter("userid");
				
				daoUsuarioEnderecoRepository.excluirUsuarioEndereco(Long.parseLong(idFone));

				ModelLogin modelLogin = daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(user_id));

				List<ModelEndereco> modelEnderecos = daoUsuarioEnderecoRepository.consultaUsuarioEnderecoList(modelLogin.getId());

				request.setAttribute("modelEnderecos", modelEnderecos);
				request.setAttribute("modelLogin", modelLogin);
				response.getWriter().write("Excluído com sucesso!");
								
				return;
			}
			*/
			
			String iduser = request.getParameter("iduser");	//parâmetro recebido na url
			if(iduser != null && !iduser.isEmpty())
			{
				ModelLogin modelLogin = daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(iduser));
				
				List<ModelEndereco> modelEnderecos = daoUsuarioEnderecoRepository.consultaUsuarioEnderecoList(modelLogin.getId());

				request.setAttribute("modelEnderecos", modelEnderecos);
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/endereco.jsp").forward(request, response);
			} else {
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaListaUsuario();
				request.setAttribute("modelLogins", modelLogins); 	// mantém os dados na tela
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina());
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
			String endereco_id = request.getParameter("endereco_id");
			String cep = request.getParameter("cep");
			String logradouro = request.getParameter("logradouro");
			String numero = request.getParameter("numero");
			String complemento = request.getParameter("complemento");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String uf = request.getParameter("uf");
			boolean isEnderecoPrincipal = request.getParameter("isEnderecoPrincipal") != null ? true : false;
			
			ModelEndereco modelEndereco = new ModelEndereco();
			modelEndereco.setId(endereco_id != null && !endereco_id.isEmpty() ? Long.parseLong(endereco_id) : null);
			modelEndereco.setUsuario_id(daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(iduser)));
			modelEndereco.setEnderecoPrincipal(isEnderecoPrincipal);
			modelEndereco.setCep(cep);
			modelEndereco.setLogradouro(logradouro);
			modelEndereco.setNumero(numero);
			modelEndereco.setComplemento(complemento);
			modelEndereco.setBairro(bairro);
			modelEndereco.setCidade(cidade);
			modelEndereco.setUf(uf);
			modelEndereco.setCriado_por(super.getUserLogadoObject(request));
			
			if(daoUsuarioEnderecoRepository.validarEnderecoUsuarioEmUso(Long.parseLong(iduser), modelEndereco.getLogradouro())  && modelEndereco.getId() == null ) { //VALIDA SE NUMERO EXISTE NA BASE DE DADOS
				msg = "Endereco já registrado no banco de dados para este usuário. Nenhuma alteração realizada !";
			} else {
				if(modelEndereco.isNovo()) {
					/*** Registra novo usuário ***/ 
					msg = "Endereço registrado com sucesso !";
				} else {
					/*** Atualiza Usuário Existente ***/ 
					msg = "Endereço atualizado com sucesso !";
				}
				daoUsuarioEnderecoRepository.gravaEndereco(modelEndereco);
			}
			
			List<ModelEndereco> modelEnderecos = daoUsuarioEnderecoRepository.consultaUsuarioEnderecoList(Long.parseLong(iduser));

			ModelLogin modelLogin = daoUsuarioRepository.consultaDadosUsuarioPorId(Long.parseLong(iduser));
			
			if (endereco_id != null && !endereco_id.isEmpty()) {
				ModelEndereco modelUserEnderecos = daoUsuarioEnderecoRepository.consultaDadosEnderecoUsuarioPorId(Long.parseLong(endereco_id));
				request.setAttribute("modelUserEnderecos", modelUserEnderecos);
			}
			
			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("modelEnderecos", modelEnderecos);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("principal/endereco.jsp").forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
