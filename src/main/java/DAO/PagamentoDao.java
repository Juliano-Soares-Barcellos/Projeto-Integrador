/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.JPAutil;
import jakarta.persistence.EntityManager;
import model.cartao;
import model.pix;

/**
 * Classe de acesso a dados para manipulação de informações de pagamentos.
 */
public class PagamentoDao {

    /**
     * Cadastra um novo pagamento do tipo Pix no banco de dados.
     *
     * @param pix O objeto de <code>pix</code> a ser cadastrado.
     */
    public void cadastrar(pix d) {
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
     * Cadastra um novo pagamento do tipo Cartão no banco de dados.
     *
     * @param cartao O objeto de <code>cartao</code> a ser cadastrado.
     */
    public void cadastrarCartao(cartao cartao) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cartao);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAutil.closeEtityManager();
        }
    }
}
