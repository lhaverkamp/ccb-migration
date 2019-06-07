package us.haverkamp.ccb.dao;

import java.util.Date;
import java.util.List;

import us.haverkamp.ccb.domain.Transaction;
import us.haverkamp.utils.DateUtils;

public abstract class TransactionDAO extends GenericDAO<Transaction> {
	public List<Transaction> findBy() throws DataAccessException {
		final String xml = get("batch_profiles");
		
		return getItems(xml);
	}
	
	public List<Transaction> findBy(Date startDate, Date endDate) throws DataAccessException {
		final String xml = get("batch_profiles_in_date_range&date_start=" + DateUtils.toString(startDate) + "&date_end=" + DateUtils.toString(endDate));
		
		return getItems(xml);
	}
	
	public abstract int[] update(List<Transaction> items) throws DataAccessException;
	
	public abstract int[] update(List<Transaction> items, boolean append) throws DataAccessException;

	@Override
	protected Transaction getItem(String xml) throws DataAccessException {
		return Mapper.getTransaction(xml);
	}

	@Override
	protected List<Transaction> getItems(String xml) throws DataAccessException {
		return Mapper.getTransactions(xml);
	}
}
