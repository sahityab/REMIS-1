package com.prop.mnt.property.user.relation;

import java.util.ArrayList;
import java.util.List;

import com.prop.mnt.admin.user.UserDAO;
import com.prop.mnt.model.PropUser;
import com.prop.mnt.model.PropUserRel;
import com.prop.mnt.vo.PropRentInfoVo;



public class PropertyUserRelationService implements PropertyUserRelationInterface{
	private PropertyUserRelationDAO propUserRelDAO;
	private UserDAO userDao;
	
	
	
	public PropertyUserRelationService(){
		propUserRelDAO=new PropertyUserRelationDAO();
		userDao=new UserDAO();
	}
	
	
	
	public List<PropUserRelVO> getRelationListService() throws Exception{
		PropUserRelVO propUserRelVO = null;
		List<PropUserRel> propUserRelListDO = null;
		List<PropUserRelVO> propUserRelListVO = new ArrayList<PropUserRelVO>();
		propUserRelListDO = propUserRelDAO.getRelationList();
		for(PropUserRel itemDO : propUserRelListDO){
			propUserRelVO = new PropUserRelVO();
			propUserRelVO.setRelationCode(itemDO.getRelationCode());
			propUserRelVO.setRelationName(itemDO.getRelationName());
			//BeanUtils.copyProperties(propUserRelVO, itemDO);
			propUserRelListVO.add(propUserRelVO);
		}
		return propUserRelListVO;
	}
	
	public PropRentInfoVo getRentTntInfo(int propId){
		return propUserRelDAO.getPropertyTenantRentInfo(propId);
	}
	
	
	public List<PropUserVO> findProperties(int  userId){
	      return userDao.getPropList(userId);
	}
	
	/*public List<PropUserRelVO> findPropertyTenants(int userId,int propertyId){
		return propUserRelDAO.getUserPropertyTenants(userId,propertyId);
	}
	
	public List<PropUserRelVO> findUserRentProperties(int userId){
		return propUserRelDAO.getUserSubPropertyList(userId);
		List<PropUserRelVO> rentPropList=new ArrayList<PropUserRelVO>();
		PropUserRelVO propuservo=null;
		for(PropUser propuser:subpropList){
			propuservo=new PropUserRelVO();
			propuservo.setId(propuser.getId());
			propuservo.setPropName(propuser.getProperty().getPropName());
			if(propuser.getPropertyDivision()!=null)propuservo.setDivisionInfo(propuser.getPropertyDivision().getDivId()+", "+propuser.getPropertyDivision().getDivTitle());
			propuservo.setUserName(propuser.getUser().getFullName());
			propuservo.setRentamount(propuser.getRentamount());
			rentPropList.add(propuservo);
		}
		return rentPropList;
	}
	
	
	public List<SubPropertyVo> findSubProperties(int propId){
		List<SubPropertyVo> subPropList=new ArrayList<SubPropertyVo>();
		List<PropertyDivision> subpropList=propUserRelDAO.getSubPropertyList(propId);
		SubPropertyVo subPropertyVo=null;
		try{
		for(PropertyDivision subprop:subpropList){
			subPropertyVo = new SubPropertyVo();
			subPropertyVo.setDivId(subprop.getDivId());
			subPropertyVo.setDivTitle(subprop.getDivTitle());
			subPropertyVo.setId(subprop.getId());
			//BeanUtils.copyProperties(subPropertyVo, subprop);
			subPropList.add(subPropertyVo);
		}
		}catch(Exception e){e.printStackTrace();}
		System.out.println("subPropList=="+subPropList.size());
		return subPropList;
	}*/
	public Integer payRentPayment(Integer rentinfoId,int userid){
		return propUserRelDAO.payRentPayment(rentinfoId,userid);
	}
	public List<PropRentInfoVo> findMonthRentals(int  userId){
		return propUserRelDAO.getTNTRentalss(userId);
	}
	
	public List<PropRentInfoVo> findCurrnetTNTPayments(int  userId){
		return propUserRelDAO.getTNTPayments(userId);
	}
	
	
	public Integer saveRent(PropRentInfoVo propRentInfoVo){
		int i =0;
		 if(propRentInfoVo.getId()==null) 
			 i= propUserRelDAO.saveRent(propRentInfoVo);
		 else i=propUserRelDAO.updateRent(propRentInfoVo);
		System.out.println("rent saved=="+i);
		return i;
	
	}
	
	public Integer saveRelation(PropUserVO propUserVO)  throws Exception{
		int i = propUserRelDAO.saveRelation(propUserVO);
		return i;
	
	}
	
	public Integer deleteRelation(PropUserVO propUserVO) throws Exception {
		PropUser puDO = new PropUser();
		puDO =propUserRelDAO.selectRecord(propUserVO);
		puDO.setStatus("D");
		int i = propUserRelDAO.deleteRelation(puDO);
		return i;
	}
}