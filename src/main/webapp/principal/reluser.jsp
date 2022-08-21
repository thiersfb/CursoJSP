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
														<h5>Relatório de usuário</h5>
													</div>
													<div class="card-block">
                                                        <%-- <form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post" id="formUser"> --%>
                                                        <form class="form-material" enctype="multipart/form-data"  action="<%= request.getContextPath() %>/ServletUsuarioController" method="get" id="formUser">
                                                        
                                                        	<!-- <input type="hidden" name="acao" id="acaoRelatorioImprimirTipo" value="imprimirRelatorioUser"> -->
                                                        	<input type="hidden" name="acao" id="acaoRelatorioImprimirTipo">

															<div class="form-row align-items-center">
																<div class="col-auto">
																	<label class="sr-only" for="inlineFormInput">Data Inicial</label>
																	<input type="text" class="form-control" placeholder="Data Inicial" id="dataInicial" name="dataInicial" value="${dataInicial}">
																</div>
																<div class="col-auto">
																	<label class="sr-only" for="inlineFormInput">Data Final</label>
																	<input type="text" class="form-control" placeholder="Data Final" id="dataFinal" name="dataFinal" value="${dataFinal}">
																</div>
																<div class="col-auto">
																	<!-- <button type="submit" class="btn btn-primary mb-2">Imprimir Relatório</button> -->
																	<button type="button" onclick="imprimirHtml();" class="btn btn-primary waves-effect waves-light">Listar</button>
																	<button type="button" onclick="imprimirPDF();" class="btn btn-primary waves-effect waves-light">Gerar Relatório PDF</button>
																	<button type="button" onclick="imprimirExcel();" class="btn btn-primary waves-effect waves-light">Gerar Relatório Excel</button>
																</div>
															</div>
															
														</form>
														
														<div style="height: 300px; overflow:scroll;">
															<table class="table table-hover" id="tabelaresultadosview">
															  <thead>
															    <tr>
															      <th scope="col">ID</th>
															      <th scope="col">Login</th>
															      <th scope="col">Nome</th>
															      <th scope="col">CPF</th>
															      <th scope="col">RG</th>
															      <th scope="col">E-mail</th>
															      <th scope="col">Perfil</th>
															      <th scope="col">Renda Mensal</th>
															      <th scope="col">Status</th>
															      <th scope="col">Genero</th>
															    </tr>
															  </thead>
															  <tbody>
																<c:forEach items="${relatorioUser}" var="ml">
																	<tr>
																		<td><c:out value="${ml.id}"></c:out></td>
																		<td><c:out value="${ml.login}"></c:out></td>
																		<td><c:out value="${ml.nome} ${ml.sobrenome}"></c:out></td>
																		<td><c:out value="${ml.cpf}"></c:out></td>
																		<td><c:out value="${ml.rg}"></c:out></td>
																		<td><c:out value="${ml.email}"></c:out></td>
																		<td><c:out value="${ml.perfil}"></c:out></td>
																		<td><c:out value="R$ ${ml.rendamensal}"></c:out></td>
																		<c:if test="${ ml.statusId == '1'}">
																			<td>Ativo</td>
																		</c:if>
	                                                            		<c:if test="${ ml.statusId == '0'}">
	                                                            			<td>Inativo</td>
	                                                            		</c:if>
																		<%-- <td><c:out value="${ml.genero}"></c:out></td> --%>
																		<c:if test="${ ml.genero == 'M'}">
																			<td>Masculino</td>
																		</c:if>
	                                                            		<c:if test="${ ml.genero == 'F'}">
	                                                            			<td>Feminino</td>
	                                                            		</c:if>
																	</tr>
																	<%-- 
																	<c:if test="${ not empty ml.telefones }">
																	<tr>
																		<th/>
																		<th scope="col">ID Telefone</th>
																		<th scope="col">Telefones</th>
																	</tr>
																	</c:if>
																	 --%>
																	 
																	  
																	<c:if test="${ not empty ml.telefones }">
																	<tr>
																		<th/>
																		<th scope="col">ID Telefone</th>
																		<th scope="col">Telefones</th>
																		<th/>
																		<th/>
																		<th/>
																		<th/>
																		<th/>
																		<th/>
																		<th/>
																	</tr>
																	</c:if>
																	 
																	<c:forEach items="${ml.telefones}" var="fone">
																	<tr>
																		<td/>
																		<td><c:out value="${fone.id}"></c:out></td>
																		<td><c:out value="${fone.numero}"></c:out></td>
																		<td/>
																		<td/>
																		<td/>
																		<td/>
																		<td/>
																		<td/>
																		<td/>
																	</tr>
																	</c:forEach>
																</c:forEach>
															  </tbody>
															</table>
														</div>
														
                                                   	</div>
												</div>
											</div>
										</div>
									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<jsp:include page="javascriptfile.jsp"></jsp:include>


<script type="text/javascript">

function imprimirHtml() {
	document.getElementById('acaoRelatorioImprimirTipo').value = 'imprimirRelatorioUser';
	$("#formUser").submit();
}

function imprimirPDF() {
	document.getElementById('acaoRelatorioImprimirTipo').value = 'imprimirRelatorioPDF';
	$("#formUser").submit();
	return false;
}

function imprimirExcel() {
	document.getElementById('acaoRelatorioImprimirTipo').value = 'imprimirRelatorioExcel';
	$("#formUser").submit();
	return false;
}

$( function() {
	  
	  $("#dataInicial").datepicker({
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

$( function() {
	  
	  $("#dataFinal").datepicker({
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


</script>

</body>

</html>
