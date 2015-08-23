package br.com.caelum.pm73.dao;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.pm73.dominio.Usuario;

public class UsuarioDaoTest {
	
	private Session session;
    private UsuarioDao usuarioDao;

    @Before
    public void antes() {
        // criamos a sessao e a passamos para o dao
        session = new CriadorDeSessao().getSession();
        usuarioDao = new UsuarioDao(session);
        // inicia transacao
        session.beginTransaction();
    }

    @After
    public void depois() {
    	// faz o rollback
        session.getTransaction().rollback();
        // fechamos a sessao
        session.close();
    }

	
    @Test
    public void deveEncontrarPeloNomeEEmail() {
        Usuario novoUsuario = new Usuario
                ("João da Silva", "joao@dasilva.com.br");
        usuarioDao.salvar(novoUsuario);

        Usuario usuarioDoBanco = usuarioDao
                .porNomeEEmail("João da Silva", "joao@dasilva.com.br");

        assertEquals("João da Silva", usuarioDoBanco.getNome());
        assertEquals("joao@dasilva.com.br", usuarioDoBanco.getEmail());
    }

    @Test
    public void deveRetornarNuloSeNaoEncontrarUsuario() {
        Usuario usuarioDoBanco = usuarioDao
                .porNomeEEmail("João Joaquim", "joao@joaquim.com.br");

        assertNull(usuarioDoBanco);
    }
    
    @Test
    public void deveDeletarUmUsuario() {
        Usuario usuario = 
                new Usuario("Mauricio Aniche", "mauricio@aniche.com.br");

        usuarioDao.salvar(usuario);
        usuarioDao.deletar(usuario);
        
        // envia tudo para o banco de dados        
        session.flush();
        session.clear();

        Usuario usuarioNoBanco = 
                usuarioDao.porNomeEEmail("Mauricio Aniche", "mauricio@aniche.com.br");

        assertNull(usuarioNoBanco);

    }
    
    @Test
    public void deveAlterarUmUsuario() {
        Usuario usuario = 
                new Usuario("Mauricio Aniche", "mauricio@aniche.com.br");

        usuarioDao.salvar(usuario);
        usuario.setNome("Cesar Cruz");
        usuario.setEmail("cesar@cruz");
        usuarioDao.atualizar(usuario);
        
        // envia tudo para o banco de dados        
        session.flush();
        session.clear();

        Usuario usuarioNoBanco = 
                usuarioDao.porNomeEEmail("Mauricio Aniche", "mauricio@aniche.com.br");

        assertNull(usuarioNoBanco);
        
        usuarioNoBanco = 
                usuarioDao.porNomeEEmail("Cesar Cruz", "cesar@cruz");

        assertNotNull(usuarioNoBanco);

    }
}
