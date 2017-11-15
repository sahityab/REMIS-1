package com.prop.mnt.service;

import java.util.List;

import com.prop.mnt.model.AttCase;
import com.prop.mnt.model.CaseHearing;
import com.prop.mnt.vo.AttorneyVo;

public interface AttorneyService {

	public Integer  saveAttCase(AttorneyVo attCase);
	public Integer  saveCaseHearing(AttorneyVo caseHearing);
	public Integer updateCaseHearing(AttorneyVo caseHearing);
	public AttorneyVo  viewCaseHear(int casehearId,int userId);
	public AttorneyVo  viewAttCase(int caseId,int userId);
	public List<AttorneyVo> getCaseHearings(Integer caseId, int limit,AttorneyVo baseVo);
	public List<AttorneyVo> getAttProperties(int attId);
	public List<AttorneyVo> getOwnerCases(Integer ownerId);
	public List<AttorneyVo> getAttorneyCases(Integer attId);
	public Integer updateAttCase(AttorneyVo attVo);
	
	public Integer resetIsnew(Integer caseid);
	public Integer resetHearingIsnew(Integer caseid,Integer hearId);
}
