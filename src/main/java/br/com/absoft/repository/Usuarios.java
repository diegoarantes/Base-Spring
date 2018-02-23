package br.com.absoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.absoft.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long>{

}
