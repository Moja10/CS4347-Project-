package cs4347.jdbcProject.ecomm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cs4347.jdbcProject.ecomm.dao.PurchaseDAO;
import cs4347.jdbcProject.ecomm.entity.Purchase;
import cs4347.jdbcProject.ecomm.services.PurchaseSummary;
import cs4347.jdbcProject.ecomm.util.DAOException;
import cs4347.sakilaEntities.Customer;

public class PurchaseDaoImpl implements PurchaseDAO
{
	/**
	 * The create method must throw a DAOException if the 
	 * given Purchase has a non-null ID. The create method must 
	 * return the same Purchase with the ID attribute set to the
	 * value set by the application's auto-increment primary key column. 
	 * @throws DAOException if the given Purchase has a non-null id.
	 */
	
	
	public Purchase create(Connection connection, Purchase purchase) throws SQLException, DAOException
	{
		final String insertSQL = 
				"INSERT INTO PURCHASE (customerID, productID, purchaseDate, purchaseAmount)"
				+ "VALUES (?,?,?,?)";  
		// throw DAOException if the ID is not NULL 
		if (purchase.getId() != null){
			throw new DAOException("Trying to insert Purchase with a NON-NULL ID");
		}
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, purchase.getCustomerID());
			ps.setLong(2, purchase.getProductID());
			ps.setDate(3, purchase.getPurchaseDate());
			ps.setDouble(4, purchase.getPurchaseAmount());

			int res = ps.executeUpdate();
			if(res != 1) {
				throw new DAOException("Create Did Not Update Expected Number Of Rows");
			}
			
			ResultSet keyRS = ps.getGeneratedKeys();
			keyRS.next();
			int lastKey = keyRS.getInt(1);
			purchase.setId((long)lastKey);
			return purchase;
		}
		finally{
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
	}
	public Purchase retrieve(Connection connection, Long id) throws SQLException, DAOException{
		final String selectSQL =
				"SELECT id, productID, customerID, purchaseDate, purchaseAmount "
				+"FROM purchase WHERE ID = ?";
		if (id == null){
				throw new DAOException("Trying to retrieve Purchase with a NULL ID");
		}
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(selectSQL);
			ps.setLong(1, id);  //Set positional parameter #1 in String selectSQL to var id
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				return null;
			}
			// Create a new Purchase object
			Purchase purch = new Purchase();
			// Fill Purchase object with values from ResultSet
			purch.setId(rs.getLong("ID"));
			purch.setProductID(rs.getLong("productID"));
			purch.setCustomerID(rs.getLong("customerID"));
			purch.setPurchaseAmount(rs.getDouble("purchaseAmount"));
			purch.setPurchaseDate(rs.getDate("purchaseDate"));
			return purch;
		}
		finally{
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
	}
	public int update(Connection connection, Purchase purchase) throws SQLException, DAOException{
		final String updateSQL = 
				"UPDATE purchase SET productID = ?, customerID = ?, purchaseAmount = ?, purchaseDate = ?"
				+ "WHERE id = ?";
		if (purchase.getId() == null) {
			throw new DAOException("Trying to update Purchase with a NULL ID");
		}
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(updateSQL);
			ps.setLong(1, purchase.getProductID());
			ps.setLong(2, purchase.getCustomerID());
			ps.setDouble(3, purchase.getPurchaseAmount());
			ps.setDate(4, purchase.getPurchaseDate());
			int rows = ps.executeUpdate();
			return rows;			
		}
		finally{
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
	}
	public int delete(Connection connection, Long id) throws SQLException, DAOException{
		final String deleteSQL = 
				"DELETE FROM purchase WHERE id = ?";
		if(id == null){
			throw new DAOException("Trying to delete from Purchase with a NULL ID");
		}
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(deleteSQL);
			ps.setLong(1, id);
			
			int rows = ps.executeUpdate();
			return rows;
		}
		finally{
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
	}
	public List<Purchase> retrieveForCustomerID(Connection connection, Long customerID) throws SQLException, DAOException{
		final String querySQL = 
				"SELECT id, productID, customerID, purchaseAmount, purchaseDate "
				+"FROM purchase "
				+"WHERE customerID = ?";
		if((customerID < 0)){
			throw new DAOException("Invalid customer ID provided");
		}
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(querySQL);
			ps.setLong(1, customerID);  //Set positional parameter #1 in String selectSQL to var id
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				return null;
			}
			// Create a new list to store the results in
			List<Purchase> result = new ArrayList<Purchase>();
			while(rs.next()){
				// Create a new Purchase object
				Purchase purch = new Purchase();
				// Fill Purchase object with values from ResultSet
				purch.setId(rs.getLong("ID"));
				purch.setProductID(rs.getLong("productID"));
				purch.setCustomerID(rs.getLong("customerID"));
				purch.setPurchaseAmount(rs.getDouble("purchaseAmount"));
				purch.setPurchaseDate(rs.getDate("purchaseDate"));
				// Add purch object to result arraylist
				result.add(purch);
			}
			return result;
		}
		finally{
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
	}
	public List<Purchase> retrieveForProductID(Connection connection, Long productID) throws SQLException, DAOException{
		final String querySQL = 
				"SELECT id, productID, customerID, purchaseAmount, purchaseDate "
				+"FROM purchase "
				+"WHERE productID = ?";
		if((productID < 0)){
			throw new DAOException("Invalid product ID provided");
		}
	}
	public PurchaseSummary retrievePurchaseSummary(Connection connection, Long customerID) throws SQLException, DAOException{
		
	}
}
