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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;

/**
 *
 * @author julianob
 */
@Entity
public class acessorio_venda {

  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



   
//    private double valorTotal;
//
//    public double getValorTotal() {
//        return valorTotal;
//    }
//
//    public void setValorTotal(double valorTotal) {
//        this.valorTotal = valorTotal;
//    }

    public venda getVenda() {
        return venda;
    }

    public void setVenda(venda venda) {
        this.venda = venda;
    }
    private int quantidade;
   
   @OneToOne
@JoinColumn(name = "venda_id")
private venda venda;

    @ManyToOne
    @JoinColumn(name="acessorios_id")
    private acessorios acessorios;

    public acessorios getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(acessorios acessorios) {
        this.acessorios = acessorios;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public venda getVenda_id() {
        return venda;
    }

    public void setVenda_id(venda venda_id) {
        this.venda = venda_id;
    }

 

    @Override
    public String toString() {
        return "acessorio_venda{" + "id=" + id + ", valor_unidade=" +  ", quantidade=" + quantidade + ", venda_id=" + venda + ", acessorios=" + acessorios + '}';
    }


    


}
