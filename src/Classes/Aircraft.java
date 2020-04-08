package Classes;

public abstract class Aircraft {
    protected Long id;
    protected String name;
    protected Coordinates coordinates;
    private static Long idCounter = 1l;

    // id Counter setup.
    private Long nextId(){
        return idCounter++;
    }

    // Constructor
    public Aircraft(String name, Coordinates coordinates){
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }

}
