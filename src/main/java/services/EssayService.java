package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EssayRepository;
import domain.Author;
import domain.Contest;
import domain.Essay;
import domain.Organiser;

@Service
@Transactional
public class EssayService {

	// Managed repository
	// -----------------------------------------------------------------------

	@Autowired
	private EssayRepository essayRepository;

	// Supporting services
	// -----------------------------------------------------------------------
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private OrganiserService organiserService;

	// Constructors
	// --------------------------------------------------------------------------------

	// Simple CRUD methods
	// ------------------------------------------------------------------

	
	public Essay findOne(int essayId){
		Essay result;
		
		Assert.notNull(essayId);
		
		result = essayRepository.findOne(essayId);
		
		Assert.notNull(result);
		
		return result;
	}
	
	public void save(Essay essay){
		Assert.notNull(essay);
		
		essayRepository.save(essay);
		
		
		
	}
	
	//Others methods
	
	public Collection<Essay> findPublishedEssay(){
		Collection<Essay> result;
		
		result = essayRepository.findPublishedEssay();
		
		return result;
	}
	
	public Collection<Essay> findOrganiserEssay(){
		Collection<Essay> result;
		Organiser organiser;
		
		organiser = organiserService.findByPrincipal();
		result = essayRepository.findOrganiserEssay(organiser.getId());
		
		return result;
	}
	
	public void publish (int essayId){
		Essay result;
		
        Organiser organiser;
        organiser = organiserService.findByPrincipal();

        result = findOne(essayId);

        Assert.isTrue(result.getContest().getOrganisers().contains(organiser));

        result.setIsPublished(true);


        save(result);
		
	}
	
	public Essay submitEssay(Contest contest){
		Essay result;
		Author author;
		
		author=authorService.findByPrincipal();
		
		result = new Essay();
		result.setAuthor(author);
		result.setContest(contest);
		result.setIsPublished(false);
		result.setSubmissionDate(new Date(System.currentTimeMillis()));
		
		return result;
	}
	
	public Collection<Essay> findAuthorEssayByContest(int userAccountId, int contestId){
		Collection<Essay> result;
		
		Assert.notNull(userAccountId);
		Assert.notNull(contestId);
		
		result = essayRepository.findAuthorEssayByContest(userAccountId, contestId);
		
		Assert.notNull(result);
		
		return result;
	}
	

	public Double avgEssayForAuthor(){
		Double result;
		
		result = essayRepository.avgEssayForAuthor();
		
		Assert.notNull(result);
		
		return result;
		
	}
	

	public Collection<String> findEssayTitleByContest(int contestId){
		Collection<String> result;
		
		Assert.notNull(contestId);
		result = essayRepository.findEssayTitleByContest(contestId);
		
		Assert.notNull(result);
		return result;
	}

	public Collection<Essay> findPublishedEssayWithContest(Integer contestId) {
		Collection<Essay> result;
		
		result = essayRepository.findPublishedEssayWithContest(contestId);
		
		return result;
	}
	

	

}
