package cs4347.jdbcProject.ecomm.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import cs4347.jdbcProject.ecomm.dao.PurchaseDAO;
import cs4347.jdbcProject.ecomm.dao.impl.PurchaseDaoImpl;
import cs4347.jdbcProject.ecomm.entity.Purchase;
import cs4347.jdbcProject.ecomm.services.PurchasePersistenceService;
import cs4347.jdbcProject.ecomm.services.PurchaseSummary;
import cs4347.jdbcProject.ecomm.util.DAOException;

public class PurchasePersistenceServiceImpl implements PurchasePersistenceService
{
	private DataSource dataSource;

	public PurchasePersistenceServiceImpl(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	public Purchase create(Purchase purchase) throws SQLException, DAOException{
		PurchaseDAO PurchDAOImpl = new PurchaseDaoImpl();
		Connection connection = dataSource.getConnection();
		try{
			connection.setAutoCommit(false);
			Purchase purch = PurchDAOImpl.create(connection, purchase);
			connection.commit();
			return purch;
		}
		catch (Exception ex){
			// Rollback will set Autocommit back to true
			connection.rollback();
			throw ex;
		}
		finally{
			// Autocommit is set back to true in the finally block
			if (connection != null && !connection.isClosed()) {
				connection.setAutoCommit(true);
				connection.close();
			}
		}
	}
		
	public Purchase retrieve(Long id) throws SQLException, DAOException{
		
	}
	
	public int update(Purchase purchase) throws SQLException, DAOException{
		
	}
	
	public int delete(Long id) throws SQLException, DAOException{
		
	}
	
	public List<Purchase> retrieveForCustomerID(Long customerID) throws SQLException, DAOException{
		
	}
	
	public PurchaseSummary retrievePurchaseSummary(Long customerID) throws SQLException, DAOException{
		
	}
	
	public List<Purchase> retrieveForProductID(Long productID) throws SQLException, DAOException{
		
	}
}
