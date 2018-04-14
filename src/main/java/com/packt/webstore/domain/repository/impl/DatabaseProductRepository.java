package com.packt.webstore.domain.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepositoryDb;
import com.packt.webstore.exception.DatabaseException;

@Repository
public class DatabaseProductRepository implements ProductRepositoryDb {

	static final String url = "jdbc:postgresql://localhost:5432/webstoredb";
	static final String user = "postgres";
	static final String password = "pass";
	
	private Connection connect() throws SQLException {	try {
		Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e1) {
		System.out.println("Class not found " + e1);
		e1.printStackTrace();
	}
		return DriverManager.getConnection(url, user, password);
	}
	
//	private Connection connect(boolean transactional) throws SQLException {
//		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
//		connection.setAutoCommit(transactional);
//		return connection;
//	}
	
	private void close(Connection connection, Statement statement) {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
		}
	}
	
	public void createTable() throws DatabaseException {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = connect();
			statement = connection.createStatement();
			String sqlCreate = "CREATE TABLE IF NOT EXISTS product" 
			        + "  (id           		VARCHAR(10) PRIMARY KEY,"
			        + "   name         		VARCHAR(25) NOT NULL,"
			        + "   price          	INTEGER NOT NULL,"
			        + "   description    	VARCHAR(100),"
			        + "   manufacturer      VARCHAR(20),"
			        + "   category     		VARCHAR(15),"
			        + "   units_in_stock 	INTEGER)";
			statement.executeUpdate(sqlCreate);
			
		} catch  (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("creating table failed", e);
		} finally {
			close(connection, statement);
		}
	}

	private Product resultToProduct (ResultSet resultSet) throws SQLException {
		String productId = resultSet.getString("id");
		String name = resultSet.getString("name");
		int price = resultSet.getInt("price");
		String description = resultSet.getString("description");
		String manufacturer = resultSet.getString("manufacturer");
		String category = resultSet.getString("category");
		int unitsInStock = resultSet.getInt("units_in_stock");
		// Player player = new Player(id, nickname, gold);
		Product product = new Product();
		product.setProductId(productId);
		product.setName(name);
		product.setUnitPrice(price);
		product.setDescription(description);
		product.setManufacturer(manufacturer);
		product.setCategory(category);
		product.setUnitsInStock(unitsInStock);
		return product;
	}
	
	public List<Product> getAllProducts() {
		Connection connection = null;
		Statement statement = null;
		List<Product> listOfProducts = new ArrayList<Product>();
		try {
			connection = connect();
			statement = connection.createStatement();
			String sql = "SELECT * FROM product";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Product product = resultToProduct(resultSet);
				listOfProducts.add(product);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("getting all products failed", e);
		} finally {
			close(connection, statement);
		}
		return listOfProducts;
	}
	
	public void addProduct (Product product) throws DatabaseException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = connect();
			String url = "INSERT INTO product("
					+ "id, "
					+ "name, "
					+ "price, "
					+ "description, "
					+ "manufacturer, "
					+ "category, "
					+ "units_in_stock)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(url);
			statement.setString(1, product.getProductId());
			statement.setString(2, product.getName());
			statement.setInt(3, product.getUnitPrice());
			statement.setString(4, product.getDescription());
			statement.setString(5, product.getManufacturer());
			statement.setString(6, product.getCategory());
			statement.setLong(7, product.getUnitsInStock());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("getting all products failed", e);
		} finally {
			close(connection, statement);
		}
	}
	

}
