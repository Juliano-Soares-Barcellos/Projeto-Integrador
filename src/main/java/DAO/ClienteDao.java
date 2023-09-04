/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.JPAutil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import model.cliente;

/**
 * Classe de acesso a dados para manipulação de clientes.
 */
public class ClienteDao {

    /**
     * Busca um cliente pelo nome.
     *
     * @param nomeCliente O nome do cliente a ser buscado.
     * @return O cliente encontrado ou null se nenhum cliente com o nome especificado for encontrado.
     */
    public cliente buscarClientePorNome(String nomeCliente) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT c FROM cliente c WHERE c.nome = :nome");
            consulta.setParameter("nome", nomeCliente);
            List<cliente> resultados = consulta.getResultList();
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
     * Cadastra um novo cliente.
     *
     * @param cliente O cliente a ser cadastrado.
     */
    public void CadastrarCliente(cliente cliente) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Calcula o valor total gasto por um cliente com suas compras de acessórios.
     *
     * @param nomeCliente O nome do cliente para calcular o valor total gasto.
     * @return O valor total gasto pelo cliente com suas compras de acessórios.
     */
    public Double calcularValorTotal(String nomeCliente) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT SUM(a.valor * av.quantidade) AS totalGasto FROM cliente c INNER JOIN venda v ON c.id = v.cliente.id INNER JOIN acessorio_venda av ON v.id = av.venda.id INNER JOIN acessorios a ON a.id = av.acessorios.id WHERE c.nome = :nomeCliente");
            consulta.setParameter("nomeCliente", nomeCliente);

            Double totalGasto = (Double) consulta.getSingleResult();
            if (totalGasto != null) {
                return totalGasto;
            } else {
                return 0.0;
            }
        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Procura as compras de um cliente e retorna uma lista com os nomes dos clientes e o valor total gasto em suas compras.
     *
     * @param nome O nome do cliente a ser buscado.
     * @return Lista de arrays de objetos contendo o nome do cliente e o valor total gasto em suas compras.
     */
    public List<Object[]> procurarCompra(String nome) {
        EntityManager em = JPAutil.getEntityManager();

        try {
            Query consulta = em.createQuery("SELECT f.nome, SUM(a.valor * av.quantidade) FROM acessorio_venda av INNER JOIN av.acessorios a INNER JOIN av.venda v INNER JOIN v.cliente f WHERE f.nome = :nome GROUP BY f.nome");
            consulta.setParameter("nome", nome);
            List<Object[]> result = consulta.getResultList();
            return result;
        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Procura os clientes que fizeram compras com desconto e retorna uma lista com os nomes dos clientes e o valor total gasto em suas compras.
     *
     * @return Lista de arrays de objetos contendo o nome do cliente e o valor total gasto em suas compras com desconto.
     */
    public List<Object[]> procurarClientesComDesconto() {
        EntityManager em = JPAutil.getEntityManager();

        try {
            Query consulta = em.createQuery("SELECT f.nome, SUM(a.valor * av.quantidade) FROM acessorio_venda av INNER JOIN av.acessorios a INNER JOIN av.venda v INNER JOIN v.cliente f GROUP BY f.nome HAVING SUM(a.valor * av.quantidade) >= 300");
            List<Object[]> result = consulta.getResultList();
            return result;
        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Procura os clientes que fizeram compras sem desconto e retorna uma lista com os nomes dos clientes e o valor total gasto em suas compras.
     *
     * @return Lista de arrays de objetos contendo o nome do cliente e o valor total gasto em suas compras sem desconto.
     */
    public List<Object[]> procurarClientesSemDesconto() {
        EntityManager em = JPAutil.getEntityManager();

        try {
            Query consulta = em.createQuery("SELECT f.nome, SUM(a.valor * av.quantidade) FROM acessorio_venda av INNER JOIN av.acessorios a INNER JOIN av.venda v INNER JOIN v.cliente f GROUP BY f.nome HAVING SUM(a.valor * av.quantidade) <= 299");
            List<Object[]> result = consulta.getResultList();
            return result;
        } finally {
            JPAutil.closeEtityManager();
        }
    }

 
}
