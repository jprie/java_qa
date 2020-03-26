package service;

import java.util.List;

import exception.ServiceException;
import model.Photographer;
import repository.PhotographerRepository;
import repository.PhotographerRepositoryJPA;

/**
 * The photographer service is just a stub at the moment
 * Its purpose is to allow the controller to access the database 
 * repository. It could be used to load data asynchronously, 
 * calling the controller when ready. 
 * @author jprie
 *
 */
public class PhotographerService {

	private PhotographerRepository repository = new PhotographerRepositoryJPA(); 

	public void add(Photographer photographer) throws ServiceException {
		
		repository.add(photographer);

	}

	public Photographer update(Photographer photographer) throws ServiceException {

		return repository.update(photographer);
	}

	public List<Photographer> getAll() throws ServiceException {

		List<Photographer> photographerList = repository.getAll();
		return photographerList;

	}
	
	public void delete(Photographer photographer) throws ServiceException {
		
		repository.delete(photographer);
	}

}
