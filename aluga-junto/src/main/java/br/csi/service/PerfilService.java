package br.csi.service;

import br.csi.dao.PerfilDAO;
import br.csi.dao.VagaDAO;
import br.csi.model.Perfil;

public class PerfilService {
    public boolean inserir(Perfil perfil) {
        return new PerfilDAO().inserir(perfil);
    }

    public Perfil buscarPerfil(Perfil perfil) {
        return new PerfilDAO().buscarPerfil(perfil);
    }

    public void excluir(int codperfil) {
        new PerfilDAO().excluir(codperfil);
    }

    public boolean editar(int codperfil, String genero, String idade, String pet, String observacao) {
        return new PerfilDAO().editar(codperfil, genero, idade, pet, observacao);
    }

    public Perfil buscarCodperfil(int codperfil) {
        return new PerfilDAO().buscarCodperfil(codperfil);
    }
}
