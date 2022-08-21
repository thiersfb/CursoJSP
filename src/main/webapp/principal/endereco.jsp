<%@page import="models.ModelLogin"%>
<%@page import="models.ModelEndereco"%>
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
                                            <div class="col-sm-12">
                                    			<!-- Basic form Inputs card start -->
                                    			<div class="card">
                                                    <div class="card-header">
                                                        <h5>Cadastro de Endereços</h5>
                                                        
                                                    </div>
                                    				<div class="card-block">
                                    					
                                    					<form class="form-material"  action="<%= request.getContextPath() %>/ServletUsuarioEnderecoController" method="post" id="formEndereco">
                                    					
                                    						<input type="hidden" name="acao" id="acao" value="">
                                                            
                                                            <div class="form-group row form-default form-static-label">
                                                                
                                                                <div class="col-sm-2">
	                                                                <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${ modelLogin.id }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID User:</label>
                                                            	</div>
                                                                <div class="col">
	                                                                <input type="text" name="nome" id="nome" class="form-control" readonly="readonly" value="${ modelLogin.nome } ${ modelLogin.sobrenome }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nome</label>
                                                            	</div>
                                                            </div>
                                                            
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <%-- <input type="text" name="numero" id="numero" class="form-control" value="${ modelLogin.id }"> --%>
                                                                <input type="text" name="endereco_id" id="endereco_id" class="form-control" readonly="readonly" value="${ modelUserEnderecos.id }" >
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID Endereço</label>
                                                            </div> 
                                    					                                                            
                                                            
                                                            <div class="form-group row form-default form-static-label">
                                                            	<div class="col-sm-3">
                                                        	<!-- <div class="form-group form-default form-static-label"> -->
                                                                <input onblur="pesquisaCep()" type="text" name="cep" id="cep" class="form-control" required="required" autocomplete="off" value="${ modelUserEnderecos.cep }">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CEP</label>
                                                                </div>
                                                            </div>
                                                           	
                                                        	<div class="form-group row form-default form-static-label">
                                                                
                                                                <div class="col-sm-7">
	                                                                <input type="text" name="logradouro" id="logradouro" class="form-control" required="required" readonly="readonly"  autocomplete="off" value="${ modelUserEnderecos.logradouro }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Logradouro</label>
                                                            	</div>
                                                                <div class="col-sm-1">
	                                                                <input type="text" name="numero" id="numero" class="form-control" required="required" autocomplete="off" value="${ modelUserEnderecos.numero }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Número</label>
                                                            	</div>
                                                            	<div class="col">
                                                            		<input type="text" name="complemento" id="complemento" class="form-control" autocomplete="off" value="${ modelUserEnderecos.complemento }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Complemento</label>
                                                            	</div>
                                                            </div>
                                                            
                                                            <div class="form-group row form-default form-static-label">
                                                            	<div class="col-sm-7">
	                                                            	<input type="text" name="bairro" id="bairro" class="form-control" required="required" readonly="readonly"  autocomplete="off" value="${ modelUserEnderecos.bairro }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bairro</label>
                                                            	</div>
                                                            	<div class="col">
	                                                            	<input type="text" name="cidade" id="cidade" class="form-control" required="required" readonly="readonly" autocomplete="off" value="${ modelUserEnderecos.cidade }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cidade</label>
                                                            	</div>
                                                            	<div class="col-sm-1">
                                                            		<input type="text" name="uf" id="uf" class="form-control" required="required" readonly="readonly"  autocomplete="off" value="${ modelUserEnderecos.uf }">
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Estado</label>
                                                            	</div>
                                                            </div>
                                                            
                                    						<div class="form-group form-default form-static-label">
                                                                <input type="checkbox" id="isEnderecoPrincipal" name="isEnderecoPrincipal" value="isPrincipal" <%  
                                                               	ModelEndereco modelEndereco = (ModelEndereco) request.getAttribute("modelUserEnderecos");

                                                                  if (modelEndereco != null && modelEndereco.isEnderecoPrincipal() == true) {
                                                                  	out.print(" ");
                                                                  	out.print(" checked=\"checked\"");
                                                                  	out.print(" ");
                                                                  } %>>
                                                                <i class="helper"></i>Definir este endereço como principal
                                                                <span class="form-bar"></span>
                                                           	</div>
                                    						
												            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm();">Novo</button>
												            <button type="submit" class="btn btn-success waves-effect waves-light">Salvar</button>
												            <!-- <button type="button" class="btn btn-danger waves-effect waves-light" onclick="excluirUsuarioComAjax()">Excluir</button> -->
												            
												            <div class="form-group form-default">
												            	<br/>
												            	<span id="mensagem">${msg}</span>
												            </div>
												            
                                                        </form>
                                    					
                                    				</div>
                                   				
                                    			</div>
                                    			
                                    				
                                   				<div style="height: 300px; overflow:scroll;">
													<table class="table table-hover" id="tabelaresultadosview">
													  <thead>
													    <tr>
													      <th scope="col">ID</th>
													      <th scope="col">Endereço</th>
													      <th scope="col">Editar</th>
													      <th scope="col">Excluir</th>
													    </tr>
													  </thead>
													  <tbody>
														<c:forEach items="${modelEnderecos}" var="me">
															<tr>
																<td><c:out value="${me.id}"></c:out></td>
																<td><c:out value="${me.logradouro}, ${me.numero} ${me.complemento}, ${me.bairro}-${me.uf} - ${me.cep}"></c:out></td>
																<td><a class="btn btn-info waves-effect waves-light" href="<%= request.getContextPath() %>/ServletUsuarioEnderecoController?acao=buscarEditar&id=${me.id}&userid=${ modelLogin.id }">Editar</a></td>
																<td><a class="btn btn-danger waves-effect waves-light" onclick="return confirm('Deseja realmente excluir os dados?')" href="<%= request.getContextPath() %>/ServletUsuarioEnderecoController?acao=excluir&id=${me.id}&userid=${ modelLogin.id }">Excluir</a></td>
																<%-- <td><button type="button" class="btn btn-danger waves-effect waves-light" onclick="excluirEnderecoComAjax(${me.id})">Excluir</button></td> --%>
															</tr>
														</c:forEach>
													  </tbody>
													</table>
												</div>
													 
                                    		</div>
                                        </div>
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

<script type="text/javascript">

	/* função para aceitar digitação apenas de números, campos capturados pelo id */
	$("#cep").keypress(function (event) {
		return /\d/.test(String.fromCharCode(event.keyCode));
	});
	
	$("#numero").keypress(function (event) {
		return /\d/.test(String.fromCharCode(event.keyCode));
	});
	
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
	
	
	

	function limparForm() {
		var elementos = document.getElementById("formEndereco").elements; /* Retorna os elementos html dentro do form */
		
		for (p = 0; p < elementos.length; p++) {
			if (p > 2) {
				elementos[p].value = '';
			}
		}
		document.getElementById('mensagem').textContent = '';
	}

	function excluirEnderecoComAjax(idEndereco) {
		
		if (confirm('Deseja realmente excluir os dados?')) {
	
			var urlAction = document.getElementById('formEndereco').action;
			var idUser = document.getElementById('id').value;
			
			
			if (idEndereco == "" || idEndereco == null) {
				//alert("Não há dados selecionados. Não foi possível excluir !");
				document.getElementById('mensagem').textContent = "Não há dados selecionados. Não foi possível excluir !";
			    return false;
			} else {
			
				//alert('entrei')
				$.ajax({
					method: "get",
					url: urlAction,
					data: "id=" + idEndereco + "&acao=excluirComAjax&userid=" + idUser,
					success: function (response) {
						//alert('to aqui');
						limparForm();
						document.getElementById('mensagem').textContent = response;
					}
					
				}).fail(function(xhr, status, errorThrown){
					alert("Erro ao excluir endereço por ID:" + xhr.responseText);
				});
			}
		}	
	}
	
</script>

    
</body>

</html>
