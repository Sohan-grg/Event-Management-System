package Controller;

import Model.Event;
import Model.EventData;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    
    public static void selectionSortById() {
        ArrayList<Event> list = EventData.eventList;
        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (list.get(j).getId()
                        .compareTo(list.get(minIndex).getId()) < 0) {
                    minIndex = j;
                }
            }

            Event temp = list.get(minIndex);
            list.set(minIndex, list.get(i));
            list.set(i, temp);
        }
    }
    
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // ===============================
    // Merge Sort by Event Date
    // ===============================
    public static void mergeSortByDate() {
        EventData.eventList = mergeSort(EventData.eventList);
    }

    private static ArrayList<Event> mergeSort(ArrayList<Event> list) {
        if (list.size() <= 1) return list;

        int mid = list.size() / 2;

        ArrayList<Event> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Event> right = new ArrayList<>(list.subList(mid, list.size()));

        return merge(mergeSort(left), mergeSort(right));
    }

    private static ArrayList<Event> merge(ArrayList<Event> left,
                                      ArrayList<Event> right) {

    ArrayList<Event> result = new ArrayList<>();

    while (!left.isEmpty() && !right.isEmpty()) {

        LocalDate leftDate = LocalDate.parse(
                left.get(0).getDate().trim(), FORMATTER);

        LocalDate rightDate = LocalDate.parse(
                right.get(0).getDate().trim(), FORMATTER);

        if (leftDate.isBefore(rightDate) || leftDate.isEqual(rightDate)) {
            result.add(left.remove(0));
        } else {
            result.add(right.remove(0));
        }
    }

    result.addAll(left);
    result.addAll(right);
    return result;
}

    public static void insertionSortByName() {
        ArrayList<Event> list = EventData.eventList;

        for (int i = 1; i < list.size(); i++) {
            Event key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                   list.get(j).getName()
                   .compareToIgnoreCase(key.getName()) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }

    
}
