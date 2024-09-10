package org.example.factory;


import org.example.dao.ClienteMysqlDAO;
import org.example.dao.FacturaProductoMysqlDAO;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2; // no esta implementado

    public abstract ClienteMysqlDAO getClienteMysqlDAO();
    public abstract FacturaProductoMysqlDAO getFacturaProductoMysqlDAO();
    public abstract FacturaProductoMysqlDAO getRecaudacion();

    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : {
                return MySQLDAOFactory.getInstance();
            }
            case DERBY_JDBC: return null;
            default: return null;
        }
    }

}
