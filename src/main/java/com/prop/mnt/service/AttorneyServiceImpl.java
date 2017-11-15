package com.prop.mnt.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.prop.mnt.dao.AttorneyDao;
import com.prop.mnt.model.AttCase;
import com.prop.mnt.model.CaseHearing;
import com.prop.mnt.vo.AttorneyVo;

public class AttorneyServiceImpl implements AttorneyService {
	  private AttorneyDao attDao;
	
		public AttorneyServiceImpl(){
			attDao=new AttorneyDao();
		}
	
		public Integer  saveAttCase(AttorneyVo attVo){
			AttCase attCase=new AttCase();
			attCase.setCaseName(attVo.getCaseName());
			attCase.setCaseDesc(attVo.getCaseDesc());
			attCase.setCourtName(attVo.getCourtName());
			attCase.setAttUser(attDao.findByUserId(attVo.getUserId()));
			attCase.setPetitioner(attVo.getPetitioner());
			attCase.setProperty(attDao.findByPropertyId(attVo.getPropertyId()));
			attCase.setRespondent(attVo.getRespondent());
			attCase.setStartDate(attVo.getStartDate());
			attCase.setStatus(attVo.getStatus());
			attCase.setIsnew(true);
			attCase.setNewreason("New Case");
			return attDao.saveAttCase(attCase);
		}
	
		
		
		public Integer  saveCaseHearing(AttorneyVo attorneyVo){
			CaseHearing caseHearing=new CaseHearing();
			caseHearing.setAttCase(attDao.viewAttCase(attorneyVo.getCaseId()));
			caseHearing.setHearingDate(attorneyVo.getCaseHearingDate());
			caseHearing.setHearingTask(attorneyVo.getHearingTask());
			caseHearing.setCreated(new Date());
			caseHearing.setIsnew(true);
			caseHearing.setNewreason("New Hearing");
			return attDao.saveCaseHearing(caseHearing);
		}
	
	
		public Integer updateCaseHearing(AttorneyVo attorneyVo){
			CaseHearing casehear=attDao.viewCaseHear(attorneyVo.getCasehearId());
			if(casehear==null) return null;
			
			casehear.setHearingDate(attorneyVo.getCaseHearingDate());
			casehear.setHearingTask(attorneyVo.getHearingTask());
			casehear.setHearinDetails(attorneyVo.getHearinDetails());
			casehear.setUpdated(new Date());
			casehear.setIsnew(true);
			casehear.setNewreason("Hearing Updated");
		    return attDao.updateCaseHearing(casehear);
		}
		
		
		public AttorneyVo  viewCaseHear(int casehearId,int userId){
			CaseHearing casehear=attDao.viewCaseHear(casehearId);
			if(casehear==null) return null;
			
			AttorneyVo atthear=new AttorneyVo();
			atthear.setCasehearId(casehear.getCasehearId());
			atthear.setCaseId(casehear.getAttCase().getCaseId());
			atthear.setHearingTask(casehear.getHearingTask());
			atthear.setCaseHearingDate(casehear.getHearingDate());
			atthear.setHearinDetails(casehear.getHearinDetails());
			atthear.setPropertyId(casehear.getAttCase().getProperty().getPropCode());
			atthear.setPropertyName(casehear.getAttCase().getProperty().getPropName()+"#"+casehear.getAttCase().getProperty().getUser().getFirstName()+", "+casehear.getAttCase().getProperty().getUser().getLastName());
			atthear.setCaseName(casehear.getAttCase().getCaseName());
			atthear.setCourtName(casehear.getAttCase().getCourtName());
			System.out.println("log u "+userId+" att Id="+casehear.getAttCase().getAttUser().getId());
			if(userId==casehear.getAttCase().getAttUser().getId()){
				atthear.setUpdateaccess(true);
			}else atthear.setUpdateaccess(false);
			return atthear;
		}
	
	
	public AttorneyVo  viewAttCase(int caseId,int userId){
		AttCase attCas=attDao.viewAttCase(caseId);
		if(attCas==null) return null;
		AttorneyVo attCase=new AttorneyVo();
		attCase.setCaseId(attCas.getCaseId());
		attCase.setCaseName(attCas.getCaseName());
		attCase.setCaseDesc(attCas.getCaseDesc());
		attCase.setCourtName(attCas.getCourtName());
		//attCase.setCaseattId(attCas.getUserId());
		attCase.setPetitioner(attCas.getPetitioner());
		attCase.setPropertyId(attCas.getProperty().getPropCode());
		attCase.setPropertyName(attCas.getProperty().getPropName()+"#"+attCas.getProperty().getUser().getFirstName()+", "+attCas.getProperty().getUser().getLastName());
		attCase.setRespondent(attCas.getRespondent());
		attCase.setStartDate(attCas.getStartDate());
		attCase.setStatus(attCas.getStatus());
		System.out.println("log u "+userId+" att Id="+attCas.getAttUser().getId());
		if(userId==attCas.getAttUser().getId()){
			attCase.setUpdateaccess(true);
		}else attCase.setUpdateaccess(false);
		
		return attCase;
	}
	
	public Integer updateAttCase(AttorneyVo attVo){
		AttCase attCas=attDao.viewAttCase(attVo.getCaseId());
		attCas.setStatus(attVo.getStatus());
		attCas.setStartDate(attVo.getStartDate());
		attCas.setCloseDate(attVo.getCloseDate());
		attCas.setCourtName(attVo.getCourtName());
		attCas.setPetitioner(attVo.getPetitioner());
		attCas.setRespondent(attVo.getRespondent());
		attCas.setIsnew(true);
		attCas.setNewreason("Case Updated");
		return attDao.updateAttCase(attCas);
	}
	
	public Integer resetIsnew(Integer caseid){
		return attDao.resetIsnew(caseid);
	}
	
	public Integer resetHearingIsnew(Integer caseid,Integer hearId){
		return attDao.resetHearingIsnew(caseid, hearId);
	}	
	
	public List<AttorneyVo> getCaseHearings(Integer caseId, int limit,AttorneyVo baseVo){
		List<CaseHearing> casehears=attDao.getCaseHearings(caseId, limit);

		List<AttorneyVo> hearList=new ArrayList<AttorneyVo>();
		AttorneyVo atthear=null;
		int i=0;
		baseVo.setCaseId(caseId);
		for(CaseHearing casehear: casehears){
			atthear=new AttorneyVo();
			atthear.setCasehearId(casehear.getCasehearId());
			atthear.setCaseId(casehear.getAttCase().getCaseId());
			atthear.setHearingTask(trimString(casehear.getHearingTask(),30));
			atthear.setCaseHearingDate(casehear.getHearingDate());
			atthear.setHearinDetails(trimString(casehear.getHearinDetails(),30));
			atthear.setIsnew(casehear.getIsnew());
			atthear.setNewreason(casehear.getNewreason());
			hearList.add(atthear);
			if(i==0){
				baseVo.setCaseName(casehear.getAttCase().getCaseName());
				baseVo.setCourtName(casehear.getAttCase().getCourtName());
				baseVo.setPropertyName(casehear.getAttCase().getProperty().getPropName());
			}
			i++;
		}
		
		return hearList;
	}
	
	  private String trimString(final String input,int size){
	    	if(input!=null && input.length()>size){
	    		return input.substring(0, size)+" ...";
	    	}
	    	return input;
	    }
	
	
	
	public List<AttorneyVo> getAttProperties(int attId){
		return attDao.getAttProperties(attId);
		
	}
	
	public List<AttorneyVo> getOwnerCases(Integer ownerId){
		List<AttCase> ownCases=attDao.getOwnerCases(ownerId);
		List<AttorneyVo> ownCaseList=new ArrayList<AttorneyVo>();
		AttorneyVo atthear=null;
		for(AttCase ownCase: ownCases){
			atthear=new AttorneyVo();
			atthear.setCaseId(ownCase.getCaseId());
			atthear.setCaseName(ownCase.getCaseName());
			atthear.setCaseDesc(ownCase.getCaseDesc());
			atthear.setCourtName(ownCase.getCourtName());
			atthear.setUserName(ownCase.getAttUser().getFirstName()+", "+ownCase.getAttUser().getLastName());
			atthear.setPetitioner(ownCase.getPetitioner());
			atthear.setPropertyId(ownCase.getProperty().getPropCode());
			atthear.setPropertyName(ownCase.getProperty().getPropName());
			atthear.setRespondent(ownCase.getRespondent());
			atthear.setStartDate(ownCase.getStartDate());
			atthear.setStatus(ownCase.getStatus());
			atthear.setIsnew(ownCase.getIsnew());
			atthear.setNewreason(ownCase.getNewreason());
			ownCaseList.add(atthear);
		}
		return ownCaseList;
	 }
	
	public List<AttorneyVo> getAttorneyCases(Integer attId){
		List<AttCase> attCases=attDao.getAttorneyCases(attId);
		List<AttorneyVo> attCaseList=new ArrayList<AttorneyVo>();
		AttorneyVo atthear=null;
		for(AttCase attCase: attCases){
			atthear=new AttorneyVo();
			atthear.setCaseId(attCase.getCaseId());
			atthear.setCaseName(attCase.getCaseName());
			atthear.setCaseDesc(attCase.getCaseDesc());
			atthear.setCourtName(attCase.getCourtName());
			//attCase.setCaseattId(attCas.getUserId());
			atthear.setPetitioner(attCase.getPetitioner());
			atthear.setPropertyId(attCase.getProperty().getPropCode());
			atthear.setPropertyName(attCase.getProperty().getPropName());
			atthear.setUserName(attCase.getProperty().getUser().getFirstName()+", "+attCase.getProperty().getUser().getLastName());
			atthear.setRespondent(attCase.getRespondent());
			atthear.setStartDate(attCase.getStartDate());
			atthear.setStatus(attCase.getStatus());
			attCaseList.add(atthear);
		}
		
		return attCaseList;
	}
	
}
