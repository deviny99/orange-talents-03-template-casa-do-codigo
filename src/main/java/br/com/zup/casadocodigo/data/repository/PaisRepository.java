package br.com.zup.casadocodigo.data.repository;

import br.com.zup.casadocodigo.data.domain.Pais;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PaisRepository extends CrudRepository<Pais,Short> {

    @Query("SELECT p FROM Pais p JOIN p.estados e WHERE p.id = ?1")
    public Optional<Pais> findByIdToLazy(Short id);

}
