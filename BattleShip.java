/**
 * 
 */
package facss.co.jp.java.src;

import java.util.List;

//import org.apache.commons.lang3.StringUtils;

/**
 * @author PC User
 *
 */
public class BattleShip {

	/**
	 * 
	 */
	public BattleShip() {
		
	}

	public String[] setOrgName(List<BattleShipDto> dataDto, int startIndex, int endIndex){
		
		String[] retArray = new String[endIndex];
		
		System.out.println("配列の数:  "+ endIndex);
		for(int index = startIndex; index < endIndex ; index++){
			
			// 団体名使用済みフラグ
			boolean orgNameUseFlag = false;
			StringBuilder sbOutPutOrgName = new StringBuilder();
			
			if(!this.isBlank(dataDto.get(index).abbreviation)){
				sbOutPutOrgName.append(dataDto.get(index).abbreviation);
			}
			
			if(!this.isBlank(dataDto.get(index).orgName1)){
				sbOutPutOrgName.append(",");
				sbOutPutOrgName.append(dataDto.get(index).orgName1);
			} else {
				if(!orgNameUseFlag){
					sbOutPutOrgName.append(",");
					sbOutPutOrgName.append(dataDto.get(index).orgName);
					orgNameUseFlag = true;
				}
			}

			if(!this.isBlank(dataDto.get(index).orgName2)){
				sbOutPutOrgName.append(",");
				sbOutPutOrgName.append(dataDto.get(index).orgName2);
			} else {
				if(!orgNameUseFlag){
					sbOutPutOrgName.append(",");
				    sbOutPutOrgName.append(dataDto.get(index).orgName);
				    orgNameUseFlag = true;
				}
			}

			if(!this.isBlank(dataDto.get(index).orgName3)){
				sbOutPutOrgName.append(",");
				sbOutPutOrgName.append(dataDto.get(index).orgName3);

			} else {
				if(!orgNameUseFlag){
					sbOutPutOrgName.append(",");
				    sbOutPutOrgName.append(dataDto.get(index).orgName);
				    orgNameUseFlag = true;
				}
			}

			if(!this.isBlank(dataDto.get(index).orgName4)){
				sbOutPutOrgName.append(",");
				sbOutPutOrgName.append(dataDto.get(index).orgName4);
			} else {
				if(!orgNameUseFlag){
					sbOutPutOrgName.append(",");
				    sbOutPutOrgName.append(dataDto.get(index).orgName);
				    orgNameUseFlag = true;
				}
			}
			
//			if(!StringUtils.isBlank(dataDto.get(index).orgName4)){
//				sbOutPutOrgName.append(dataDto.get(index).orgName4);
//			} else {
//				if(!orgNameUseFlag){
//				    sbOutPutOrgName.append(dataDto.get(index).orgName);
//				    orgNameUseFlag = true;
//				}
//			}
			
			retArray[index] = sbOutPutOrgName.toString();
		}
		return retArray;
	}

	/**
	 * @param target
	 * @return
	 */
	private boolean isBlank(String target) {
		
		if(target == null || "".equals(target)){
			return true;
		}
		return false;
	}
}
