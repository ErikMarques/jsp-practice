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

<center>
	<h1>Cadastro de Produtos</h1>
	<h4 style="color: blue;">${msg}</h4>

	<form action="salvarProduto" method="post" id="formProduto">
		<table border="5">

			<tr>
				<td><label>Código:</label></td>
				<td><input type="text" id="nome" name="id" value="${id}"></td>
			</tr>
			<tr>
				<td><label>Nome do Produto:</label></td>
				<td><input type="text" id="nome" name="nome" value="${nome}"></td>
			</tr>
			<tr>
				<td><label>Quantidade:</label></td>
				<td><input type="text" id="nome" name="quantidade" value="${quantidade}"></td>
			</tr>
			<tr>
			<td><label>Valor:</label></td>
				<td><input type="text" id="nome" name="valor" value="${valor}"></td>
			</tr>
			<td><input type="submit" value="Salvar" onClick="document.getElementById('formProduto').action = 'salvarProduto?acao=salvar'"></td>

		</table>

	</form>

</center>
</body>
</html>