package com.techsupport.crud.techsupportCRUD.repo;

import com.techsupport.crud.techsupportCRUD.model.SupportRequest;
import com.techsupport.crud.techsupportCRUD.model.TechnicianAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepo extends JpaRepository<TechnicianAccount, Long> {
    TechnicianAccount findByUsername(String username);

}
