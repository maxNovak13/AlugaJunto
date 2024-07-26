package br.csi.service;

import br.csi.dao.VagaDAO;
import br.csi.model.Vaga;

import java.sql.SQLException;

public class VagaService {


    public static boolean incrementaInter(int codvaga) { return new VagaDAO().incrementaInter(codvaga);
    }

    public boolean inserir(Vaga vaga) {
        return new VagaDAO().inserir(vaga);
    }

    public Vaga buscarVaga(Vaga vaga) {
        return new VagaDAO().buscarVaga(vaga);
    }

    public boolean excluir(int codvaga) {
        new VagaDAO().excluirRelacao(codvaga); //exclui relação
        return new VagaDAO().excluir(codvaga); //exclui vaga
    }
    public boolean editar(int codvaga, String titulo, int dormitorio, int banheiro,String observacao, int area, int garagem, String imagem) {
        return new VagaDAO().editar(codvaga, titulo, dormitorio, banheiro, observacao, area, garagem, imagem);
    }

    public boolean criaInteresse(int codvaga, int codusu) {
        return new VagaDAO().criaInteresse(codvaga, codusu);
    }

}
