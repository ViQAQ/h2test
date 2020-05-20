package com.test.demo.service;

import java.util.List;

import com.test.demo.entity.Transaction;

public interface TranService {
	List<Transaction> selectAll();
	
	List<Transaction> selectOutput();
	
	List<Integer> selectId();
	
	int insertById(int tradeID,String securityCode,int quantity,String buySell);
	
	int updateById(int tradeID,String securityCode,int quantity,String buySell);
	
	int cancelById(int tradeID,String securityCode,int quantity,String buySell);
}
