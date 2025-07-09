package Bean;

//BEAN CLASS

//pojo class
public class Product {
    private  int Product_id;
    private  String Product_name;
    private  float Product_price;
    private  int Product_stock;

    //DEFAULT CONSTRUCTOR
    public Product() {
    }

    //PARAMETERIZED CONSTRUCTOR
    public Product(int product_id, String product_name, float product_price, int product_stock) {
        Product_id = product_id;
        Product_name = product_name;
        Product_price = product_price;
        Product_stock = product_stock;
    }

    //GETTER AND SETTER
    public int getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(int product_id) {
        Product_id = product_id;
    }

    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public float getProduct_price() {
        return Product_price;
    }

    public void setProduct_price(float product_price) {
        Product_price = product_price;
    }

    public int getProduct_stock() {
        return Product_stock;
    }

    public void setProduct_stock(int product_stock) {
        Product_stock = product_stock;
    }


    //TO STRING
    @Override
    public String toString() {
        return "Product{" +
                "Product_id=" + Product_id +
                ", Product_name='" + Product_name + '\'' +
                ", Product_price=" + Product_price +
                ", Product_stock=" + Product_stock +
                '}';
    }



}

