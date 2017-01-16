package org.ddd.samples.viewmodels;

import com.google.common.base.Strings;

public class EmployeeViewModel {

    private String firstName;
    private String lastName;

    public boolean isValid() {
        return !isNullOrEmpty(firstName) && !isNullOrEmpty(lastName);
    }

    private boolean isNullOrEmpty(final String string) {
        return Strings.isNullOrEmpty((string +"").trim());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
