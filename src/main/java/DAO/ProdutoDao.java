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
import model.acessorios;

/**
 * Classe de acesso a dados para manipulação de informações de produtos.
 */
public class ProdutoDao {

    /**
     * Carrega a lista de acessórios na tabela de produtos.
     *
     * @return A lista de objetos de <code>acessorios</code>.
     */
    public List<acessorios> CarregarListaNaTabela() {
        EntityManager em = JPAutil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT a FROM acessorios a", acessorios.class);
            List<acessorios> lista = consulta.getResultList();
            return lista;
        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Busca um produto pelo seu ID.
     *
     * @param idProduto O ID do produto a ser buscado.
     * @return O objeto de <code>acessorios</code> correspondente ao ID, ou null se não encontrado.
     */
    public acessorios buscarProdutoPorId(int idProduto) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT a FROM acessorios a WHERE a.id = :idProduto");
            consulta.setParameter("idProduto", idProduto);
            List<acessorios> resultados = consulta.getResultList();
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
     * Cadastra um novo acessório de venda no banco de dados.
     *
     * @param d O objeto de <code>acessorio_venda</code> a ser cadastrado.
     */
    public void cadastrarAcessorio_venda(acessorio_venda d) {
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
}
