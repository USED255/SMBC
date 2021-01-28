/**
 * 
 */
package facss.co.jp.java.src;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PC User
 *
 */
public class ViewMain {

	/**
	 * 
	 */
	public ViewMain() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BattleShip bs = new BattleShip();
		
		List<BattleShipDto> dto = new ArrayList<BattleShipDto>();
		
		BattleShipDto bsDto01 = new BattleShipDto();
		
		bsDto01.orgName = "金剛";
		bsDto01.abbreviation = "『金』";
		//bsDto01.orgName1 = "金剛";	
		bsDto01.orgName2 = "比叡";
		bsDto01.orgName3 = "榛名";
		bsDto01.orgName4 = "霧島";
		dto.add(bsDto01);

		BattleShipDto bsDto02 = new BattleShipDto();
		bsDto02.orgName = "鳥海";
		bsDto02.abbreviation = "『高』";
		bsDto02.orgName1 = "高雄";	
		bsDto02.orgName2 = "愛宕";
		bsDto02.orgName3 = "摩耶";
		bsDto02.orgName4 = null;
		dto.add(bsDto02);

		BattleShipDto bsDto03 = new BattleShipDto();
		bsDto03.orgName = "暁";
		bsDto03.abbreviation = "『特Ⅲ』";
		bsDto03.orgName1 = "";	
		bsDto03.orgName2 = "Верный";
		bsDto03.orgName3 = "雷";
		bsDto03.orgName4 = "電";
		dto.add(bsDto03);
		
		BattleShipDto bsDto04 = new BattleShipDto();
		bsDto04.orgName = "川内";
		bsDto04.abbreviation = "『川内』";
		bsDto04.orgName1 = "";	
		bsDto04.orgName2 = "神通";
		bsDto04.orgName3 = "那珂";
		dto.add(bsDto04);

		
		
		int startIndex = 0;
		int endIndex = dto.size();
		String[] bsArray = bs.setOrgName(dto, startIndex, endIndex);
		
		System.out.println("配列の数:  "+ bsArray.length);
		for(int index = 0 ; index < bsArray.length ; index++){
			System.out.println(bsArray[index]);
		}
	}
}
