/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author sohangurung
 */

public class Registration {
    private String participantName;
    private Event event;

    public Registration(String participantName, Event event) {
        this.participantName = participantName;
        this.event = event;
    }

    public String getParticipantName() { return participantName; }
    public Event getEvent() { return event; }
}

