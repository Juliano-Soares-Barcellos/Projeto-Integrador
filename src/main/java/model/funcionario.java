/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author julianob
 */
@Entity
public class funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private char status;

    public char getStatus() {
        return status;
    }

    public void setStatus(char Status) {
        this.status = Status;
    }
    private String nome;
    private String cpf;
    private String cep;
    private LocalDate data_nascimento;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private cargo cargo;

    @OneToOne
    @JoinColumn(name = "login_senhaId")
    private login_senha login_senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }



    public cargo getCargo() {
        return cargo;
    }

    public void setCargo(cargo cargo) {
        this.cargo = cargo;
    }

    public login_senha getLogin_senha() {
        return login_senha;
    }

    public void setLogin_senha(login_senha login_senha) {
        this.login_senha = login_senha;
    }

}
