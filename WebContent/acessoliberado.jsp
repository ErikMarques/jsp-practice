<jsp:useBean id="calcula" class="bean.BeanCursoJsp" type="bean.BeanCursoJsp" scope="page"></jsp:useBean>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liberado JSP</title>
<!-- Declaracao do link do css -->
<link rel="stylesheet" href="resources/css/estilo.css">
</head>
<body>

	<div class="login-page">
		<div class="form">
			Seja bem vindo ao sistema em JSP!!
			<p>
				<!-- Link para página de cadastro de usuário -->
				<a href="salvarUsuario?acao=listartodos"><img alt="Usuário logado" title="Imagem do usuário" src="resources/img/user_icon.png" width="200px" height="200px"></a>
				<!-- Chamada atraves de link vai ser resgatado pelo doGet na Servlet. -->
			</p>
			Clique na imagem para acessar a tela de cadastro.
		</div>
	</div>

</body>
</html>