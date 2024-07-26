<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<jsp:useBean id="dao" class="br.csi.dao.UsuarioDAO"/>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>Cadastro</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://rawgit.com/RobinHerbots/jquery.inputmask/3.x/dist/jquery.inputmask.bundle.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#telefone").inputmask({
                mask: ["(99)9999-9999", "(99)99999-9999", ],
                keepStatic: true
            });
        });
    </script>
</head>

<nav class="navbar navbar-light" style="background-color: #B0E0E6;"> <!-- background #E0FFFF-->
    <a class="navbar-brand fs-3" href="index.jsp">
        <img src="deal.png" width="40" height="40" class="d-inline-block align-top" alt="" style="margin-left: 20px"> &nbsp; &nbsp;
        AlugaJunto
    </a>
</nav>
<body>
    <div class="container-sm text-left border rounded mt-4" style="padding-left: 30px; padding-right: 30px; padding-bottom: 30px;">
    <h3 class="text-center" style="margin: 1em;">Cadastre-se</h3>
    <form action="cadastrarusu" method="post">
        <div class="row justify-content-md-center">
            <div>
                <label for="nome" class="form-label">
                    <b>Nome</b>
                </label>
                <input type="text" id="nome" class="form-control" placeholder="Escreva seu nome" name="nome" maxlength="100" required>
                <br/>
            </div>
        </div>
            <div>
                <label for="email" class="form-label">
                    <b>Email</b>
                </label>
                <input type="email" id="email" class="form-control" placeholder="Escreva seu email" name="email" aria-describedby="emailHelp" title="Digite no formato nome@exemplo.com" maxlength="100" required>
                <br/>
            </div>
            <div>
                <label for="telefone" class="form-label">
                    <b>Telefone</b>
                </label>
                <input type="text" class="form-control" placeholder="Escreva seu telefone" id="telefone" name="telefone" aria-describedby="emailHelp" title="Escreva seu telefone no formato (00)00000-0000" maxlength="30" required>
                <br/>
            </div>
            <div>
                <label for="senha" class="form-label">
                    <b>Senha</b>
                </label>
                <input type="password" id="senha" class="form-control" placeholder="Escreva sua senha" name="senha" maxlength="30" required>
                <br/>
            </div>

        <button type="submit" class="btn" style="background-color: #B0E0E6; color:#000000; border-color: #B0E0E6" value="CADASTRARUSU" name="cadastrarusu">Criar conta</button>
        <a class="btn btn-secondary" href="index.jsp">Cancelar</a>
        <br/>
        <c:if test="${not empty retorno}">
            <h5>${retorno}</h5>
        </c:if>
    </form>

    </div>
</body>
</html>
