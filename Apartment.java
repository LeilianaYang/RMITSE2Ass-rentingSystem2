import java.util.Scanner;

public class Apartment extends RentalProperty {

/*   private double dailyExpenses;
 *    private double lateFee;
 *   private DateTime rentTime;
 */



    public Apartment(String propertyId, String streetNum, String streetNmame, String suburb, int numOfRoom) {
        super.setPropertyId(propertyId);
        super.setStreetNum(streetNum);
        super.setStreetName(streetNmame);
        super.setSuburb(suburb);
        super.setNumOfRoom(numOfRoom);
        super.setStatus(Status.Available);
        //super.setRecord(new LinkedList<>());
        super.setRecord1(new RentalRecord[10]);

        super.propertyType = "Apartment";
    }

    /* be called on RentalProperty , Override here
     * be rented ----return false
     * can be rent ,
     * 1. updating Status
     * 2.create new record
     * 3.uodating record[]
     * show successfully -----return true */
    @Override
    public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
        if(super.getStatus() != Status.Available){
            return false;
        }else{
            /*always add retalreord as the first one (position 0 ) )*/
            setStatus(Status.Rented);
            //super.getRecord().add(0,new RentalRecord(customerId, rentDate, numOfRentDay));
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
     public Object returnProperty()  {
        //int diffDays(DateTime endDate, DateTime startDate)
        //getRecord().getFirst() = getRecord1()[0]
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

            switch(this.getNumOfRoom()) {
                case 1:
                    rentalFee = estimatedRentDays * 143;
                    lateFee = lateDays * 143 * 1.15;
                    lateFee = estimatedRentDays*1.15;

                case 2:
                    rentalFee = estimatedRentDays * 210;
                    lateFee = lateDays * 210 * 1.15;
                    lateFee = estimatedRentDays*1.15;
                case 3:
                    rentalFee = estimatedRentDays * 319;
                    lateFee = lateDays * 319 * 1.15;
                    lateFee = estimatedRentDays*1.15;
            }
            this.setStatus(Status.Available);
//            RentalRecord rr = super.getRecord1()[0];
//
//            rr.setRentalFee(rentalFee);
//            rr.setLateFee(lateFee);
//            rr.setActualReturnDate(rtnDate);
            this.getRecord1()[targetIndex].setRentalFee(rentalFee);
            this.getRecord1()[targetIndex].setLateFee(lateFee);

            this.getRecord1()[targetIndex].printRecord();
            this.showPropertyStatus();


        }else {
            System.out.println("Return failed.");

        }

        return null;
    }


    /*  be called on RentalProperty , Override here
     * being rented-------return false
     * underMaintenance || ready to maintenance -------return true*/
    @Override
    public boolean performMaintenance() {
        if(super.getStatus() == Status.Rented){
            return false;
        }else if(super.getStatus() == Status.UnderMaintenance){
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
     * build and show the situation of Apartment As a String
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
        result.append(super.getStatus().toString());
        return result.toString();
    }


    /*  be called on RentalProperty , Override here
     * all information about the rental property(ID,address,type,NumOfRoom,Status,last maintenance)
     including details about up to 10 most recent rental records of that property.
     */
    @Override
    public String getDetails() {
       StringBuffer result = new StringBuffer();

/*		Property ID: A_108CRSB
 *      Address: 108 City Road Southbank
 *		Type: Apartment
 *		Bedroom: 2
 *		Status: Available
 *      RENTAL RECORD: empty
*/

       result.append("Property ID:	" + super.getPropertyId());
       result.append("\nAddress:		" + super.getStreetNum() + " "
               + this.getStreetName() + " " + super.getSuburb());
       result.append("\nType:		    " + super.propertyType);
       result.append("\nBedroom:		" + super.getNumOfRoom());
       result.append("\nStatus:		    " + super.getStatus());
       //super.getRecord().isEmpty()
       if(super.getRecord1()[0]== null){
           result.append("\nRENTAL RECORD:    empty");
           result.append("\n--------------------------------------");
        }else{
           result.append("\nRENTAL RECORD");
           for(int i = 0;i<super.getNumberOfRecords();i++){
               result.append(this.getRecord1()[i].getDetails());
               result.append("\n--------------------------------------");
           }
        }
        return result.toString();

    }


}

