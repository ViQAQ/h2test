package com.test.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.test.demo.entity.Transaction;

@Mapper
public interface TransactionDao {
	List<Transaction> selectAll();
	
	List<Transaction> selectOutput();
	
	List<Integer> selectId();
	
	int insertById(int tradeID,String securityCode,int quantity,String buySell);
	
	int updateById(int tradeID,String securityCode,int quantity,String buySell);
	
	int cancelById(int tradeID,String securityCode,int quantity,String buySell);
}
