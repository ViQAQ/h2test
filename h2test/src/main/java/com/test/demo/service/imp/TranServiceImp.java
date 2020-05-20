package com.test.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.dao.TransactionDao;
import com.test.demo.entity.Transaction;
import com.test.demo.service.TranService;

@Service
public class TranServiceImp implements TranService{
	
	@Autowired
	TransactionDao transactionDao;
	
	public List<Transaction> selectAll() {
		List<Transaction> list= transactionDao.selectAll();
		return list;
	}
	public List<Transaction> selectOutput() {
		List<Transaction> list= transactionDao.selectOutput();
		return list;
	}
	public List<Integer> selectId(){
		List<Integer> list= transactionDao.selectId();
		return list;
	}
	
	public int insertById(int tradeID,String securityCode,int quantity,String buySell) {
		int row = transactionDao.insertById(tradeID,securityCode,quantity,buySell);
		
		return row;
	}
	
	public int updateById(int tradeID,String securityCode,int quantity,String buySell) {
		int row = transactionDao.updateById(tradeID, securityCode, quantity, buySell);
		
		return row;
	}
	
	public int cancelById(int tradeID,String securityCode,int quantity,String buySell) {
		int row = transactionDao.cancelById(tradeID, securityCode, quantity, buySell);
		
		return row;
	}
}
