package us.haverkamp.ccb.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import us.haverkamp.ccb.domain.Occurrence;
import us.haverkamp.utils.DateUtils;

public abstract class AttendanceDAO extends GenericDAO<Occurrence> {
	public List<Occurrence> findBy() throws DataAccessException {
		// TODO need to figure out an appropriate default for start_date
		final String xml = get("attendance_profiles&start_date=2000-01-01&end_date=" + DateUtils.toString(Calendar.getInstance().getTime()));
		
		return getItems(xml);
	}

	public List<Occurrence> findBy(Date startDate, Date endDate) throws DataAccessException {
		final String xml = get("attendance_profiles&start_date=" + DateUtils.toString(startDate) + "&end_date=" + DateUtils.toString(endDate));
		
		return getItems(xml);
	}	

	public abstract int[] update(List<Occurrence> items) throws DataAccessException;

	@Override
	protected Occurrence getItem(String xml) throws DataAccessException {
		return Mapper.getOccurrence(xml);
	}

	@Override
	protected List<Occurrence> getItems(String xml) throws DataAccessException {
		return Mapper.getOccurrences(xml);
	}
}