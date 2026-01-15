package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RegistrationData {

    public static ArrayList<Registration> registrationList = new ArrayList<>();
    
    // ===== QUEUE =====
    public static final int QUEUE_SIZE = 50;
    public static Registration[] queue = new Registration[QUEUE_SIZE];
    public static int front = -1;
    public static int rear = -1;

    // ===== STACK =====
    public static final int STACK_SIZE = 50;
    public static Registration[] stack = new Registration[STACK_SIZE];
    public static int top = -1;
    
    // Stack for undo delete (LIFO)
    public static Stack<Registration> deletedStack = new Stack<>();

    private static int regCounter = 1;

    public static String generateRegId() {
        return String.valueOf(regCounter++);
    }
}
