package com.test.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.demo.entity.Transaction;
import com.test.demo.service.TranService;

@Controller
public class IndexController {

	@Autowired
	TranService tranService;
	
	@RequestMapping("/select")	
	public String index(Model model){
		
		List<Transaction> list = tranService.selectAll();
		model.addAttribute("list", list);
		output(model);
		return "result";
	}
	@SuppressWarnings("finally")
	@RequestMapping("/add")
	public String add(@ModelAttribute Transaction tran,Model model) {
		Integer tradeid = tran.getTradeID();
		String securityCode = tran.getSecurityCode();
		try {
			if(tradeid == null) {
				String message ="tradeid cannot be null";
				model.addAttribute("message" , message);
			}
			if(securityCode == null) {
				String message ="securityCode cannot be null";
				model.addAttribute("message" , message);
			}
			List<Integer> tradeIds = tranService.selectId();
			for (Integer i : tradeIds) {
				if(tradeid == i) {
					String message ="tradeid has been inserted";
					model.addAttribute("message" , message);
				}
			}
		
			//插入事务表
			tranService.insertById(tradeid, securityCode,tran.getQuantity(),tran.getBuySell());
		}catch (Exception e) {
			return "error";
		} finally {
			//展示事务表
			List<Transaction> list = tranService.selectAll();
			model.addAttribute("list", list);
			//计算output并输出
			output(model);
			return "result";
		}
	}
	@SuppressWarnings("finally")
	@RequestMapping("/update")
	public String update(@ModelAttribute Transaction tran,Model model) {
		try {
			if(tran.getAction().equals("CANCEL")) {
				String message ="canceled transaction cannot be update";
				model.addAttribute("message" , message);
			}
			//TODO 不允许修改不存在的TradeID
			if(tran.getTradeID()!=1 || tran.getTradeID()!=1 || tran.getTradeID()!=3 || tran.getTradeID()!=4) {
				String message = "tradeid doesnt exist";
				model.addAttribute("message" , message);
			}
			//TODO 在MYBATIS判断,如果有值添加新的值,为空添加上个版本的值
			//插入事务表
			tranService.updateById(tran.getTradeID(),tran.getSecurityCode(),tran.getQuantity(),tran.getBuySell());
		} catch (Exception e) {
			return "error";
		} finally {
			//展示事务表
			List<Transaction> list = tranService.selectAll();
			model.addAttribute("list", list);
			//计算output并输出
			output(model);
			return "result";
		}
	
		
	}
	@RequestMapping("/cancel")
	public String cancel(@ModelAttribute Transaction tran,Model model) {
		tranService.cancelById(tran.getTradeID(),tran.getSecurityCode(),tran.getQuantity(),tran.getBuySell());
		
		List<Transaction> list = tranService.selectAll();
		model.addAttribute("list", list);
		output(model);
		return "result";
	}
	
	public void addmodel(Model model,int REL,int INF,int ITC) {
		model.addAttribute("REL", REL);
		model.addAttribute("ITC", ITC);
		model.addAttribute("INF", INF);
	}
	public void output(Model model) {
		int REL = 0;
		int ITC = 0;
		int INF = 0;
		List<Transaction> list= tranService.selectOutput();
		for(Transaction item:list) {
			switch (item.getSecurityCode()) {
			case "REL":
				if(item.getAction().equals("CANCEL")) {
					REL +=0;
				}else {
					if (item.getBuySell().equals("BUY")) {
						REL +=item.getQuantity();
					}else {
						REL -=item.getQuantity();
					}
				}
	
				break;
			case "ITC":
				if(item.getAction().equals("CANCEL")) {
					ITC +=0;
				}else {
					if (item.getBuySell().equals("BUY")) {
						ITC +=item.getQuantity();
					}else {
						ITC -=item.getQuantity();
					}
				}
				
				break;
			case "INF":
				if(item.getAction().equals("CANCEL")) {
					INF +=0;
				}else {
					if (item.getBuySell().equals("BUY")) {
						INF +=item.getQuantity();
					}else {
						INF -=item.getQuantity();
					}
				}
				break;
			default:
				break;
			}
		}
		addmodel(model, REL, INF, ITC);
	}

}
