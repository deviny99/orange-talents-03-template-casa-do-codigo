package br.com.zup.casadocodigo.web.controller.dto.response;

import br.com.zup.casadocodigo.data.domain.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroDetalhes {

    private Long id;
    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal valor;
    private Short paginas;
    private Long isbn;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dtPub;
    private AutorDetalhes autor;
    private CategoriaDto categoria;

    @Deprecated
    public LivroDetalhes(){ }

    public LivroDetalhes(Livro livro) {

        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.valor = livro.getValor();
        this.paginas = livro.getPaginas();
        this.isbn = livro.getIsbn();
        this.dtPub = livro.getDtPub();
        this.autor = new AutorDetalhes(livro.getAutor());
        this.categoria = new CategoriaDto(livro.getCategoria());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Short getPaginas() {
        return paginas;
    }

    public Long getIsbn() {
        return isbn;
    }

    public LocalDate getDtPub() {
        return dtPub;
    }

    public AutorDetalhes getAutor() {
        return autor;
    }

    public CategoriaDto getCategoria() {
        return categoria;
    }
}