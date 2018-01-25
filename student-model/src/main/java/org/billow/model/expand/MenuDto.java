package org.billow.model.expand;

import org.billow.model.domain.MenuBase;

public class MenuDto extends MenuBase {

    private static final long serialVersionUID = -5259281341286131357L;

    private String spreadName;
    private String validindName;

    public String getSpreadName() {
        return spreadName;
    }

    public void setSpreadName(String spreadName) {
        this.spreadName = spreadName;
    }

    public String getValidindName() {
        return validindName;
    }

    public void setValidindName(String validindName) {
        this.validindName = validindName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
