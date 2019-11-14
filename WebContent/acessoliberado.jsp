

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

	<p>
	<center>
		<h2>Seja bem vindo ao sistema em JSP!!</h2>


		Selecione a opção desejada: <br> <br> <br>

		<table border="0" bgcolor="">
			<tr>
				<td>
					<!-- Link para página de cadastro de usuário --> <a href="salvarUsuario?acao=listartodos"><img alt="Usuário logado" title="Cadastrar Usuário" src="resources/img/user_icon.png" width="200px"
						height="200px"></a> <!-- Chamada atraves de link vai ser resgatado pelo doGet na Servlet. -->
					<center>Cadastrar Usuário</center>
				</td>
				<td><img alt="Usuário logado" title="Imagem cadastro de produto" src="resources/img/linha_vertical_icon.png" width="200px" height="200px"></td>
				<td>
					<center>
						<a href="salvarProduto?acao=listartodos"><img alt="Usuário logado" title="Cadastrar Produto" src="resources/img/produto_icon.png" width="200px" height="200px"> </a>
						<center>Cadastrar Produto</center>
				</td>
			</tr>
		</table>

		</div>

	</center>
</body>
</html>