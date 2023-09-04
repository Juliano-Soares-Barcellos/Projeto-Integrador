/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.JPAutil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import model.login_senha;

/**
 * Classe de acesso a dados para manipulação de dados relacionados a login e senha de usuários.
 */
public class LoginDao {

    /**
     * Valida o usuário com o login e senha fornecidos.
     *
     * @param login O login do usuário.
     * @return Lista contendo objetos do tipo <code>Object[]</code> com informações do funcionário e sua respectiva senha.
     */
    public List<Object[]> validarUsuarioSeguro(login_senha login) {
        EntityManager em = JPAutil.getEntityManager();

        try {
            Query consulta = em.createQuery("SELECT f, ls FROM funcionario f INNER JOIN f.login_senha ls INNER JOIN f.cargo c WHERE ls.login = :Login AND ls.senha = :senha");
            consulta.setParameter("Login", login.getLogin());
            consulta.setParameter("senha", login.getSenha());
            List<Object[]> resultados = consulta.getResultList();
            return resultados;
        } finally {
            JPAutil.closeEtityManager();
        }

    }

    /**
     * Cadastra um novo usuário no banco de dados.
     *
     * @param u O objeto de <code>login_senha</code> a ser cadastrado.
     */
    public void CadastrarLogin(login_senha u) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Obtém o último ID inserido na tabela de login_senha.
     *
     * @return O último ID inserido na tabela de login_senha, ou 0 se não houver nenhum registro.
     */
    public int obterUltimoIdInserido() {
        EntityManager em = JPAutil.getEntityManager();
        try {
            Query query = em.createNativeQuery("SELECT max(v.id) from login_senha v");
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
     * Retorna um objeto <code>login_senha</code> com base no ID fornecido.
     *
     * @param id O ID do login_senha a ser retornado.
     * @return O objeto de <code>login_senha</code> encontrado ou <code>null</code> se não existir nenhum com o ID fornecido.
     */
    public login_senha voltarVendaId(int id) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT v FROM login_senha v WHERE v.id = :id");
            consulta.setParameter("id", id);
            List<login_senha> resultados = consulta.getResultList();
            if (!resultados.isEmpty()) {
                return resultados.get(0);
            } else {
                return null;
            }
        } finally {
            JPAutil.closeEtityManager();
        }
    }
}
