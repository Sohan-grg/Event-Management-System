package Model;

public class Registration {

    private String name;
    private String eventId;
    private String eventName;
    private String contact;
    private String email;
    private int noOfPeople;

    public Registration(String name, String eventId, String eventName,
                        String contact, String email, int noOfPeople) {
        this.name = name;
        this.eventId = eventId;
        this.eventName = eventName;
        this.contact = contact;
        this.email = email;
        this.noOfPeople = noOfPeople;
    }

    // Getters
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

    public int getNoOfPeople() {
        return noOfPeople;
    }
}
