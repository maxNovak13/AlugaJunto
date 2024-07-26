<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<jsp:useBean id="dao" class="br.csi.dao.VagaDAO"/>
<html lang="pt-br">
    <head>
        <title>Visualizar minha oferta</title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <nav class="navbar navbar-expand-lg" style="background-color: #B0E0E6;">
        <a class="navbar-brand fs-3" href="vaga?opcao=home">
            <img src="deal.png" width="40" height="40" class="d-inline-block align-top" alt="" style="margin-left: 20px"> &nbsp; &nbsp;
            AlugaJunto
        </a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="vaga?opcao=home">Home</a>
                </li>
                    <li class="nav-item">
                        <a class="nav-link" href="vaga?opcao=cadastrarvaga">Oferecer uma vaga</a>
                    </li>
                <c:if test="${dao.vagasUsuario(sessionScope.get('sessaousuario').codusu) != null}">
                    <li class="nav-item ">
                        <a class="nav-link active" href="vaga?opcao=visualizarvaga">Minha oferta</a>
                    </li>
                </c:if>
            </ul>
        </div>
        <a style="padding: 10px; margin:10px;">${sessionScope.get('sessaousuario').nome}</a>
        <a href="cadastrarusu?opcao=sair" class="btn btn-danger" style="padding-left: 10px; padding-right: 10px; margin:10px;" >Sair</a>
    </nav>
    <body>
        <div class="container">
            <br/>
            <h1>Minhas ofertas</h1>
            <c:if test="${not empty retorno}">
                <h2 >${retorno}</h2>
            </c:if>

                <c:if test="${dao.vagasUsuario(sessionScope.get('sessaousuario').codusu) != null}">
                        <div class="card-deck">

                            <c:forEach var="vagaUsu" items="${dao.vagasUsuario(sessionScope.get('sessaousuario').codusu)}">
                                <div class="row">
                                    <div class="card text-center col-md-6" style="margin: 20px;  padding:20px;">
                                        <img class="card-img-top" src="${vagaUsu.imagem}" alt="Imagem do lugar" class="img-fluid">

                                        <div class="card-body">
                                            <h5 class="card-title">${vagaUsu.titulo}</h5>
                                        </div>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item">Dormitório: ${vagaUsu.dormitorio} </br> Banheiro:  ${vagaUsu.banheiro} </br>
                                                    Área: ${vagaUsu.area} m²</br> Vaga garagem: ${vagaUsu.garagem} </br>  Observação: ${vagaUsu.observacao}</li>
                                                <li class="list-group-item">Rua ${vagaUsu.endereco.rua} - Bairro  ${vagaUsu.endereco.bairro},
                                                        ${vagaUsu.endereco.cidade} - ${vagaUsu.endereco.estado}, ${vagaUsu.endereco.cep}</li>
                                                <li class="list-group-item">Gênero: ${vagaUsu.perfil.genero}  </br> Idade:  ${vagaUsu.perfil.idade} anos</br> É necessário afinidade c/ pets: ${vagaUsu.perfil.pet}
                                                    </br> Perfil esperado: ${vagaUsu.perfil.observacao}</li>
                                            </ul>
                                            <a href="vaga?opcao=pageditar&&codvaga=${vagaUsu.codvaga}" class="btn" style="background-color: #00CED1; color:#FFFFFF; border-color: #00CED1">Editar</a>
                                            <br/>
                                            <a href="vaga?opcao=excluir&&codvaga=${vagaUsu.codvaga}&&codendereco=${vagaUsu.endereco.codendereco}&&codperfil=${vagaUsu.perfil.codperfil}"
                                               class="btn btn-danger">Excluir</a>
                                    </div>
                                    <div class="col-md-5">
                                        <br/>
                                        <c:choose>
                                            <c:when test="${dao.VerificaInteresse(vagaUsu.codvaga)}"> <!-- testo se há pessoas interessadas -->
                                                <h7>Interessados: ${vagaUsu.numinteres}</h7>
                                                <table class="table">
                                                    <thead>
                                                        <th scope="col">Nome</th>
                                                        <th scope="col">E-mail</th>
                                                        <th scope="col">Telefone</th>
                                                    </thead>
                                                    <c:forEach var="usu" items="${dao.usuariosInteressados(vagaUsu.codvaga)}">
                                                    <tbody>
                                                        <tr>
                                                        <td>${usu.nome}</td>
                                                        <td>${usu.email}</td>
                                                        <td>${usu.telefone}</td>
                                                        </tr>
                                                    <tbody>
                                                    </c:forEach>
                                                </table>

                                            </c:when>
                                            <c:otherwise>
                                                <h2 >Ainda não há pessoas interessadas</h2>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>
                    </div>

                </c:if>

            <a class="btn btn-secondary" href="vaga?opcao=home">Voltar</a>
        </div>
    </body>
</html>
