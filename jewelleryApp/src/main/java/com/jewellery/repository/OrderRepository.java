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

	// 1. Get all Order Headers (com_order_hdr)
	public List<Order> getAllOrdersRepo() {
		List<Order> orderList = new ArrayList<>();
		try {
			String sql = "CALL GET_ALL_ORDERS()";
			jdbcTemplate.query(sql, (ResultSetExtractor<Void>) rs -> {
				while (rs.next()) {
					Order ord = new Order();
					ord.setOId(rs.getInt("oId"));
					ord.setCustName(rs.getString("custName"));
					ord.setWhatsappNo(rs.getString("whatsappNo"));
					ord.setAltPhoneNo(rs.getString("altPhoneNo"));
					ord.setTotalAmt(rs.getDouble("totalAmt"));
					ord.setOCrId(rs.getString("oCrId"));
					ord.setOrderDate(rs.getString("orderDate"));
					ord.setOrderStatus(rs.getString("orderStatus"));
					orderList.add(ord);
				}
				return null;
			});
		} catch (Exception e) {
			System.out.println("Error fetching orders: " + e.getMessage());
		}
		return orderList;
	}

	// 2. Get Order Detail Items by Order ID (com_order_dtl)
	public List<Product> getOrderDtlByIdRepo(Integer oId) {
		List<Product> itemList = new ArrayList<>();
		try {
			String sql = "CALL GET_ORDER_DTL_BY_ID(?)";
			jdbcTemplate.query(sql, ps -> ps.setInt(1, oId), (ResultSetExtractor<Void>) rs -> {
				while (rs.next()) {
					Product item = new Product();
					item.setPId(rs.getInt("pId"));
					item.setPCode(rs.getString("pCode"));
					item.setPName(rs.getString("oItems"));
					item.setPAmount(rs.getDouble("itemPrice"));

					// Extract first cover image if product has multiple comma-separated images
					String rawImg = rs.getString("pImage");
					if (rawImg != null && !rawImg.trim().isEmpty()) {
						item.setImageName(rawImg.split(",")[0].trim());
					}
					itemList.add(item);
				}
				return null;
			});
		} catch (Exception e) {
			System.out.println("Error fetching order details: " + e.getMessage());
		}
		return itemList;
	}

	// 3. Update Order Status
	public void updateOrderStatusRepo(Integer oId, String status) {
		String sql = "CALL UPDATE_ORDER_STATUS(?, ?)";
		jdbcTemplate.update(sql, oId, status);
	}
}