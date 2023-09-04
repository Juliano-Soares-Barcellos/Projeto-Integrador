/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.JPAutil;
import jakarta.persistence.EntityManager;
import model.telefone_funcionarios;

/**
 * Classe de acesso a dados para manipulação de informações de telefones de funcionários.
 */
public class TelefoneDao {

    /**
     * Cadastra um novo telefone de funcionário no banco de dados.
     *
     * @param u O objeto de <code>telefone_funcionarios</code> a ser cadastrado.
     */
    public void CadastrarTelefoneFuncionario(telefone_funcionarios u) {
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
