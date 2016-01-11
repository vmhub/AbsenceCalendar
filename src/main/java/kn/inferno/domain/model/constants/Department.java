package kn.inferno.domain.model.constants;


public enum Department {
    GI_PK(1);
    private int value;
    Department(int value) { this.value = value; }

    public int getValue() { return value; }

    public static Department parse(int id) {
        Department right = null;
        for (Department item : Department.values()) {
            if (item.getValue() == id) {
                right = item;
                break;
            }
        }
        return right;
    }
}
