package Contact;

public class Contact {
    private String name;

    private String number;

    @Override
    public String toString() {
        return  name + " | " + number ;
    }
//accessors(getters and setters)

    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String newNumber){
        this.number = newNumber;
    }
}
