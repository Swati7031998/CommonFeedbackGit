package com.cg.capstore.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.capstore.entities.CommonFeedback;
import com.cg.capstore.entities.CustomerDetails;
import com.cg.capstore.entities.MerchantDetails;
import com.cg.capstore.entities.Order;
import com.cg.capstore.entities.Product;
@Repository
public class CommonFeedbackDaoImpl implements CommonFeedbackDao {
	@Autowired
	EntityManager entity_manager;
	@Override
	public boolean commonFeedbackUser(String username,String merchant_name,CommonFeedback common_feedback) {
		CustomerDetails ob=entity_manager.find(CustomerDetails.class,username);
		ob.addCommonFeedback(common_feedback);
		MerchantDetails merchant=entity_manager.find(MerchantDetails.class, merchant_name);
		merchant.addFeedback(common_feedback);
		entity_manager.merge(merchant);
		entity_manager.merge(ob);
		entity_manager.persist(common_feedback);
		return true;
	}
	@Override
	public Set<String> merchantNameList(String username) {
		CustomerDetails customer=entity_manager.find(CustomerDetails.class, username);
		Set<String> merchant_name=new HashSet<>();
		Set<Order> order_set=new HashSet<>();
		List<Product> product_list=new ArrayList<>();
		order_set=customer.getOrders();
		Iterator itr=order_set.iterator();
		while(itr.hasNext()) {
			Order order=(Order)itr.next();
			product_list.add(order.getProduct());
		}
		Iterator itr1=product_list.iterator();
		while(itr1.hasNext()) {
			Product product=(Product)itr1.next();
			merchant_name.add(product.getMerchant().getUsername());
		}
		return merchant_name;
		
	}
	@Override
	public Set<CommonFeedback> commonFeedbackMerchant(String m_username) {
			MerchantDetails merchant=entity_manager.find(MerchantDetails.class, m_username);
			Set<CommonFeedback> feedbacksAll=new HashSet<>();
			feedbacksAll=merchant.getFeedbacks();
			Set<CommonFeedback> feedbacks=new HashSet<>();
			Iterator itr=feedbacksAll.iterator();
			while(itr.hasNext()) {
				CommonFeedback feedback1=(CommonFeedback)itr.next();
				if(feedback1.isEnableRead() && feedback1.getResponse()==null)
					feedbacks.add(feedback1);
			}
			return feedbacks;	
	}
	@Override
	public boolean merchantResponse(int feedbackId,String response) {

		CommonFeedback feedback=entity_manager.find(CommonFeedback.class,feedbackId);
		feedback.setResponse(response);
		return true;
		
	}
	@Override
	public Set<CommonFeedback> responseToUser(String username) {
		CustomerDetails ob=entity_manager.find(CustomerDetails.class,username);
		return ob.getFeedbacks();
		
	}
	
}
