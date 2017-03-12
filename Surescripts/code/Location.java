package sure;


public class Location {

    private String name;
    private int onHand;

    //create a location with the name of it, with the amount it has on hand (of a certain book)
    public Location (String name, int onHand){
        this.name = name;
        this.onHand = onHand;
    }

    public String getName() {
        return name;
    }

    public int getOnHand() {
        return onHand;
    }
    
    //check if this location equals that location
    public boolean equals(Object o){
        Location temp = (Location)o;

        return (this.name.equals(temp.name))&&(this.onHand == temp.onHand);
    }
}
