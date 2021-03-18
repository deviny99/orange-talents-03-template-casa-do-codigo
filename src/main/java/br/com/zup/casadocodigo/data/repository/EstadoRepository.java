package br.com.zup.casadocodigo.data.repository;

import br.com.zup.casadocodigo.data.domain.Estado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EstadoRepository extends CrudRepository<Estado,Integer> {

    @Query("SELECT e FROM Estado e JOIN e.pais p WHERE e.nome = ?1 and p.id = ?2")
    public Optional<Estado> findByNomeAndPais(String estado,Short pais);


}
