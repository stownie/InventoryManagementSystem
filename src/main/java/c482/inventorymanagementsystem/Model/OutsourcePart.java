package c482.inventorymanagementsystem.Model;

public class OutsourcePart extends Part {
    private String companyName;
    public OutsourcePart(int id, String name, double price, int stock, int min, int max, String companyName){
        super( id,  name,  price, stock, min, max);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
