package com.cdac.training.sms.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cdac.training.sms.exception.ResourceNotFoundException;
import com.cdac.training.sms.model.Address;
import com.cdac.training.sms.model.Dealer;
import com.cdac.training.sms.model.DealerAndAddressProjection;
import com.cdac.training.sms.service.DealerService;

@RestController
@RequestMapping("/api")
public class DealerController {

	
	@Autowired
	private DealerService dservice;
	//http://localhost:8085/sms/api/register
	
	@PostMapping("/register")
	public ResponseEntity<String> createDealer(@Validated @RequestBody Dealer dealer){
		
		try {
			
			Address address=dealer.getAddress();
			
			
			
			//Establish Bi-directional Relationship of Address and Dealer
			address.setDealer(dealer);
			dealer.setAddress(address);
			Dealer registeredDealer=dservice.saveDealerDetails(dealer);
			
			if(registeredDealer!=null) {
				
				return  ResponseEntity.status(HttpStatus.OK).body("Registraion succcesful");
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registraion failed");
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An Error Occured"+e.getMessage());
			
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginDealer(@Validated @RequestBody Dealer dealer ) 
			throws ResourceNotFoundException{
		
		//Boolean isAuthenticated=false;
		String email=dealer.getEmail();
		String password=dealer.getPassword();
		
		Dealer d=dservice.loginDealer(email).
				orElseThrow(()-> new ResourceNotFoundException("Dealer Not Found For this email :"+email));
				
		if(email.equals(d.getEmail())&& password.equals(d.getPassword())) {
			
			//isAuthenticated=true;
			//return  ResponseEntity.ok(isAuthenticated);
			return ResponseEntity.status(HttpStatus.OK).body("Suucessfully Login \n"+email+"\n"+password);
			
		}
		return ResponseEntity.status(HttpStatus.OK).body("Entered Wrong Email & password!!! \n Try Again ");
	}


@GetMapping("/dealers")

public ResponseEntity<List<DealerAndAddressProjection>> getDealerInfo(){
	
	try {
		
		List<DealerAndAddressProjection> dealerdetails=dservice.getAllDeatails();			
		return ResponseEntity.ok(dealerdetails);
		
	} catch (Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}

}
