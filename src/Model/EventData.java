package Model;

import java.util.ArrayList;

public class EventData {
    public static ArrayList<Event> eventList = new ArrayList<>();
    
     static {
        eventList.add(new Event("2", "Tech Conference", "New York", "12-03-2026", "05-03-2026"));
        eventList.add(new Event("1", "Music Festival", "Los Angeles", "20-02-2026", "27-02-2026"));
        eventList.add(new Event("4", "Art Expo", "San Francisco", "01-05-2026", "25-04-2026"));
        eventList.add(new Event("3", "Food Carnival", "Chicago", "10-06-2026", "05-06-2026"));
        eventList.add(new Event("5", "Startup Pitch", "Boston", "15-07-2026", "10-07-2026"));
    }
}
