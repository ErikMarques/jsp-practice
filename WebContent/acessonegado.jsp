<jsp:useBean id="calcula" class="bean.BeanCursoJsp" type="bean.BeanCursoJsp" scope="page"></jsp:useBean>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Negado JSP</title>
<!-- Declaracao do link do css -->
<link rel="stylesheet" href="resources/css/estilo.css">
</head>
<body>

	<div class="login-page">
		<div class="form">
			Acesso negado ao sistema para este usu�rio!
			<p>
				<!-- Link para p�gina de cadastro de usu�rio -->
				<a href="index.jsp">Voltar para p�gina de login</a>
		</div>
	</div>
</body>
</html>