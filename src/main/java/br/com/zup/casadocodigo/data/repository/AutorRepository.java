package br.com.zup.casadocodigo.data.repository;

import br.com.zup.casadocodigo.data.domain.Autor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends CrudRepository<Autor,Long> {

}