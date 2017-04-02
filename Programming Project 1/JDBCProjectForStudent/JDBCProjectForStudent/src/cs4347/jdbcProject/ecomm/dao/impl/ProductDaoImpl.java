package cs4347.jdbcProject.ecomm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cs4347.jdbcProject.ecomm.dao.ProductDAO;
import cs4347.jdbcProject.ecomm.entity.Product;
import cs4347.jdbcProject.ecomm.util.DAOException;

public class ProductDaoImpl implements ProductDAO
{
	public Product create(Connection connection, Product product) throws SQLException, DAOException{
		final String insertSQL = 
				"INSERT INTO product (prodName, prodDescription, prodCategory, prodUPC)"
				+"VALUES (?,?,?,?)";
		// throw DAOException if the ID is not NULL 
		if (product.getId() != null){
			throw new DAOException("Trying to insert Purchase with a NON-NULL ID");
		}
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(insertSQL);
			ps.setString(1, product.getProdName());
			ps.setString(2, product.getProdDescription());
			ps.setInt(3, product.getProdCategory());
			ps.setString(4, product.getProdUPC());
			int res = ps.executeUpdate();
			if(res != 1) {
				throw new DAOException("Create Did Not Update Expected Number Of Rows");
			}
			ResultSet keyRS = ps.getGeneratedKeys();
			keyRS.next();
			int lastKey = keyRS.getInt(1);
			product.setId((long)lastKey);
			return product;
		}
		finally{
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
	}
}
