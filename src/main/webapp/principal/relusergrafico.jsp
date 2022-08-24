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
														<h5>Média Salarial X Perfil</h5>
													</div>
													<div class="card-block">
                                                        <%-- <form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post" id="formUser"> --%>
                                                        <form class="form-material" action="<%= request.getContextPath() %>/ServletUsuarioController" method="get" id="formUser">
                                                        
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
																	<button type="button" onclick="gerarGrafico();" class="btn btn-primary waves-effect waves-light">Gerar Gráfico</button>
																	
																</div>
															</div>
															
														</form>
														
															<div style="height: 50%;width: 50%;">
																<canvas id="myChart" ></canvas>
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

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script type="text/javascript">

var myChart = new Chart(document.getElementById('myChart'));

function gerarGrafico() {
		
	var urlAction = document.getElementById('formUser').action;
	var dataInicial = document.getElementById('dataInicial').value;
	var dataFinal = document.getElementById('dataFinal').value;
	
	$.ajax({
		method: "get",
		url: urlAction,
		data: "dataInicial=" + dataInicial + "&dataFinal=" + dataFinal + "&acao=graficoSalario",
		success: function (response) {
			
			var json = JSON.parse(response);
			
			myChart.destroy();
			//alert(json.perfis);
			//alert(json.salarios);
			
			myChart = new Chart(
				    document.getElementById('myChart'),
				    {
						  type: 'line',
						  data: {
							  labels: json.perfis,
							  datasets: [{
							    label: 'Gráfico média salarial X tipo',
							    backgroundColor: 'rgb(255, 99, 132)',
							    borderColor: 'rgb(255, 99, 132)',
							    data: json.salarios,
							  }]
							},
						  options: {}
						}
			    );
			
		}
		
	}).fail(function(xhr, status, errorThrown){
		alert("Erro ao buscar dados para o gráfico " + xhr.responseText);
	});

	
	
		  
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
