package br.csi.dao;

import br.csi.model.Usuario;
import br.csi.model.Vaga;
import br.csi.service.EnderecoService;
import br.csi.service.PerfilService;
import br.csi.util.ConectaDBPostgres;

import java.sql.*;
import java.util.ArrayList;

public class VagaDAO {

    public ArrayList<Vaga> vagasUsuario(int codigousu){//busca as vagas de relação admin de um usuario
        try{
            ConectaDBPostgres cdb = new ConectaDBPostgres();
            Statement stmt = cdb.getConexao().createStatement();
            ArrayList<Vaga> vagasUsu = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT vaga.codvaga, titulo, dormitorio, banheiro, observacao, area, garagem, numinteres, imagem, vaga.codendereco, vaga.codperfil " +
                    "FROM usuario, relacao, vaga " +
                    "WHERE'"+ codigousu +"' = usuario.codusu " +
                    "AND usuario.codusu = relacao.codusu " +
                    "AND relacao.codvaga = vaga.codvaga "+
                    "AND relacao.tipo = 'admin' ");
                while (rs.next()) {
                    Vaga vag =
                            new Vaga(
                                    rs.getInt("codvaga"),
                                    rs.getString("titulo"),
                                    rs.getInt("dormitorio"),
                                    rs.getInt("banheiro"),
                                    rs.getString("observacao"),
                                    rs.getInt("area"),
                                    rs.getInt("garagem"),
                                    rs.getInt("numinteres"),//busco endereco pelo codigo e mando a classe Endereço para o construtor
                                    rs.getString("imagem"),
                                    new EnderecoService().buscaCodendereco(rs.getInt("codendereco")),
                                    new PerfilService().buscarCodperfil(rs.getInt("codperfil"))
                            );
                    vagasUsu.add(vag);
                }
            cdb.getConexao().close();
            return vagasUsu;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<Vaga> getVagas(){//busca todas as vagas disponiveis
        ConectaDBPostgres cdb = new ConectaDBPostgres();
        ArrayList<Vaga> vagas = new ArrayList<>();

        try{
            Statement stmt = cdb.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vaga");
            while (rs.next()){
                Vaga v =
                        new Vaga(
                                rs.getInt("codvaga"),
                                rs.getString("titulo"),
                                rs.getInt("dormitorio"),
                                rs.getInt("banheiro"),
                                rs.getString("observacao"),
                                rs.getInt("area"),
                                rs.getInt("garagem"),
                                rs.getInt("numinteres"),
                                rs.getString("imagem"),//busco endereco pelo codigo e mando a classe Endereço para o construtor
                                new EnderecoDAO().buscaCodendereco(rs.getInt("codendereco")),
                                new PerfilDAO().buscarCodperfil(rs.getInt("codperfil"))
                        );
                vagas.add(v);
            }
            cdb.getConexao().close();
            return vagas;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Vaga getVaga(int cod){//busca uma vaga pelo cod e retorna
        try{
            ConectaDBPostgres cdb = new ConectaDBPostgres();
            Statement stmt = cdb.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vaga WHERE codvaga = " + cod);
            rs.next();
            Vaga vag =
                    new Vaga(
                            rs.getInt("codvaga"),
                            rs.getString("titulo"),
                            rs.getInt("dormitorio"),
                            rs.getInt("banheiro"),
                            rs.getString("observacao"),
                            rs.getInt("area"),
                            rs.getInt("garagem"),
                            rs.getInt("numinteres"),
                            rs.getString("imagem"),
                            new EnderecoDAO().buscaCodendereco(rs.getInt("codendereco")),
                            new PerfilDAO().buscarCodperfil(rs.getInt("codperfil"))
                    );
            cdb.getConexao().close();
            return vag;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Vaga buscarVaga(Vaga vaga){//busca por vaga sem codvaga e retorna vaga com cod
        try{
            ConectaDBPostgres cdb = new ConectaDBPostgres();
            Statement stmt = cdb.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vaga WHERE titulo = '" + vaga.getTitulo() + "' AND dormitorio ='" + vaga.getDormitorio() + "' AND banheiro ='" + vaga.getBanheiro() + "' AND observacao ='" + vaga.getObservacao() + "' AND area ='" + vaga.getArea() + "'  AND garagem ='" + vaga.getGaragem() + "' AND numinteres ='" + vaga.getNuminteres() + "' AND imagem ='" + vaga.getImagem()+"'");
            rs.next();
            Vaga vag =
                    new    Vaga(
                            rs.getInt("codvaga"),
                            rs.getString("titulo"),
                            rs.getInt("dormitorio"),
                            rs.getInt("banheiro"),
                            rs.getString("observacao"),
                            rs.getInt("area"),
                            rs.getInt("garagem"),
                            rs.getInt("numinteres"),
                            rs.getString("imagem")//criei construtor só pra isso
                    );
            cdb.getConexao().close();
            return vag;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }



    public boolean inserir(Vaga vaga) {//quando cria a vaga inicia o número de interessados com 0
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            String sql= "INSERT INTO vaga(codendereco, codperfil, titulo, dormitorio, banheiro, observacao, area, garagem, numinteres, imagem) VALUES ('"+vaga.getEndereco().getCodendereco()+"','"+vaga.getPerfil().getCodperfil()+"','"+vaga.getTitulo()+"','"+vaga.getDormitorio()+"','"+vaga.getBanheiro()+"' ,'"+vaga.getObservacao()+"','"+vaga.getArea()+"','"+vaga.getGaragem()+"', '"+vaga.getNuminteres()+"', '"+vaga.getImagem()+"')";
            stmt.execute(sql);
            System.out.println("  SQL"+sql);
            con.close();
            return true;
        }catch(SQLException e){
            System.out.println("deu sqlexception");
            e.printStackTrace();
            return false;
        }

    }

    public boolean excluir(int codvaga) {
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            String sql =
                    "DELETE FROM vaga where codvaga = "+codvaga;

            stmt.execute(sql);
            System.out.println("SQL: "+sql);
            con.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void excluirRelacao(int codvaga) {
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM relacao where codvaga = "+codvaga;
            stmt.execute(sql);
            System.out.println("SQL: "+sql);
            con.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

       public boolean editar(int codvaga, String titulo, int dormitorio, int banheiro,String observacao, int area, int garagem, String imagem) {
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            String sql =
                    "UPDATE vaga SET titulo ='"+titulo+"', dormitorio = '"+dormitorio+"', banheiro= '"+banheiro+"', observacao='"+observacao+"', area='"+area+"', garagem='"+garagem+"', imagem='"+imagem+"' WHERE codvaga='"+codvaga+"'";
            stmt.execute(sql);
            System.out.println("SQL: "+sql);
            con.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    public boolean incrementaInter(int codvaga){
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT numinteres FROM vaga WHERE codvaga = " + codvaga);
            rs.next();
            int numInteressados = rs.getInt("numinteres");
            numInteressados=++numInteressados;//incrementa o que tem no BD
            String sql =
                    "UPDATE vaga SET numinteres ='"+ numInteressados +"' WHERE codvaga ="+codvaga;
            stmt.execute(sql);
            System.out.println("SQL: "+sql);
            con.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean criaInteresse(int codvaga, int codusu) {//cria relação de interesse
        try{
            Connection con = new ConectaDBPostgres().getConexao();
            Statement stmt = con.createStatement();
            String sql= "INSERT INTO relacao(tipo, codusu, codvaga) VALUES ('inter','"+codusu+"','"+codvaga+"')";
            stmt.execute(sql);
            System.out.println("  SQL"+sql);
            con.close();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean VerificaInteresse(int codvaga){//verifica se tem usuarios interessados na vaga
        try{
            ConectaDBPostgres cdb = new ConectaDBPostgres();
            Statement stmt = cdb.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM relacao WHERE tipo = 'inter' AND codvaga ="+ codvaga);
            cdb.getConexao().close();
            if(rs.next()) return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Usuario> usuariosInteressados(int codvaga){//busco em relacao usuarios com tipo 'inter'
        try{
            ArrayList<Usuario> usuariosInter = new ArrayList<>();
            ConectaDBPostgres cdb = new ConectaDBPostgres();
            Statement stmt = cdb.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT usuario.nome, usuario.email, usuario.telefone FROM relacao, usuario WHERE relacao.tipo = 'inter' AND relacao.codvaga = '"+ codvaga +"' AND relacao.codusu = usuario.codusu");
            while(rs.next()){
                Usuario usu =
                        new Usuario(
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("telefone") //criei construtor só pra isso
                        );
                usuariosInter.add(usu);
            }
            cdb.getConexao().close();
            return usuariosInter;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public int verifRelacao(int codvaga, int codusu) throws SQLException {//verifico se x usu é admin/inter da vaga -> logo sem botão interessado

        try{
            ConectaDBPostgres cdb = new ConectaDBPostgres();
            Statement stmt = cdb.getConexao().createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT tipo FROM relacao WHERE codusu ='"+ codusu+"' AND codvaga = '"+ codvaga +"'");
            if(rs.next()){
                String tipo = rs.getString("tipo");
                cdb.getConexao().close();
                if(tipo.equals("inter")){
                    return 1;//se tem relação interessado
                }
                if(tipo.equals("admin")){
                    return 2;//se tem relação administrador
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;//se não tiver relação
    }
}
