package cs4347.jdbcProject.ecomm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cs4347.jdbcProject.ecomm.dao.CreditCardDAO;
import cs4347.jdbcProject.ecomm.entity.CreditCard;
import cs4347.jdbcProject.ecomm.util.DAOException;


public class CreditCardDaoImpl implements CreditCardDAO
{

	@Override
	public CreditCard create(Connection connection, CreditCard creditCard, Long customerID)
			throws SQLException, DAOException {
		final String insertSQL = 
				"INSERT INTO creditcard (Customer_id, name, ccNumber, expDate, securityCode)"
				+"VALUES (?,?,?,?)";
		if (creditCard == null){
			throw new DAOException("Trying to insert a null creditcard");
		}
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, customerID);
			ps.setString(2, creditCard.getName());
			ps.setString(3, creditCard.getCcNumber());
			ps.setString(4, creditCard.getExpDate());
			ps.setString(5, creditCard.getSecurityCode());
			
			ps.executeUpdate();
			return creditCard;
		}
		finally{
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
	}

	@Override
	public CreditCard retrieveForCustomerID(Connection connection, Long customerID) throws SQLException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteForCustomerID(Connection connection, Long customerID) throws SQLException, DAOException {
		// TODO Auto-generated method stub
		
	}

}
