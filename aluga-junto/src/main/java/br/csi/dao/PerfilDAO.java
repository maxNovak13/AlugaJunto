package br.csi.dao;

import br.csi.model.Endereco;
import br.csi.model.Perfil;
import br.csi.util.ConectaDBPostgres;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PerfilDAO {
    public boolean inserir(Perfil perfil) {//insere perfil
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            String sql= "INSERT INTO perfil(genero, idade, pet, observacao) VALUES ('"+perfil.getGenero()+"','"+perfil.getIdade()+"','"+perfil.getPet()+"' ,'"+perfil.getObservacao()+"')";
            stmt.execute(sql);
            System.out.println("  SQL"+sql);
             con.close();
            return true;
        }catch(
                SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Perfil buscarPerfil(Perfil perfil) { // busca perfil sem ter o codperfil pra relacionar com a vaga
        try{
            ConectaDBPostgres cdb = new ConectaDBPostgres();
            Statement stmt = cdb.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM perfil WHERE genero = '" + perfil.getGenero() + "' AND idade ='" + perfil.getIdade() + "' AND pet ='" + perfil.getPet() + "' AND observacao ='" + perfil.getObservacao() + "'" );
            rs.next();
            Perfil perf =
                    new Perfil(
                            rs.getInt("codperfil"),
                            rs.getString("pet"),
                            rs.getString("idade"),
                            rs.getString("genero"),
                            rs.getString("observacao")
                            );
            cdb.getConexao().close();
            return perf;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public Perfil buscarCodperfil(int cod) {        //busca perfil pelo codperfil retorna perfil
        try{
            ConectaDBPostgres cdb = new ConectaDBPostgres();
            Statement stmt = cdb.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM perfil WHERE codperfil= '" + cod + "'" );
            rs.next();
            Perfil perf =
                    new Perfil(
                            rs.getInt("codperfil"),
                            rs.getString("pet"),
                            rs.getString("idade"),
                            rs.getString("genero"),
                            rs.getString("observacao")
                    );
            cdb.getConexao().close();
            return perf;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void excluir(int codperfil) {//exclui perfil
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            String sql =
                    "DELETE FROM perfil where codperfil = "+codperfil;
            stmt.execute(sql);
            System.out.println("SQL: "+sql);
              con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public boolean editar(int codperfil, String genero, String idade, String pet, String observacao) { //edita perfil
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            String sql =
                    "UPDATE perfil SET genero ='"+genero+"', idade = '"+idade +"', pet= '"+pet+"', observacao='"+observacao+"' WHERE codperfil = "+codperfil;
            stmt.execute(sql);
            System.out.println("SQL: "+sql);
           con.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
            return false;
    }
}
