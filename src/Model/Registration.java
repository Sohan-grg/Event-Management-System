package Model;

public class Registration {

    private String regId;   
    private String name;
    private String eventId;
    private String eventName;
    private String contact;
    private String email;
    private String noOfPeople;

    public Registration(String regId, String name, String eventId,
                        String eventName, String contact,
                        String email, String noOfPeople) {

        this.regId = regId;
        this.name = name;
        this.eventId = eventId;
        this.eventName = eventName;
        this.contact = contact;
        this.email = email;
        this.noOfPeople = noOfPeople;
    }

    // GETTERS
    public String getRegId() {
        return regId;
    }

    public String getName() {
        return name;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getNoOfPeople() {
        return noOfPeople;
    }
}
