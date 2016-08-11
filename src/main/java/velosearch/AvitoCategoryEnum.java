package velosearch;

/**
 * Created by Vitek000 on 11.08.2016.
 */
public enum AvitoCategoryEnum {

    VELOSIPEDI(getNextId(), "Велосипеды"),
    TOVARI_DLY_DETEY(getNextId(), "Товары для детей и игрушки"),
    SPORT_I_OTDIH(getNextId(), "Спорт и отдых"),
    ZAPCHASTI_I_AKSESS(getNextId(), "Запчасти и аксессуары"),
    KVARTIRI(getNextId(), "Квартиры"),
    KOLLEKTSIONIROVANIE(getNextId(), "Коллекционирование"),
    UNKNOWN_CATEGORY(getNextId(), "Неизвестная категория!!!")


    ;

    public static int id = 1;
    public static int getNextId() {
        return id++;
    }

    public static AvitoCategoryEnum findByName(String name) {
        AvitoCategoryEnum[] values = AvitoCategoryEnum.values();
        for (AvitoCategoryEnum value : values) {
            if(value.name.equals(name)) {
                return value;
            }
        }

        return UNKNOWN_CATEGORY;

    }

    private int index;
    private String name;

    AvitoCategoryEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
