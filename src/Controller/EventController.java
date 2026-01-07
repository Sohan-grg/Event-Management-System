package Controller;

import Model.Event;
import Model.EventData;
import java.util.Iterator;

public class EventController {

    // ADD EVENT
    public static boolean addEvent(Event event) {
        for (Event e : EventData.eventList) {
            if (e.getId().equalsIgnoreCase(event.getId())) {
                return false; // duplicate ID
            }
        }
        EventData.eventList.add(event);
        return true;
    }

    // SEARCH EVENT (by ID or Name)
    public static Event searchEvent(String keyword) {
        for (Event e : EventData.eventList) {
            if (e.getId().equalsIgnoreCase(keyword) ||
                e.getName().equalsIgnoreCase(keyword)) {
                return e;
            }
        }
        return null;
    }

    // UPDATE EVENT
    public static boolean updateEvent(String id, String name, String location,
                                      String date, String deadline) {

        for (Event e : EventData.eventList) {
            if (e.getId().equalsIgnoreCase(id)) {

                if (!name.isEmpty()) e.setName(name);
                if (!location.isEmpty()) e.setLocation(location);
                if (!date.isEmpty()) e.setDate(date);
                if (!deadline.isEmpty()) e.setDeadline(deadline);

                return true;
            }
        }
        return false;
    }

    // DELETE EVENT (by ID)
    public static boolean deleteEvent(String id) {
        Iterator<Event> iterator = EventData.eventList.iterator();

        while (iterator.hasNext()) {
            Event e = iterator.next();
            if (e.getId().equalsIgnoreCase(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
