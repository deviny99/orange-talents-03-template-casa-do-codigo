package br.com.zup.casadocodigo.data.repository;

import br.com.zup.casadocodigo.data.domain.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente,Long> {
    @Query("SELECT c FROM Cliente c JOIN c.documento d where d.registro = ?1")
    Optional<Cliente> findByDocumento(String documento);
}
