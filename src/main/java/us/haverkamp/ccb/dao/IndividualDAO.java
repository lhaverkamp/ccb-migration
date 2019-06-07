package us.haverkamp.ccb.dao;

import java.util.List;

import us.haverkamp.ccb.domain.Individual;

public abstract class IndividualDAO extends GenericDAO<Individual> {
	public List<Individual> findBy() throws DataAccessException {
		// include_inactive is necessary to have updates regarding deceased members
		final String xml = get("individual_profiles&include_inactive=true");
		
		return getItems(xml);
	}
	
	public Individual findBy(Long id) throws NoDataFoundException, DataAccessException {
		final String xml = get("individual_profile_from_id&individual_id=" + id + "&include_inactive=true");
		
		return getItem(xml);
	}
	
	public abstract int[] update(List<Individual> items) throws DataAccessException;

	@Override
	protected Individual getItem(String xml) throws DataAccessException {
		return Mapper.getIndividual(xml);
	}

	@Override
	protected List<Individual> getItems(String xml) throws DataAccessException {
		return Mapper.getIndividuals(xml);
	}
}
