import java.util.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FlexiRentSystem {

    private Scanner input = new Scanner(System.in);
    HashMap<String, RentalProperty> RPTable = new HashMap<>();

    public HashMap<String, RentalProperty> getRPTable() {
        return RPTable;
    }


//    ArrayList<RentalProperty> RP = new ArrayList<>();
//    public ArrayList<RentalProperty> getRP() {
//        return this.RP;
//    }

    private void printMenu() {
        System.out.print(
                "**** FLEXIRENT SYSTEM MENU ****\n" +
                        "\n" +
                        "Add Property:			 1\n" +
                        "Rent Property:			 2\n" +
                        "Return Property:		 3\n" +
                        "Property Maintenance:	 4\n" +
                        "Complete Maintenance:	 5\n" +
                        "Display All Properties:  6\n" +
                        "Exit Program:			 7\n" +
                        "Enter your choice: 	   " + "\n");


    }

    private void getInputOption() {

        /*String Option = "";*/
        String Option = input.nextLine();
        switch (Option) {
            case "1":
                System.out.println("Please add the details of a new property.\n");
                addProperty();
                break;
            case "2":
                System.out.println("Rent the property you like and find whether it is available or not.\n");
                rentProperty();
                break;
            case "3":
                System.out.println("Return the property.\n");
                returnProperty();
                break;
            case "4":
                System.out.println("Show the property maintenance.\n");
                propertyMaintenance();
                break;
            case "5":
                System.out.println("Complete Maintenance.\n");
                completeMaintenance();
                break;
            case "6":
                System.out.println("Display All Properties.\n");
                diplayAllProperty();
                break;
            case "7":
                input.close();
                System.exit(0);
                break;
            default:
                break;
        }

    }

    public void addProperty() {
        //Scanner addP = new Scanner(System.in);

//        String propertyId ;
//        String streetNumber ;
//        String streetName ;
//        String suburb;
//        int numberOfBedrooms;
//        int propertyType;


        System.out.println("\"******* ADD PROPERTY ********\n" +
                "Choose the type of the property:\n" +
                "Add an Apartment     : 1\n" +
                "Add an Premium Suite : 2\n" +
                "Return to Main menu  : Any other Character\n ");
        String Option = input.nextLine();
        switch (Option) {
            case "1":
                addApartment();
                break;
            case "2":
                addPremiumSuite();
                break;
            default:
                break;
        }
    }

    private void addApartment() throws InputMismatchException {
        System.out.println("Please input the street number :)");
        String streetNumber = input.nextLine();
        System.out.println("Please input the street name :)");
        String streetName = input.nextLine();
        System.out.println("Please input the suburb :)");
        String suburb = input.nextLine();
        System.out.println("plesase input the number of rooms you want to rent(1-3)");
        int numberOfBedrooms = Integer.parseInt(input.nextLine());

        while (numberOfBedrooms < 1 || numberOfBedrooms > 3) {
            System.out.println("please input the number of rooms within 1 and 3");
            numberOfBedrooms = Integer.parseInt(input.nextLine());
        }
        String propertyId = "A_" + streetNumber + streetName.substring(0, 1).toUpperCase() + "S" + suburb.substring(0, 2).toUpperCase();
        System.out.println("Your property ID is here:) : " + propertyId);

        Apartment ap = new Apartment(propertyId, streetNumber, streetName, suburb, numberOfBedrooms);

//        //!!!!!!!!!!!!!!!!!!!!!!
//        //System.out.println(ap.getStreetNum());
//
//        for (String key : RPTable.keySet()) {
//            System.out.println(RPTable.get(key).getStatus());
//        }
//        //this.RP.add(new Apartment(propertyId,streetNumber,streetName,suburb,numberOfBedrooms));
        this.RPTable.put(ap.getPropertyId(), ap);

    }

    private void addPremiumSuite() {
        try {
            System.out.println("Please input the street number");
            String streetNumber = input.nextLine();
            System.out.println("Please input the street name");
            String streetName = input.nextLine();
            System.out.println("Please input the suburb");
            String suburb = input.nextLine();
            System.out.println("Last Maintenance Date(day):			");
            int year, month, day;
            day = input.nextInt();
            input.nextLine();
            System.out.println("Last Maintenance Date(month):			");
            month = input.nextInt();
            input.nextLine();
            System.out.println("Last Maintenance Date(year):			");
            year = input.nextInt();
            input.nextLine();
            DateTime lastMaintenance = new DateTime(day, month, year);
            String propertyId = "S_" + streetNumber + streetName.substring(0, 1).toUpperCase() + "S" + suburb.substring(0, 2).toUpperCase();
            System.out.println("Your property ID is here:) : " + propertyId);

            premiumSuite ps = new premiumSuite(propertyId, streetNumber, streetName, suburb, lastMaintenance);
            //this.RP.add(new premiumSuite(propertyId,streetNumber,streetName,suburb,numberOfBedrooms));
            this.RPTable.put(ps.getPropertyId(), ps);
        } catch (InputMismatchException e) {
            System.out.println("Invaild Input. :(");

        }
    }


    public void rentProperty() {
        //Scanner input = new Scanner(System.in);

//        String inputId;
//        String customerId;
//        DateTime rentDate;
//        String parseRentDate;
//        int rentLength;
//        int propertyType;


        System.out.println("Choose the type of the property:\n" +
                "1. Apartment\n" +
                "2. Premium Suite\n" +
                "3. Show all the propertyId\n");
        int propertyType = Integer.parseInt(input.nextLine());


        //if the user choose the apartment ||premiumSuite
        if (propertyType == 1 || propertyType == 2) {

            System.out.println("Please enter the propertyId you want to rent:) ");
            String propertyId = input.nextLine();
            if (RPTable.containsKey(propertyId) &&
                    RPTable.get(propertyId).getStatus()== RentalProperty.Status.Available) {
                System.out.println("Please input  you customer name ");
                String customerId = input.nextLine();
                System.out.println("Enter the date you want to rent(day) ");
                int day, month, year;
                day = input.nextInt();
                input.nextLine();
                System.out.println("Enter the date you want to rent(month) ");
                month = input.nextInt();
                System.out.println("Enter the date you want to rent(year) ");
                year = input.nextInt();
                DateTime rentdate = new DateTime(day, month, year);
                System.out.println("How many days do you want to rent ");
                int numOfRentDay = input.nextInt();
                input.nextLine();
                System.out.println(customerId + " want to rent " + propertyId + " succeed: "
                        + RPTable.get(propertyId).rent(customerId, new DateTime(), numOfRentDay));
            }else {
                System.out.println("Invid input!!!!!!");
            }
        }


        else if (propertyType == 3) {

            for (String id : this.RPTable.keySet()) {
                System.out.println(RPTable.get(id).getPropertyId());
            }
            System.out.println("\n");
            rentProperty();

        }
    }

        private void returnProperty () {
            System.out.println("Enter PropertyID: 			");
            String propertyId = input.nextLine();
//            System.out.println("Return " + propertyId + " succeed: " + RPTable.get(propertyId).returnProperty(new DateTime()));
            System.out.printf("Return %s succeed: %s%n", propertyId, RPTable.get(propertyId).returnProperty());
        }


        public void propertyMaintenance () {
            System.out.println("Enter Property ID:			");
            System.out.println("Perform Maintenance: " +
                    this.RPTable.get(input.nextLine()).performMaintenance());
        }


        public void completeMaintenance () {
            System.out.println("Enter Property ID:			");
            System.out.println("Complete Maintenance: " +
                    this.RPTable.get(input.nextLine()).performMaintenance());

        }


        public void diplayAllProperty () {

            for (String id : this.RPTable.keySet()) {
                System.out.println(RPTable.get(id).getDetails());
            }
        }


        public void run () {
            while (true) {
                printMenu();
                getInputOption();
            }
        }


    private int findProperty(String targetId) {
        int i;
        int numberOfProperties = RPTable.size();
        boolean ifFound = false;
        for (i = 0; i < numberOfProperties; i++) {
            if (RPTable.get(i).getPropertyId().toUpperCase().equals(targetId.toUpperCase())) {
                ifFound = true;
                break;
            }
        }
        if (!ifFound) {
            System.out.println("Property does not exist!");
            i = -1;
            return i;
        }
        else {
            return i;
        }
    }



    }








