package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Classe que representa um cliente.
 * Esta classe é uma entidade JPA e pode ser mapeada para uma tabela em um banco de dados.
 */
@Entity
public class cliente {

    /**
     * Identificador único do cliente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Nome do cliente.
     */
    private String nome;

    /**
     * CPF do cliente.
     */
    private String cpf;

    /**
     * Cidade do cliente.
     */
    private String cidade;

    /**
     * Estado do cliente.
     */
    private String estado;

    /**
     * Data de nascimento do cliente.
     */
    private String nascimento;

    /**
     * País do cliente.
     */
    private String pais;

    /**
     * Obtém o identificador único do cliente.
     *
     * @return O identificador único do cliente.
     */
    public long getId() {
        return id;
    }

    /**
     * Define o identificador único do cliente.
     *
     * @param id O identificador único do cliente a ser definido.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome O nome do cliente a ser definido.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o CPF do cliente.
     *
     * @return O CPF do cliente.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do cliente.
     *
     * @param cpf O CPF do cliente a ser definido.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Obtém a cidade do cliente.
     *
     * @return A cidade do cliente.
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Define a cidade do cliente.
     *
     * @param cidade A cidade do cliente a ser definida.
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Obtém o estado do cliente.
     *
     * @return O estado do cliente.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define o estado do cliente.
     *
     * @param estado O estado do cliente a ser definido.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtém a data de nascimento do cliente.
     *
     * @return A data de nascimento do cliente.
     */
    public String getNascimento() {
        return nascimento;
    }

    /**
     * Define a data de nascimento do cliente.
     *
     * @param nascimento A data de nascimento do cliente a ser definida.
     */
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    /**
     * Obtém o país do cliente.
     *
     * @return O país do cliente.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Define o país do cliente.
     *
     * @param pais O país do cliente a ser definido.
     */
    public void setPais(String pais) {
        this.pais = pais;
    }
}
