package br.csi.dao;

import br.csi.model.Endereco;
import br.csi.model.Usuario;
import br.csi.util.ConectaDBPostgres;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EnderecoDAO {


    public boolean inserir(Endereco endereco) {     //insere Endereco no BD
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            String sql= "INSERT INTO endereco(rua, bairro, cep, cidade, estado) VALUES ('"+endereco.getRua()+"','"+endereco.getBairro()+"','"+endereco.getCep()+"' ,'"+endereco.getCidade()+"','"+endereco.getEstado()+"')";
            stmt.execute(sql);
            System.out.println("  SQL"+sql);
            System.out.println("ADICIONOU ENDERECO NO BD!!");
           con.close();
            return true;
        }catch(
                SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public Endereco buscaCodendereco(int cod){      //busca endreco pelo codigo
        try{
            ConectaDBPostgres cdb = new ConectaDBPostgres();
            Statement stmt = cdb.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM endereco WHERE codendereco = '" + cod + "'" );
            rs.next();
            Endereco end =
                    new Endereco(
                            rs.getInt("codendereco"),
                            rs.getString("rua"),
                            rs.getString("bairro"),
                            rs.getString("cep"),
                            rs.getString("cidade"),
                            rs.getString("estado"));
            cdb.getConexao().close();
            return end;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Endereco buscarEndereco(Endereco endereco){  //busca por endereço no BD sem codendereco e retorna endereco completo
        try{
            ConectaDBPostgres cdb = new ConectaDBPostgres();
            Statement stmt = cdb.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM endereco WHERE rua = '" + endereco.getRua() + "' AND bairro ='" + endereco.getBairro() + "' AND cep ='" + endereco.getCep() + "' AND cidade ='" + endereco.getCidade() + "' AND estado ='" + endereco.getEstado() + "'" );
            rs.next();
            Endereco end =
                    new Endereco(
                            rs.getInt("codendereco"),
                            rs.getString("rua"),
                            rs.getString("bairro"),
                            rs.getString("cep"),
                            rs.getString("cidade"),
                            rs.getString("estado"));
            cdb.getConexao().close();
            return end;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void excluir(int codendereco) {          //exclui endereco
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            String sql =
                    "DELETE FROM endereco where codendereco = "+codendereco;

            stmt.execute(sql);
            System.out.println("SQL: "+sql);
           con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean editar(int codendereco, String rua, String bairro, String cep, String cidade, String estado) {// edita endereco
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            String sql =
                    "UPDATE endereco SET rua ='"+rua+"', bairro = '"+bairro+"', cep= '"+cep+"', cidade='"+cidade+"', estado='"+estado+"' WHERE codendereco = "+codendereco;
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
