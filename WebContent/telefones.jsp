<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- Decalaração da tag do jstl para mostrar os dados recuperados do banco na tabela -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="resources/css/cadastro.css">
<meta charset="ISO-8859-1">
<title>Cadastro telefones JSP</title>
</head>

<body>
	<a href="acessoliberado.jsp"> Inicío</a>
	<a href="index.jsp">Sair</a>

	<center>
		<h1>Cadastro de Telefones</h1>
		<h4 style="color: red">${msg}</h4>
	</center>

	<!-- Criando um formulario com os campos para cadastrar um novo usuário no banco de dados -->
	<!-- Abertura da tag de formulário -->
	<!-- A ação é salvarUsuario -->

	<!-- O post envia os dados -->

	<form action="salvarTelefones" method="post" id="formUser" onsubmit="return validarCampos() ? true : false;">
		<!-- IF ternário em java e java script -->
		<ul class="form-style-1">
			<li>
				<table border="0"margin:auto;>

					<tr>
						<td><label>User:</label></td>
						<td><input type="text" readonly="readonly" id="id" name="id" value="${userEscolhido.id}"></td>
						<td><input type="text" readonly="readonly" id="nome" name="nome" value="${userEscolhido.nome}"></td>
					</tr>
					<tr>
						<td>Número:</td>
						<td><input type="text" id="numero" name="numero"></td>
						<td><select id="tipo" name="tipo">
								<option>Casa</option>
								<option>Contato</option>
								<option>Celular</option>
						</select></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar">
						<td></td>
					</tr>

				</table>
			</li>
		</ul>
	</form>

	<!-- Criar tabela -> abrir tag c: do jstl -> abrir um for dentro da tag c: -> abrir expression language  ->  e uma variável var="fone -->

	<!-- Neste caso os items passados são "telefones" da Servlet TelefonesServlets.java do request.setAttribute("telefones", daoUsuario.listar()); -->

	<div class="container">
		<table class="responsive-table">
			<caption>Usuários cadastrados</caption>
			<tr>
				<th>Id</th>
				<th>Número</th>
				<th>Tipo</th>
				<th>Excluir</th>
			</tr>

			<c:forEach items="${telefones}" var="fone">
				<tr>
					<!-- Tag c:out imprime na tela o valor passado na expression language -->
					<td><c:out value="${fone.id}"></c:out></td>
					<td><c:out value="${fone.numero}"></c:out></td>
					<td><c:out value="${fone.tipo}"></c:out>
					<td><a href="salvarUsuario?acao=delete&user=${fone.id }"><img src="resources/img/btn_excluir.png" title="Excluir" alt="excluir" width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {

			if (document.getElementById("numero").value == '') {
				alert('Informe o número!');
				return false;
			} else if (document.getElementById("tipo").value == '') {
				alert('Informe o tipo');
				return false;
			}
			return true;
		}
	</script>
</body>
</html>