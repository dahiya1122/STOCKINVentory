package DaoInterface;

import Bean.Product;

public interface ProductInterface {

    //TO GET TO KNOW THE QUANTITY OF THE ALL PRODUCT
    //SHOW ALL PRODUCT QUANTITY
    public void GETPRODUCTDETAILS();

    //TO UPDATE THE STOCK(QUANTITY)
    public void UPDATETHESTOCK(int Product_id, int Product_quantity);

    //decrease stock
    public void DECREASESTOCK(int Product_id, int Product_quantity);

    //INSERT NEW PRODUCT
    void INSERTNEWSTOCK(Product prd);

    //UPDATE PRODUCT PRICE
    public void UPDATEPRICE(float Product_price,int Product_id);

    //TO DROP THE PRODUCT
    public void DROPPRODUCT(int Product_id);

    //where product quantity is less than 20
    public void lessthan(int Product_quantity);

    //price less than
    public void pricelessthan(float Product_price);

}

