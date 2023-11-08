package com.qsp.shop.view;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.qsp.shop.controller.ShopController;
import com.qsp.shop.model.Product;

public class ShopView {
	
	static Scanner myInput = new Scanner(System.in);
	static Product product = new Product();
	static ShopController shopController = new ShopController();
	
	
	public static void main(String[] args) {
		
		do {
			System.out.println("Select operation to perform : ");
			System.out.println("1.Add product\n2.Remove product\n3.Update product details\n4.Fetch Product\n0.Exit");
			System.out.print("Enter digit respective to desired option : ");
			
			int userInput=myInput.nextInt();
			myInput.nextLine();
			
			switch (userInput) {
			case 0:
				myInput.close();
				System.out.println("-----EXITED-----");
				System.exit(0);
				break;
			case 1:
//				System.out.println("Do you want to add a single product or multiple product?");
				System.out.println("How many product do you want to add?\n1.add single product\n2.add multiple product");
				int count = myInput.nextInt();
				myInput.nextLine();
				
				
				if (count==1) {
					
				
				System.out.print("Enter product id : ");
				int i_p_id=myInput.nextInt();
				myInput.nextLine();
				System.out.print("Enter product name : ");
				String i_p_name=myInput.nextLine();
				System.out.print("Enter product price : ");
				int i_p_price=myInput.nextInt();
				myInput.nextLine();
				System.out.print("Enter product quantity : ");
				int i_p_quantity=myInput.nextInt();
				myInput.nextLine();
				
				boolean i_p_availability=false;
				if (i_p_quantity>0) {
					i_p_availability=true;
				} 
				if ((shopController.addProduct(i_p_id,i_p_name,i_p_price,i_p_quantity,i_p_availability))!=0) {
					System.out.println("Product added");
				}
				
				else {
					System.out.println("Product not added");
				}
				
				}
				
				else {
					boolean toContinue = true;
					ArrayList<Product> products = new ArrayList<Product>();
					do {
						Product product = new Product();
						System.out.println("Enter Product id : ");
						product.setP_id(myInput.nextInt());
						myInput.nextLine();
						System.out.println("Enter Product Name : ");
						product.setP_name(myInput.nextLine());
						System.out.println("Enter Product Price : ");
						product.setP_price(myInput.nextInt());
						myInput.nextLine();
						System.out.println("Enter Product Quantity : ");
						int quantity = myInput.nextInt();
						myInput.nextLine();
						product.setP_quantity(myInput.nextInt());
						myInput.nextLine();
						
						boolean i_p_availability = false;
						if (quantity > 0) {
							i_p_availability = true;
						}
						product.setP_availability(i_p_availability);
						products.add(product);
						System.out.println("Press 1 for continue adding product, Press 0 for stop adding product");
						int toAdd = myInput.nextInt();
						if (toAdd == 0) {
							toContinue = false;
						}
					} while (toContinue);
					shopController.addMultipleProduct(products);
				}
				break;
				
				
				
			case 2:
//				Remove product 
				System.out.println("Enter product id to remove : ");
				int nextInt = myInput.nextInt();
				myInput.nextLine();
				
				if (shopController.removeProduct(nextInt) !=0) {
					System.out.println("Product removed");
				} else {
					System.out.println("Product with the given id does not exist.");
				}
				break;
				
				
				
			case 3:   

				System.out.print("Enter product id to update: ");
				int productIdToUpdate=myInput.nextInt();
				myInput.nextLine();
				ResultSet product = shopController.fetchproduct(productIdToUpdate);

				try {
					if (product.next()) {
						System.out.println("What You want to Update:");
						System.out.println("1.Name \n2.Price \n3.Quantity");
						System.out.println("Enter No. respective to Desire Option :");
						int  updateOption = myInput.nextInt();
						myInput.nextLine();

						switch (updateOption) {
						case 1:
							System.out.print("Enter product name to update: ");
							String i_p_name=myInput.nextLine();
							int updateProduct = shopController.updateProductName(productIdToUpdate,i_p_name);
							
							if(updateProduct!=0) {
								System.out.println("Product Name Updated");
					} else {
						System.out.println("Product with Not Updated");
					}
							break;
						case 2:
							System.out.print("Enter product name to update: ");
							String i_p_price=myInput.nextLine();
							int updatePrice = shopController.updateProductName(productIdToUpdate,i_p_price);
							
							if(updatePrice!=0) {
								System.out.println("Product Price Updated");
					} else {
						System.out.println("Product Not Updated");
					}
							break;
						}}} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			case 4:
//				fetch product
				System.out.print("Enter product id to fetch : ");
				int productIdToFind= myInput.nextInt();
				myInput.nextLine();
				ResultSet fetchproduct = shopController.fetchproduct(productIdToFind);
				try {
					boolean next = fetchproduct.next();
					if (next) {
						System.out.println("PRODUCT DETAILS");
						System.out.println("Id : " + fetchproduct.getInt(1));
						System.out.println("Name : " + fetchproduct.getString(2));
						System.out.println("Price : " + fetchproduct.getInt(3));
						System.out.println("Quantity : " + fetchproduct.getInt(4));
						if (fetchproduct.getBoolean(5)) {
							System.out.println("Availability : Available");
						} else {
							System.out.println("Availability : Not Available");
						}
						
					} else {
						System.out.println("Product with id : " + "does not exist.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				break;

			default:
				System.out.println("-----Invalid selection-----");
				break;
			}
			
		} while (true);
		
	}
			

}
				
		
