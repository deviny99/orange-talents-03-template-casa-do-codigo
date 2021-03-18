package br.com.zup.casadocodigo.web.controller.dto.response.livro;

import br.com.zup.casadocodigo.data.domain.Livro;
import org.springframework.data.domain.Page;

public class LivroResponseList {

    private Long id;
    private String titulo;

    public LivroResponseList(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public static Page<LivroResponseList> convertList(Page<Livro> lista) {
        return lista.map(LivroResponseList::new);
    }

}
