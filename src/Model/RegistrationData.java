package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RegistrationData {

    public static ArrayList<Registration> registrationList = new ArrayList<>();
    
    // Queue for accepting registrations (FIFO)
    public static Queue<Registration> registrationQueue = new LinkedList<>();

    // Stack for undo delete (LIFO)
    public static Stack<Registration> deletedStack = new Stack<>();

    private static int regCounter = 1;   // ✅ ID counter

    public static String generateRegId() {
        return "REG-" + regCounter++;
    }
}
