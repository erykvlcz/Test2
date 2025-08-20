package pl.kurs.ex1.models.enums;

public enum Sex {
    FEMALE, MALE ;

    public static Sex setSexFromString(String sex){
        if(sex == null){
            throw new IllegalArgumentException("Sex can not be null!");
        }
        switch (sex.trim().toLowerCase()){
            case "s": return MALE;
            case "c": return FEMALE;
            default: throw new IllegalArgumentException("Unknown sex: " + sex);
        }
    }
}
