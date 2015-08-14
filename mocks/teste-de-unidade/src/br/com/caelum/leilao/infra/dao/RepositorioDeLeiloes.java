package br.com.caelum.leilao.infra.dao;

import java.util.List;

import br.com.caelum.leilao.dominio.Leilao;

//A grande vantagem é trabalhar sempre voltado para interfaces, que é um importante princípio de orientação a objetos.
//Sempre que for trabalhar com mocks, pense em criar interfaces entre suas classes. Dessa forma, seu teste e código passam a depender apenas de um contrato, e não de uma classe concreta.
public interface RepositorioDeLeiloes {
    void salva(Leilao leilao);
    List<Leilao> encerrados();
    List<Leilao> correntes();
    void atualiza(Leilao leilao);
}
