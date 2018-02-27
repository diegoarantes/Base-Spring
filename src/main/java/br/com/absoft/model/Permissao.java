package br.com.absoft.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author D.Arantes
 */
@Entity
@Data
@EqualsAndHashCode(of = "permissao")
public class Permissao implements GrantedAuthority {

    @Id
    private String permissao;

    @ManyToMany(mappedBy = "permissoes")
    private List<Usuario> usuarios;

    /* Métodos da classe auxiliar GrantedAuthority */
    @Override
    public String getAuthority() {
        return this.permissao;
    }

}
