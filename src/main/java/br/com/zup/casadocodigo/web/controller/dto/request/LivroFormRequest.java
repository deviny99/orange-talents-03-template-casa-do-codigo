package br.com.zup.casadocodigo.web.controller.dto.request;

import br.com.zup.casadocodigo.data.domain.Autor;
import br.com.zup.casadocodigo.data.domain.Categoria;
import br.com.zup.casadocodigo.data.domain.Livro;
import br.com.zup.casadocodigo.web.controller.dto.validation.generic.UniqueConstraints;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@UniqueConstraints(entityClass = Livro.class, fieldsEntitiy = {"titulo", "isbn",})
public class LivroFormRequest {

    private Long id = 0L;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String resumo;
    @NotBlank
    private String sumario;
    @NotNull
    @Min(20)
    private BigDecimal valor;
    @NotNull
    @Min(100)
    private Short paginas;
    @NotNull
    private Long isbn;
    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dtPub;
    @NotNull
    private Long autorID;
    @NotNull
    private Long categoriaID;

    @Deprecated
    public LivroFormRequest(){ }

    public LivroFormRequest(Long id, @NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario, @NotNull @Min(20) BigDecimal valor,
                            @NotNull @Min(100) Short paginas, @NotNull Long isbn, @NotNull @FutureOrPresent LocalDate dtPub, @NotNull Long autor,
                            @NotNull Long categoria) {
        this.id = id;
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.valor = valor;
        this.paginas = paginas;
        this.isbn = isbn;
        this.dtPub = dtPub;
        this.autorID = autor;
        this.categoriaID = categoria;
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

    public Long getAutorID() {
        return this.autorID;
    }

    public Long getCategoriaID() {
        return this.categoriaID;
    }


    public Livro map() {
        return new Livro(this.id,
                this.titulo,
                this.resumo,
                this.sumario,
                this.valor,
                this.paginas,
                this.isbn,
                this.dtPub,
                new Autor(this.autorID),
                new Categoria(this.categoriaID));
    }

}