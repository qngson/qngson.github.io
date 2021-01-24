package com.estore.dao;

import java.util.List;

public interface ReportDao {

	public List<Object[]> invetory();
	public List<Object[]> revenueByCategory();
	public List<Object[]> revenueByCustomer();
	public List<Object[]> revenueByYear();
	public List<Object[]> revenueByQuarter();
	public List<Object[]> revenueByMonth();
}
