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
<title>Registro de Animal</title>
</head>
<body>
<div class="bg-site">
    <div class="form-container">
        <c:if test="${result == 'registered'}">
            <div class="alert alert-success">Animal salvo com sucesso.</div>
        </c:if>
        <c:if test="${result == 'notRegistered'}">
            <div class="alert alert-danger">Animal não salvo. Verifique os dados.</div>
        </c:if>

        <form action="animalRegisterServlet" method="post">
            <h1>${animal.id != null ? 'Editar Animal' : 'Novo Animal'}</h1>
            <input type="hidden" name="id" value="${animal.id != null ? animal.id : 0}">
            <input type="text" name="name" placeholder="Nome" class="form-control" value="${animal.name}" required>
            <select name="species" class="form-select" required>
                <option value="">Espécie</option>
                <option value="Cachorro" ${animal.species == 'Cachorro' ? 'selected' : ''}>Cachorro</option>
                <option value="Gato" ${animal.species == 'Gato' ? 'selected' : ''}>Gato</option>
                <option value="Pássaro" ${animal.species == 'Pássaro' ? 'selected' : ''}>Pássaro</option>
                <option value="Outro" ${animal.species == 'Outro' ? 'selected' : ''}>Outro</option>
            </select>
            <input type="date" name="birth_date" class="form-control" value="${animal.dateOfBirth}" required>
            <select name="status" class="form-select" required>
                <option value="">Status</option>
                <option value="DISPONIVEL" ${animal.status == 'DISPONIVEL' ? 'selected' : ''}>Disponível</option>
                <option value="ADOTADO" ${animal.status == 'ADOTADO' ? 'selected' : ''}>Adotado</option>
            </select>
            <textarea name="description" class="form-control" rows="3">${animal.description}</textarea>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">${animal.id != null ? 'Atualizar' : 'Salvar'}</button>
                <a href="home.jsp" class="btn btn-secondary">Voltar</a>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
