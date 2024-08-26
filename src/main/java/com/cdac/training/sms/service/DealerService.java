package com.cdac.training.sms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cdac.training.sms.model.Dealer;
import com.cdac.training.sms.model.DealerAndAddressProjection;
import com.cdac.training.sms.repository.DealerRepository;

@Service
public class DealerService {

	@Autowired
	private DealerRepository drepo;
	
	public Dealer saveDealerDetails(Dealer d) {
		
		return drepo.save(d);
	}
	public Optional<Dealer> loginDealer(String email){
		return drepo.findByEmail(email);
	}
	
	public List<DealerAndAddressProjection> getAllDeatails(){
		
		return drepo.findSelectedFieldsFromDealerAndAddress();
		
	}
}
