package br.csi.controller;


import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;
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

@WebServlet("cadastrarusu")
public class UsuarioServlet extends HttpServlet {//cadastrar usuario

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String telefone = req.getParameter("telefone");
        String senha = req.getParameter("senha");

        Usuario usuario = new Usuario(nome, email, telefone, senha);
        if(new UsuarioService().verificarSeExisteUsu(usuario)){//verifico se o email inserido já foi cadastrado
            if (new UsuarioService().inserir(usuario)) {//não tem validação se já existe no bd -> fazer futuramente
                req.setAttribute("retorno", "Você foi cadastrado com sucesso!");
                RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                rd.forward(req, resp);
            } else {
                req.setAttribute("retorno", "Ocorreu algum erro, tente novamente!");
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/cadastrarusu.jsp");
                rd.forward(req, resp);
            }
        } else{
            req.setAttribute("retorno", "O email inserido já foi cadastrado, utilize outro!");
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/cadastrarusu.jsp");
            rd.forward(req, resp);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String opcao = request.getParameter("opcao");
        if(opcao != null){
            if(opcao.equals("home")){
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/home.jsp");
                rd.forward(request, response);
            }
            else if(opcao.equals("cadastro")){
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/cadastrarusu.jsp");
                rd.forward(request, response);
            }
            else if(opcao.equals("sair")){
                HttpSession session = request.getSession(false);
                session.invalidate();//exclui sessao
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        }
    }

}
