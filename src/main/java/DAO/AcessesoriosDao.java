/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.JPAutil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import model.acessorio_venda;


/**
 *
 * @author julianob
 */
public class AcessesoriosDao {

    public List<acessorio_venda> ProcurarProdutor() {
        EntityManager em = JPAutil.getEntityManager();
        Object[] s;
        try {
            Query consulta = em.createQuery("SELECT a.nome,a.descricao,a.cor,a.valor FROM acessorios a  ");
            List<acessorio_venda> result = consulta.getResultList();
            return result;
        } finally {
            JPAutil.closeEtityManager();
        }
    }
}
