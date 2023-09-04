/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.JPAutil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import model.cargo;

/**
 * Classe de acesso a dados para manipulação de informações de cargos.
 */
public class cargoDao {

    /**
     * Obtém o cargo correspondente ao nome informado.
     *
     * @param nome O nome do cargo a ser procurado.
     * @return O cargo correspondente ao nome ou null caso não seja encontrado.
     */
    public cargo pegarCargo(String nome) {
        EntityManager em = JPAutil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT v FROM cargo v WHERE v.nome = :nome");
            consulta.setParameter("nome", nome);
            List<cargo> resultados = consulta.getResultList();
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
     * Obtém o ID do último cargo inserido no banco de dados.
     *
     * @return O ID do último cargo inserido ou 0 caso não haja cargos no banco de dados.
     */
    public int obterUltimoIdInserido() {
        EntityManager em = JPAutil.getEntityManager();
        try {
            Query query = em.createNativeQuery("SELECT max(v.id) from cargo v");
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
}
