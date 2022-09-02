<%@page import="models.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html lang="en">


<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="theme-loader.jsp"></jsp:include>

  <body>
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
          
		<jsp:include page="navbar.jsp"></jsp:include>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
                  
                  
				<jsp:include page="navbar_main_menu.jsp"></jsp:include>
                  
                  <div class="pcoded-content">
                      
                      
                      <!-- Page-header start -->
                      
					<jsp:include page="page-header.jsp"></jsp:include>
                      <!-- Page-header end -->
                      
                      
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                    
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Cadastro de usuário</h5>
                                                    </div>
                                                    <div class="card-block">
                                                        <form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post" id="formUser">
                                                        
                                                        	<input type="hidden" name="acao" id="acao" value="">
                                                        	
                                                        	<div class="form-group form-default form-static-label">
                                                                <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${ modelLogin.id }">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default input-group mb-3">
                                                            	<div class="input-group-prepend">
                                                            		<c:if test="${ modelLogin.fotoUser != '' && modelLogin.fotoUser != null}">
                                                            			<a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=downloadFoto&id=${ modelLogin.id }">
                                                            				<img alt="Imagem Usuário" id="fotoembase64" src="${ modelLogin.fotoUser }" width="70px">
                                                            			</a>
                                                            		</c:if>
                                                            		<c:if test="${ modelLogin.fotoUser == '' || modelLogin.fotoUser == null}">
                                                            			<img alt="Imagem Usuário" id="fotoembase64" src="assets/images/avatar.png" width="70px">
                                                            		</c:if>
                                                            	</div>
                                                            	<input type="file" class="form-control" id="fileFoto" name="fileFoto" accept="image/*" onchange="visualizarImg('fotoembase64','fileFoto');"  style="margin-top: 15px; margin-left: 5px;">
                                                            	<label class="float-label">Foto</label>
                                                            </div>

                                                        	
                                                            
                                                            <div class="form-group row form-default form-static-label">
                                                            	<div class="col">
	                                                            	<input type="text" name="login" id="login" class="form-control" required="required" autocomplete="off" value="${ modelLogin.login }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Login</label>
                                                            	</div>
                                                            	<div class="col">
	                                                            	<input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off" value="${ modelLogin.senha }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Senha</label>
                                                            	</div>
                                                            </div>
                                                            
                                                            
                                                        	<%-- <div class="form-group form-default form-static-label">
                                                                <input type="text" name="login" id="login" class="form-control" required="required" autocomplete="off" value="${ modelLogin.login }">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Login</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off" value="${ modelLogin.senha }">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha</label>
                                                            </div> --%>
                                                            <%-- 
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="nome" id="nome" class="form-control" required="required" value="${ modelLogin.nome }">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Nome</label>
                                                            </div> --%>
                                                            <%-- 
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="email" name="email" id="email" class="form-control" required="required" value="${ modelLogin.email }">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">E-mail</label>
                                                            </div>
                                                            --%>
                                                            
                                                            
                                                            <div class="form-group row form-default form-static-label">
                                                            	<div class="col">
	                                                            	<input type="text" name="nome" id="nome" class="form-control" required="required" value="${ modelLogin.nome }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nome</label>
                                                            	</div>
                                                            	<div class="col">
	                                                            	<input type="text" name="sobrenome" id="sobrenome" class="form-control" required="required" value="${ modelLogin.sobrenome }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sobrenome</label>
                                                            	</div>
                                                            </div>
                                                            
                                                            
                                                            
                                                            <div class="form-group row form-default form-static-label">
                                                            	<div class="col-sm-6">
	                                                            	<input type="email" name="email" id="email" class="form-control" required="required" value="${ modelLogin.email }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;E-mail</label>
                                                            	</div>
                                                            </div>
                                                            
                                                            <div class="form-group row form-default form-static-label">
                                                            	<div class="col-sm-3">
                                                        	    <input type="text" name="dataNascimento" id="dataNascimento" class="form-control" required="required" autocomplete="off" value="${ modelLogin.dtNascimento }" >
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Data de Nascimento</label>
                                                                </div>
                                                            </div>
                                                            
                                                            <div class="form-group row form-default form-static-label">
                                                            	<div class="col-sm-3">
                                                        	    <input type="text" name="rendamensal" id="rendamensal" class="form-control" required="required" autocomplete="off" value="${ modelLogin.rendamensal }" >
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Renda Mensal</label>
                                                                </div>
                                                            </div>
                                                            
                                                            
                                                            <div class="form-group row form-default form-static-label">
                                                            	<div class="col-sm-3">
	                                                            	<input type="text" oninput="cpfMask(this)" name="cpf" id="cpf" class="form-control" required="required" autocomplete="off" value="${ modelLogin.cpf }" >
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CPF</label>
                                                            	</div>
                                                            	<div class="col-sm-3">
	                                                            	<input type="text" name="rg" id="rg" class="form-control" required="required" autocomplete="off" value="${ modelLogin.rg }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RG</label>
                                                            	</div>
                                                            </div>
                                                           	
                                                            
                                                            
															<div class="form-group form-default form-static-label">
                                                                <input type="radio" id="radioGenero" name="radioGenero" value="M" <%  
                                                               	ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");

                                                                  if (modelLogin != null && modelLogin.getGenero().equals("M")) {
                                                                  	out.print(" ");
                                                                  	out.print(" checked=\"checked\"");
                                                                  	out.print(" ");
                                                                  } %>>Masculino</>
                                                                <input type="radio" id="radioGenero" name="radioGenero" value="F" <%  
                                                               	modelLogin = (ModelLogin) request.getAttribute("modelLogin");

                                                                  if (modelLogin != null && modelLogin.getGenero().equals("F")) {
                                                                  	out.print(" ");
                                                                  	out.print(" checked=\"checked\"");
                                                                  	out.print(" ");
                                                                  } %>>Feminino</>
                                                                <!-- <span class="form-bar"></span> -->
                                                           	</div>
                                                           	
															<div class="form-group form-default form-static-label">
                                                                <input type="radio" id="radioStatus" name="radioStatus" value="ativo" <%  
                                                               	modelLogin = (ModelLogin) request.getAttribute("modelLogin");

                                                                  if (modelLogin != null && modelLogin.getStatusId().equals("1")) {
                                                                  	out.print(" ");
                                                                  	out.print(" checked=\"checked\"");
                                                                  	out.print(" ");
                                                                  } %>>
                                                                <i class="helper"></i>Ativo
                                                                <input type="radio" id="radioStatus" name="radioStatus" value="inativo" <%  
                                                               	modelLogin = (ModelLogin) request.getAttribute("modelLogin");

                                                                  if (modelLogin != null && modelLogin.getStatusId().equals("0")) {
                                                                  	out.print(" ");
                                                                  	out.print(" checked=\"checked\"");
                                                                  	out.print(" ");
                                                                  } %>>
                                                                <i class="helper"></i>Inativo
                                                                <span class="form-bar"></span>
                                                           	</div>
                                                           	
                                                           	
                                                           	<div class="form-group row form-default form-static-label">
                                                            	<div class="col-sm-3">
                                                            	<select class="form-control" aria-lable="Default select example" name="perfil">
                                                                    <option disabled="disabled">Selecione o perfil</option>
                                                                    <c:forEach items="${listaPerfil}" var="perfil">
																        <option value="${perfil.id}"   
																        	<c:if test="${perfil.id == perfilSelecionadoId}">selected=\"selected\"</c:if>
																	        >${perfil.descricao}
																        </option> 
																    </c:forEach>
                                                                </select>
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Perfil</label>
                                                            	</div>
                                                            </div>
                                                           	
                                                           	<%-- 
                                                           	<div class="form-group form-default form-static-label">
                                                        		<select class="form-control" aria-lable="Default select example" name="perfil">
                                                                    <option disabled="disabled">Selecione o perfil</option>
                                                                    <c:forEach items="${listaPerfil}" var="perfil">
																        <option value="${perfil.id}"   
																        	<c:if test="${perfil.id == perfilSelecionadoId}">selected=\"selected\"</c:if>
																	        >${perfil.descricao}
																        </option> 
																    </c:forEach>
                                                                </select>
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Perfil</label>
                                                            </div>
                                                             --%>
                                                           	
                                                           	 <%-- <div class="form-group form-default form-static-label">
                                                        		<select class="form-control" aria-lable="Default select example" name="perfil">
                                                                    <option disabled="disabled">Selecione o perfil</option>
                                                                    <option value="ADMIN" <%                                                                     
                                                                    modelLogin = (ModelLogin) request.getAttribute("modelLogin");
                                                                    
                                                                    if (modelLogin != null && modelLogin.getPerfil().equals("ADMIN")) {
                                                                    	out.print(" ");
                                                                    	out.print(" selected=\"selected\"");
                                                                    	out.print(" ");
                                                                    } %>>Admin</option>
                                                                    <option value="SECRETARIA" <% 
                                                                 	modelLogin = (ModelLogin) request.getAttribute("modelLogin");                          
                                                                    if (modelLogin != null && modelLogin.getPerfil().equals("SECRETARIA")) {
                                                                    	out.print(" ");
                                                                    	out.print(" selected=\"selected\"");
                                                                    	out.print(" ");
                                                                    } %>>Secretária</option>
                                                                    <option value="AUXILIAR" <%
                                                                    modelLogin = (ModelLogin) request.getAttribute("modelLogin");                                   
                                                                    if (modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")) {
                                                                    	out.print(" ");
                                                                    	out.print(" selected=\"selected\"");
                                                                    	out.print(" ");
                                                                    } %>>Auxiliar</option>
                                                                </select>
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Perfil</label>
                                                            </div>  --%>
                                                           	
                                                           	
                                                            <%-- 
                                                           	<div class="form-group row form-default form-static-label">
                                                            	<div class="col-sm-3">
                                                        	    <input onblur="pesquisaCep()" type="text" name="cep" id="cep" class="form-control" required="required" autocomplete="off" value="${ modelLogin.cep }">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CEP</label>
                                                                </div>
                                                            </div>
                                                           	 --%>
                                                           	
                                                           	<%-- 
                                                        	<div class="form-group row form-default form-static-label">
                                                                
                                                                <div class="col-sm-7">
	                                                                <input type="text" name="logradouro" id="logradouro" class="form-control" required="required" autocomplete="off" value="${ modelLogin.logradouro }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Logradouro</label>
                                                            	</div>
                                                                <div class="col-sm-1">
	                                                                <input type="text" name="numero" id="numero" class="form-control" required="required" autocomplete="off" value="${ modelLogin.numero }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Número</label>
                                                            	</div>
                                                            	<div class="col">
                                                            		<input type="text" name="complemento" id="complemento" class="form-control" autocomplete="off" value="${ modelLogin.complemento }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Complemento</label>
                                                            	</div>
                                                            </div>
                                                             --%>
                                                            
                                                            <%-- 
                                                            <div class="form-group row form-default form-static-label">
                                                            	<div class="col-sm-7">
	                                                            	<input type="text" name="bairro" id="bairro" class="form-control" required="required" readonly="readonly"  autocomplete="off" value="${ modelLogin.bairro }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bairro</label>
                                                            	</div>
                                                            	<div class="col">
	                                                            	<input type="text" name="cidade" id="cidade" class="form-control" required="required" readonly="readonly" autocomplete="off" value="${ modelLogin.cidade }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cidade</label>
                                                            	</div>
                                                            	<div class="col-sm-1">
                                                            		<input type="text" name="uf" id="uf" class="form-control" required="required" readonly="readonly"  autocomplete="off" value="${ modelLogin.uf }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Estado</label>
                                                            	</div>
                                                            </div>
                                                             --%>
                                                           	
												            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm();">Novo</button>
												            <button type="submit" class="btn btn-success waves-effect waves-light">Salvar</button>
												            <button type="button" class="btn btn-danger waves-effect waves-light" onclick="excluirUsuarioComAjax()">Excluir</button>
												            
												            <%-- 
												            <c:if test="${ modelLogin.id > 0 }">
												            	<a href="<%= request.getContextPath() %>/ServletUsuarioTelefoneController?iduser=${ modelLogin.id }" class="btn btn-primary waves-effect waves-light">Telefone</a>
												            	<a href="<%= request.getContextPath() %>/ServletUsuarioEnderecoController?iduser=${ modelLogin.id }" class="btn btn-primary waves-effect waves-light">Endereço</a>
												            </c:if>
												             --%>
												            
												            <button type="button" class="btn btn-info waves-effect waves-light" data-toggle="modal" data-target="#pesquisaUsuarioModal">Pesquisar</button>
												            
												            
												            <div class="form-group form-default">
												            	</br>
												            	<span id="mensagem">${msg}</span>
												            </div>
												            
                                            			</form>
                                           			
                                                    </div>
                                       			</div>
                                     		</div>

                                        </div>
                                        
                                        
                                        <div style="height: 300px; overflow:scroll;">
											<table class="table table-hover" id="tabelaresultadosview">
											  <thead>
											    <tr>
											      <th scope="col">ID</th>
											      <th scope="col">Login</th>
											      <th scope="col">Nome</th>
											      <th scope="col">E-mail</th>
											      <!-- <th scope="col" colspan="3" style="text-align: center;">Ações</th> -->
											      <th scope="col">Visualizar</th>
											      <th scope="col">Cadastrar Telefone</th>
											      <th scope="col">Cadastrar Endereço</th>
											    </tr>
											  </thead>
											  <tbody>
												<c:forEach items="${modelLogins}" var="ml">
													<tr>
														<td><c:out value="${ml.id}"></c:out></td>
														<td><c:out value="${ml.login}"></c:out></td>
														<td><c:out value="${ml.nome}"></c:out></td>
														<td><c:out value="${ml.email}"></c:out></td>
														<%-- <td><c:out value="${ml.email}"></c:out></td> --%>
														<td><a class="btn btn-success waves-effect waves-light" href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">Visualizar</a></td>
														<td><a href="<%= request.getContextPath() %>/ServletUsuarioTelefoneController?iduser=${ ml.id }" class="btn btn-info waves-effect waves-light">Cad. Telefone</a></td>
														<td><a href="<%= request.getContextPath() %>/ServletUsuarioEnderecoController?iduser=${ ml.id }" class="btn btn-info waves-effect waves-light">Cad. Endereço</a></td>
													</tr>
												</c:forEach>
											  </tbody>
											</table>
										</div>
                                        
                                        <br/>
                                        
                                        <!-- paginação -->
                                        <nav aria-label="Page navigation example">
										  <ul class="pagination">
										  
										  	<%
										  		int totalPagina = (int) request.getAttribute("totalPagina"); //nome dado na ServLet
										  		for(int p = 0; p < totalPagina; p++) {
										  			String url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&pagina=" + (p * 10);	//10 é a quantidade desejada para exibição de registros na pagina
										  			out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+ url +"\">"+ (p + 1) +"</a></li>");
										  		}
										  	%>
										  </ul>
										</nav>
                                        
                                        
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
 


<jsp:include page="javascriptfile.jsp"></jsp:include>    

<!-- Modal -->

<div class="modal fade bd-example-modal-xl" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      ...
    </div>
  </div>
</div>


<!-- 
<div class="modal fade" id="pesquisaUsuarioModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document"> 
-->
<div class="modal fade" id="pesquisaUsuarioModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="max-width: 1080px!important;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usuário</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
	      <div class="input-group mb-3">
		      <input type="text" class="form-control" placeholder="Pesquisar" aria-label="nome" id="nomeBusca" aria-describedby="basic-addon2" title="Pesquisar por nome, login ou e-mail">
			  <div class="input-group-append">
			    <button class="btn btn-info" type="button" onclick="buscarUsuario()">Buscar</button>
			  </div>
		  </div>
	  
			<div style="height: 300px; overflow:scroll;">
				<table class="table table-hover" id="tabelaresultados">
				  <thead>
				    <tr>
				      <th scope="col" style="width:5%">ID</th>
				      <th scope="col" style="width:20%">Login</th>
				      <th scope="col" style="width:35%">Nome</th>
				      <th scope="col" style="width:25%">E-mail</th>
				      <th scope="col" style="width:15%">Ação</th>
				    </tr>
				  </thead>
				  <tbody>
				
				  </tbody>
				</table>
			</div>
			
			<!-- paginação modal pesquisa -->
            <nav aria-label="Page navigation example">
			  <ul class="pagination" id="ulPaginacaoUserAjax">

			  </ul>
			</nav>
                                        
			
			<span id="totalResultados"> &nbsp; </span>
      	</div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
        <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
      </div>
    </div>
  </div>
</div>


<script type="text/javascript">

	$("#rendamensal").maskMoney({showSymbol:true, symbol: "R$ ", decimal:",", thousands:"."});
		
	const formatter = new Intl.NumberFormat('pt-BR', {
		currency : 'BRL',
		minimumFractionDigits : 2
	});
	
	$("#rendamensal").val(formatter.format($("#rendamensal").val()));
	$("#rendamensal").focus();
	
	var dataNascimento = $("#dataNascimento").val();
	var dateFormat = new Date(dataNascimento);
	
	$("#dataNascimento").val(dateFormat.toLocaleDateString('pt-BR', {timeZone: 'UTC'}));
	
	$("#nome").focus();

	$( function() {
		  
		  $("#dataNascimento").datepicker({
			    dateFormat: 'dd/mm/yy',
			    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
			    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
			    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
			    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
			    nextText: 'Próximo',
			    prevText: 'Anterior'
			});
	} );
	
	function cpfMask(i){
	   
	   var v = i.value;
	   
	   if(isNaN(v[v.length-1])){ // impede entrar outro caractere que não seja número
	      i.value = v.substring(0, v.length-1);
	      return;
	   }
	   
	   i.setAttribute("maxlength", "14");
	   if (v.length == 3 || v.length == 7) i.value += ".";
	   if (v.length == 11) i.value += "-";

	}
	
	function pesquisaCep() {
		var cep = $("#cep").val();
		
		$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function(dados){
			if(!("erro" in dados)) {
				$("#cep").val(dados.cep);
				$("#logradouro").val(dados.logradouro);
				$("#bairro").val(dados.bairro);
				$("#cidade").val(dados.localidade);	//cidade é nome do id do textBox e localidade é o nome do atributo do objeto recebido
				$("#uf").val(dados.uf);
			} else {
				alert("CEP informado não encontrado");
			}
		});
	}
	
	function visualizarImg(fotoembase64, filefoto){
		
		var preview = document.getElementById(fotoembase64);	// campo IMG do HTML
		var fileUser = document.getElementById(filefoto).files[0];
		var reader = new FileReader();
		
		reader.onloadend = function (){
			preview.src = reader.result;	//Carrega a foto na tela
		};
		
		if (fileUser) {
			reader.readAsDataURL(fileUser);	//Preview da imagem
		} else {
			preview.src = '';
		}
		
	}

	function selecionarUsuario(id) {
		var urlAction = document.getElementById('formUser').action;
		window.location.href = urlAction + '?acao=buscarEditar&id=' + id 
	}
	
	function buscaUserPagAjax(url) {
		
		var urlAction = document.getElementById('formUser').action;
		var nomeBusca = document.getElementById('nomeBusca').value;
		
		$.ajax({
			method: "get",
			url: urlAction,
			//data: "nomeBusca=" + nomeBusca + "&acao=buscarUserAjax",
			data: url,
			success: function (response, textStatus, xhr) {
				
				var json = JSON.parse(response);
				
				$('#tabelaresultados > tbody > tr').remove(); //remove todas as linhas pesquisadas anteriormente
				$('#ulPaginacaoUserAjax > li').remove();
				
				for (var p = 0; p < json.length; p++) {
					$('#tabelaresultados > tbody').append('<tr><td style="width:5%">' + json[p].id + '</td><td style="width:20%">' + json[p].login + '</td><td style="width:35%">' + json[p].nome + '</td><td style="width:25%">' + json[p].email + '</td><td style="width:15%"><button class="btn btn-info" type="button" onclick="selecionarUsuario(' + json[p].id + ') ">Visualizar</button></td></tr>');
				}
				
				document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length;
				
				var totalPagina = xhr.getResponseHeader("totalPagina");
				
				for (var p = 0; p < totalPagina; p++) {
					/* var url = urlAction + "?nomeBusca=" + nomeBusca + "&acao=buscarUserAjaxPage&pagina=" + (p * 10);	//10 é a quantidade desejada para exibição de registros na pagina */
					/* $("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href=' + url + '>' + (p + 1) + '</a></li>'); */
					
					var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina=' + (p * 10);
					//$("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\'' + url + '\')">' + (p + 1) + '</a></li>');
					$("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\'' + url + '\')">' + (p + 1) + '</a></li>');
				}

			}
			
		}).fail(function(xhr, status, errorThrown){
			alert("Erro ao buscar usuário. Busca realizada: " + xhr.responseText);
		});
		
	}
	
	function buscarUsuario() {
		
		var nomeBusca = document.getElementById('nomeBusca').value;
		
		if(nomeBusca != '' && nomeBusca.trim() != '' && nomeBusca != null) { /*Validando se o campo de busca foi preenchido*/
			
			var urlAction = document.getElementById('formUser').action;
		
			$.ajax({
				method: "get",
				url: urlAction,
				data: "nomeBusca=" + nomeBusca + "&acao=buscarUserAjax",
				success: function (response, textStatus, xhr) {
					
					var json = JSON.parse(response);
					
					//console.info(json);
					$('#tabelaresultados > tbody > tr').remove(); //remove todas as linhas pesquisadas anteriormente
					$('#ulPaginacaoUserAjax > li').remove();
					
					
					for (var p = 0; p < json.length; p++) {
						$('#tabelaresultados > tbody').append('<tr><td style="width:5%">' + json[p].id + '</td><td style="width:20%">' + json[p].login + '</td><td style="width:35%">' + json[p].nome + '</td><td style="width:25%">' + json[p].email + '</td><td style="width:15%"><button class="btn btn-info" type="button" onclick="selecionarUsuario(' + json[p].id + ') ">Visualizar</button></td></tr>');
					}
					
					document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length;
					
					var totalPagina = xhr.getResponseHeader("totalPagina");
					
					for (var p = 0; p < totalPagina; p++) {
						
						//var url = urlAction + "?nomeBusca=" + nomeBusca + "&acao=buscarUserAjaxPage&pagina=" + (p * 10);	//10 é a quantidade desejada para exibição de registros na pagina
						/* $("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href=' + url + '>' + (p + 1) + '</a></li>'); */
						var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina=' + (p * 10); 
						$("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\'' + url + '\')">' + (p + 1) + '</a></li>');
					}

				}
				
			}).fail(function(xhr, status, errorThrown){
				alert("Erro ao buscar usuário. Busca realizada: " + xhr.responseText);
			});
		} 
	}

	function excluirUsuarioComAjax() {
		
		if (confirm('Deseja realmente excluir os dados?')) {

			var urlAction = document.getElementById('formUser').action;
			var idUser = document.getElementById('id').value;
			
			if (idUser == "" || idUser == null) {
				//alert("Não há dados selecionados. Não foi possível excluir !");
				document.getElementById('mensagem').textContent = "Não há dados selecionados. Não foi possível excluir !";
			    return false;
			} else {
			
				$.ajax({
					method: "get",
					url: urlAction,
					data: "id=" + idUser + "&acao=deletarajax",
					success: function (response) {
						limparForm();
						document.getElementById('mensagem').textContent = response;
					}
					
				}).fail(function(xhr, status, errorThrown){
					alert("Erro ao excluir usuário por ID:" + xhr.responseText);
				});
			}
				
		}
		
	}

	function excluirUsuario() {
		
		if (confirm('Deseja realmente excluir os dados?')) {
			
			var x = document.forms["formUser"]["id"].value;
			if (x == "" || x == null) {
				alert("Não há dados selecionados. Não foi possível excluir !");
			    return false;
			} else {
				document.getElementById("formUser").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("formUser").submit();
		  	}
				
		}
		
	}
	
	function limparForm() {
		var elementos = document.getElementById("formUser").elements; /* Retorna os elementos html dentro do form */
		
		for (p = 0; p < elementos.length; p++) {
			elementos[p].value = '';
		}
	}

</script>

</body>

</html>
