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

/**
 *
 * @author julianob
 */
@Entity
public class venda {

    @Id
    private int id;
    private LocalDate data_venda;
    
    @OneToOne(mappedBy = "venda")
     private acessorio_venda Acessorio_venda;

    public acessorio_venda getAcessorio_venda() {
        return Acessorio_venda;
    }

    public void setAcessorio_venda(acessorio_venda Acessorio_venda) {
        this.Acessorio_venda = Acessorio_venda;
    }
    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private cartao cartao;
    
    @ManyToOne
    @JoinColumn(name = "pix_id")
    private pix pix;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private funcionario funcionario;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public LocalDate getData_venda() {
        return data_venda;
    }

    public void setData_venda(LocalDate data_venda) {
        this.data_venda = data_venda;
    }




    public cartao getCartao() {
        return cartao;
    }

    public void setCartao(cartao cartao) {
        this.cartao = cartao;
    }

    public pix getPix() {
        return pix;
    }

    public void setPix(pix pix) {
        this.pix = pix;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }

    public funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(funcionario funcionario) {
        this.funcionario = funcionario;
    }
   

}
