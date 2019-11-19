<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- Decalaração da tag do jstl para mostrar os dados recuperados do banco na tabela -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="resources/css/cadastro.css">

<meta charset="ISO-8859-1">

<title>Cadastro usuario JSP</title>
</head>
<body>

	<!-- Criando um formulario com os campos para cadastrar um novo usuário no banco de dados -->

	<!-- Abertura da tag de formulário -->
	<!-- A ação é salvarUsuario -->
	<!-- O post envia os dados -->

	<form action="salvarUsuario" method="post" id="formUser" onsubmit="return validarCampos() ? true : false;"><!-- IF ternário em java e java script -->
		<ul class="form-style-1">

			<h1>Cadastro de usuário</h1>

			<h4 style="color: red">${msg}</h4>

			<table border="0">
				<!-- Abertura da tag de tabela para organização -->
				<!-- Cria uma linha dentro da tabela -->
				<tr>
					<!-- Cria uma célula dentro da linha com o texto Login-->
					<td><label>Código <span class="required">*</span></label></td>
					<!-- Cria um campo para receber o valor que será o login -->
					<td><input type="text" readonly="readonly" id="id" name="id" value="${user.id}" class="field-long"></td>
					<td></td>
				</tr>

				<tr>
					<!-- Cria uma célula dentro da linha com o texto nome-->
					<td><label>Nome <span class="required">*</span></label></td>
					<!-- Cria um campo para receber o valor que será o nome -->
					<td><input type="text" id="nome" name="nome" value="${user.nome}" class="field-long"></td>
					<td></td>
				</tr>

				<tr>
					<!-- Cria uma célula dentro da linha com o texto Login-->
					<td><label>Login <span class="required">*</span></label></td>
					<!-- Cria um campo para receber o valor que será o login -->
					<td><input type="text" id="login" name="login" value="${user.login}" class="field-long"></td>
					<td><h5 style="color: red">${msg_login}</h5></td>
				</tr>

				<tr>
					<td><label>Senha <span class="required">*</span></label></td>
					<td><input type="password" id="senha" name="senha" value="${user.senha}" class="field-long"></td>
					<td></td>
				</tr>

				<tr>
					<td><label>Telefone <span class="required">*</span></label></td>
					<td><input type="text" id="telefone" name="telefone" value="${user.telefone}" class="field-long"></td>
					<td></td>
				</tr>

				<tr>
					<td></td>
					<td>
						<p /> <!-- Botão que irá salvar os dados do novo usuário no banco de dado --> <input type="submit" value="Salvar"> <!-- Botão que irá salvar os dados do novo usuário no banco de dado -->
						<input type="submit" value="Cancelar" onClick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'">
					</td>
					<td></td>
				</tr>

			</table>
		</ul>
	</form>

	<!-- Criar tabela -> abrir tag c: do jstl -> abrir um for dentro da tag c: -> abrir expression language  ->  e uma variável var="user -->

	<!-- Neste caso os items passados são "usuarios" da classe Usuario.java do request.setAttribute("usuarios", daoUsuario.listar()); -->

	<div class="container">
		<table class="responsive-table">
			<caption>Usuários cadastrados</caption>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Login</th>
				<th>Senha</th>
				<th>Editar</th>
				<th>Excluir</th>
			</tr>

			<c:forEach items="${usuarios}" var="user">

				<tr>
					<!-- Criando uma linha para cada registro -->
					<td>
						<!-- Criando uma célula na linha para o registro --> <c:out value="${user.id}"></c:out> <!-- Tag c:out imprime na tela o valor passado na expression language -->
					</td>

					<td>
						<!-- Criando uma célula na linha para o registro --> <c:out value="${user.nome}"></c:out> <!-- Tag c:out imprime na tela o valor passado na expression language -->
					</td>
					<td>
						<!--Criando uma célula na linha para o registro --> <c:out value="${user.telefone }"></c:out> <!--  Tag c:out imprime na tela o valor passado na expression language -->
					<td>
						<!-- Criando uma célula na linha para o registro --> <c:out value="${user.login}"></c:out> <!-- Tag c:out imprime na tela o valor passado na expression language -->
					</td>

					<td>
						<!-- Criando uma célula na linha para o registro --> <c:out value="${user.senha}"></c:out>
					</td>

					<td><a href="salvarUsuario?acao=editar&user=${user.id }"><img src="resources/img/btn_edit.png" title="Editar dados" alt="Editar" width="20px" height="20px"></a></td>
					<td><a href="salvarUsuario?acao=delete&user=${user.id }"><img src="resources/img/btn_excluir.png" title="Excluir dados" alt="Excluir" width="20px" height="20px"></a></td>
				</tr>

			</c:forEach>

		</table>
	</div>


	<script type="text/javascript">
		function validarCampos() {

			if (document.getElementById("nome").value == '') {
				alert('Informe o Nome!');
				return false;
			} else if (document.getElementById("login").value == '') {
				alert('Informe o Login!');
				return false;
			} else if (document.getElementById("senha").value == '') {
				alert('Informe a Senha!');
				return false;
			} else if (document.getElementById("telefone").value == '') {
				alert('Informe o Telefone!');
				return false;
			}
		return true;
		}
	</script>
	
	
	
</body>
</html>