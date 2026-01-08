package Controller;

import Model.Registration;
import Model.RegistrationData;

import javax.swing.JOptionPane;

public class RegistrationController {

    public static boolean registerUser(
            String name,
            String eventId,
            String eventName,
            String contact,
            String email,
            String noOfPeopleStr
    ) {
        // Validation
        if (name.isEmpty() || eventId.isEmpty() || eventName.isEmpty()
                || contact.isEmpty() || email.isEmpty() || noOfPeopleStr.isEmpty()) {

            JOptionPane.showMessageDialog(null,
                    "Please fill all fields",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int noOfPeople;
        try {
            noOfPeople = Integer.parseInt(noOfPeopleStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "No of People must be a number",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Create model object
        Registration reg = new Registration(
                name, eventId, eventName, contact, email, noOfPeople
        );

        // Store data in model
        RegistrationData.registrationList.add(reg);

        JOptionPane.showMessageDialog(null,
                "Registration Successful!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        return true;
    }
}
