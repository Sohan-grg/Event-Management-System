package Controller;

import Model.Event;
import Model.EventData;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * EventController handles all business logic related to events.
 * It acts as the Controller layer in the MVC architecture.
 */
public class EventController {

    // ADD EVENT
    public static boolean addEvent(Event event) {
        for (Event e : EventData.eventList) { // Loop through all existing events
            if (e.getId().equalsIgnoreCase(event.getId())) {// Compare existing ID with new event ID
                return false; // duplicate ID
            }
        }
        EventData.eventList.add(event);// No duplicate found, add event to list
        return true;
    }

    // Linear Search 
    public static ArrayList<Event> linearSearchMultiField(String keyword) {

    ArrayList<Event> results = new ArrayList<>();// List to store matching search results
    keyword = keyword.toLowerCase().trim();// Convert keyword to lowercase for case-insensitive search

    for (Event e : EventData.eventList) {

        // Extract searchable fields
        String title = e.getName().toLowerCase();      
        String location = e.getLocation().toLowerCase(); 
        // Extract year from date string (dd-MM-yyyy)
        String year = e.getDate().substring(6, 10);   

        // Check if keyword matches any field
        if (title.contains(keyword) ||
            location.contains(keyword) ||
            year.contains(keyword)) {

            // Add matching event to result list
            results.add(e);
        }
    }

    return results;
}

    //Binary Search
    public static Event binarySearchById(String searchId) {

    // Ensure list is sorted before binary search
    selectionSortById();

    int low = 0;
    int high = EventData.eventList.size() - 1;

    while (low <= high) {
        int mid = (low + high) / 2;
        Event midEvent = EventData.eventList.get(mid);

        int compare = midEvent.getId().compareTo(searchId);

        if (compare == 0) {
            return midEvent; // Found
        } else if (compare < 0) {
            low = mid + 1;   // Search right
        } else {
            high = mid - 1;  // Search left
        }
    }

    return null; // Not found
}


    // UPDATE EVENT
    public static boolean updateEvent(String id, String name, String location,
                                      String date, String deadline) {

        for (Event e : EventData.eventList) {
            // Check for matching Event ID
            if (e.getId().equalsIgnoreCase(id)) {

                // Update fields only if new values are provided
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
        Iterator<Event> iterator = EventData.eventList.iterator();// Create iterator for safe traversal

        while (iterator.hasNext()) {
            Event e = iterator.next();
            if (e.getId().equalsIgnoreCase(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    
    //Sorts events by Event ID using Selection Sort.
    public static void selectionSortById() {
        ArrayList<Event> list = EventData.eventList;
        int n = list.size();

        for (int i = 0; i < n - 1; i++) { // Outer loop selects position
            int minIndex = i;// Assume current index is minimum

            for (int j = i + 1; j < n; j++) { // Find smallest ID in remaining list
                if (list.get(j).getId()
                        .compareTo(list.get(minIndex).getId()) < 0) {
                    minIndex = j;
                }
            }

            Event temp = list.get(minIndex);// Swap smallest element with current position
            list.set(minIndex, list.get(i));
            list.set(i, temp);
        }
    }
    
    // Formatter used to convert String dates into LocalDate
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // ===============================
    // Merge Sort by Event Date
    // ===============================
    public static void mergeSortByDate() {
        EventData.eventList = mergeSort(EventData.eventList);
    }

    private static ArrayList<Event> mergeSort(ArrayList<Event> list) { // Recursive merge sort function
        if (list.size() <= 1) return list;// Base case: list with 0 or 1 element is already sorted

        int mid = list.size() / 2;// Divide list into two halves

        ArrayList<Event> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Event> right = new ArrayList<>(list.subList(mid, list.size()));

        return merge(mergeSort(left), mergeSort(right)); // Recursively sort and merge
    }

    private static ArrayList<Event> merge(ArrayList<Event> left,
                                      ArrayList<Event> right) { // Merge two sorted lists

    ArrayList<Event> result = new ArrayList<>();

    while (!left.isEmpty() && !right.isEmpty()) {  // Compare dates from both lists

        LocalDate leftDate = LocalDate.parse(
                left.get(0).getDate().trim(), FORMATTER);

        LocalDate rightDate = LocalDate.parse(
                right.get(0).getDate().trim(), FORMATTER);

        if (leftDate.isBefore(rightDate) || leftDate.isEqual(rightDate)) { // Add earlier date first
            result.add(left.remove(0));
        } else {
            result.add(right.remove(0));
        }
    }

    // Add remaining elements
    result.addAll(left);
    result.addAll(right);
    return result;
}

    //Sorts events alphabetically by name using Insertion Sort.
     
     
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
    
    public static int getTotalEvents() {
    return EventData.eventList.size();
}


//Counts events occurring today or in the future.
public static int getUpcomingEvents() {
    int count = 0;
    LocalDate today = LocalDate.now();

    for (Event e : EventData.eventList) {
        LocalDate eventDate =
                LocalDate.parse(e.getDate().trim(), FORMATTER);

        if (eventDate.isAfter(today) || eventDate.isEqual(today)) {
            count++;
        }
    }
    return count;
}

/**
     * Counts events that have already occurred.
     */
public static int getPastEvents() {
    int count = 0;
    LocalDate today = LocalDate.now();

    for (Event e : EventData.eventList) {
        LocalDate eventDate =
                LocalDate.parse(e.getDate().trim(), FORMATTER);

        if (eventDate.isBefore(today)) {
            count++;
        }
    }
    return count;
}

    
}
