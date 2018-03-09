package br.com.absoft;

import br.com.absoft.model.Usuario;
import br.com.absoft.repository.UsuarioRepository;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new FixedLocaleResolver(new Locale("pt", "BR"));
    }

    /**
     * Adiciona o usuario Admin no banco de dados.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
       adicionaUsuarioAdmin();
    }
    @Autowired
    private UsuarioRepository usuarioRepository; 

    public void adicionaUsuarioAdmin() {
        Usuario usuario = new Usuario();
        usuario.setLogin("admin@absoft.com.br");
        usuario.setSenha(new BCryptPasswordEncoder().encode("absoft.123"));
        usuario.setAtivo(true);
        usuario.setNomeCompleto("Administrador");

        usuarioRepository.save(usuario);
        System.out.println("Usuario Admin adicionado!");
    }
}
