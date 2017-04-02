package cs4347.jdbcProject.ecomm.services.impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

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
	Purchase create(Purchase purchase) throws SQLException, DAOException;
	// Throw a DAOException if the ID is not NULL
	
	Purchase retrieve(Long id) throws SQLException, DAOException;
	/**
	 * The update method must throw DAOException if the provided 
	 * Purchase has a NULL id. 
	 */
	int update(Purchase purchase) throws SQLException, DAOException;
	/**
	 * The update method must throw DAOException if the provided 
	 * ID is null. 
	 */
	int delete(Long id) throws SQLException, DAOException;
	/**
	 * Retrieve purchases made by the given customer.
	 */
	List<Purchase> retrieveForCustomerID(Long customerID) throws SQLException, DAOException;
	/**
	 * Produce a purchase summary report for the given customer.
	 */
	PurchaseSummary retrievePurchaseSummary(Long customerID) throws SQLException, DAOException;
	/**
	 * Retrieve purchases made for the given product.
	 */
	List<Purchase> retrieveForProductID(Long productID) throws SQLException, DAOException;
}
