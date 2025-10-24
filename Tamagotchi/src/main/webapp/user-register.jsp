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
<title>Cadastro - Tamagotchi</title>
</head>
<body>
<div class="bg-site">
    <div class="form-container">
        <c:if test="${result == 'notRegistered'}">
            <div class="alert alert-danger">E-mail já cadastrado.</div>
        </c:if>

        <form action="userRegisterServlet" method="post">
            <h1>Cadastre-se</h1>
            <input type="text" name="name" placeholder="Nome completo" class="form-control" required>
            <input type="email" name="email" placeholder="E-mail" class="form-control" required>
            <input type="password" name="password" placeholder="Senha" class="form-control" required>
            <input type="password" name="confirmPassword" placeholder="Confirmar Senha" class="form-control" required>
            <select name="type" class="form-select" required>
                <option value="">Tipo</option>
                <option value="ONG">ONG</option>
                <option value="ADOTANTE">Adotante</option>
            </select>
            <input type="date" name="dateOfBirth" class="form-control" required>
            <select name="gender" class="form-select" required>
                <option value="">Gênero</option>
                <option value="MASCULINO">Masculino</option>
                <option value="FEMININO">Feminino</option>
                <option value="OUTRO">Outro</option>
            </select>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Salvar</button>
                <a href="login.jsp" class="btn btn-secondary">Voltar</a>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
