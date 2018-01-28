package org.billow.model.translate;


public enum MenuSpreadEunm {

    SPREAD_TRUE(true, "是"), SPREAD_FALSE(false, "否");

    private Object codeCode;
    private String codeName;

    MenuSpreadEunm(Object codeCode, String codeName) {
        this.codeCode = codeCode;
        this.codeName = codeName;
    }


    public static String getCodeName(Object codeCode) {
        for (MenuSpreadEunm spreadEunm : MenuSpreadEunm.values()) {
            if (spreadEunm.getCodeCode().equals(codeCode)) {
                return spreadEunm.getCodeName();
            }
        }
        return null;
    }

    public Object getCodeCode() {
        return codeCode;
    }

    public String getCodeName() {
        return codeName;
    }
}
