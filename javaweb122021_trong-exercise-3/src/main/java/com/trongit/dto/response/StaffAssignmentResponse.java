package com.trongit.dto.response;

import com.trongit.dto.UserDTO;

public class StaffAssignmentResponse extends UserDTO {
    private String checked = "";

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
