package kn.inferno.domain.model.constants;

// TODO: absence type color and letter

public enum AbsenceType {
    Vacation(1), Offday(2), Training(3), SR_Airfreight(4), SR_Seafreight(5), SR_Overland(6), SR_Core(7);

    private int value;
    AbsenceType(int value) { this.value = value; }

    public int getValue() { return value; }

    public static AbsenceType parse(int id) {
        AbsenceType right = null;
        for (AbsenceType item : AbsenceType.values()) {
            if (item.getValue() == id) {
                right = item;
                break;
            }
        }
        return right;
    }
}

