package DaoImplementation;

import Bean.Product;
import Connection.CONNECTIONDB;
import DaoInterface.ProductInterface;

import java.sql.*;
//dao
//package import with class


public class ImplementationClassOfProductInterface implements ProductInterface {
    Connection connection;


    //GET THE ALL DETAILS OF THE PRODUCT
    @Override
    public void GETPRODUCTDETAILS() {

        System.out.println("Stock details : ");

        System.out.format("%-10s %-25s %-15s %-10s\n", "ID", "Name", "Price", "Stock");

        String query="SELECT * FROM  STOCK";
        try (Connection con = CONNECTIONDB.createDBConnection(); // Get connection from your CONNECTIONDB utility
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) { // Execute query once

            if (!resultSet.isBeforeFirst()) { // Check if ResultSet is empty
                System.out.println("No products found in stock.");
                System.out.println("------------------------------------------------------------------");
                return;
            }

            while (resultSet.next()) {
                int id = resultSet.getInt("Product_id");
                String name = resultSet.getString("Product_name");
                float price = resultSet.getFloat("Product_price"); // Use getFloat for float type
                int stock = resultSet.getInt("stock_quantity");
                System.out.format("%-10d %-25s %-15.2f %-10d\n", id, name, price, stock);
            }
            System.out.println("------------------------------------------------------------------");

        } catch (SQLException e) {
            System.err.println("Error getting product details: " + e.getMessage());
            e.printStackTrace();
        }
    }


    //UPDATE STOCK QUANTITY
    @Override
    public void UPDATETHESTOCK(int Product_id , int stock_quantity) {
        String query="update stock set stock_quantity =stock_quantity+? where Product_Id=?";
        Connection con = CONNECTIONDB.createDBConnection();

        try // Get connection from your CONNECTIONDB utility
        {
            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1,stock_quantity);
            preparedStatement.setInt(2,Product_id);
            int cnt = preparedStatement.executeUpdate();
            if (cnt != 0) {

                System.out.println("Stock updated successfully ");
            } else {
                System.out.println("Stocks not updated please try again ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void DECREASESTOCK(int Product_id , int stock_quantity) {

        String query="update stock set stock_quantity =stock_quantity-? where Product_Id=? and stock_quantity >=?";// stock quantaty( jo actual h ) vo jyda ya equal honi chye purchased one k

        try(Connection con = CONNECTIONDB.createDBConnection(); // Get connection from your CONNECTIONDB utility
            PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1,stock_quantity); // amount to subtract
            preparedStatement.setInt(2,Product_id); // product id
            preparedStatement.setInt(3, stock_quantity); // must be enough stock
            int cnt = preparedStatement.executeUpdate();
            if (cnt != 0) {
                System.out.println("Stock updated successfully ");
            } else {
                System.out.println("Stocks not updated please try again ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //INSERT NEW PRODUCT
    @Override
    public void INSERTNEWSTOCK(Product prd) {
        Connection con = CONNECTIONDB.createDBConnection();// Get connection from your CONNECTIONDB utility

        String query="INSERT INTO stock(product_name,product_price,stock_quantity) VALUES(?,?,?)";

        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            // System.out.println("Inserting: " + prd.getProduct_name() + ", " + prd.getProduct_price() + ", " + prd.getProduct_stock());
            preparedStatement.setString(1, prd.getProduct_name());

            preparedStatement.setFloat(2, prd.getProduct_price());

            preparedStatement.setInt(3, prd.getProduct_stock());
            int cnt = preparedStatement.executeUpdate();
            System.out.println(cnt);
            if (cnt != 0) {
                System.out.println("Stock Inserted successfully ");
            } else {
                System.out.println("Stocks not Inserted please try again ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //UPDATE THE PRICE
    public void UPDATEPRICE(float P_price,int Product_id) {

        Connection con = CONNECTIONDB.createDBConnection();
        String query="update stock set product_price =? where product_id=?";

        try{ // Get connection from your CONNECTIONDB utility
            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setFloat(1,P_price);
            preparedStatement.setInt(2,Product_id);

            int cnt = preparedStatement.executeUpdate();
            if (cnt != 0) {
                System.out.println("updated successfully ");
            } else {
                System.out.println("not updated please try again ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //DEL THE PRODUCT
    @Override
    public void DROPPRODUCT(int Product_id) {
        String query= "Delete from stock where Product_ID=?";
        try (Connection con = CONNECTIONDB.createDBConnection(); // Get connection from your CONNECTIONDB utility
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1,Product_id);

            int cnt= preparedStatement.executeUpdate();
            if(cnt!=0){
                System.out.println("Product deleted successfully");
            }else {
                System.out.println("Product not deleted successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //quantity less then
    @Override
    public void lessthan(int Product_quantity) {
        System.out.println("\n--- Products with Stock <= " + Product_quantity + " ---");
        System.out.format("%-10s %-25s %-15s %-10s\n", "ID", "Name", "Price", "Stock");
        System.out.println("------------------------------------------------------------------");

        String query = "SELECT product_id, product_name, product_price, stock_quantity FROM stock WHERE stock_quantity <= ?";

        try (Connection con = CONNECTIONDB.createDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, Product_quantity);

            // Use executeQuery() for SELECT statements
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean foundProducts = false;
                while (resultSet.next()) {
                    foundProducts = true;
                    int id = resultSet.getInt("product_id");
                    String name = resultSet.getString("product_name");
                    float price = resultSet.getFloat("product_price");
                    int stock = resultSet.getInt("stock_quantity");
                    System.out.format("%-10d %-25s %-15.2f %-10d\n", id, name, price, stock);
                }

                if (!foundProducts) { // Check if any products were found
                    System.out.println("No products found with stock less than or equal to " + Product_quantity + ".");
                }
                System.out.println("------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving products with low stock: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //price less than
    @Override
    public void pricelessthan(float Product_price) {
        System.out.println("\n--- Products with Stock <= " + Product_price + " ---");
        System.out.format("%-10s %-25s %-15s %-10s\n", "ID", "Name", "Price", "Stock");
        System.out.println("------------------------------------------------------------------");

        String query = "SELECT product_id, product_name, product_price, stock_quantity FROM stock WHERE product_price <= ?";

        try (Connection con = CONNECTIONDB.createDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setFloat(1,Product_price);

            // Use executeQuery() for SELECT statements
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean foundProducts = false;
                while (resultSet.next()) {
                    foundProducts = true;
                    int id = resultSet.getInt("product_id");
                    String name = resultSet.getString("product_name");
                    float price = resultSet.getFloat("product_price");
                    int stock = resultSet.getInt("stock_quantity");
                    System.out.format("%-10d %-25s %-15.2f %-10d\n", id, name, price, stock);
                }

                if (!foundProducts) { // Check if any products were found
                    System.out.println("No products found with price less than or equal to " + Product_price + ".");
                }
                System.out.println("------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving products with low stock: " + e.getMessage());
            e.printStackTrace();
        }
    }
}




