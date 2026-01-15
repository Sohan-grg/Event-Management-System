package Controller;

import Model.*;
import javax.swing.JOptionPane;

public class RegistrationController {
 
    public static boolean registerUser(
        String name,
        String eventId,
        String eventName,
        String contact,
        String email,
        String noOfPeople) {

    // Basic validation
    if (name.isEmpty() || eventId.isEmpty() || eventName.isEmpty()
            || contact.isEmpty() || email.isEmpty() || noOfPeople.isEmpty()) {

        JOptionPane.showMessageDialog(null, "Please fill all required fields");
        return false;
    }

    // 🔍 Validate Event ID
    Event event = findEventById(eventId);

    if (event == null) {
        JOptionPane.showMessageDialog(
                null,
                "Invalid Event ID. This event does not exist."
        );
        return false;
    }

    // 🔍 Validate Event Name matches Event ID
    if (!event.getName().equalsIgnoreCase(eventName)) {
        JOptionPane.showMessageDialog(
                null,
                "Event name does not match the selected Event ID."
        );
        return false;
    }

    // ✅ AUTO-GENERATE REG ID
    String regId = RegistrationData.generateRegId();

    Registration reg = new Registration(
            regId,
            name,
            eventId,
            eventName,
            contact,
            email,
            noOfPeople
    );

    // ENQUEUE (FIFO)
if (RegistrationData.rear == RegistrationData.QUEUE_SIZE - 1) {
    JOptionPane.showMessageDialog(null, "Registration queue is full");
    return false;
}

if (RegistrationData.front == -1) {
    RegistrationData.front = 0;
}

RegistrationData.rear++;
RegistrationData.queue[RegistrationData.rear] = reg;


    JOptionPane.showMessageDialog(
            null,
            "Registration Successful!\nRegistration ID: " + regId
    );

    return true;
}

    

       
    
    private static Event findEventById(String eventId) {
    for (Event e : Model.EventData.eventList) {
        if (e.getId().equals(eventId)) {
            return e;
        }
    }
    return null;
}
    
 public static Registration acceptNextRegistration() {

    if (RegistrationData.front == -1) {
        return null;
    }

    Registration accepted =
            RegistrationData.queue[RegistrationData.front];

    RegistrationData.queue[RegistrationData.front] = null;
    RegistrationData.front++;

    if (RegistrationData.front > RegistrationData.rear) {
        RegistrationData.front = RegistrationData.rear = -1;
    }

    return accepted;
}

    
    // Delete registration and push to Stack
    public static boolean deleteRegistration(String regId) {
        for (Registration r : RegistrationData.registrationList) {
            if (r.getRegId().equals(regId)) {

                // Push to stack before deleting
                RegistrationData.deletedStack.push(r);

                RegistrationData.registrationList.remove(r);
                return true;
            }
        }
        return false;
    }
    
    // Undo last deleted registration
    public static boolean undoDelete() {
        if (!RegistrationData.deletedStack.isEmpty()) {
            Registration lastDeleted = RegistrationData.deletedStack.pop();
            RegistrationData.registrationList.add(lastDeleted);
            return true;
        }
        return false;
    }
    
    public static int getTotalRegistrations() {
    return RegistrationData.registrationList.size();
}

}
