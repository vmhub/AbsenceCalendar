package kn.inferno.domain.model.constants;


public enum Location {
    HAM(1), TLL(2);
    private int value;
    Location(int value) { this.value = value; }

    public int getValue() { return value; }

    public static Location parse(int id) {
        Location right = null;
        for (Location item : Location.values()) {
            if (item.getValue() == id) {
                right = item;
                break;
            }
        }
        return right;
    }
}
