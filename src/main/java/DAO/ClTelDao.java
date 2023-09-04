/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.JPAutil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import model.telefone_cliente;

/**
 * Classe de acesso a dados para manipulação de telefone_cliente.
 */
public class ClTelDao {

    /**
     * Gerenciador de entidade (EntityManager) para interagir com o banco de dados.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Procura os telefone_cliente associados aos clientes e retorna uma lista ordenada pelo nome do cliente.
     *
     * @return Lista de telefone_cliente associados aos clientes, ordenada pelo nome do cliente.
     */
    public List<telefone_cliente> procurarTelefoneClientes() {
        try {
            Query consulta = entityManager.createQuery("SELECT t FROM cliente c INNER JOIN telefone_cliente t ON c.id = t.cliente.id ORDER BY c.nome");
            List<telefone_cliente> result = consulta.getResultList();
            return result;
        } finally {
            JPAutil.closeEtityManager();
        }
    }
}
