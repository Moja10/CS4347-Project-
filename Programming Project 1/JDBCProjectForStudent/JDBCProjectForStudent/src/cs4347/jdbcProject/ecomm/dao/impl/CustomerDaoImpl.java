package cs4347.jdbcProject.ecomm.dao.impl;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cs4347.jdbcProject.ecomm.entity.Customer;
import cs4347.jdbcProject.ecomm.util.DAOException;
import cs4347.jdbcProject.ecomm.dao.CustomerDAO;



public class CustomerDaoImpl implements CustomerDAO
{
	
	public Customer create(Connection connection, Customer customer) throws SQLException, DAOException
	{
		final String insertSQL = "INSERT INTO CUSTOMER(id, firstName, lastName, gender, dob, email) VALUES (?, ?, ?, ?, ?, ?);";  
		// throw DAOException if the ID is NULL 
		if (customer.getId() != null)
		{
			throw new DAOException("Trying to insert customer with a NULL ID");
		}
		PreparedStatement ps = null;
		try
		{
			ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, 0); //set ID to 0 before getting auto-increment ID
			ps.setString(2, customer.getFirstName());
			ps.setString(3, customer.getLastName());
			ps.setString(4, Character.toString(customer.getGender()));
			ps.setDate(5, customer.getDob());
			ps.setString(6, customer.getEmail());
			ps.executeUpdate();
			
			//Copy the SQL generated auto-increment key to the customer ID
			ResultSet keyRS = ps.getGeneratedKeys();
			//set ID to the correct ID
			if(keyRS.next())
			{
				int lastKey = keyRS.getInt(1);
				customer.setId((long) lastKey);
			}
			else
			{
				throw new SQLException("Could not generate auto-increment key");
			}
			return customer;
		}
		finally
		{
			if (ps != null && !ps.isClosed())
			{
				ps.close();
			}
		}
	}
	
	public Customer retrieve(Connection connection, Long id) throws SQLException, DAOException
	{
		
		final String selectSQL = "SELECT id, firstName, lastname, gender, dob, email FROM customer where id = ?";
		// throw DAOException if the ID is NULL 
		if (id == null){
				throw new DAOException("Trying to retrieve Customer with a NULL ID");
		}
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(selectSQL);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				return null;
			}
			//create a Customer to return for CustomerPersistenceImpl use
			Customer cust = new Customer();
			cust.setId(rs.getLong("id"));
			cust.setFirstName(rs.getString("firstname"));
			cust.setLastName(rs.getString("lastname"));
			cust.setGender(rs.getString("gender").charAt(0));
			cust.setDob(rs.getDate("dob"));
			cust.setEmail(rs.getString("email"));
			
			return cust;
			
			
		}
		finally{
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
	}
	
	public int update(Connection connection, Customer customer) throws SQLException, DAOException
	{
		final String updateSQL = "UPDATE customer SET firstName = ?, lastname = ?, gender = ?, dob = ?, email = ? WHERE id = ?;";
		Long id = customer.getId();
		if (id == null) {
			throw new DAOException("Trying to update Customer with NULL ID");
		}

		
		PreparedStatement ps = null;
		//set the Customer fields of Customer with ID id to the updated values
		ps = connection.prepareStatement(updateSQL);
		ps.setString(1, customer.getFirstName());
		ps.setString(2, customer.getLastName());
		ps.setString(3, String.valueOf(customer.getGender()));
		ps.setDate(4, customer.getDob());
		ps.setString(5, customer.getEmail());
		ps.setLong(6, id);

		int rows = ps.executeUpdate();
		//After the the updateSQL query is executed with the udpate values, connection.prepareStatement returns # of rows affected, return this value for CustomerPersistenceService usage
		return rows;
		
	}
	
	public int delete(Connection connection, Long id) throws SQLException, DAOException
	{
		
		final String deleteSQL =  "DELETE FROM CUSTOMER WHERE ID = ?;";
		
		if (id == null) {
			throw new DAOException("Trying to delete Customer with NULL ID");
		}

		
		PreparedStatement ps = null;
		//delete Customer with ID id
		ps = connection.prepareStatement(deleteSQL);
		ps.setLong(1, id);
		//Should return 1 row of deletion
		int rows = ps.executeUpdate();
		return rows;
	}
	
	public List<Customer> retrieveByZipCode(Connection connection, String zipCode) throws SQLException, DAOException
	{
		final String zipQuery = "SELECT id, firstName, lastName, gender, dob, email FROM customer INNER JOIN address ON customer.ID=address.customer_ID where address.zipcode = ?";
		
		if (zipCode == null) {
			throw new DAOException("Trying to retrieve Customer with NULL zipCode");
		}

		List<Customer> result = new ArrayList<Customer>();
		
		PreparedStatement ps = null;
		
			ps = connection.prepareStatement(zipQuery);
			ps.setString(1, zipCode);
			ResultSet rs = ps.executeQuery();
			//The result set can consist of multiple rows; for each row
			//create a Customer instance and add it to the resultSet list, 
			//which is used by the CustomerPersistenceService
			while(rs.next()) {
			
				Customer cust = new Customer();
				cust.setId(rs.getLong("id"));
				cust.setFirstName(rs.getString("firstname"));
				cust.setLastName(rs.getString("lastname"));
				cust.setGender(rs.getString("gender").charAt(0));
				cust.setDob(rs.getDate("dob"));
				cust.setEmail(rs.getString("email"));
				result.add(cust);
			}
			return result;
	}
	
	public List<Customer> retrieveByDOB(Connection connection, Date startDate, Date endDate) throws SQLException, DAOException
	{
		final String dobQuery = "SELECT id, firstName, lastName, gender, dob, email FROM customer WHERE dob between ? and ?";
		
		if (startDate == null || endDate == null) {
			throw new DAOException("Trying to retrieve Customer with NULL dob");
		}

		List<Customer> result = new ArrayList<Customer>();
	
		PreparedStatement ps = null;
		
			ps = connection.prepareStatement(dobQuery);
			//1(startDate) BETWEEN 2 (endDate)
			ps.setDate(1, startDate);
			ps.setDate(2, endDate);
			ResultSet rs = ps.executeQuery();
			//while there are still rows in the result set of BETWEEN the two dates, create a Customer instance 
			//and set its attributes to the values of the fields in the row, and add
			//The customer instance to the resultSet list of customers used by the CustomerPersistenceService
			while(rs.next()) {
			
				Customer cust = new Customer();
				cust.setId(rs.getLong("id"));
				cust.setFirstName(rs.getString("firstname"));
				cust.setLastName(rs.getString("lastname"));
				cust.setGender(rs.getString("gender").charAt(0));
				cust.setDob(rs.getDate("dob"));
				cust.setEmail(rs.getString("email"));
				result.add(cust);
			}
			//CustomerPersistenceService uses this
			return result;
	}

	
}
