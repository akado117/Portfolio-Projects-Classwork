package com.swcguild.addressbookfortheweb.controller;

import com.swcguild.addressbookfortheweb.dao.AddressDao;
import com.swcguild.addressbookfortheweb.model.Address;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddressController {
        
    AddressDao dao;
    
    @Inject
    public AddressController(AddressDao dao){
        this.dao = dao;
    }  
      
    
    @RequestMapping(value="/sayhi", method=RequestMethod.GET)
    public String sayHi(Map<String, Object> model) {
        model.put("message", "Hello from the controller" );
        return "hello";
    }
    
    @RequestMapping(value="/displayAddresses", method=RequestMethod.GET)
    public String getAddresses(Map<String,Object> model){
        
//        Address emptyAddy = new Address();
//        emptyAddy.setAddressId(0);
//        emptyAddy.setFirst("");
//        emptyAddy.setLast("");
//        emptyAddy.setStreet("");
//        emptyAddy.setCity("");
//        emptyAddy.setState("");
//        emptyAddy.setZip("");
        
        
        
        model.put("addresses",dao.getAll());
        model.put("numContacts",dao.size());
        model.put("search",new Address());
        model.put("address",new Address());
        
        return "displayAddress";
    }
    
    //displays the add an address form
     @RequestMapping(value = "/displayaddaddressform", method = RequestMethod.GET)
    public String displayNewAddressForm(Map<String, Object> model) {

        return "addAddressForm";
    }
    
      //the addContact relates to the action on the form
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addContact(Map<String, Object> model,
            HttpServletRequest req, //req holds all the form parameters by their name
            HttpServletResponse res) {

        String street = req.getParameter("addStreet"); //as shown holds parameter by name
        String first = req.getParameter("addFirst");
        String last = req.getParameter("addLast");
        String city = req.getParameter("addCity");
        String state = req.getParameter("addState");
        String zip = req.getParameter("addZip");
        Address address = new Address();

        address.setFirst(first);
        address.setLast(last);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);

        dao.add(address); //add it to the dao

        return "redirect:displayAddresses";

    }
    
    
    //used to create functionality for searching 
    @RequestMapping(value="/searchAddress", method=RequestMethod.POST)
    public String searchAddresses(@ModelAttribute("search") Address address,
                                    Map<String, Object> model){
        
        
        
        Address[] addys = dao.search(address);
        
        model.put("addresses", addys);
        
        return "displayAddress";
               
    }
            
         
    
    //used to delete an address, requests a parameter called addressId
    @RequestMapping(value = "/deleteAddress", method = RequestMethod.GET)
    public String deleteContact(@RequestParam("addressId") String id, 
                                Map<String, Object> model) {

        dao.remove(Integer.parseInt(id));

        return "redirect:displayAddresses"; 
    }
    
    //used to display a prefilled form with information for the address of interest
    @RequestMapping(value="/editAddress", method=RequestMethod.GET)
    public String displayEditAddress(@RequestParam("addressId") String id,
                                        Map<String,Object> model){
        
        Address addy = dao.get(Integer.parseInt(id));
        
        //puts the retrieved address into the model under the name of address
        model.put("address", addy);
        
        
        return "editAddressForm";
    }
    
    //grabs the address object from the form and applies it to the dao's update method
    @RequestMapping(value="updateAddress", method=RequestMethod.POST)
    public String updateAddress(@ModelAttribute("address") Address address, 
                                 Map<String,Object> model){
        
        dao.update(address);
        
        return "redirect:displayAddresses";
    }
}

