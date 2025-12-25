/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.Event;
import Model.EventData;

public class EventController {

    // Search event by ID or Name
    public static Event searchEvent(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return null;
        }

        for (Event e : EventData.eventList) {
            if (e.getId().equalsIgnoreCase(keyword)
                    || e.getName().equalsIgnoreCase(keyword)) {
                return e;
            }
        }
        return null; // not found
    }
}





