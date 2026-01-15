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

    // Check if queue is empty
    if (RegistrationData.front == -1) {
        JOptionPane.showMessageDialog(null, "No registrations to delete");
        return false;
    }

    for (int i = RegistrationData.front; i <= RegistrationData.rear; i++) {

        Registration r = RegistrationData.queue[i];

        if (r != null && r.getRegId().equals(regId)) {

            // STACK FULL CHECK
            if (RegistrationData.top == RegistrationData.STACK_MAX - 1) {
                JOptionPane.showMessageDialog(null, "Delete stack is full");
                return false;
            }

            // PUSH TO STACK
            RegistrationData.top++;
            RegistrationData.deletedStackArr[RegistrationData.top] = r;

            // REMOVE FROM QUEUE
            for (int j = i; j < RegistrationData.rear; j++) {
                RegistrationData.queue[j] = RegistrationData.queue[j + 1];
            }

            RegistrationData.queue[RegistrationData.rear] = null;
            RegistrationData.rear--;

            if (RegistrationData.rear < RegistrationData.front) {
                RegistrationData.front = RegistrationData.rear = -1;
            }

            return true;
        }
    }
    return false;
}

    
    // Undo last deleted registration
    public static boolean undoDelete() {

    if (RegistrationData.top == -1) {
        JOptionPane.showMessageDialog(null, "No deleted registration to undo");
        return false;
    }

    // POP FROM STACK
    Registration r = RegistrationData.deletedStackArr[RegistrationData.top];
    RegistrationData.deletedStackArr[RegistrationData.top] = null;
    RegistrationData.top--;

    // ENQUEUE BACK
    if (RegistrationData.rear == RegistrationData.QUEUE_SIZE - 1) {
        JOptionPane.showMessageDialog(null, "Queue full, cannot undo");
        return false;
    }

    if (RegistrationData.front == -1) {
        RegistrationData.front = 0;
    }

    RegistrationData.rear++;
    RegistrationData.queue[RegistrationData.rear] = r;

    return true;
}

    
    public static int getTotalRegistrations() {
    return RegistrationData.registrationList.size();
}

}
