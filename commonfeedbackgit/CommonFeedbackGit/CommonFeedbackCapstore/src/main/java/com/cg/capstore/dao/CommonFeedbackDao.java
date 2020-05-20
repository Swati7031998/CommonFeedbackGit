package com.cg.capstore.dao;
import java.util.Set;

import com.cg.capstore.entities.CommonFeedback;
public interface CommonFeedbackDao {
	public boolean commonFeedbackUser(String username,String merchant_name,CommonFeedback common_feedback);
	public Set<String> merchantNameList(String username);
	public Set<CommonFeedback> commonFeedbackMerchant(String m_username);
	public boolean merchantResponse(int feedbackId,String response);
	public Set<CommonFeedback> responseToUser(String username);
//	public void orderUser(String username,Order order);
}
