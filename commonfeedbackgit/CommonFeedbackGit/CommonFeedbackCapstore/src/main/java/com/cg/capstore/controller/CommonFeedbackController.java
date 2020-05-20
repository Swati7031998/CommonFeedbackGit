package com.cg.capstore.controller;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.capstore.entities.CommonFeedback;
import com.cg.capstore.service.CommonFeedbackServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class CommonFeedbackController {
	@Autowired
	CommonFeedbackServiceImpl capstore_service;
	@PostMapping("/addfeedback")
	public boolean commonFeedbackUser(@RequestBody CommonFeedback1 common_feedback)
	{
		CommonFeedback cf=new CommonFeedback();
		cf.setFeedbackSubject(common_feedback.feedbackSubject);
		cf.setFeedbackMessage(common_feedback.feedbackMessage);
		return capstore_service.commonFeedbackUser(common_feedback.username,common_feedback.merchant_name,cf);
	}
	
	@GetMapping("/getMerchantNameList/{username}")
	public Set<String> merchantNameList(@PathVariable String username)
	{
		return capstore_service.merchantNameList(username);
	}
	@GetMapping("/getFeedbacksMerchant/{m_username}")
	public Set<CommonFeedbackDetailsForMerchant> commonFeedbackMerchant(@PathVariable String m_username){
		 Set<CommonFeedback> feedbackDetailed=new HashSet<CommonFeedback>();
		 Set<CommonFeedbackDetailsForMerchant> feedbackRequired=new HashSet<CommonFeedbackDetailsForMerchant>();
		 feedbackDetailed=capstore_service.commonFeedbackMerchant(m_username);
		 Iterator itr=feedbackDetailed.iterator();
			while(itr.hasNext()) {
				CommonFeedback feedback1=(CommonFeedback)itr.next();
				CommonFeedbackDetailsForMerchant obj=new CommonFeedbackDetailsForMerchant();
				obj.feedbackId=feedback1.getFeedbackId();
				obj.feedbackSubject=feedback1.getFeedbackSubject();
				obj.feedbackMessage=feedback1.getFeedbackMessage();
				feedbackRequired.add(obj);
				System.out.println(obj);
			}
		return feedbackRequired;
	}
	@PostMapping("/getMerchantResponse")
	public boolean merchantResponse(@RequestBody merchantResponse1 response1) {
		return capstore_service.merchantResponse(response1.feedbackId,response1.response);
	}
	@GetMapping("/responseToUser/{username}")
	public Set<CommonFeedback> responseToUser(@PathVariable String username){
		return capstore_service.responseToUser(username);
	}

}

class CommonFeedback1{
	public String username;
	public String merchant_name;
	public String feedbackSubject;
	public String feedbackMessage;
}
class CommonFeedbackDetailsForMerchant{
	public int feedbackId;
	public String feedbackSubject;
	public String feedbackMessage;
}
class merchantResponse1{
	public int feedbackId;
	public String response;
}
