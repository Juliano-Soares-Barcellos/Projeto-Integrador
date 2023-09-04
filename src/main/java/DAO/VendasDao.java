/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.JPAutil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import model.venda;

/**
 * Classe de acesso a dados para manipulação de informações de vendas.
 */
public class VendasDao {

    /**
     * Cadastra uma nova venda no banco de dados.
     *
     * @param d O objeto de <code>venda</code> a ser cadastrado.
     */
    public void cadastrarVenda(venda d) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Obtém o ID da última venda inserida no banco de dados.
     *
     * @return O ID da última venda inserida ou 0 caso não haja vendas no banco de dados.
     */
    public int obterUltimoIdInserido() {
        EntityManager em = JPAutil.getEntityManager();
        try {
            Query query = em.createNativeQuery("SELECT max(v.id) from venda v");
            Object result = query.getSingleResult();
            if (result != null) {
                return (int) result;
            } else {
                return 0;
            }

        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Retorna a venda correspondente ao ID informado.
     *
     * @param id O ID da venda a ser procurada.
     * @return A venda correspondente ao ID ou null caso não seja encontrada.
     */
    public venda voltarVendaId(int id) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT v FROM venda v WHERE v.id = :id");
            consulta.setParameter("id", id);
            List<venda> resultados = consulta.getResultList();
            if (!resultados.isEmpty()) {
                return resultados.get(0);
            } else {
                return null;
            }
        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Procura todas as vendas realizadas e retorna uma lista de objetos contendo o total gasto em cada venda e o objeto da venda.
     *
     * @return A lista de objetos contendo o total gasto em cada venda e o objeto da venda.
     */
    public List<Object[]> ProcurarVenda() {
        EntityManager em = JPAutil.getEntityManager();

        try {
            Query consulta = em.createQuery("SELECT SUM(av.valor_unidade * av.quantidade), v FROM cliente c INNER JOIN venda v ON c.id = v.cliente.id INNER JOIN acessorio_venda av ON v.id = av.venda.id INNER JOIN acessorios ac ON ac.id = av.acessorios.id GROUP BY c.nome ORDER BY c.nome ASC");

            List<Object[]> result = consulta.getResultList();
            return result;
        } finally {
            JPAutil.closeEtityManager();
        }
    }
}
