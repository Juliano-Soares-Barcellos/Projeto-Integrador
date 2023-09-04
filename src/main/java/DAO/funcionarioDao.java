/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.JPAutil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import javax.swing.JOptionPane;
import model.funcionario;

/**
 * Classe de acesso a dados para manipulação de informações de funcionários.
 */
public class funcionarioDao {

    /**
     * Obtém o ID do último funcionário inserido no banco de dados.
     *
     * @return O ID do último funcionário inserido ou 0 caso não haja funcionários no banco de dados.
     */
    public int obterUltimoIdInserido() {
        EntityManager em = JPAutil.getEntityManager();
        try {
            Query query = em.createNativeQuery("SELECT max(v.id) from funcionario v");
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
     * Retorna o funcionário correspondente ao ID informado.
     *
     * @param id O ID do funcionário a ser procurado.
     * @return O funcionário correspondente ao ID ou null caso não seja encontrado.
     */
    public funcionario voltarVendaId(int id) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT v FROM funcionario v WHERE v.id = :id");
            consulta.setParameter("id", id);
            List<funcionario> resultados = consulta.getResultList();
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
     * Procura as vendas dos funcionários.
     *
     * @return Uma lista de objetos contendo o nome do cargo, nome do funcionário, salário e total de vendas.
     */
    public List<Object[]> procurarVendaDosFuncionarios() {
        EntityManager em = JPAutil.getEntityManager();

        try {
            Query consulta = em.createQuery("SELECT c.nome, f.nome, c.salario, sum(a.valor * av.quantidade) FROM acessorio_venda av INNER JOIN av.acessorios a INNER JOIN av.venda v INNER JOIN v.funcionario f INNER JOIN f.cargo c GROUP BY f.nome");

            List<Object[]> result = consulta.getResultList();
            return result;
        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Procura os funcionários ativos.
     *
     * @return Uma lista de nomes dos funcionários ativos.
     */
    public List<String> procurarFuncionariosAtivos() {
        EntityManager em = JPAutil.getEntityManager();

        try {
            Query consulta = em.createQuery("SELECT f.nome FROM funcionario f WHERE f.status='S'");

            List<String> result = consulta.getResultList();
            return result;
        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Tira o acesso de um funcionário.
     *
     * @param nome O nome do funcionário a ter o acesso tirado.
     */
    public void tirarAcessoFuncionario(String nome) {
        EntityManager em = JPAutil.getEntityManager();
        em.getTransaction().begin();

        try {
            TypedQuery<funcionario> query = em.createQuery("SELECT f FROM funcionario f WHERE f.nome = :nome", funcionario.class);
            query.setParameter("nome", nome);

            List<funcionario> funcionarios = query.getResultList();

            if (!funcionarios.isEmpty()) {
                funcionario funcionario = funcionarios.get(0);
                funcionario.setStatus('N');
            } else {
                JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            // Lidar com a exceção apropriadamente
            e.printStackTrace();
        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Procura as vendas dos funcionários pelo nome.
     *
     * @param nome O nome do funcionário a ser procurado.
     * @return Uma lista de objetos contendo o nome do cargo, nome do funcionário, salário e total de vendas do funcionário especificado.
     */
    public List<Object[]> procurarVendaDosFuncionariosPorNome(String nome) {
        EntityManager em = JPAutil.getEntityManager();

        try {
            Query consulta = em.createQuery("SELECT c.nome, f.nome, c.salario, sum(a.valor * av.quantidade) FROM acessorio_venda av INNER JOIN av.acessorios a INNER JOIN av.venda v INNER JOIN v.funcionario f INNER JOIN f.cargo c WHERE f.nome = :nome GROUP BY f.nome");
            consulta.setParameter("nome", nome);
            List<Object[]> result = consulta.getResultList();
            return result;
        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Concede acesso a um funcionário.
     *
     * @param nome O nome do funcionário a ter o acesso concedido.
     */
    public void ConsederAcessoFuncionario(String nome) {
        EntityManager em = JPAutil.getEntityManager();
        em.getTransaction().begin();

        try {
            TypedQuery<funcionario> query = em.createQuery("SELECT f FROM funcionario f WHERE f.nome = :nome", funcionario.class);
            query.setParameter("nome", nome);

            List<funcionario> funcionarios = query.getResultList();

            if (!funcionarios.isEmpty()) {
                funcionario funcionario = funcionarios.get(0);
                funcionario.setStatus('S');
            } else {
                // Lidar com o funcionário não encontrado, se necessário
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            JPAutil.closeEtityManager();
        }
    }

    /**
     * Cadastra um novo funcionário no banco de dados.
     *
     * @param u O objeto de funcionário a ser cadastrado.
     */
    public void CadastrarFuncionario(funcionario u) {
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
}
