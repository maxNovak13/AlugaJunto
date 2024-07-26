<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<jsp:useBean id="dao" class="br.csi.dao.VagaDAO"/>
<html lang="pt-br">
<head>
    <title>Editar vaga</title>
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
<div class="container-sm text-left border rounded mt-4" style="padding-left: 30px; padding-right: 30px; padding-bottom: 10px; margin-bottom: 30px;">
<c:if test="${not empty erro}">
    <h2  >${erro}</h2>
</c:if>
    <h2 class="text-center" style="margin: 1em;">Editar informações da vaga</h2>
    <br/>
    <h4>Sobre o lugar informe:</h4>
    <c:set var="vagaUsu" value="${dao.getVaga(codigo)}" />
    <form action="vaga" method="post">
        <div>
            <label for="imagem" class="form-label">
                <b>Insira o link de uma imagem do lugar: </b>
            </label>
            <input type="text" id="imagem" value="${vagaUsu.imagem}" class="form-control"
                   name="imagem"  maxlength="2000" required/>
            <br/>
        </div>
        <div>
            <label for="titulo" class="form-label">
                <b>Com qual título deseja anunciar a vaga? </b>
            </label>
            <input type="text" id="titulo" value="${vagaUsu.titulo}" class="form-control"
                   name="titulo"  maxlength="50" required/>
            <br/>
        </div>
        <div class="row">
            <div class="col-md-3">
                <label for="area" class="form-label">
                    <b>Área total em m²</b>
                </label>
                <input type="number" id="area" value="${vagaUsu.area}" class="form-control" name="area"   required/>
                <br/>
            </div>
            <div class="col-md-3">
                <label for="dormitorio" class="form-label">
                    <b>Número de dormitório(s)</b>
                </label>
                <input type="number" id="dormitorio" value="${vagaUsu.dormitorio}" class="form-control" name="dormitorio" value="${vagaUsu.titulo}" required/>
                <br/>
            </div>
            <div class="col-md-3">
                <label for="banheiro" class="form-label">
                    <b>Número de banheiro(s)</b>
                </label>
                <input type="number" id="banheiro" value="${vagaUsu.banheiro}" class="form-control" name="banheiro" required/>
                <br/>
            </div>
            <div class="col-md-3">
                <label for="garagem" class="form-label">
                    <b>Vaga(s) de garagem</b>
                </label>
                <input type="number" id="garagem" value="${vagaUsu.garagem}" class="form-control" placeholder="Se não tiver selecione 0" name="garagem"
                       required/>
                <br/>
            </div>
        </div>

        <div>
            <label for="observacao" class="form-label">
                <b>Alguma observação sobre a vaga/local? </b>
            </label>
            <input type="text" id="observacao" value="${vagaUsu.observacao}" class="form-control" placeholder="Local bem iluminado e arejado, com uma boa localização."
                   name="observacao" maxlength="100" required/>
            <br/>
        </div>

        <h4>Sobre o endereço informe:</h4>
        <div class="row">
            <div class="col-md-4">
                <label for="cidade"  class="form-label">
                    <b>Cidade</b>
                </label>
                <input type="text" id="cidade" value="${vagaUsu.endereco.cidade}" class="form-control" name="cidade" maxlength="30" required/>
                <br/>
            </div>
            <div class="col-md-4">
                <label for="estado" class="form-label">
                    <b>Estado</b>
                </label>
                <select class="form-control" id="estado" name="estado">
                    <option>${vagaUsu.endereco.estado}</option>  <!-- arrumar o selected-->
                    <option>AC</option>
                    <option>AL</option>
                    <option>AP</option>
                    <option>AM</option>
                    <option>BA</option>
                    <option>CE</option>
                    <option>DF</option>
                    <option>ES</option>
                    <option>GO</option>
                    <option>MA</option>
                    <option>MT</option>
                    <option>MS</option>
                    <option>MG</option>
                    <option>PA</option>
                    <option>PB</option>
                    <option>PR</option>
                    <option>PE</option>
                    <option>PI</option>
                    <option>RJ</option>
                    <option>RN</option>
                    <option>RS</option>
                    <option>RO</option>
                    <option>RR</option>
                    <option>SC</option>
                    <option>SP</option>
                    <option>SE</option>
                    <option>TO</option>
                </select>
                <br/>
            </div>
            <div class="col-md-4">
                <label for="cep"   class="form-label">
                    <b>CEP </b>
                </label>
                <input type="text" id="cep" value="${vagaUsu.endereco.cep}" class="form-control" placeholder="Formato 00000-000" name="cep" maxlength="9"
                       aria-describedby="cepHelp" title="Escreva no formato 00000-000" onkeyup="handleZipCode(event)" required/>
                <br/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <label for="rua"  class="form-label">
                    <b>Rua </b>
                </label>
                <input type="text" id="rua" value="${vagaUsu.endereco.rua}" class="form-control" name="rua" maxlength="80" required/>
                <br/>
            </div>
            <div class="col-md-3">
                <label for="bairro"  class="form-label">
                    <b>Bairro </b>
                </label>
                <input type="text" id="bairro" value="${vagaUsu.endereco.bairro}" class="form-control" name="bairro" maxlength="80" required/>
                <br/>
            </div>
        </div>

        <h4>Sobre o perfil desejado:</h4>
        <div class="row">
            <div class="col-md-3">
                <label for="genero" class="form-label">
                    <b>Qual gênero esperado? </b>
                </label>
                <br/>
                <select class="form-control" id="genero" name="genero">
                    <option>${vagaUsu.perfil.genero}</option>
                    <option>Tanto faz</option>
                    <option>Feminino</option>
                    <option>Masculino</option>
                </select>
            </div>
            <div class="col-md-3">
                <label for="idade" class="form-label">
                    <b>Qual a idade? </b>
                </label>
                <select class="form-control" id="idade" name="idade">
                    <option>${vagaUsu.perfil.idade}</option>
                    <option>Tanto faz</option>
                    <option>18-25</option>
                    <option>20-30</option>
                    <option>30-40</option>
                    <option>+40</option>
                </select>
                <br/>
            </div>
            <div class="col-md-3">
                <label for="pet" class="form-label">
                    <b>É necessário ter afinidade com pets? </b>
                </label>
                <select class="form-control" id="pet" name="pet">
                    <option>${vagaUsu.perfil.pet}</option>
                    <option>Tanto faz</option>
                    <option>Sim</option>
                    <option>Não</option>
                </select>
                <br/>
            </div>
        </div>
        <div>
            <label for="observacaoper"  class="form-label">
                <b>Alguma característica esperada da pessoa que deseja morar junto? </b>
            </label>
            <input type="text" id="observacaoper" value="${vagaUsu.perfil.observacao}" class="form-control" placeholder="Uma pessoa organizada, responsável e de preferência um universitário." name="observacaoper" maxlength="100" required/>
            <br/>
        </div>
         <input type="hidden" id="codvaga" name="codvaga" value="${vagaUsu.codvaga}">
        <input type="hidden" id="codendereco" name="codendereco" value="${vagaUsu.endereco.codendereco}">
        <input type="hidden" id="codperfil" name="codperfil" value="${vagaUsu.perfil.codperfil}">
        <button class="btn" style="background-color: #00CED1; color:#FFFFFF; border-color: #00CED1" type="submit"
                value="editarvaga" name="acao">Editar</button>
        <a class="btn btn-secondary" href="vaga?opcao=visualizarvaga">Voltar</a>
    </form>
</div>
</body>
</html>
