package examples;

public class PetItems {
    String ItemID;
    String ProductID;
    String Description;
    boolean InStock;
    int Quantity;
    float List_Price;
    float TotalCost;

    @Override
    public String toString() {
        return "PetItems{" +
                "ItemID='" + ItemID + '\'' +
                ", ProductID='" + ProductID + '\'' +
                ", Description='" + Description + '\'' +
                ", InStock=" + InStock +
                ", Quantity=" + Quantity +
                ", List_Price=" + List_Price +
                ", TotalCost=" + TotalCost +
                '}';
    }

}
