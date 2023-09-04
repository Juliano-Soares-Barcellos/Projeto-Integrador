/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.JPAutil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

/**
 * Classe de acesso a dados para manipulação de dados relacionados a funcionários e combos.
 */
public class ComboDao {

    /**
     * Lista os nomes dos funcionários ativos.
     *
     * @return Lista contendo os nomes dos funcionários ativos.
     */
    public List<String> listarFuncionario() {
        EntityManager em = JPAutil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT nome FROM funcionario WHERE status = 'S'");
            List<String> funcionarios = consulta.getResultList();
            return funcionarios;
        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Obtém o ID de um funcionário pelo nome.
     *
     * @param nomeFuncionario O nome do funcionário para o qual deseja obter o ID.
     * @return O ID do funcionário encontrado ou -1 se o funcionário não for encontrado.
     */
    public int obterIdFuncionario(String nomeFuncionario) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT f.id FROM funcionario f WHERE f.nome = :nome");
            consulta.setParameter("nome", nomeFuncionario);
            Integer id = (Integer) consulta.getSingleResult();
            return id != null ? id : -1; // Retorna -1 se não encontrar o ID
        } finally {
            JPAutil.closeEtityManager();
        }
    }
}
