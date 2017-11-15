package com.prop.mnt.property.user.relation;

import java.util.List;

import com.prop.mnt.model.PropUser;
import com.prop.mnt.vo.PropRentInfoVo;

public interface PropertyUserRelationInterface {
	List<PropUserRelVO> getRelationListService() throws Exception;
	Integer saveRelation(PropUserVO propUserVO)  throws Exception;
	Integer deleteRelation(PropUserVO propUserVO) throws Exception;
	public List<PropUserVO> findProperties(int  userId);
	public PropRentInfoVo getRentTntInfo(int propId);
	public Integer saveRent(PropRentInfoVo propRentInfoVo);
	public List<PropRentInfoVo> findCurrnetTNTPayments(int  userId);
	public List<PropRentInfoVo> findMonthRentals(int  userId);
	public Integer payRentPayment(Integer rentinfoId,int userid) ;
	/*public List<PropUserRelVO> findUserRentProperties(int userId);
	public List<PropUserRelVO> findPropertyTenants(int userId,int propertyId);*/
}
