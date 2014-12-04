package us.haverkamp.ccb.migration;

import java.util.List;

import us.haverkamp.ccb.dao.DataAccessException;
import us.haverkamp.ccb.dao.Factory;
import us.haverkamp.ccb.dao.IndividualDAO;
import us.haverkamp.ccb.domain.Individual;

public class App {
    public static void main( String[] args ) throws DataAccessException {
        final IndividualDAO dao = Factory.getInstance().getIndividualDAO();
        final List<Individual> individuals = 
        	dao.findBy(IndividualDAO.SQL_UPDATE);
        
        int cnt = 0;
        for(Individual individual : individuals) {
        	if(dao.update(individual) == 200) {
        		cnt++;
        	};
        }
        
        System.out.println(cnt + " of " + individuals.size());
    }
}
