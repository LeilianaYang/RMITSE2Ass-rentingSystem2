import java.util.Scanner;

public abstract class RentalProperty {

    /*if change into private ,can not edit it in subclass*/
    protected enum Status{Rented, Available, UnderMaintenance};
    private   String propertyId;
    private   String streetNum;
    private   String streetName;
    private   String suburb;
    private   int    numOfRoom;
    protected String propertyType;
    private   Status status;
    //private   LinkedList<RentalRecord> record;
    private   RentalRecord[] record1 = new RentalRecord[10] ;
    //RentalRecord[] rd = new RentalRecord[10];


    public String getPropertyId() {
        return propertyId;
    }
    public String getStreetNum() {
        return streetNum;
    }
    public String getStreetName() {
        return streetName;
    }
    public String getSuburb() {
        return suburb;
    }
    public int getNumOfRoom() {
        return numOfRoom;
    }

    RentalRecord[] getRecord1() {
        return record1;
    }

    public Status getStatus() {
        return status;
    }

//    public LinkedList<RentalRecord> getRecord() { return record; }


    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }
    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }
    public void setStreetName(String streetNmame) {
        this.streetName = streetNmame;
    }
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }
    public void setNumOfRoom(int numOfRoom) {
        this.numOfRoom = numOfRoom;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public void setRecord1(RentalRecord[] record1) {
        this.record1 = record1;
    }


   // public void setRecord(LinkedList<RentalRecord> record) { this.record = record;   }



    /*public RentalProperty(String propertyId, int numOfRoom) {
        this.propertyId = propertyId;
        this.numOfRoom = numOfRoom;
        this.propertyType = null;
    }*/



    public abstract boolean rent(String customerId, DateTime rentDate, int numOfRentDay);
    public abstract Object returnProperty();
    public abstract boolean performMaintenance();
    public abstract boolean completeMaintenance(DateTime completionDate);
    public abstract String toString();

    public abstract String getDetails();


/*   due to subclass do not need super.attr, so do not need to write constructor
 *   public RentalProperty(String propertyId, String streetNum, String streetNmame, String suburb, int numOfRoom) {
 *       this.propertyId = propertyId;
 *       this.streetNum = streetNum;
 *       this.streetNmame = streetNmame;
 *     this.suburb = suburb;
 *     this.numOfRoom = numOfRoom;
 *     this.propertyStatus = true;
 *   }
 * public void rentOff(){
        this.propertyStatus = false;
    }
 */

//add Array record ,and count only 10 most newly recent recordsand
//    rd[9]
//
//            ;
//    rd[0] --> rd[1];
//    rd[0] = new RentalRecord();
//    rd[0].getRecordId()
    public void addRecord(RentalRecord r){
        int i =9;
        while(i >=1 ){
            this.record1[i] = this.record1[i-1];
            i--;
        }
        this.record1[0] = r;
    }

    int getNumberOfRecords() {
        int numOfRecords = 0;
        int i;
        for (i = 0; i < 10; i++) {
            if (this.record1[i] != null) {
                numOfRecords += 1;
            }
            else {
                break;
            }
        }
        return numOfRecords;
    }


    int findRentalRecord(String recordId) {
        int end = this.getNumberOfRecords();
        int i;
        boolean ifFound = false;
        for (i = 0; i < end; i++) {
            if (recordId.toUpperCase().equals(this.record1[i].getRecordId().toUpperCase())) {
                ifFound = true;
                break;
            }
        }
        if (ifFound) {
            return i;
        }
        else {
            System.out.println("Record does not exist!");
            i = -1;
            return i;
        }
    }

    void showPropertyStatus() {
        System.out.println("Property ID: " + this.propertyId);
        System.out.println("Address: " + this.streetNum + " " + this.streetName + " " + this.suburb);

        System.out.println("Bedroom: " + this.numOfRoom);
        if (this.status == Status.Available) {
            System.out.println("Status: Available");
        }
        else {
            System.out.println("Status: Unavailable");
        }
    }



}




