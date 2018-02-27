package br.com.absoft.model;

import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@EqualsAndHashCode(of = "login")
public class Usuario implements UserDetails {

    @Id
    private String login;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String nomeCompleto;

    private boolean ativo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_permissoes",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "login"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id", referencedColumnName = "permissao"))
    private List<Permissao> permissoes;

    /* Metodos da classe auxiliar UserDetails */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissoes;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.ativo;
    }
}
