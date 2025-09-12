<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
	<meta charset="UTF-8">
	<title>Cadastro de Pessoas</title>
	<style type="text/css">
		textarea {
			display: block;
			margin: 20px 10px;
		}
	</style>
</head>
<body>
	<form action="PersonServlet" method="post">
		<label for="people">Dados das pessoas:</label>
		<textarea rows="10" cols="80" name="people" id="people">
		</textarea>
		<input type="submit" value="Enviar">
	</form>
</body>
</html>