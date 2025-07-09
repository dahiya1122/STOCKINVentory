package Main;

import Bean.Product;
import Bean.UserLogin;
import DaoImplementation.ImplementationClassOfProductInterface;
import DaoImplementation.ImplementationClassOfUserLogin;
import DaoInterface.ProductInterface;
import DaoInterface.UserLoginInterface;


import java.util.Scanner;
public class Main {

    //admin login and password
    private static final String ADMIN_ID = "admin123";
    private static final String ADMIN_PASSWORD = "password123"; // Store as String

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        //achive abstraction and loose coupling
        ProductInterface pi=new ImplementationClassOfProductInterface();
        UserLoginInterface ui=new ImplementationClassOfUserLogin();

        Product product = new Product();
        UserLogin userLogin = new UserLogin();

        System.out.println("WELCOME 😇");
        // FOR AGAIN PERFORMING THE SAME PROCESS

        //last vala while
        String con = "y";


        //function for calling admin part


        do {
            System.out.println("YOU ARE ADMIN 👤 OR CUSTOMER 👥");

            //asking you are customer or admin
            String youare = scanner.nextLine();

            int ch; // for int input
            String string; //
            char Char;


            if (youare.equals("admin") | youare.equals("ADMIN")) {

                // password for entering
                System.out.println("Enter admin id : ");
                String ADMINID = scanner.nextLine();
                System.out.println("Enter admin password : ");
                String ADMINPASSWORD = scanner.nextLine();

                if (ADMINID.equals(ADMIN_ID) && ADMINPASSWORD.equals(ADMIN_PASSWORD)) {
                    System.out.println("YOU SUCCESSFULLY ENTERED AS A ADMIN");
                    boolean running = true;
                    do {
                        //asking from the admin what operation they want to perform
                        System.out.println("HERE ARE THE OPERATION THAT YOU 🫵🏻 CAN PERFORM ✍🏻");
                        System.out.println("1. GET THE PRODUCT DETAILS 🧾");
                        System.out.println("2. GET THE DETAILS of  all the user  🧾");
                        System.out.println("3. WANT TO UPDATE THE STOCK (QUANTITY)");
                        System.out.println("4. WANT TO INSERT NEW STOCK RECORD ✍🏻");
                        System.out.println("5. WANT TO UPDATE THE PRODUCT PRICE 💵");
                        System.out.println("6. YOU WANT TO DROP THE PRODUCT ");
                        // where product is less than 20
                        System.out.println("7. where product quantity is less than ");
                        System.out.println("8. where product price is less than equal to ");
                        System.out.println("9. get user details by their user name");
                        System.out.println("10. DON'T WANT TO PERFORM MORE OPERATION");

                        System.out.println("ENTER YOUR CHOICE ");
                        ch = scanner.nextInt();
                        scanner.nextLine();
                        if (ch == 1) {

                            //this will show all the product details like id , name , stock ,price
                            pi.GETPRODUCTDETAILS();

                        } else if (ch==2) {
                            //this will show all the details of the user
                            ui.DetailsOfUser();

                        } else if (ch == 3) {
                            System.out.println("Enter the product id : ");
                            int prdid = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter the quantity for the product : ");
                            int prdquantity = scanner.nextInt();
                            scanner.nextLine();
                            pi.UPDATETHESTOCK(prdid,prdquantity);

                        } else if (ch == 4) {


                            System.out.println("Enter the name of the product : ");
                            String Productname = scanner.nextLine();

                            System.out.println("Enter the price of the product : ");
                            float Productprice = scanner.nextFloat();
                            scanner.nextLine();

                            System.out.println("Enter the stock you have : ");
                            int Productstock = scanner.nextInt();
                            scanner.nextLine();

                            product.setProduct_name(Productname);
                            product.setProduct_price(Productprice);
                            product.setProduct_stock(Productstock);

                            pi.INSERTNEWSTOCK(product);

                        } else if (ch == 5) {
                            System.out.println("Enter the product id : ");
                            int prdid = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter the new price for the product : ");
                            float prdprice= scanner.nextFloat();
                            scanner.nextLine();
                            pi.UPDATEPRICE((float)prdprice,prdid);

                        } else if (ch == 6) {
                            System.out.println("Enter the product id that you want to delete : ");
                            int prdid = scanner.nextInt();
                            scanner.nextLine();
                            pi.DROPPRODUCT(prdid);
                        } else if (ch==7) {
                            System.out.println("enter the quantity ");
                            int quant=scanner.nextInt();
                            scanner.nextLine();
                            pi.lessthan(quant);

                        } else if (ch==8) {
                            System.out.println(" enter the price ( in float (eg. 200.00)");
                            float price=scanner.nextFloat();
                            scanner.nextLine();
                            pi.pricelessthan(price);

                        } else if (ch==9) {
                            System.out.println("Enter user name : ");
                            String name=scanner.nextLine();
                            UserLogin user = ui.getUserByUsername(name); // get the result

                            if (user != null) {
                                // Access fields
                                System.out.println("✅ User found: ");
                                int id = user.getUserId();
                                String username = user.getUsername();
                                String passwordHash = user.getPassword_hash();
                                String role = user.getRole();

                                System.out.println("User ID: " + id);
                                System.out.println("Username: " + username);
                                System.out.println("Password Hash: " + passwordHash);
                                System.out.println("Role: " + role);
                            } else {
                                System.out.println("❌ User not found.");
                            }

                        } else if (ch == 10) {
                            System.out.println(" you don't want to perform any operation !!!!!!!");
                            running = false;
                        } else {
                            System.out.println("WRONG INPUT !!!!!!!!!!!!!!!! PLEASE TRY AGAIN ");
                        }
                    } while (running);
                } else {
                    System.out.println("WRONG ID AND PASSWORD ");
                    continue;
                }
            } else if (youare.equals("customer") || youare.equals("CUSTOMER")) {

                //for asking that you are new user or not
                int choice;
                System.out.println("1. already login ");
                System.out.println(("2. new user"));
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {

                    System.out.println("Enter your customer id : ");
                    String customerid = scanner.nextLine();
                    System.out.println("Enter your password : ");
                    String customerpassword = scanner.nextLine();
                    boolean run = true;
                    UserLogin authenticatedCustomer = ui.AlreadyRegistered(customerid,customerpassword);

                    // Authentication checking for login
                    if (authenticatedCustomer != null && authenticatedCustomer.getRole().equalsIgnoreCase("customer")) {
                        System.out.println("Login successful! Welcome, " + authenticatedCustomer.getUsername() + " (CUSTOMER)");

                        do {

                            //sign up if already login

                            System.out.println("WHAT YOU WANT TO DO");
                            System.out.println("1. BUY A PRODUCT");
                            System.out.println("2. return the product");
                            System.out.println("3.Don't  want to perform any operation ");
                            System.out.println("ENTER YOUR CHOICE");
                            //user choice
                            ch = scanner.nextInt();
                            scanner.nextLine();

                            if (ch == 1) {
                                System.out.println("HERE IS THE LIST OF PRODUCT");
                                System.out.println("PLEASE 🙏🏻 CHOOSE YOUR PRODUCT");

                                //this will show all the product details like id , name , stock ,price
                                pi.GETPRODUCTDETAILS();

                                System.out.println("Enter the product id : ");
                                int prdid = scanner.nextInt();
                                scanner.nextLine();

                                System.out.println("Enter the quantity for the product : ");
                                int prdquantity = scanner.nextInt();
                                scanner.nextLine();

                                pi.DECREASESTOCK(prdid,prdquantity);

                            } else if (ch == 2) {
                                System.out.println("PLEASE ENTER THE PRODUCT ID HERE : ");
                                int prdid = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("PLEASE ENTER THE PRODUCT quantity HERE : ");
                                int prdquantity = scanner.nextInt();
                                scanner.nextLine();
                                pi.UPDATETHESTOCK(prdid,prdquantity);
                            } else if (ch == 3) {
                                System.out.println("operation end");
                                run = false;
                            } else {
                                System.out.println("Wrong input !!!!!!!!!!!");
                            }
                        } while (run);
                    } else {
                        System.out.println("wrong input!!!!"); //password wrong input (of already present )
                        continue;
                    }
                }
                //else of admin / customer
                else {
                    System.out.println(" WRONG INPUT PLEASE TRY AGAIN ");
                }
                System.out.println("you want to continue or not y/n");
                con = scanner.nextLine();
            }
        } while (con.equals("y") || con.equals("Y")) ;// sb say phelay valy ka while h ye admin or customer
    }
}
