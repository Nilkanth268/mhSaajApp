package com.jewellery.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.jewellery.model.Order;
import com.jewellery.model.Product;

import tools.jackson.databind.ObjectMapper;

@Repository
public class OrderRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Integer saveOrderRepo(Order order) {
		System.out.println("saveOrderRepo called: " + order);

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> hdrMap = new HashMap<>();

		// 1. Populate Header Data (for com_order_hdr)
		hdrMap.put("custName", order.getCustName());
		hdrMap.put("whatsappNo", order.getWhatsappNo());
		hdrMap.put("altPhoneNo", order.getAltPhoneNo());
		hdrMap.put("totalAmt", order.getTotalAmt());
		hdrMap.put("oCrId", order.getOCrId());

		// 2. Populate Detail List (for com_order_dtl)
		List<Map<String, Object>> dtlList = new ArrayList<>();
		if (order.getItemList() != null) {
			for (Product p : order.getItemList()) {
				Map<String, Object> itemMap = new HashMap<>();
				itemMap.put("pId", p.getPId());
				itemMap.put("pCode", p.getPCode());
				itemMap.put("oItems", p.getPName());
				itemMap.put("itemPrice", p.getPAmount());
				dtlList.add(itemMap);
			}
		}
		hdrMap.put("items", dtlList);

		String orderJsonObj = objectMapper.writeValueAsString(hdrMap);

		// 3. Call Stored Procedure & Return Generated Order ID (e.g., 101)
		String sqlStr = "CALL SAVE_ORDER(?)";
		return jdbcTemplate.query(sqlStr, ps -> ps.setString(1, orderJsonObj), (ResultSetExtractor<Integer>) rs -> {
			if (rs.next()) {
				return rs.getInt("oId");
			}
			return null;
		});
	}
}