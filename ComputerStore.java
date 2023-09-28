package PartA;

import java.util.Scanner;

public class ComputerStore {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Welcome to Computer Store!! "); 

        int size = 0;
        do {
            System.out.println("Enter the maximum number of computers that the store can contain: ");
            size = kb.nextInt();
            if (size <= 0) {
                System.out.println("Invalid input. The inventory size should be a positive number.");
            }
        } while (size <= 0);

        Computer[] inventory = new Computer[size]; 

        int mainMenu = 0;
        do {
            System.out.println("What do you want to do?\n" +
                    "1. Enter new computers (password required)\n" +
                    "2. Change information of a computer (password required)\n" +
                    "3. Display all computers by a specific brand\n" +
                    "4. Display all computers under a certain price\n" +
                    "5. Quit\n" +
                    "Please enter your choice: ");
            
            mainMenu = kb.nextInt();
            kb.nextLine(); // Consume the newline character
            
            switch (mainMenu) {
                case 1:
                    if (!checkPassword()) {
                        break;
                    }
                    int createdcomp = Computer.findNumberOfCreatedComputers();
                    int compcount = 0;
                    do {
                        System.out.println("How many computers do you want to enter: ");
                        compcount = kb.nextInt();
                        if (compcount > size) {
                            System.out.println("There are only " + (size - createdcomp) + " empty spaces available. Please enter a valid number.");
                        } else if (compcount <= 0) {
                            System.out.println("Invalid input. Please enter a positive value.");
                        } else {
                            kb.nextLine(); // Consume the newline character
                            for (int i = createdcomp; i < createdcomp + compcount; i++) {
                                System.out.println("Enter computer " + (i + 1) + "'s brand: ");
                                String brand = kb.nextLine();
                                System.out.println("Enter computer " + (i + 1) + "'s model: ");
                                String model = kb.nextLine();
                                double price = 0;
                                do {
                                    System.out.println("Enter computer " + (i + 1) + "'s price: ");
                                    price = kb.nextDouble();
                                    kb.nextLine(); // Consume the newline character
                                    if (price <= 0) {
                                        System.out.println("Invalid input. Price should be a positive value.");
                                    }
                                } while (price <= 0);

                                inventory[i] = new Computer(brand, model, price);
                                Computer.displayComputer(inventory[i]);
                            }
                        }
                    } while (size <= createdcomp || compcount > size || compcount <= 0);
                    break;
                case 2:
                    if (!checkPassword()) {
                        break;
                    }
                    int compnum, option = 0, changeval, flag = 0;
                    do {
                        System.out.println("Enter the computer number you wish to modify: ");
                        compnum = kb.nextInt();
                        kb.nextLine(); // Consume the newline character
                        if (compnum <= 0) {
                            System.out.println("Invalid input. Computer number should be a positive value.");
                            flag++;
                        } else if (compnum > size || inventory[compnum - 1] == null) {
                            System.out.println("Given computer doesn't exist.");
                            System.out.println("1. Enter another computer number");
                            System.out.println("2. Quit this operation and go back to the main menu");
                            option = kb.nextInt();
                            kb.nextLine(); // Consume the newline character
                            flag++;
                        } else {
                            compnum -= 1; 
                            System.out.println("Computer " +
                                    "\nBrand: " + inventory[compnum].getBrand()
                                    + "\nModel: " + inventory[compnum].getModel()
                                    + "\nSN: " + inventory[compnum].getSN()
                                    + "\nPrice: $" + inventory[compnum].getPrice());
                            do {
                                System.out.println("What information would you like to change?\n" +
                                        "1. Brand\n" +
                                        "2. Model\n" +
                                        "3. SN\n" +
                                        "4. Price\n" +
                                        "5. Quit\n" +
                                        "Enter your choice: ");
                                changeval = kb.nextInt();
                                kb.nextLine(); // Consume the newline character
                                switch (changeval) {
                                    case 1:
                                        System.out.println("Enter the new Brand name: ");
                                        String newBrand = kb.nextLine();
                                        inventory[compnum].setBrand(newBrand);
                                        Computer.displayComputer(inventory[compnum]);
                                        break;
                                    case 2:
                                        System.out.println("Enter the new Model name: ");
                                        String newModel = kb.nextLine();
                                        inventory[compnum].setModel(newModel);
                                        Computer.displayComputer(inventory[compnum]);
                                        break;
                                    case 3:
                                        System.out.println("Enter the new serial number: ");
                                        int newSN = kb.nextInt();
                                        inventory[compnum].setSN(newSN);
                                        Computer.displayComputer(inventory[compnum]);
                                        break;
                                    case 4:
                                        double newPrice = 0;
                                        do {
                                            System.out.println("Enter the new price: ");
                                            newPrice = kb.nextDouble();
                                            kb.nextLine(); // Consume the newline character
                                            if (newPrice <= 0) {
                                                System.out.println("Invalid input. Price should be a positive value.");
                                            }
                                        } while (newPrice <= 0);
                                        inventory[compnum].setPrice(newPrice);
                                        Computer.displayComputer(inventory[compnum]);
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        System.out.println("Invalid input.");
                                }
                            } while (changeval != 5);
                        }
                    } while (option == 1 || flag != 0);
                    break;
                case 3:
                    System.out.println("Enter Brand name: ");
                    String brandname = kb.nextLine();
                    if (findComputersBy(inventory, brandname) == 0)
                        System.out.println("There are no computers with the brand name as " + brandname);
                    break;
                case 4:
                    System.out.println("Enter the price value to display the computers cheaper than the given price: ");
                    double pr = kb.nextDouble();
                    kb.nextLine(); // Consume the newline character
                    if (findCheaperThan(inventory, pr) == 0)
                        System.out.println("There are no computers cheaper than " + pr);
                    break;
                case 5:
                    System.out.println("Thank you for visiting Computer store!! \nHope we have served you the best!");
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
            }
        } while (mainMenu != 5);
    }

    public static boolean checkPassword() {
        Scanner kb = new Scanner(System.in);
        String psd;
        int loopCount = 0;
        do {
            System.out.println("Enter the password: ");
            psd = kb.nextLine();
            if (psd.equals("password"))
                return true;
            loopCount++;
            System.out.println("Invalid, Please enter a valid password!.");
            if (loopCount == 2)
                System.out.println("This is your last attempt to enter a valid password!");
        } while (loopCount < 3);
        return false;
    }

    public static int findComputersBy(Computer[] inventory, String brandname) {
        int flag = 0;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getBrand().equals(brandname)) {
                System.out.println("Computer's information of the given brand:");
                Computer.displayComputer(inventory[i]);
                flag++;
            }
        }
        return flag;
    }

    public static int findCheaperThan(Computer[] inventory, double pr) {
        int flag = 0;
        for (Computer ele : inventory) {
            if (ele != null && ele.getPrice() < pr) {
                System.out.println("The computer/computers cheaper than the given price:");
                Computer.displayComputer(ele);
                flag++;
            }
        }
        return flag;
    }
}
