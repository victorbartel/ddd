package org.ddd.samples.viewmodels;

import static com.google.common.base.Strings.isNullOrEmpty;

public class InventoryItemViewModel {
    private String title;
    private int count;

    public boolean isValid() {
        return !isNullOrEmpty(title) && count > 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
