package kn.inferno.domain.model.constants;

// think about variables
public enum AbsenceTypeColor {

    Green(1), LightGreen(2), LightBlue(3), Orng(4), Orang(5), Orangg(6), Orange(7);

    private int value;
    AbsenceTypeColor(int value) { this.value = value; }

    public int getValue() { return value; }

    public static AbsenceTypeColor parse(int id) {
        AbsenceTypeColor right = null;
        for (AbsenceTypeColor item : AbsenceTypeColor.values()) {
            if (item.getValue() == id) {
                right = item;
                break;
            }
        }
        return right;
    }
}
