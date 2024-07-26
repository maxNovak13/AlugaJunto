package br.csi.service;

import br.csi.dao.EnderecoDAO;
import br.csi.dao.UsuarioDAO;
import br.csi.model.Endereco;

public class EnderecoService {

    public boolean inserir(Endereco endereco) {
        return new EnderecoDAO().inserir(endereco);
    }

    public Endereco buscarEndereco(Endereco endereco) {
        return new EnderecoDAO().buscarEndereco(endereco);
    }

    public void excluir(int codendereco) {
        new EnderecoDAO().excluir(codendereco);
    }

    public boolean editar(int codendereco, String rua, String bairro, String cep, String cidade, String estado) {
        return new EnderecoDAO().editar(codendereco, rua, bairro, cep, cidade, estado);
    }

    public Endereco buscaCodendereco(int cod) {
        return new EnderecoDAO().buscaCodendereco(cod);
    }
}
