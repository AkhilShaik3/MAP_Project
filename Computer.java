package PartA;

public class Computer {
    private String brand;
    private String model;
    private int SN;
    private double price;
    private static int serialNumCounter = 100000;
    private static int computerCounter = 0;
    public Computer(){
        SN = serialNumCounter;
        serialNumCounter++;
        computerCounter++;
    }
    public Computer(String brnd, String mdl, double pr){
        brand = brnd;
        model = mdl;
        price = pr;
        SN = serialNumCounter;
        serialNumCounter++;
        computerCounter++;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brnd) {
        brand = brnd;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String mdl) {
        model = mdl;
    }
    public int getSN() {
        return SN;
    }
    public void setSN(int serialNum) {
        SN = serialNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double pr) {
        price = pr;
    }
    public static void displayComputer(Computer c){
        System.out.println("The computer's brand is : " + c.getBrand());
        System.out.println("The computer's model is : " + c.getModel());
        System.out.println("The computer's serial Number is : " + c.getSN());
        System.out.println("The computer's price is : " + c.getPrice());
    }
    @Override
    public String toString() {
        return "Computer [brand=" + brand + ", model=" + model
                + ", price=" + price + ", serialNumber=" + SN + "]";
    }
    public static int findNumberOfCreatedComputers(){
        return computerCounter;
    }
    public boolean equal(Computer c){
        if(brand == c.getBrand() && model == c.getModel() && price == c.getPrice())
            return  true;
        return false;
    }
}

