package com.techsupport.crud.techsupportCRUD.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class UrgentSupportRequest extends SupportRequest{

    @Override
    public boolean isUrgent(){
        return true;
    }

    @Override
    public String getRequestType() {
        return "Urgent Request";
    }
}
