package com.techsupport.crud.techsupportCRUD.service;

import com.techsupport.crud.techsupportCRUD.model.SupportRequest;
import com.techsupport.crud.techsupportCRUD.model.UrgentSupportRequest;
import com.techsupport.crud.techsupportCRUD.repo.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    RequestRepo requestRepo;

    public List<SupportRequest> getAllRequests(){
        List<SupportRequest> requests = new ArrayList<>(requestRepo.findAll());
        return requests;
    }

    public SupportRequest getRequestById(Long id){
        return requestRepo.findById(id).get();
    }

    public boolean saveRequest(SupportRequest request){
        if(request.isUrgent()){
            UrgentSupportRequest urgentRequest = new UrgentSupportRequest();
            urgentRequest.setName(request.getName());
            urgentRequest.setPhoneNumber(request.getPhoneNumber());
            urgentRequest.setEmail(request.getEmail());
            urgentRequest.setDeviceType(request.getDeviceType());
            urgentRequest.setIssueDescription(request.getIssueDescription());
            urgentRequest.setStatus("Open");
            urgentRequest.setDateCreated(LocalDateTime.now());
            requestRepo.save(urgentRequest);
            return true;
        }
        request.setStatus("Open");
        request.setDateCreated(LocalDateTime.now());
        requestRepo.save(request);

        if (requestRepo.findById(request.getId()).isPresent()){
            return true;
        }

        return false;
    }

    public boolean deleteRequest(Long id){
        if(getRequestById(id) != null){
            requestRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<SupportRequest> findRequestByName(String keyword){
        if(keyword != null){
            return requestRepo.search(keyword);
        }
        return getAllRequests();
    }

    public boolean editRequest(Long id, String status){
        SupportRequest request = getRequestById(id);
        if(getRequestById(id) != null){
            request.setStatus(status);
            requestRepo.save(request);
            return true;
        }
        return false;
    }

    public long requestCount(){
        return requestRepo.count();
    }

    public String countStatus(String status){
        return requestRepo.countStatus(status);
    }

    public String countDeviceType(String deviceType){
        return requestRepo.countDeviceType(deviceType);
    }
}
