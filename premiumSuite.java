import java.util.Scanner;

public class premiumSuite extends RentalProperty {

//    private double dailyExpensesS;
//    private double lateFeeS;

    private DateTime lastMaintenanceDate;

    public premiumSuite(String propertyId, String streetNum, String streetNmame, String suburb, DateTime lastMaintenanceDate) {
        super.setPropertyId(propertyId);
        super.setStreetNum(streetNum);
        super.setStreetName(streetNmame);
        super.setSuburb(suburb);
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.setNumOfRoom(3);
        this.setStatus(Status.Available);
        //this.setRecord(new LinkedList<>());
        super.setRecord1(new RentalRecord[10]);


        super.propertyType  = "premiumSuite";

    }
    /* be called on RentalProperty , Override here
     * be rented || undermaintenance----return false
     * can be rent ,
     * 1. updating Status
     * 2.create new record
     * 3.uodating record[]
     * show successfully -----return true */
    @Override
    public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
        if(super.getStatus()!=Status.Available)
        {
            return false;
        } else if (DateTime.diffDays(new DateTime(rentDate,numOfRentDay),
                this.lastMaintenanceDate) >= 10) {
                return false;
            }
            else {
                setStatus(Status.Rented);

                //super.getRecord().add(0,new RentalRecord(customerId,rentDate,numOfRentDay));
                super.addRecord(new RentalRecord(customerId,rentDate,numOfRentDay));
                return true;
            }
        }


    /*  be called on RentalProperty , Override here
     *  return time < Rent Date -----return false
     * Rent Date >estimated Return Date,
     * get LateFee (*latedays) , and rentalFee(*estimatedRentDays)
     * Satus Available
     * get new Records and put it into Array recore1-------retun true
     */
    @Override
    public Object returnProperty() {

        System.out.println("The available records are as follows:");
        int i;
        int end = this.getNumberOfRecords();
        Scanner sc = new Scanner(System.in);
        for (i = 0; i < end; i++) {

            if (super.getRecord1()[i].getInUse()) {
                System.out.println(this.getRecord1()[i].getRecordId());
            }
        }

        System.out.println("Input the record ID you are going to return:");
        String targetId = sc.nextLine();
        int targetIndex = this.findRentalRecord(targetId);
        if (targetIndex >= 0) {
            System.out.println("Enter the returning date (dd/mm/yyyy):");
            String parseDate = sc.nextLine();
            int day = Integer.valueOf(parseDate.substring(0,2));
            int month = Integer.valueOf(parseDate.substring(3,5));
            int year = Integer.valueOf(parseDate.substring(6,10));
            DateTime rtnDate = new DateTime(day, month, year);
            this.getRecord1()[targetIndex].setActualReturnDate(rtnDate);
            this.getRecord1()[targetIndex].turnOffRecord();
            double rentalFee = 0;
            double lateFee = 0;

            int estimatedRentDays =  DateTime.diffDays(super.getRecord1()[0].getEstimatedReturnDate(),
                    this.getRecord1()[0].getRentDate());
            int lateDays = DateTime.diffDays(rtnDate, super.getRecord1()[0].getEstimatedReturnDate());

            rentalFee        = estimatedRentDays * 554;
            lateFee          = lateDays * 662;
            lateFee          = estimatedRentDays * 662;
            this.setStatus(Status.Available);
//            RentalRecord rr = super.getRecord1()[0];
//            rr.setActualReturnDate(rtnDate);
//            rr.setRentalFee(rentalFee);
//            rr.setLateFee(lateFee);
            this.getRecord1()[targetIndex].setRentalFee(rentalFee);
            this.getRecord1()[targetIndex].setLateFee(lateFee);
            this.getRecord1()[targetIndex].printRecord();
            this.showPropertyStatus();

        }else{
            System.out.println("Return failed.");
        }
        return null;
    }

    /*  be called on RentalProperty , Override here
     * being rented-------return false
     * underMaintenance || ready to maintenance -------return true
     */
    @Override
    public boolean performMaintenance() {
        if(this.getStatus() == Status.Rented){
            return false;
        }else if(this.getStatus() == Status.UnderMaintenance) {
            return true;
        }else{
            super.setStatus(Status.UnderMaintenance);
            return true;
        }
    }

    /*  be called on RentalProperty , Override here
     * being rented-------return false
     * has already maintenance-> Available -------return true
     */
    @Override
    public boolean completeMaintenance(DateTime completionDate) {
        if(super.getStatus() == Status.Rented){
            return false;
        }else if(super.getStatus() == Status.UnderMaintenance) {
            super.setStatus(Status.Available);
            return true;
        }
        return false;
    }

    /* be called on RentalRecord, override here
     * build and show the situation of permiumSuite As a String
     * return to the main method in RentalRecord
     */
    @Override
    public String toString() {
        //propertyId:streetNumber:streetName:suburb:propertyType:numOfBedRoom:status
        StringBuffer result = new StringBuffer();
        result.append(super.getPropertyId() + ":");
        result.append(super.getStreetNum() + ":");
        result.append(super.getStreetName() + ":");
        result.append(super.getClass().getName() + ":");
        result.append(super.getNumOfRoom() + ":");
        result.append(super.getStatus().toString()+ ":");
        result.append(this.lastMaintenanceDate.toString());
        return result.toString();
    }

    /*  be called on RentalProperty , Override here
     * all information about the rental property(ID,address,type,NumOfRoom,Status,last maintenance)
     including details about up to 10 most recent rental records of that property.*/
    @Override
    public String getDetails() {

        StringBuffer result = new StringBuffer();
/*	    Property ID: S_63WMSB
 *      Address: 63 Whiteman Street Southbank
 *		Type: Premium Suite
 *		Bedroom: 3
 *		Status: Rented
 *	    Last maintenance: 23/07/2018
 *		RENTAL RECORD
 *	    Record ID: S_63WMSB_CUS1108_29072018
 *		Rent Date: 29/07/2018
 *	    Estimated Return Date: 01/08/2018
 *	    --------------------------------------
 *		Record ID: S_63WMSB_CUS6237_23072018
 *		Rent Date: 23/07/2018
 *		Estimated Return Date: 26/07/2018
 *		Actual Return Date: 26/07/2018
 *		Rental Fee: 1662.00
 *		Late Fee: 0.00
 *		--------------------------------------
 */
        result.append("Property ID:	"+ super.getPropertyId());
        result.append("\nAddress:		" + super.getStreetNum() + " "
                + super.getStreetName() + " " + super.getSuburb());
        result.append("\nType:		    " + super.propertyType);
        result.append("\nBedroom:		" + this.getNumOfRoom());
        result.append("\nStatus:		    " + this.getStatus());
        result.append("\nLast Maintenance:	" + this.lastMaintenanceDate.toString());
        //if(super.getRecord().isEmpty()
        if(super.getRecord1()[0]== null){
            result.append("\nRENTAL RECORD:		empty" );
            result.append("\n--------------------------------------");
        }else{
            result.append("\nRENTAL RECORD");
            //super.getRecord().size()
            for(int i = 0; i < super.getNumberOfRecords(); i++ ){
                //result.append(super.getRecord().get(i).getDetails())
                result.append(this.getRecord1()[i].getDetails());

                result.append("\n--------------------------------------");
            }
        }
        return result.toString();
    }
}
