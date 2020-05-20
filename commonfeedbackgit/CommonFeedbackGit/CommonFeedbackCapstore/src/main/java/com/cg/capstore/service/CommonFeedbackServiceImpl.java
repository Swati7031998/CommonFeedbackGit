package com.cg.capstore.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.dao.CommonFeedbackDaoImpl;
import com.cg.capstore.entities.CommonFeedback;


@Service
@Transactional
public class CommonFeedbackServiceImpl implements CommonFeedbackService {
	@Autowired
	CommonFeedbackDaoImpl capstore_dao;
	@Override
	public boolean commonFeedbackUser(String username,String merchant_name,CommonFeedback common_feedback) {
		return capstore_dao.commonFeedbackUser(username,merchant_name,common_feedback);
	}

	@Override
	public Set<String> merchantNameList(String username) {
		return capstore_dao.merchantNameList(username);
	}

	@Override
	public Set<CommonFeedback> commonFeedbackMerchant(String m_username) {
		return capstore_dao.commonFeedbackMerchant(m_username);
	}

	@Override
	public boolean merchantResponse(int feedbackId, String response) {
		return capstore_dao.merchantResponse(feedbackId, response);
		
	}

	@Override
	public Set<CommonFeedback> responseToUser(String username) {
		return capstore_dao.responseToUser(username);
	}
	

//	@Override
//	public void orderUser(String username, Order order) {
//		capstore_dao.orderUser(username, order);
//		
//	}
	

}
