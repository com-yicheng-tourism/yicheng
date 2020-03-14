package com.yicheng.tourism.base.enums;

public enum CycleEnums {

    DAY("d", 0, "天", 1),
    WEEK("w", 2, "周", 7),
    YEAR("y", 1, "年", 365),
    MONTH("m", 3, "非自然月", 30),
    QUARTER("q", 4, "季度", 90),
    NATURAL_MONTH("cm", 5, "自然月", 1);

    private String code;
    private Integer cycle;
    private String desc;
    private int day;


    CycleEnums(String code, Integer cycle, String desc, int day) {
        this.code = code;
        this.cycle = cycle;
        this.desc = desc;
        this.day = day;
    }

    public static Integer getCycle(String code) {
        for (CycleEnums enums : values()) {
            if (enums.getCode().equals(code)) {
                return enums.getCycle();
            }

        }

        return null;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
