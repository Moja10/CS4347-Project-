package cs4347.jdbcProject.ecomm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cs4347.jdbcProject.ecomm.dao.PurchaseDAO;
import cs4347.jdbcProject.ecomm.entity.Purchase;
import cs4347.jdbcProject.ecomm.services.PurchaseSummary;
import cs4347.jdbcProject.ecomm.util.DAOException;

public class PurchaseDaoImpl implements PurchaseDAO
{
	/**
	 * The create method must throw a DAOException if the 
	 * given Purchase has a non-null ID. The create method must 
	 * return the same Purchase with the ID attribute set to the
	 * value set by the application's auto-increment primary key column. 
	 * @throws DAOException if the given Purchase has a non-null id.
	 */
	String insertString "
	Purchase create(Connection connection, Purchase purchase) throws SQLException, DAOException{
	// throw DAOException if the ID is not NULL 
		if (purchase.getId() != null){
			throw new DAOException("Trying to insert Purchase with NON-NULL ID");
		}
		PreparedStatement ps = null;
		try{
			
		}
		finally{
			
		}
	}
	Purchase retrieve(Connection connection, Long id) throws SQLException, DAOException{
	
	/**
	 * The update method must throw DAOException if the provided 
	 * Purchase has a NULL id. 
	 */
	}
	int update(Connection connection, Purchase purchase) throws SQLException, DAOException{
	
	/**
	 * The update method must throw DAOException if the provided 
	 * ID is null. 
	 */
	}
	int delete(Connection connection, Long id) throws SQLException, DAOException{
	
	/**
	 * Retrieve purchases for the given customer id
	 */
	}
	List<Purchase> retrieveForCustomerID(Connection connection, Long customerID) throws SQLException, DAOException{
	
	/**
	 * Retrieve purchases for the given product id
	 */
	}
	List<Purchase> retrieveForProductID(Connection connection, Long productID) throws SQLException, DAOException{
	
	/**
	 * Retrieve purchase summary for the given customer id
	 */
	}
	PurchaseSummary retrievePurchaseSummary(Connection connection, Long customerID) throws SQLException, DAOException{
		
	}
}
}
