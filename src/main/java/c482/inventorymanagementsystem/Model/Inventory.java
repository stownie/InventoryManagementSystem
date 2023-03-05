package c482.inventorymanagementsystem.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart){
    allParts.add(newPart);
    }
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId){
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (int i = 0; i < allParts.size(); ++i){
            Part part = allParts.get(i);
            if(part.getId() == partId){
                return part;
            }
        }
        return null;
    }

    public static Product lookupProduct(int productId){
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for (int i = 0; i < allProducts.size(); ++i){
            Product product = allProducts.get(i);
            if(product.getId() == productId){
                return product;
            }
        }
        return null;
    }
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> nameParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part part : allParts){
            if (part.getName().contains(partName)) {
                nameParts.add(part);
            }
        }
        return nameParts;
    }
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> nameProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for (Product product : allProducts){
            if (product.getName().contains(productName)){
                nameProducts.add(product);
            }
        }
        return nameProducts;
    }
    public static void updatePart(int index, Part selectedPart){
        Part oldPart = Inventory.lookupPart(index);
        Inventory.deletePart(oldPart);
        Inventory.addPart(selectedPart);
    }
    public static void updateProduct(int index, Product newProduct){
        Product oldProduct = Inventory.lookupProduct(index);
        Inventory.deleteProduct(oldProduct);
        Inventory.addProduct(newProduct);
    }
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            Inventory.allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }
    public static boolean deleteProduct(Product selectedProduct){
        if (allProducts.contains(selectedProduct)){
            Inventory.allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
