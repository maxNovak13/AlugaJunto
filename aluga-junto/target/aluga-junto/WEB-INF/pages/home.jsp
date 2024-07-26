<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<jsp:useBean id="dao" class="br.csi.dao.VagaDAO"/>
<jsp:useBean id="daoU" class="br.csi.dao.UsuarioDAO"/>

<html lang="pt-br">
<head>
    <title>Página Inicial</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<nav class="navbar navbar-expand-lg" style="background-color: #B0E0E6;">
    <a class="navbar-brand fs-3" href="cadastrarusu?opcao=home">
        <img src="deal.png" width="40" height="40" class="d-inline-block align-top" alt="" style="margin-left: 20px"> &nbsp; &nbsp;
        AlugaJunto
    </a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link active" href="vaga?opcao=home">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="vaga?opcao=cadastrarvaga">Oferecer uma vaga</a>
            </li>
            <c:if test="${daoU.verifSeTemVaga(sessionScope.get('sessaousuario').codusu)}">
            <li class="nav-item">
                <a class="nav-link" href="vaga?opcao=visualizarvaga">Minha oferta</a>
            </li>
            </c:if>
        </ul>
    </div>
    <a style="padding: 10px; margin:10px;">${sessionScope.get('sessaousuario').nome}</a>
    <a href="cadastrarusu?opcao=sair" class="btn btn-danger" style="padding-left: 10px; padding-right: 10px; margin:10px;" >Sair</a>
</nav>
<body>

<div class="container text-center">
    <h1>Vagas disponíveis</h1>
    <div class="card-deck row justify-content-md-center"  style="display: flex;">
<c:forEach var="vaga" items="${dao.getVagas()}">
        <div class="card col-md-3" style="margin: 20px;  padding:20px;">
            <img class="card-img-top" src="${vaga.imagem}" alt="Imagem do lugar"  style="max-height: 200px; width: auto;">
            <div class="card-body">
                <h5 class="card-title">${vaga.titulo}</h5>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Dormitório: ${vaga.dormitorio} </br> Banheiro:  ${vaga.banheiro} </br>
                    Área: ${vaga.area} m²</br> Vaga garagem: ${vaga.garagem} </br>  Observação: ${vaga.observacao}</li>
                <li class="list-group-item">Rua ${vaga.endereco.rua} - Bairro  ${vaga.endereco.bairro},
                     ${vaga.endereco.cidade} - ${vaga.endereco.estado}, ${vaga.endereco.cep},</li>
                <li class="list-group-item">Gênero: ${vaga.perfil.genero} </br> Idade:  ${vaga.perfil.idade} anos </br> É necessário afinidade c/ pets: ${vaga.perfil.pet}
                    </br> Perfil esperado: ${vaga.perfil.observacao}</li>
            </ul>


            <c:choose>
                <c:when test="${dao.verifRelacao(vaga.codvaga, sessionScope.get('sessaousuario').codusu) ==1}">
                    <a type="button" href="#" class="btn btn-success" >Você demonstrou interesse!</a>
                </c:when>
                <c:when test="${dao.verifRelacao(vaga.codvaga, sessionScope.get('sessaousuario').codusu) ==2}">
                    <a type="button" href="#" class="btn btn-secondary" >Sua vaga</a>
                </c:when>
                <c:otherwise>
                    <a type="button" href="vaga?opcao=interesse&&codvaga=${vaga.codvaga}&&codusu=${sessionScope.get('sessaousuario').codusu}"
                       class="btn btn-primary active" >Tenho interesse</a>
                </c:otherwise>
            </c:choose>

        </div>
</c:forEach>
    </div>
</div>

</body>
</html>