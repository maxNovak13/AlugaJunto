<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>AlugaJunto</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<nav class="navbar navbar-light" style="background-color: #B0E0E6;"> <!-- background #E0FFFF-->
    <a class="navbar-brand fs-3" href="index.jsp">
        <img src="deal.png" width="40" height="40" class="d-inline-block align-top" alt="" style="margin-left: 20px"> &nbsp; &nbsp;
        AlugaJunto
    </a>
</nav>

<body >

<div class="container-sm text-left border rounded mt-4" style="padding-left: 30px; padding-right: 30px; padding-bottom: 30px;">
<h3 class="text-center" style="margin: 1em;">Página de acesso</h3>
<form action="login" method="post" style="margin: 2em;">  <!--action é o nome do UsuarioServlet -> @WebServlet("usuario")-->
    <div>
        <label for="email" class="form-label">
            <b>Login</b>
        </label>
        <input type="email" id="email" class="form-control" placeholder="Escreva seu e-mail" name="email" maxlength="100" required>
        <br>
    </div>
        <div>
        <label for="senha" class="form-label">
            <b>Senha</b>
        </label>
        <input type="password" id="senha" class="form-control" placeholder="Escreva sua senha" name="senha" maxlength="30" required>
    </div>
    <br>
    <button type="submit" class="btn" style="background-color: #B0E0E6; color:#000000; border-color: #B0E0E6" value="LOGAR" name="login">Acessar</button>
    <h4 style="margin: 1em;">Não tem conta? <a href="cadastrarusu?opcao=cadastro">Cadastre-se aqui</a></h4>
</form>


<c:if test="${not empty erro}">
    <h2 style="color:red;" >${erro}</h2>
</c:if>
    <c:if test="${not empty retorno}">
        <h5>${retorno}</h5>
    </c:if>
</div>
</body>
</html>
