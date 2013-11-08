package com.pearl.normandy.userSalary;

import java.util.List;
import org.apache.log4j.Logger;
import com.pearl.safe.EncryptUtil;
import flex.messaging.MessageException;

public class UserSalaryService {

	static Logger log = Logger.getLogger(UserSalary.class.getName());
	
	public List<UserSalary> getUserSalarysByUserId(int userId) throws Exception{
		return userSalaryDao.getUserSalarysByUserId(userId);
	}
	
	public List<UserSalary> getDecryptedUserSalarysByUserId(int userId, String modulus, String priExponent, String userName){
		List<UserSalary> list = userSalaryDao.getUserSalarysByUserId(userId);
		try{
			for(UserSalary us : list){
				String salary 		= Float.toString(EncryptUtil.decrypt(modulus, priExponent, us.getSalary(), userName));
				String insurance 	= Float.toString(EncryptUtil.decrypt(modulus, priExponent, us.getInsurance(), userName));
				String socialType1	= Float.toString(EncryptUtil.decrypt(modulus, priExponent, us.getSocialBenefitType1(), userName));
				String socialType2	= Float.toString(EncryptUtil.decrypt(modulus, priExponent, us.getSocialBenefitType2(), userName));
				String houseFound	= Float.toString(EncryptUtil.decrypt(modulus, priExponent, us.getHouseFound(), userName));
				String lunchBenefit	= Float.toString(EncryptUtil.decrypt(modulus, priExponent, us.getLunchBenefit(), userName));
				
				us.setSalary(salary);
				us.setInsurance(insurance);
				us.setSocialBenefitType1(socialType1);
				us.setSocialBenefitType2(socialType2);
				us.setHouseFound(houseFound);
				us.setLunchBenefit(lunchBenefit);
			}
		}catch(Exception e){
			throw new MessageException();
		}
			
		return list;
	}
	
	public int createUserSalary(UserSalary us) throws Exception{			
		return userSalaryDao.createUserSalary(encryptObjectProperties(us));
	}
	
	public void updateUserSalary(UserSalary us) throws Exception{
		userSalaryDao.updateUserSalary(encryptObjectProperties(us));
	}
	
	private UserSalary encryptObjectProperties(UserSalary us) throws Exception{
		float salary		= new Float(us.getSalary());
		float insurance		= new Float(us.getInsurance());
		float socialType1	= new Float(us.getSocialBenefitType1());
		float socialType2	= new Float(us.getSocialBenefitType2());
		float houseFound	= new Float(us.getHouseFound());
		float lunchBenefit	= new Float(us.getLunchBenefit());
		
		us.setSalary(EncryptUtil.encrypt(salary));
		us.setInsurance(EncryptUtil.encrypt(insurance));
		us.setSocialBenefitType1(EncryptUtil.encrypt(socialType1));
		us.setSocialBenefitType2(EncryptUtil.encrypt(socialType2));
		us.setHouseFound(EncryptUtil.encrypt(houseFound));
		us.setLunchBenefit(EncryptUtil.encrypt(lunchBenefit));
		
		return us;
	}
	
	public void deleteUserSalary(UserSalary us) throws Exception{
		userSalaryDao.deleteUserSalary(us);
	}
	
	private UserSalaryDao userSalaryDao;

	public void setUserSalaryDao(UserSalaryDao userSalaryDao) {
		this.userSalaryDao = userSalaryDao;
	}
}
