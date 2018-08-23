public class RentalRecord {
    private String recordId;
    private DateTime rentDate;
    private DateTime estimatedReturnDate;
    private DateTime actualReturnDate;
    private double rentalFee;
    private double lateFee;
    private boolean inUse;

    /* change recordId into propertyId_
     */
    public RentalRecord(String propertyId){
        this.recordId = propertyId + "_";
    }

    /* Constructor-RentalRecord to create 3 parameters，
     * recordId :"A_442ESME_ANNA_12121998"
     * rentdate
     * estimatedReturnDate and actualReturnDate
     */
    public RentalRecord(String customerId, DateTime rentDate, int numOfRentDay) {
        int rentalLength;

        //test recordId!!!!!!!!!!!!!!!!!!!ANNA_12121998
        this.recordId = customerId.concat("_" + rentDate.toString());
        //test recordId!!!!!!!!!!!!!!!A_442ESME_ANNA_12121998
        //this.recordId = recordId + propertyId ;
        this.rentDate = rentDate;
        this.estimatedReturnDate = new DateTime(rentDate, numOfRentDay);
        this.actualReturnDate = null;
        this.inUse = true;
    }

    // If a record is turned off, it still can be showed but can not be returned.
    void turnOffRecord() {
        this.inUse = false;
    }

    boolean getInUse() {
        return this.inUse;
    }

    public String getRecordId() {
        return recordId;
    }

    public DateTime getRentDate() {
        return rentDate;
    }

    public DateTime getEstimatedReturnDate() {
        return estimatedReturnDate;
    }

    public DateTime getActualReturnDate() {
        return actualReturnDate;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public double getLateFee() {
        return lateFee;
    }

    public void setActualReturnDate(DateTime actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }



    /* build and show the situation of permiumSuite As a String  @Override premiumSuite @override Apartment
     * instantiation the StringBuilder-result,
     * and convert the value into String,
     * and using append() to add String
     like "recordId:rentDate:estimatedReturnDate:actualReturnDate:rentalFee:lateFee"
     * and do a judging whether actualReturnDate==null or not
     * return String=result
     */

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(recordId).append(":");
        //recordId:rentDate:estimatedReturnDate:actualReturnDate:rentalFee:lateFee
        result.append(rentDate.toString()).append(":");
        result.append(estimatedReturnDate.toString()).append(":");
        if (actualReturnDate != null) {
            result.append(actualReturnDate.toString()).append(":");
            result.append(rentalFee).append(":");
            result.append(lateFee);
        } else {
            result.append("none:none:none");
        }
        return result.toString();
    }

    /*show all the detail as a String , @Override premiumSuite @override Apartment
    * differentiate been rented & been returned  */
    public String getDetails() {

            StringBuilder result = new StringBuilder();
		/*
		Record ID: A_108CRSB_e73581_15072018
		Rent Date: 15/07/2018
		Estimated Return Date: 16/07/2018
		*/
            result.append("\nRecord ID:		").append(recordId);
            result.append("\nRent Date:		" + rentDate.toString());
            result.append("\nEstimated Return Date:		" + estimatedReturnDate.toString());


        /*
		Actual Return Date: 18/07/2018
		Rental Fee: 957.00
		Late Fee: 0.00
		 */
            if (actualReturnDate != null) {
                result.append("\nAcutal Return Date:		").append(actualReturnDate.toString());
                result.append("\nRental Fee:		").append(rentalFee);
                result.append("\nLate Fee:		").append(rentalFee);
            }
            return result.toString();
        }

    void printRecord() {
        System.out.println("Record ID: " + this.recordId);
        System.out.println("Rent Date: " + this.rentDate.getFormattedDate());
        System.out.println("Estimated Return Date: " + this.estimatedReturnDate.getFormattedDate());
        if (actualReturnDate == null) {
            System.out.println("Actual Return Date: Currently in reservation.");
        }
        else {
            System.out.println("Actual Return Date: " + this.actualReturnDate.getFormattedDate());
        }
        System.out.println("Rental Fee: " + String.format("%.2f", this.rentalFee));
        System.out.println("Late Fee: " + String.format("%.2f", this.lateFee));
    }

    // can only be used to change estimated return date in maintenance record, so the customers don't need to wait for the maintenance date to be over.
    void setEstRtnDateForMaintenanceRecord(DateTime dateTime) {
        this.estimatedReturnDate = dateTime;
    }
}





