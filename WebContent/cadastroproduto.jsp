<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- Decalaração da tag do jstl para mostrar os dados recuperados do banco na tabela -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro Produto JSP</title>
</head>
<body>

	<table>
		<h1>Cadastro de Produtos</h1>
		<h4 style="color: blue;">${msg}</h4>
	</table>

	<form action="salvarProduto" method="post" id="formProduto">
		<table border="5">

			<tr>
				<td><label>Código:</label></td>
				<td><input type="text" id="id" name="id" value="${product.id}"></td>
				
			</tr>
			<tr>
				<td><label>Nome do Produto:</label></td>
				<td><input type="text" id="nome" name="nome" value="${product.nome}"></td>
			</tr>
			<tr>
				<td><label>Quantidade:</label></td>
				<td><input type="text" id="nome" name="quantidade" value="${product.quantidade}"></td>
			</tr>
			<tr>
				<td><label>Valor:</label></td>
				<td><input type="text" id="nome" name="valor" value="${product.valor}"></td>
			</tr>
			<tr>
			<td><input type="submit" value="Salvar">
			<input type="submit" value="Cancelar" onClick="document.getElementById('formProduto').action = 'salvarProduto?acao=reset'"></td>
			</tr>
	</form>

	<table>
		<caption>Produtos Cadastrados</caption>
		<tr>
			<th>Código:</th>
			<th>Nome do Produto:</th>
			<th>Quantidade:</th>
			<th>Valor:</th>
		</tr>

		<c:forEach items="${produtos}" var="product">

			<tr>
				<!-- Criando uma linha para cada registro -->
				<td>
					<!-- Criando uma célula na linha para o registro --> <c:out value="${product.id}"></c:out> <!-- Tag c:out imprime na tela o valor passado na expression language -->
				</td>
				<td>
					<!-- Criando uma célula na linha para o registro --> <c:out value="${product.nome}"></c:out> <!-- Tag c:out imprime na tela o valor passado na expression language -->
				</td>
				<td>
					<!--Criando uma célula na linha para o registro --> <c:out value="${product.quantidade }"></c:out> <!--  Tag c:out imprime na tela o valor passado na expression language -->
				<td>
					<!-- Criando uma célula na linha para o registro --> R$ <c:out value="${product.valor}"></c:out> <!-- Tag c:out imprime na tela o valor passado na expression language -->
				</td>

				<td><a href="salvarProduto?acao=editar&product=${product.id }"><img src="resources/img/btn_edit.png" title="Editar dados" alt="Editar" width="20px" height="20px"></a></td>
				<td><a href="salvarProduto?acao=delete&product=${product.id }"><img src="resources/img/btn_excluir.png" title="Excluir dados" alt="Excluir" width="20px" height="20px"></a></td>
			</tr>


		</c:forEach>


	</table>

</body>
</html>