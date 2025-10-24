<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<title>Login - Tamagotchi</title>
</head>
<body>
<div class="bg-site">
    <div class="form-container">
        <c:choose>
            <c:when test="${result == 'registered'}">
                <div class="alert alert-success">Usuário cadastrado com sucesso.</div>
            </c:when>
            <c:when test="${result == 'loginError'}">
                <div class="alert alert-danger">E-mail e/ou senha inválidos.</div>
            </c:when>
        </c:choose>

        <form action="loginServlet" method="post">
            <h1>Login</h1>
            <input type="email" name="email" placeholder="E-mail" class="form-control" required>
            <input type="password" name="password" placeholder="Senha" class="form-control" required>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Login</button>
                <a href="user-register.jsp" class="btn btn-secondary">Cadastrar</a>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
