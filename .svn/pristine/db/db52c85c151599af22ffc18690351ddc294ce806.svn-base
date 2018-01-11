package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ContestRepository;
import domain.Contest;
import domain.Essay;
import domain.Organiser;

@Service
@Transactional
public class ContestService {

	// Managed repository
	// -----------------------------------------------------------------------

	@Autowired
	private ContestRepository contestRepository;

	// Supporting services
	// -----------------------------------------------------------------------

	@Autowired
	private ActorService actorService;

	@Autowired
	private OrganiserService organiserService;
	// Constructors
	// --------------------------------------------------------------------------------

	// Simple CRUD methods
	// ------------------------------------------------------------------

	public Contest create() {
		Contest result;
		Organiser organiser;

		organiser = organiserService.findByPrincipal();
		Collection<Organiser> org = new LinkedList<Organiser>();
		org.add(organiser);

		result = new Contest();
		result.setOrganisers(org);
		checkByPrincipalOrganiser(result);

		return result;
	}

	public void save(Contest contest) {
		 Assert.notNull(contest);


		 checkByPrincipalOrganiser(contest);
		 
		 if (contest.getId() == 0) {
		 
		 Assert.isTrue(!checkThereAreAPassDate(contest));
		 
		 Assert.isTrue(checkNoAuthorsHaveSubmittedAnyEssays(contest));
		 
		 Assert.isTrue(contest.getResult() == null); 
		 
		 Assert.isTrue(checkHoldingDateIsAfterDeadlineDate(contest)); 
		 
		 }else{
		 if(!checkIsPuttingANewResult(contest)){
		 Assert.isTrue(checkDeadlineDateOrHoldingDateHaveBeenChangedButToFuture(contest)); 
		 }
		 Assert.isTrue(checkHoldingDateIsAfterDeadlineDate(contest));
		 
		 if (contest.getResult() != null) {
		 
		 Assert.isTrue(!checkNoAuthorsHaveSubmittedAnyEssays(contest));
		 
		 Assert.isTrue(checkHaveResultIfHoldingDateIsPass(contest));
		 }
		 
		 
		 }
		 
		 contestRepository.save(contest);
		 }
	
	public void saveOrganiser(Contest contest) {
		 Assert.notNull(contest);


		 checkByPrincipalOrganiser(contest);
		 Assert.isTrue(contest.getId()!=0);
		 contestRepository.save(contest);
		 }



		 private boolean checkIsPuttingANewResult(Contest contest) {
		 boolean isValid = false;
		 String lastStateResult;
		 Contest lastContest; 
		 String actualResult;
		 
		 actualResult = contest.getResult();
		 lastContest = findOneToEdit(contest.getId());
		 lastStateResult = lastContest.getResult();
		 
		 if((lastStateResult == null || lastStateResult == "" || lastStateResult.equals("")) ){
		 if( (actualResult != "" && actualResult != null )){
		 isValid = true;
		 }
		 }else if((lastStateResult != null || lastStateResult != "" || !lastStateResult.equals("")) ){
		 Assert.isTrue(lastStateResult.equals(actualResult));
		 }
		 
		 return isValid;
		 }

	public void delete(Contest contest) {
		Assert.notNull(contest);

		checkByPrincipalOrganiser(contest);
		
		Assert.isTrue(contestRepository.exists(contest.getId()));

		Assert.isTrue(canBeDeleted(contest));
		
		contestRepository.delete(contest);
	}

	// Other business methods
	// -----------------------------------------------------------------

	public Contest findOne(int contestId) {
		Assert.notNull(contestId);
		Contest result;

		result = contestRepository.findOne(contestId);
		Assert.notNull(result);

		return result;
	}

	public Contest findOneToEdit(int contestId) {
		Assert.notNull(contestId);
		Contest result;

		result = contestRepository.findOne(contestId);
		Assert.notNull(result);
		checkByPrincipalOrganiser(result);

		return result;
	}

	public Collection<Contest> finAll() {

		Collection<Contest> result;

		result = contestRepository.findAll();
		return result;
	}

	public Collection<Contest> findAllContestByOrganiserId() {

		Collection<Contest> result;
		Organiser organiser;

		organiser = organiserService.findByPrincipal();
		Assert.isTrue(organiser != null);

		result = contestRepository.findAllContestByOrganiserId(organiser
				.getId());

		return result;
	}

	public void checkByPrincipalOrganiser(Contest contest) {
		Assert.notNull(contest);

		Organiser actor;

		actor = organiserService.findByPrincipal();
		Assert.isTrue(contest.getOrganisers().contains(actor));
	}

	public boolean checkHoldingDateIsAfterDeadlineDate(Contest contest) {
		Assert.notNull(contest);

		Date holdingDate;
		Date deadlineDate;
		Boolean isValid = false;

		holdingDate = contest.getHoldingDate();
		deadlineDate = contest.getDeadlineDate();

		if (holdingDate.after(deadlineDate)) {
			isValid = true;
		}

		return isValid;
	}

	public boolean checkHaveResultIfHoldingDateIsPass(Contest contest) {
		Assert.notNull(contest);

		Date now;
		Date holdingDate;
		Boolean isPass = false;

		now = new Date();
		holdingDate = contest.getHoldingDate();

		if (now.after(holdingDate)) {
			isPass = true;
		}

		return isPass;
	}

	public boolean checkNoAuthorsHaveSubmittedAnyEssays(Contest contest) {
		Assert.notNull(contest);

		Boolean noHaveAnyEssays = false;

		if (contest.getEssays().isEmpty()) {
			noHaveAnyEssays = true;
		}

		return noHaveAnyEssays;

	}

	public boolean checkDeadlineDateOrHoldingDateHaveBeenChangedButToFuture(
			Contest contest) {
		Assert.notNull(contest);
		checkByPrincipalOrganiser(contest);
		
		Date holdingDate;
		Date deadlineDate;
		Date now;
		Date lastHoldingDate;
		Date lastDeadlineDate;
		Contest lastContest;

		Boolean isChangedToFuture = false;

		lastContest = findOneToEdit(contest.getId());
		lastHoldingDate = lastContest.getHoldingDate();
		lastDeadlineDate = lastContest.getDeadlineDate();
		now = new Date();
		holdingDate = contest.getHoldingDate();
		deadlineDate = contest.getDeadlineDate();

		if ((holdingDate.after(lastHoldingDate) || holdingDate.equals(lastHoldingDate))
				&& (deadlineDate.after(lastDeadlineDate) || deadlineDate.equals(lastDeadlineDate))
				&& holdingDate.after(now) && deadlineDate.after(now)) {
			isChangedToFuture = true;
		}

		return isChangedToFuture;
	}

	public Boolean canEditTheContest(Contest contest) {
		Assert.notNull(contest);
		
		Boolean canEdit = false;
		Date now;
		
		now = new Date();
		
		if(now.before(contest.getHoldingDate())){
			canEdit = true;
		}
		
		return canEdit;
	}

	public Boolean canPutResult(Contest contest) {
		 Boolean canPutResult = false;
		 if(checkByPrincipalOrganiserBoolean(contest)){
		 if(contest.getResult() == null || contest.getResult() == ""){
		 if(!checkNoAuthorsHaveSubmittedAnyEssays(contest) && checkHaveResultIfHoldingDateIsPass(contest)){
		 canPutResult = true;
		 }}}
		 return canPutResult;
		 }
	
	private boolean checkByPrincipalOrganiserBoolean(Contest contest) {
		Boolean res = false;
		Assert.notNull(contest);

		Organiser actor;

		actor = organiserService.findByPrincipal();
		if(contest.getOrganisers().contains(actor)){
			res = true;
		}
		return res;
	}

	public Boolean canBeDeleted(Contest contest) {
		Boolean canBeDeleted = false;
		
		checkByPrincipalOrganiser(contest);
		
		if(checkNoAuthorsHaveSubmittedAnyEssays(contest)){
			canBeDeleted = true;
		}
		
		return canBeDeleted;
	}
	
	
	public Collection<Contest> findAll() {
		 Collection<Contest> result;

		 result = contestRepository.findAll();
		 Assert.notNull(result);

		 return result;
		 }

	public Boolean checkThereAreAPassDate(Contest contest) {
		Boolean thereAreAPassDate = false;
		Date now ;
		Date holdingDate;
		Date deadlineDate;
		
		now = new Date();
		holdingDate = contest.getHoldingDate();
		deadlineDate = contest.getDeadlineDate();
		if(now.after(deadlineDate) || now.after(holdingDate) ){
			thereAreAPassDate = true;
		}
		return thereAreAPassDate;
	}
	
	public Collection<Contest> findAllContestOrderByEssays() {
		 Collection<Contest> result;

		 result = contestRepository.findAllContestOrderByEssays();
		 Assert.notNull(result);
		 
		 return result;
	 }
	
	public Double avgContestForOrganiser() {
		 Double result;

		 result = contestRepository.avgContestForOrganiser();
		 
		 Assert.notNull(result);
		 
		 return result;
	 }
	
	public Collection<Contest> findContestWithHoldingDate() {
		 Collection<Contest> result;	 
		Calendar calendar = Calendar.getInstance();
		
		Date date1 = new Date();
		Date date2;
		
		calendar.setTime(date1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		date1 = calendar.getTime();
		
		
		calendar.setTime(date1);
		calendar.add(Calendar.MONTH, -1);
		date2 = calendar.getTime();
			
		 result = contestRepository.findContestWithHoldingDate(date2, date1);
		
		 Assert.notNull(result);
		 
		 return result;
	 }
	
	public Collection<Essay> findEssaysByContest(int contestId){
		Assert.notNull(contestId);
		
		Collection<Essay> result;
		
		result = contestRepository.findEssayByContest(contestId);
		
		return result;
	}
	
	public Collection<Essay> findEssaysWithoutPublicSessionByContest(int contestId){
		Assert.notNull(contestId);
		
		Collection<Essay> result;
		
		result = contestRepository.findEssayWithoutPublicSessionByContest(contestId);
		
		return result;
	}
	
	public Collection<Object[]> selectContestWithPublicSessions(){
		
		Collection<Object[]> result;
		
		result = contestRepository.selectContestWithPublicSessions();
		
		return result;	
		
	}
	
	
}
