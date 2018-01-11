package services;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PublicSessionRepository;
import security.UserAccount;
import domain.Customer;
import domain.Essay;
import domain.Organiser;
import domain.PublicSession;
import forms.PublicSessionForm;

@Service
@Transactional
public class PublicSessionService {
	
		
	// Managed repository -----------------------------------------------------------------------
	@Autowired
	private PublicSessionRepository publicSessionRepository;
	
	// Supporting services -----------------------------------------------------------------------
	@Autowired
	private OrganiserService organiserService;
	
	@Autowired
	private ContestService contestService;
	
	@Autowired
	private EssayService essayService;
	
				
	// Constructors --------------------------------------------------------------------------------


	// Simple CRUD methods ------------------------------------------------------------------
				
	
	public PublicSession create(){
		PublicSession result;
		
		result = new PublicSession();
		
		return result;
	}
	
	public void save(PublicSession publicSession){
		Assert.notNull(publicSession);
		Assert.isTrue(publicSession.getStartDate().before(publicSession.getEndDate()));
		
		publicSessionRepository.save(publicSession);
	}
	
	// Other business methods -----------------------------------------------------------------
	
	public PublicSession findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		
		PublicSession result;
		
		result = publicSessionRepository.findByUserAccountId(userAccount.getId());
		
		return result;
	}
	
	public PublicSession findOne(int publicSessionId){
		PublicSession result;
		
		result = publicSessionRepository.findOne(publicSessionId);
		Assert.notNull(result);
		
		return result;
	}
	
	public PublicSession findOneToEdit(int publicSessionId){
		PublicSession result;
		Customer customer;
	
		
		result = publicSessionRepository.findOne(publicSessionId);
		Assert.notNull(result);
		
		customer = organiserService.findByPrincipal();
		Assert.isTrue(customer==result.getChairman());
		
		return result;
	}
	
	private void checkByPrincipal(PublicSession publicSession) {
		Assert.notNull(publicSession);
		
		Customer customer;
		
		customer = organiserService.findByPrincipal();
		
		Assert.isTrue(customer.getId()==publicSession.getChairman().getId());
		
	}
	

	
	
	/**
	 * Construye un PublicSession a partir de un publicSessionForm
	 * 
	 * @return
	 * @throws SQLException
	 * @throws SerialException
	 */
	public PublicSession reconstruc(PublicSessionForm publicSessionForm) throws SerialException,
			SQLException {

		PublicSession publicSession;
		
		if (publicSessionForm.getPublicSessionId() == 0) {
			publicSession = create();
		
		publicSession.setStartDate(publicSessionForm.getStartDate());
		publicSession.setEndDate(publicSessionForm.getEndDate());
		publicSession.setCapacity(publicSessionForm.getCapacity());
		publicSession.setEssayNumber(publicSessionForm.getEssayNumber());
		publicSession.setContest(publicSessionForm.getContest());
		publicSession.setChairman(publicSessionForm.getChairman());
		

		if ((publicSessionForm.getPhoto()).getSize() != 0) {
			byte[] bytes = publicSessionForm.getPhoto().getBytes();
			Blob blob = null; // is our blob object
			blob = new SerialBlob(bytes);
			publicSession.setPhoto(blob);
		}
		
		} else {
			
			publicSession = findOneToEdit(publicSessionForm.getPublicSessionId());
			
			publicSession.setStartDate(publicSessionForm.getStartDate());
			publicSession.setEndDate(publicSessionForm.getEndDate());
			publicSession.setCapacity(publicSessionForm.getCapacity());
			publicSession.setEssayNumber(publicSessionForm.getEssayNumber());
			publicSession.setContest(publicSessionForm.getContest());
			publicSession.setChairman(publicSessionForm.getChairman());

			if ((publicSessionForm.getPhoto()).getSize() != 0) {
				byte[] bytes = publicSessionForm.getPhoto().getBytes();
				Blob blob = null; // is our blob object
				blob = new SerialBlob(bytes);
				publicSession.setPhoto(blob);
			}
			
		}
		
		return publicSession;
	}
	
	public PublicSessionForm publicSessionToForm(PublicSession publicSession) {
		Assert.notNull(publicSession);
		PublicSessionForm res;

		res = new PublicSessionForm();
		
		res.setPublicSessionId(publicSession.getId());
		res.setCapacity(publicSession.getCapacity());
		res.setEssayNumber(publicSession.getEssayNumber());
		res.setStartDate(publicSession.getStartDate());
		res.setEndDate(publicSession.getEndDate());
		res.setPhoto(publicSession.getPhotoFile());
		res.setContest(publicSession.getContest());
		res.setChairman(publicSession.getChairman());

		return res;
	}
	
	public Collection<PublicSession> findByChairman(int organiserId) {
		Assert.notNull(organiserId);
		
		Collection<PublicSession> result;
		
		result = publicSessionRepository.findByChairman(organiserId);
		
		return result;
	}
	
	public void addEssayToPublicSession (int essayId, int publicSessionId) {
		Assert.notNull(essayId);
		
		Essay essay;
		PublicSession publicSession;
		
		essay = essayService.findOne(essayId);
		publicSession = publicSessionRepository.findOne(publicSessionId);
		Assert.isNull(essay.getPublicSession());
		
		essay.setPublicSession(publicSession);
		
	}
	
	public Collection<Essay> findEssaysByPublicSession(int publicSessionId) {
		Assert.notNull(publicSessionId);
		
		Collection<Essay> essays;
		
		essays = publicSessionRepository.findEssaysByPublicSession(publicSessionId);
		
		return essays;
	}
	
	public void checkDate(PublicSession publicSession) {
		Assert.notNull(publicSession);
		
		Assert.isTrue(publicSession.getStartDate().before(publicSession.getEndDate()));
		
	}
	
	// Nos devuelve true si ha sobrepasado el limite de la capacidad, falso en caso contrario
	public boolean checkAddEssays(Collection<Essay> essays, int numberEssay) {
		
		boolean res;
		
		if (essays.size() >= numberEssay) {
			res = true;
		} else {
			res = false;
		}
		
		return res;
	}
	
	// Metodo para comprobar cuando se añade un essay que sea realmente el chairman el que lo añade
		// y que la Essay que se añada no tiene publicSession asignada anteriormente
	public void checkAdd (int essayId, int publicSessionId){
		
		Organiser organiser = organiserService.findByPrincipal();
		
		PublicSession publicSession = publicSessionRepository.findOne(publicSessionId);
		Essay essay = essayService.findOne(essayId);
		
		Assert.isTrue(organiser == publicSession.getChairman());
		Assert.isTrue(essay.getPublicSession()==null);
		
	}
	
	public Collection<PublicSession> findSessionWithChairmanOrder(){
		
		Collection<PublicSession> result;
		
		result = publicSessionRepository.findSessionWithChairmanOrder();
		
		return result;		
	}
	
	public Collection<PublicSession> findSessionByContest(int contestId){
		Collection<PublicSession> result;
		
		result = publicSessionRepository.findSessionByContest(contestId);
		
		return result;	
		
		
	}
	
	
	}


