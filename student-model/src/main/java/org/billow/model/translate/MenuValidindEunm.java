package org.billow.model.translate;


public enum MenuValidindEunm {

    VALIDIND_TRUE(true, "有效"), VALIDIND_FALSE(false, "无效");

    private Object codeCode;
    private String codeName;

    MenuValidindEunm(Object codeCode, String codeName) {
        this.codeCode = codeCode;
        this.codeName = codeName;
    }


    public static String getCodeName(Object codeCode) {
        for (MenuValidindEunm spreadEunm : MenuValidindEunm.values()) {
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
