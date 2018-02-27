package br.com.absoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.absoft.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    public Usuario findByLogin(String login);
}
