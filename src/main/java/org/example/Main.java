package org.example;

import org.example.dao.ClienteMysqlDAO;
import org.example.dao.FacturaProductoMysqlDAO;
import org.example.dto.ClienteDTO;
import org.example.dto.RecaudacionDTO;
import org.example.entity.Cliente;
import org.example.factory.AbstractFactory;
import org.example.utils.HelperMySQL;

import java.util.List;

public class Main {
        public static void main(String[] args) throws Exception {
            HelperMySQL dbMySQL = new HelperMySQL();
            dbMySQL.dropTables();
            dbMySQL.createTables();
            dbMySQL.populateDB();
            dbMySQL.closeConnection();

            AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
            System.out.println();
            System.out.println("////////////////////////////////////////////");
            System.out.println();

            ClienteMysqlDAO clienteMysqlDAO = chosenFactory.getClienteMysqlDAO();
            FacturaProductoMysqlDAO facturaProductoMysqlDAO = chosenFactory.getFacturaProductoMysqlDAO();

            System.out.println("////////////////////////////////////////////");
            System.out.println("Lista de lista de clientes, ordenada por a cuál se le facturó más:  ");
            List<Cliente> listadoClientes = clienteMysqlDAO.selectList();
            System.out.println(listadoClientes);

            System.out.println("////////////////////////////////////////////");
            RecaudacionDTO recaudacionDTO = facturaProductoMysqlDAO.getRecaudacion();
            System.out.println(recaudacionDTO);

        }
}