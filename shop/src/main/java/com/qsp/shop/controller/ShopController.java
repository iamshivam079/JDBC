package com.qsp.shop.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.postgresql.Driver;

import com.qsp.shop.model.Product;

public class ShopController {
	public int addProduct(int id, String name, int price, int quantity, boolean availability) {
		Driver driver = new Driver();
		Connection connection =null;
		int rowsAffected = 0;
		try {
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);
			
			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO products VALUES (?,?,?,?,?)");
			
			prepareStatement.setInt(1, id);
			prepareStatement.setString(2, name);
			prepareStatement.setInt(3, price);
			prepareStatement.setInt(4, quantity);
			prepareStatement.setBoolean(5, availability);
			rowsAffected = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return rowsAffected;
	}
	
	public void addMultipleProduct(ArrayList<Product> products) {
		Driver driver = new Driver();
		Connection connection =null;

		try {
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);
			
			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO products VALUES (?,?,?,?,?)");
			
			for (Product product : products) {
				prepareStatement.setInt(1,product.getP_id());
				prepareStatement.setString(2, product.getP_name());
				prepareStatement.setInt(3, product.getP_price());
				prepareStatement.setInt(4, product.getP_quantity());
				prepareStatement.setBoolean(5, product.isP_availability());
				prepareStatement.addBatch();
			}
			
			prepareStatement.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}

	}
	
	public ResultSet fetchproduct(int id) {
		Driver driver = new Driver();
		Connection connection =null;
		ResultSet resultSet = null;
		try {
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);
			
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM products WHERE p_id=?");

			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return resultSet;
	}
	
	public int removeProduct(int id) {
		
		Driver driver = new Driver();
		Connection connection =null;
		int idDeleted =0;
		
		try {
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);
			
			PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM products WHERE p_id=?");
			
			prepareStatement.setInt(1, id);
			idDeleted = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return idDeleted;
	}
	
	public int updateProductName(int id, String name) {
		Driver driver = new Driver();
		Connection connection =null;
		int idUpdated =0;
		
		try {
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);
			
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE products SET p_name=? WHERE p_id=?");
			
			prepareStatement.setString(1, name);
			prepareStatement.setInt(2, id);
			idUpdated = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return idUpdated;
	}
	
	public int updateProductPrice(int id, String name) {
		Driver driver = new Driver();
		Connection connection =null;
		int idUpdated =0;
		
		try {
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);
			
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE products SET p_price=? WHERE p_id=?");
			
			prepareStatement.setString(1, name);
			prepareStatement.setInt(2, id);
			idUpdated = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (connection!=null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return idUpdated;
	}
	
	}
	

