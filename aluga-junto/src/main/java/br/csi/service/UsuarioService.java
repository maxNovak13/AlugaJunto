package br.csi.service;

import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;
import br.csi.model.Vaga;

public class UsuarioService {

    public boolean inserir(Usuario usuario) {
        return new UsuarioDAO().inserir(usuario);
    }

    public boolean criarRelacao(Usuario usu, Vaga vag) {
        return new UsuarioDAO().criarRelacaoUsuVag(usu, vag);
    }

    public boolean verificarSeExisteUsu(Usuario usuario) {
        return new UsuarioDAO().verificarSeExisteUsu(usuario);
    }
}
