package com.gugawag.pdist.ejbs;

import com.gugawag.pdist.model.Mensagem;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless(name = "mensagemService")
@Remote
public class MensagemService {
    private static final List<String> PALAVROES = List.of("porra", "Puta", "Desgraçado", "Imbecil");

    @EJB
    private MensagemDAO mensagemDAO;

    @EJB
    private MensagemDAO mensagemDao;

    public List<Mensagem> listar() {
        return mensagemDAO.listar();
    }

    public void inserir(long id, String mensagem) {
        for (String palavrao : PALAVROES) {
            if (mensagem.toUpperCase().contains(palavrao.toUpperCase())) {
                throw new RuntimeException("Mensagem contem palavras nao permitidas");
            }
        }
        Mensagem novaMensagem = new Mensagem(id, mensagem);
        mensagemDAO.inserir(novaMensagem);
    }
}
