package cs4347.jdbcProject.ecomm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
			throw new DAOException("Trying to insert prodase with a NON-NULL ID");
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

	public Product retrieve(Connection connection, Long id) throws SQLException, DAOException {
		final String selectSQL =
				"SELECT id, prodName, prodDescription, prodCategory, prodUPC"
				+"FROM product WHERE id = ?";
		if (id == null){
				throw new DAOException("Trying to retrieve product with a NULL ID");
		}
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(selectSQL);
			ps.setLong(1, id);  //Set positional parameter #1 in String selectSQL to var id
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				return null;
			}
			// Create a new product object
			Product prod = new Product();
			// Fill product object with values from ResultSet
			prod.setId(rs.getLong("ID"));
			prod.setProdName(rs.getString("prodName"));
			prod.setProdDescription(rs.getString("prodDescription"));
			prod.setProdCategory(rs.getInt("prodCategory"));
			prod.setProdUPC(rs.getString("prodUPC"));
			return prod;
		}
		finally{
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
	}

	public int update(Connection connection, Product product) throws SQLException, DAOException {
		final String updateSQL = 
				"UPDATE product SET prodName = ?, prodDecription = ?, prodCategory= ?, prodUPC = ?"
				+ "WHERE id = ?";
		if (product.getId() == null) {
			throw new DAOException("Trying to update Product with a NULL ID");
		}
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(updateSQL);
			ps.setString(1, product.getProdName());
			ps.setString(2, product.getProdDescription());
			ps.setInt(3, product.getProdCategory());
			ps.setString(4, product.getProdUPC());
			ps.setLong(5, product.getId());
			int rows = ps.executeUpdate();
			return rows;			
		}
		finally{
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		}
	}

	public int delete(Connection connection, Long id) throws SQLException, DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Product> retrieveByCategory(Connection connection, int category) throws SQLException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Product retrieveByUPC(Connection connection, String upc) throws SQLException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}
