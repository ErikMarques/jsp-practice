<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Index JSP</title>

<!-- Declaracao do link do css -->
<link rel="stylesheet" href="resources/css/estilo.css">

</head>
<body>
<h4><center>*** Role a página para mais informações ***</center></h4>

	<div class="login-page">
		<div class="form">
			<form action="LoginServlet" method="post" class="login-form">
				Login: <input type="text" id="login" name="login"> <br /> <br /> Senha: <input type="text" id="senha" name="senha"> <br />
				<p />
				<button type="submit" value="Logar">Logar</button>
			</form>
		</div>
	</div>

	<p />
	<div class="login-page">
		<div class="form">
			Teste de validação de login
			<p>Opcional:
			<p />
			Abra o CONSOLE e monitore as mensagens simulando um "ACESSO COM SUCESSO" e um "ACESSO NEGADO".
			<h5>
				Observação: Usuário cadastrado para teste:
				<p />
				Usuário: admin | senha: admin
			</h5>
		</div>
	</div>
	<p />

</body>
</html>