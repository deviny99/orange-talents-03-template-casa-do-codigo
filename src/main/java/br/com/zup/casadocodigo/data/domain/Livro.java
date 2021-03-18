package br.com.zup.casadocodigo.data.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "livros")
public class Livro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "titulo", nullable = false,  unique = true)
    private String titulo;
    @NotBlank
    @Column(name = "resumo", nullable = false,  length = 500)
    private String resumo;
    @NotBlank
    @Lob
    @Column(name = "sumario", columnDefinition="CLOB")
    private String sumario;
    @NotNull
    @Min(20)
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;
    @NotNull
    @Min(100)
    @Column(name = "paginas", nullable = false)
    private Short paginas;
    @NotNull
    @Column(name = "isbn", nullable = false, unique = true)
    private Long isbn;
    @NotNull
    @FutureOrPresent
    @Column(name = "dtPub", nullable = false)
    private LocalDate dtPub;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Autor autor;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    @Deprecated
    public Livro(){ }

    public Livro(Long id, @NotBlank String titulo, @NotBlank String resumo, @NotBlank String sumario, @NotNull @Min(20) BigDecimal valor,
                 @NotNull @Min(100) Short paginas, @NotNull Long isbn, @NotNull @FutureOrPresent LocalDate dtPub,
                 @NotNull Autor autor, @NotNull Categoria categoria) {

        this.id = id;
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.valor = valor;
        this.paginas = paginas;
        this.isbn = isbn;
        this.dtPub = dtPub;
        this.autor = autor;
        this.categoria = categoria;
    }

    public Livro(@NotBlank String titulo, @NotBlank String resumo, @NotBlank String sumario, @NotNull @Min(20) BigDecimal valor,
                 @NotNull @Min(100) Short paginas, @NotNull Long isbn, @NotNull @FutureOrPresent LocalDate dtPub, @NotNull Autor autor,
                 @NotNull Categoria categoria) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.valor = valor;
        this.paginas = paginas;
        this.isbn = isbn;
        this.dtPub = dtPub;
        this.autor = autor;
        this.categoria = categoria;
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

    public Autor getAutor() {
        return autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id) && Objects.equals(titulo, livro.titulo) && Objects.equals(isbn, livro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, isbn);
    }
}
