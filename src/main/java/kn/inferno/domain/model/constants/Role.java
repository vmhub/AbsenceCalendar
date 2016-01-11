package kn.inferno.domain.model.constants;


public enum Role {
    TM(1), TL(2), WDA(3), SA(4), SE(5), QA(6);
    private int value;
    Role(int value) { this.value = value; }

    public int getValue() { return value; }

    public static Role parse(int id) {
        Role right = null;
        for (Role item : Role.values()) {
            if (item.getValue() == id) {
                right = item;
                break;
            }
        }
        return right;
    }
}
