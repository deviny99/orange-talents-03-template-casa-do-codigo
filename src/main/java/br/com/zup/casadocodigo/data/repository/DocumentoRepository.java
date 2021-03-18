package br.com.zup.casadocodigo.data.repository;

import br.com.zup.casadocodigo.data.domain.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento,Long> {

}
