package cs4347.jdbcProject.ecomm.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.List;

import cs4347.jdbcProject.ecomm.entity.Product;
import cs4347.jdbcProject.ecomm.services.ProductPersistenceService;
import cs4347.jdbcProject.ecomm.util.DAOException;
import cs4347.jdbcProject.ecomm.dao.ProductDAO;
import cs4347.jdbcProject.ecomm.dao.impl.ProductDaoImpl;


public class ProductPersistenceServiceImpl implements ProductPersistenceService
{
	private DataSource dataSource;

	public ProductPersistenceServiceImpl(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	/**
	 * The create method must throw a DAOException if the 
	 * given Product has a non-null ID. The create method must 
	 * return the same Product with the ID attribute set to the
	 * value set by the application's auto-increment primary key column. 
	 * @throws DAOException if the given Purchase has a non-null id.
	 */
	
	//Product create(Product product) throws SQLException, DAOException;
	public Product create(Product product) throws SQLException, DAOException{
		
		ProductDAO ProdDAOImpl = new ProductDaoImpl();
		
		Connection connection = dataSource.getConnection();
		
			try{
				connection.setAutoCommit(false);
				Product prod = ProdDAOImpl.create(connection, product);
				connection.commit();
				return prod;
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
	
	/**
	 * The update method must throw DAOException if the provided 
	 * ID is null. 
	 */
	
	//Product retrieve(Long id) throws SQLException, DAOException;
	public Product retrieve(Long id) throws SQLException, DAOException{
		
		ProductDAO ProdDAOImpl = new ProductDaoImpl();
		Connection connection = dataSource.getConnection();
		
		try{
			connection.setAutoCommit(false);
			Product prod = ProdDAOImpl.retrieve(connection, id);
			connection.commit();
			return prod;
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
	
	/**
	 * The update method must throw DAOException if the provided 
	 * Product has a NULL id. 
	 */
	
	//int update(Product product) throws SQLException, DAOException;
	public int update(Product product) throws SQLException, DAOException{
		
		ProductDAO ProdDAOImpl = new ProductDaoImpl();
		Connection connection = dataSource.getConnection();
		try{
			connection.setAutoCommit(false);
			int updateID = ProdDAOImpl.update(connection, product);
			connection.commit();
			return updateID;
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
	
	/**
	 * The update method must throw DAOException if the provided 
	 * ID is null. 
	 */
	
	//int delete(Long id) throws SQLException, DAOException;
	public int delete(Long id) throws SQLException, DAOException{
		
		ProductDAO ProdDAOImpl = new ProductDaoImpl();
		Connection connection = dataSource.getConnection();
		try{
			connection.setAutoCommit(false);
			int deleteID = ProdDAOImpl.delete(connection, id);
			connection.commit();
			return deleteID;
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
	
	/**
	 * Retrieve a product by its unique UPC
	 */
	
	//Product retrieveByUPC(String upc) throws SQLException, DAOException;
	public Product retrieveByUPC(String upc) throws SQLException, DAOException{
		
		ProductDAO ProdDAOImpl = new ProductDaoImpl();
		Connection connection = dataSource.getConnection();
		try{
			connection.setAutoCommit(false);
			Product prod = ProdDAOImpl.retrieveByUPC(connection, upc);
			connection.commit();
			return prod;
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
	
	/**
	 * Retrive products in the given category
	 */
	
	//List<Product> retrieveByCategory(int category) throws SQLException, DAOException;
	public List<Product> retrieveByCategory(int category) throws SQLException, DAOException{
		
		ProductDAO ProdDAOImpl = new ProductDaoImpl();
		Connection connection = dataSource.getConnection();
		try{
			connection.setAutoCommit(false);
			List<Product> listofprod = ProdDAOImpl.retrieveByCategory(connection, category);
			connection.commit();
			return listofprod;
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
	
}
