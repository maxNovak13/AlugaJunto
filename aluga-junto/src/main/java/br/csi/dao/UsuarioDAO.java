package br.csi.dao;

import br.csi.model.Usuario;
import br.csi.model.Vaga;
import br.csi.util.ConectaDBPostgres;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO {

    public Usuario autenticarLoginDAO(String email, String senha) {//autentica login, busca por usuario no BD
        ConectaDBPostgres cdb = new ConectaDBPostgres();
        try{
            Statement stmt = cdb.getConexao().createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM usuario" +
                    " WHERE email='" + email + "' AND senha='" + senha + "'");
            //faz a busca no banco
            while(rs.next()){
                return new Usuario(
                        rs.getInt("codusu"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("senha"));
            }
            cdb.getConexao().close();
            return null;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public boolean verificarSeExisteUsu(Usuario usuario) { //verifico se o email inserido já foi cadastrado
        try{
            ConectaDBPostgres cdb = new ConectaDBPostgres();
            Statement stmt = cdb.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE email='" + usuario.getEmail() + "'");
            cdb.getConexao().close();
            if(rs.next()){return false;}
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean inserir(Usuario usuario) {//insere usuario no BD
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            String sql= "INSERT INTO usuario(nome, email, telefone, senha) VALUES ('"+usuario.getNome()+"','"+usuario.getEmail()+"','"+usuario.getTelefone()+"','"+usuario.getSenha()+"')";
            stmt.execute(sql);
            con.close();
            System.out.println("  SQL"+sql);
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean verifSeTemVaga(int codusu) {//verifca se o usuario tem vaga do tipo admin
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM relacao WHERE codusu='" + codusu + "' AND tipo= 'admin'");
            con.close();
            if (rs.next()){
                return true;}
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean criarRelacaoUsuVag(Usuario usuario, Vaga vaga) {//cria relação de admin entre usuario evaga
        ConectaDBPostgres cdb = new ConectaDBPostgres();
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            String sql= "INSERT INTO relacao(tipo, codusu, codvaga) VALUES ('admin','"+usuario.getCodusu()+"','"+vaga.getCodvaga()+"')";
            stmt.execute(sql);
            con.close();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }


}
