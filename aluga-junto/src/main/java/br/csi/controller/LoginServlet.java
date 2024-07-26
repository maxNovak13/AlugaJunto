package br.csi.controller;

import br.csi.model.Usuario;
import br.csi.service.LoginService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        Usuario usuario = new LoginService().logar(email, senha);//autentica login

        HttpSession sessao = req.getSession(true);
        sessao.setAttribute("sessaousuario", usuario);//depende do usuário que logou

        if (usuario != null) {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/home.jsp");
            rd.forward(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            req.setAttribute("erro", "Email ou senha incorretos!");
            rd.forward(req, resp);
        }

    }

}
