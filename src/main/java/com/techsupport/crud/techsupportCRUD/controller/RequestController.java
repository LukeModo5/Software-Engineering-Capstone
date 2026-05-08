package com.techsupport.crud.techsupportCRUD.controller;


import com.techsupport.crud.techsupportCRUD.model.TechnicianAccount;
import com.techsupport.crud.techsupportCRUD.model.SupportRequest;
import com.techsupport.crud.techsupportCRUD.service.RequestService;
import com.techsupport.crud.techsupportCRUD.service.TechnicianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RequestController {

    @Autowired
    RequestService requestService;

    @GetMapping({"/", "/createRequest"})
    public String createRequest(@ModelAttribute("message") String message, Model model){
        if(!model.containsAttribute("request")){
            model.addAttribute("request", new SupportRequest());
        }
        return "createRequest";
    }

    @GetMapping({"/viewRequests"})
    public String viewRequests(@ModelAttribute("message") String message, Model model){
        List<SupportRequest> requestList = requestService.getAllRequests();

        model.addAttribute("reqList", requestList);
        model.addAttribute("message", message);

        return "viewRequests";
    }

    @GetMapping({"/searchRequests"})
    public String searchRequests(@RequestParam(name = "keyword", required = false) String keyword, Model model ){
        List<SupportRequest> requestList = requestService.findRequestByName(keyword);
        model.addAttribute("reqList", requestList);
        model.addAttribute("keyword", keyword);
        return "viewRequests";
    }

    @PostMapping("/saveRequest")
    public String saveRequest(@Valid SupportRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        //If user enters invalid info, save request object and binding result in flash attribute to keep request data and display error msg
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.request", bindingResult);
            redirectAttributes.addFlashAttribute("request", request);
            return "redirect:/createRequest";
        }
        if(requestService.saveRequest(request)){
            redirectAttributes.addFlashAttribute("message", "Request Saved");
            return "redirect:/createRequest";
        }
        redirectAttributes.addFlashAttribute("message", "Request Not Saved");
        return "redirect:/createRequest";
    }

    @GetMapping("/editRequest/{id}")
    public String editRequest(@ModelAttribute("message") String message, @PathVariable Long id, Model model){
        SupportRequest request = requestService.getRequestById(id);
        model.addAttribute("request", request);
        model.addAttribute("message", message);

        return "editRequest";
    }

    @PostMapping("/editSaveRequest")
    public String editSaveRequest(@ModelAttribute SupportRequest request, RedirectAttributes redirectAttributes){
        if(requestService.editRequest(request.getId(), request.getStatus())){
            redirectAttributes.addFlashAttribute("message", "Edit was successful");
            return "redirect:/viewRequests";
        }
        redirectAttributes.addFlashAttribute("message", "Edit was not successful");
        return "redirect:/editRequest/";
    }

    @PostMapping("/editStatus")
    public String editStatus(@RequestParam Long id, @RequestParam String status, RedirectAttributes redirectAttributes){
        if(requestService.editRequest(id, status)){
            redirectAttributes.addFlashAttribute("message", "Edit was successful");
            return "redirect:/viewRequests";
        }
        redirectAttributes.addFlashAttribute("message", "Edit was not successful");
        return "redirect:/viewRequests/";
    }

    @GetMapping("/deleteRequest/{id}")
    public String deleteRequest(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if(requestService.deleteRequest(id)){
            redirectAttributes.addFlashAttribute("message", "Delete was successful");
            return "redirect:/viewRequests";
        }
        redirectAttributes.addFlashAttribute("message", "Delete was not successful");
        return "redirect:/viewRequests";
    }

    @GetMapping("/report")
    public String reportCounts(Model model){
        model.addAttribute("requestCount", requestService.requestCount());
        model.addAttribute("openStatusCount", requestService.countStatus("Open"));
        model.addAttribute("inProgressStatusCount", requestService.countStatus("In Progress"));
        model.addAttribute("closedStatusCount", requestService.countStatus("Closed"));
        model.addAttribute("phoneCount", requestService.countDeviceType("Phone"));
        model.addAttribute("tabletCount", requestService.countDeviceType("Tablet"));
        model.addAttribute("computerCount", requestService.countDeviceType("Computer"));
        model.addAttribute("tvCount", requestService.countDeviceType("Smart TV/TV"));
        model.addAttribute("internetCount", requestService.countDeviceType("Internet/Wi-Fi"));
        model.addAttribute("otherCount", requestService.countDeviceType("Other"));
        model.addAttribute("requestTodayCount", requestService.getRequestsCreatedToday().size());
        model.addAttribute("mostCommonDevice", requestService.getMostCommonDeviceType());
        model.addAttribute("mostRecentRequest", requestService.getMostRecentRequest());
        model.addAttribute("urgentRequestCount", requestService.getUrgentRequestCount());
        return "report";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
