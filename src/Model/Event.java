package Model;

public class Event {
    private String id;
    private String name;
    private String location;
    private String date;
    private String deadline;

    public Event(String id, String name, String location, String date, String deadline) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.deadline = deadline;
    }

    public String getId() { 
        return id; 
    }
    public String getName() { 
        return name; 
    }
    public String getLocation() { 
        return location; 
    }
    public String getDate() { 
        return date; 
    }
    public String getDeadline() { 
        return deadline; 
    }

    
    
    
    public void setId(String id) { 
        this.id = id; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    public void setLocation(String location) { 
        this.location = location; 
    }
    public void setDate(String date) { 
        this.date = date; 
    }
    public void setDeadline(String deadline) { 
        this.deadline = deadline; 
    } 
}
