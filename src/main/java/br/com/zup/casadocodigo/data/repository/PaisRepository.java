package br.com.zup.casadocodigo.data.repository;

import br.com.zup.casadocodigo.data.domain.Pais;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends CrudRepository<Pais,Short> {


}
