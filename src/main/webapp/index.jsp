<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>


	<!-- Required meta tags -->
    <!-- <meta charset="utf-8"> -->
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

	<title>Curso JSP</title>
	
<style type="text/css">

form{
	position: absolute;
	top: 40%;
	left: 33%;
	right: 33%;
}

h5{
	position: absolute;
	top: 30%;
	left: 33%;
}

.msg{
	position: absolute;
	top: 10%;
	left: 33%;
	font-size: 15px;
	color: #664d03;
	background-color: #fff3cd;
	border-color: #ffecb5;
	
}

</style>	
	
</head>
<body>

	<h5>Bem-vindo ao curso de JSP</h5>


	<!-- <form action="receber-nome.jsp">-->
	<form action="<%= request.getContextPath() %>/ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
	
	<input type="hidden" value="<%= request.getParameter("url") %>" name="url">
	
	
	<div class="mb-3">
		<label for="lblNome" class="form-label">Login</label> 
		<input type="text" class="form-control" name="txtLogin" placeholder="Login" required>
		<div class="valid-feedback">OK</div>
	    <div class="invalid-feedback">Preencha este campo</div>
	</div>
	<div class="mb-3">
		<label for="lblIdade" class="form-label">Senha</label>
		<input type="password" class="form-control" name="txtSenha" placeholder="Senha" required>
		<div class="valid-feedback">OK</div>
	    <div class="invalid-feedback">Preencha este campo</div>
	</div>
	
		<input type="submit" value="Entrar" class="btn btn-primary">
	
	
		<!--
		<table>
			<tr>
				<td>
					<label for="lblNome">Login</label> 
				</td>
				<td>
					<input type="text" name="txtLogin" placeholder="Login">
				</td>
			</tr>
			<tr>
				<td>
					<label for="lblIdade">Senha</label>
				</td>
				<td>
					 <input type="password" name="txtSenha" placeholder="Senha">
				</td>
			</tr>
			<tr>
				<td/>
				<td>
					<input type="submit" value="Entrar">
				</td>
			</tr>
		
		</table>
		-->

	</form>


	<!-- Expression Language (EL) ; "msg" é o atributo recebido da Servlet -->
	<h5 class="msg">${msg}</h5>
	
	
		

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
	

<script type="text/javascript">

//Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()

</script>

</body>
</html>