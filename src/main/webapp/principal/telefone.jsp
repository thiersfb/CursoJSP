<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
                                                        <h5>Cadastro de telefones</h5>
                                                        
                                                    </div>
                                    				<div class="card-block">
                                    					
                                    					
                                    					
                                    					<form class="form-material"  action="<%= request.getContextPath() %>/ServletUsuarioTelefoneController" method="post" id="formTelefone">
                                    					
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
                                                            
                                                            <div class="form-group row form-default form-static-label">
                                                                <div class="col-sm-2">
	                                                                <input type="text" name="tel_id" id="tel_id" class="form-control" readonly="readonly" value="${ modelUserTelefones.id }" >
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID Telefone:</label>
                                                            	</div>
                                                                <div class="col">
	                                                                <input type="text" name="numero" id="numero" class="form-control" required="required" value="${ modelUserTelefones.numero }" >
	                                                                <span class="form-bar"></span>
	                                                                <label class="float-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Número</label>
                                                            	</div>
                                                            </div>
                                                            
                                                            <!--
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="tel_id" id="tel_id" class="form-control" readonly="readonly" value="${ modelUserTelefones.id }" >
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID Telefone</label>
                                                            </div> 
                                    					                                                            
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="numero" id="numero" class="form-control" required="required" value="${ modelUserTelefones.numero }" >
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Número</label>
                                                            </div> 
                                                            -->
                                    						
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
													      <th scope="col">Número</th>
													      <th scope="col">Editar</th>
													      <th scope="col">Excluir</th>
													    </tr>
													  </thead>
													  <tbody>
														<c:forEach items="${modelTelefones}" var="mt">
															<tr>
																<td><c:out value="${mt.id}"></c:out></td>
																<td><c:out value="${mt.numero}"></c:out></td>
																<td><a class="btn btn-info waves-effect waves-light" href="<%= request.getContextPath() %>/ServletUsuarioTelefoneController?acao=buscarEditar&id=${mt.id}&userid=${ modelLogin.id }">Editar</a></td>
																<td><a class="btn btn-danger waves-effect waves-light" onclick="return confirm('Deseja realmente excluir os dados?')" href="<%= request.getContextPath() %>/ServletUsuarioTelefoneController?acao=excluir&id=${mt.id}&userid=${ modelLogin.id }">Excluir</a></td>
																<%-- <td><button type="button" class="btn btn-danger waves-effect waves-light" onclick="excluirTelefoneComAjax(${mt.id})">Excluir</button></td> --%>
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
	$("#numero").keypress(function (event) {
		return /\d/.test(String.fromCharCode(event.keyCode));
	});
	
	
	function limparForm() {
		var elementos = document.getElementById("formTelefone").elements; /* Retorna os elementos html dentro do form */
		
		for (p = 0; p < elementos.length; p++) {
			if (p > 2) {
				elementos[p].value = '';
			}
		}
		document.getElementById('mensagem').textContent = '';
	}

	
	
	function excluirTelefoneComAjax(idTelefone) {
		
		if (confirm('Deseja realmente excluir os dados?')) {
	
			var urlAction = document.getElementById('formTelefone').action;
			var idUser = document.getElementById('id').value;
			
			
			if (idTelefone == "" || idTelefone == null) {
				document.getElementById('mensagem').textContent = "Não há dados selecionados. Não foi possível excluir !";
			    return false;
			} else {
				$.ajax({
					method: "get",
					url: urlAction,
					data: "id=" + idTelefone + "&acao=excluirComAjax&userid=" + idUser,
					success: function (response) {
						limparForm();
						<%-- location.replace(<%= request.getContextPath() %>"/ServletUsuarioTelefoneController?iduser="+idUser) --%>		
						document.getElementById('mensagem').textContent = response;
					}
					
				}).fail(function(xhr, status, errorThrown){
					alert("Erro ao excluir telefone por ID:" + xhr.responseText);
				});
			}
		}	
	}
	

</script>
    
</body>

</html>
