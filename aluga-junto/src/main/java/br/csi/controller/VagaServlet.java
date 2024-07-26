package br.csi.controller;


import br.csi.dao.EnderecoDAO;
import br.csi.dao.UsuarioDAO;
import br.csi.dao.VagaDAO;
import br.csi.model.Endereco;
import br.csi.model.Perfil;
import br.csi.model.Usuario;
import br.csi.model.Vaga;
import br.csi.service.EnderecoService;
import br.csi.service.PerfilService;
import br.csi.service.UsuarioService;
import br.csi.service.VagaService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("vaga")
public class VagaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession sessao = req.getSession(true);
        sessao.getAttribute("sessaousuario");
        Usuario usu = (Usuario) sessao.getAttribute("sessaousuario");
        System.out.println("usuario codigo: " + usu.getCodusu());

        String rua = req.getParameter("rua");   /// infos Endereço
        String bairro = req.getParameter("bairro");
        String cep = req.getParameter("cep");
        String cidade = req.getParameter("cidade");
        String estado = req.getParameter("estado");

        String genero = req.getParameter("genero");     //infos Perfil
        String idade = req.getParameter("idade");
        String pet = req.getParameter("pet");
        String observacaoper = req.getParameter("observacaoper");

        String imagem = req.getParameter("imagem");         //infos Vaga
        String titulo = req.getParameter("titulo");
        int dormitorio = Integer.parseInt(req.getParameter("dormitorio"));
        int banheiro = Integer.parseInt(req.getParameter("banheiro"));
        String observacaoV = req.getParameter("observacao");
        int area = Integer.parseInt(req.getParameter("area"));
        int garagem = Integer.parseInt(req.getParameter("garagem"));

        String acao = req.getParameter("acao");
        if(acao.equals("cadastrarvaga")){
            Endereco endereco = new Endereco(rua, bairro, cep, cidade, estado);
            Perfil perfil = new Perfil(pet, idade, genero, observacaoper);
            if (new EnderecoService().inserir(endereco) && new PerfilService().inserir(perfil)) {//se cria ENDERECO e PERFIL E DA CERT FAZER VAGA

                Vaga vaga = new Vaga(titulo, dormitorio, banheiro, observacaoV, area, garagem, 0,imagem, new EnderecoService().buscarEndereco(endereco), new PerfilService().buscarPerfil(perfil));

                if(new VagaService().inserir(vaga)){//se a vaga for criada corretamente -> cria-se relação vaga_usuario
                    if(new UsuarioService().criarRelacao(usu, new VagaService().buscarVaga(vaga))) {//crio no bd a relação entre vaga e usuário, nesse caso como 'admin'
                        System.out.println("DEU CERTO!!!");
                    }else{
                        System.out.println("DEU ERRADO :(");
                    }
                    req.setAttribute("retorno", "Vaga salva com sucesso!");
                }else{
                    req.setAttribute("retorno", "Erro, vaga não foi salva!");
                }
            } else {
                req.setAttribute("retorno", "Algum erro aconteceu ao salvar endereço");
            }
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/visualizarvaga.jsp");//mando o user pra pagina de visualizar
            rd.forward(req, resp);
        }
        else if(acao.equals("editarvaga")){
            int codvaga = Integer.parseInt(req.getParameter("codvaga")); // pega pelo input hidden
            int codperfil = Integer.parseInt(req.getParameter("codperfil"));
            int codendereco = Integer.parseInt(req.getParameter("codendereco"));
            if(new VagaService().editar(codvaga, titulo, dormitorio, banheiro, observacaoV, area, garagem, imagem)&&new EnderecoService().editar(codendereco, rua, bairro, cep, cidade, estado)&&new PerfilService().editar(codperfil, genero, idade, pet, observacaoper)){
                System.out.println("Vaga editada com sucesso!");
                req.setAttribute("retorno", "Vaga editada com sucesso!");
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/visualizarvaga.jsp");//mando o user pra pagina visualizar após editar
                rd.forward(req, resp);
            }else{
                System.out.println("Algum erro aconteceu e a vaga não foi salva!");
                req.setAttribute("retorno", "Algum erro ao editar aconteceu, tente novamente!");
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/home.jsp");//mando o user pra pagina visualizar após editar
                rd.forward(req, resp);
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String opcao = request.getParameter("opcao");
        if(opcao != null){
            if(opcao.equals("cadastrarvaga")){
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/cadastrarvaga.jsp");
                rd.forward(request, response);
            }
            else if(opcao.equals("visualizarvaga")){
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/visualizarvaga.jsp");
                rd.forward(request, response);
            }
            else if(opcao.equals("home")){
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/home.jsp");
                rd.forward(request, response);
            }
            else if(opcao.equals("pageditar")){
                int codvaga = Integer.parseInt(request.getParameter("codvaga"));
                request.setAttribute("codigo", codvaga);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/editarvaga.jsp");
                rd.forward(request, response);
            }
            else if(opcao.equals("interesse")){
                int codvaga = Integer.parseInt(request.getParameter("codvaga"));
                int codusu = Integer.parseInt(request.getParameter("codusu"));

                if(new VagaService().criaInteresse(codvaga, codusu)){
                    VagaService.incrementaInter(codvaga);
                }
                else{
                    System.out.println("ERRO ao criar relação interesse");
                }

                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/home.jsp");
                rd.forward(request, response);
            }
            else if(opcao.equals("excluir")){
                int codvaga = Integer.parseInt(request.getParameter("codvaga"));
                int codendereco = Integer.parseInt(request.getParameter("codendereco"));
                int codperfil = Integer.parseInt(request.getParameter("codperfil"));
                System.out.println("CODPERFIL DO SERVLET " + codperfil);
                System.out.println("CODENDERECO DO SERVLET " + codendereco);
                if(new VagaService().excluir(codvaga)){//após excluir vaga excluir endereco e perfil
                    new EnderecoService().excluir(codendereco);
                    new PerfilService().excluir(codperfil);
                    System.out.println("VAGA EXCLUIDA COM SUCESSO");
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/home.jsp");
                    rd.forward(request, response);
                }else{
                    System.out.println("ERROR VAGA NAO FOI EXCLUIDA");
                    request.setAttribute("retorno", "Vaga excluída!");
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/visualizarvaga.jsp");
                    rd.forward(request, response);
                }

            }
        }

    }

}
