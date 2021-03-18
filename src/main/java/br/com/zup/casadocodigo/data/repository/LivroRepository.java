package br.com.zup.casadocodigo.data.repository;

import br.com.zup.casadocodigo.data.domain.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Long> {

    Page<Livro> findAll(Pageable pageable);
    Page<Livro> findByTituloLike(String titulo,Pageable pageable);

}