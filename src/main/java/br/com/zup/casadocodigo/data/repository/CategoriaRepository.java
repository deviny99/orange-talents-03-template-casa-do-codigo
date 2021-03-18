package br.com.zup.casadocodigo.data.repository;

import br.com.zup.casadocodigo.data.domain.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    public Optional<Categoria> findByNome(String nome);
}
