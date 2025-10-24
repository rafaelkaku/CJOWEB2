<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List, br.edu.ifspcjo.ads.cjoweb2.model.Animal"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<title>Meus Animais</title>
</head>
<body>
<!-- NAVBAR FIXA -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  <div class="container-fluid">
    <a class="navbar-brand" href="homeServlet">Tamagotchi</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" 
      aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="homeServlet">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="animal-register.jsp">Registrar Animal</a>
        </li>
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item">
          <span class="navbar-text text-white me-3">
            ${sessionScope.user.name}
          </span>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="logoutServlet">Sair</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<!-- CONTEÚDO PRINCIPAL -->
<div class="bg-site" style="padding-top:80px;"> <!-- padding-top evita sobreposição da navbar -->
    <div class="form-container" style="max-width: 1000px;">
        <h1 class="text-center mb-4">Meus Animais</h1>

        <!-- Campo de pesquisa -->
        <form class="d-flex mb-3" action="homeServlet" method="get">
            <input class="form-control me-2" type="search" name="search" placeholder="Pesquisar animal">
            <button class="btn btn-primary" type="submit">Pesquisar</button>
        </form>

        <c:choose>
            <c:when test="${fn:length(userAnimals) > 0}">
                <div class="table-responsive">
                    <table class="table table-striped table-hover text-center">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Nome</th>
                                <th>Espécie</th>
                                <th>Data de Nascimento</th>
                                <th>Status</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="animal" items="${userAnimals}" varStatus="index">
                                <tr>
                                    <td>${index.count}</td>
                                    <td>${animal.name}</td>
                                    <td>${animal.species}</td>
                                    <td>
                                        <fmt:parseDate value="${animal.dateOfBirth}" pattern="yyyy-MM-dd" var="parsedDate" />
                                        <fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy" />
                                    </td>
                                    <td>${animal.status}</td>
                                    <td>
                                        <a href="animalRegisterServlet?action=update&animal-id=${animal.id}" class="btn btn-primary btn-sm">Editar</a>
                                        <a href="animalRegisterServlet?action=delete&animal-id=${animal.id}" class="btn btn-secondary btn-sm">Excluir</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <p class="text-center">Sem nenhum animal registrado.</p>
            </c:otherwise>
        </c:choose>

        <div class="text-center mt-4">
            <a href="animal-register.jsp" class="btn btn-primary">Cadastrar Novo Animal</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
