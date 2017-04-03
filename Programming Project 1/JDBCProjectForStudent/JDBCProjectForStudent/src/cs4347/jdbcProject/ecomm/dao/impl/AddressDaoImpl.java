package cs4347.jdbcProject.ecomm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cs4347.jdbcProject.ecomm.dao.AddressDAO;
import cs4347.jdbcProject.ecomm.entity.Address;
import cs4347.jdbcProject.ecomm.util.DAOException;

public class AddressDaoImpl implements AddressDAO
{
		public Address create(Connection connection, Address address, Long customerID) throws SQLException, DAOException
		{
			final String insertSQL = "INSERT INTO address (address1, address2, city, state, zipcode, customer_ID) VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = null;
			//CustomerPersistenceService should call AddressPersistenceService to call this create method
			//AddressPersistenceService provides all the field values in the instantiated form 'address'
			ps = connection.prepareStatement(insertSQL);
			ps.setString(1, address.getAddress1());
			ps.setString(2, address.getAddress2());
			ps.setString(3, address.getCity());
			ps.setString(4, address.getState());
			ps.setString(5, address.getZipcode());
			ps.setLong(6, customerID);
			ps.executeUpdate();
			//return the address for verification purposes
			return address;
		}
		public Address retrieveForCustomerID(Connection connection, Long customerID) throws SQLException, DAOException
		{
			final String selectQuery = "SELECT address1, address2, city, state, zipcode FROM address where customerID = ?";
			
			if (customerID == null) {
				throw new DAOException("Trying to retrieve Customer with NULL ID");
			}
		
			
			PreparedStatement ps = null;
			
			ps = connection.prepareStatement(selectQuery);
			ps.setLong(1, customerID);
			ResultSet rs = ps.executeQuery();
			//if the result set of the query has multiple addresses, something is seriously wrong, and the auto-increment customer IDs have duplicates
			if(!rs.next()) {
				return null;
			}
			//construct the return address for use by AddressPersistenceModule
			Address add = new Address();
			add.setAddress1(rs.getString("address1"));
			add.setAddress2(rs.getString("address2"));
			add.setCity(rs.getString("city"));
			add.setState(rs.getString("state"));
			add.setZipcode(rs.getString("zipcode"));
			return add;
		}
		public void deleteForCustomerID(Connection connection, Long customerID) throws SQLException, DAOException
		{
			//In the .sql that builds the DB, the 'ON DELETE CASCADE;' will ensure the Address associated with Customer is deleted
			final String deleteSQL =  "DELETE FROM address WHERE customerID = ?;";
			
			if (customerID == null) {
				throw new DAOException("Trying to delete Customer with NULL ID");
			}

			
			PreparedStatement ps = null;
			//delete the Customer in DB with customerID as ID
			ps = connection.prepareStatement(deleteSQL);
			ps.setLong(1, customerID);

			ps.executeUpdate();
			
		}
}
