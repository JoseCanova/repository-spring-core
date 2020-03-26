package test.org.nanotek;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.nanotek.beans.csv.AreaBean;
import org.nanotek.beans.entity.Area;
import org.nanotek.beans.entity.AreaBeginDate;
import org.nanotek.beans.entity.AreaEndDate;

class TestAreaBeanMapper {

	@Test
	void test() {
		ModelMapper modelMapper = new ModelMapper();
		AreaBean areaBean  = new AreaBean();
		areaBean.setAreaId(100L);
		areaBean.setType(100L);
		areaBean.setName("AreaBean Name");
		areaBean.setBeginYear(2000);
		areaBean.setEndYear(2000);
		areaBean.setComment("AreaBeanComment");
		Area area = modelMapper.map(areaBean, Area.class);
		AreaEndDate areaEndDate = modelMapper.map(areaBean, AreaEndDate.class);
		AreaBeginDate areaBeginDate = modelMapper.map(areaBean, AreaBeginDate.class);
		area.setAreaEndDate(areaEndDate);
		area.setAreaBeginDate(areaBeginDate);
		assertEquals(areaBean.getAreaId(), area.getAreaId());
		assertEquals(areaBean.getName(), area.getName());
		assertEquals(areaBean.getComment(), area.getAreaComment().getComment());
		assertEquals(areaBean.getEndYear(), area.getAreaEndDate().getEndYear());
		assertEquals(areaBean.getBeginYear(), areaBeginDate.getBeginYear());
		
	}
	
	@Test
	void testAreaBeginDate() {
		ModelMapper modelMapper = new ModelMapper();
		AreaBean areaBean  = new AreaBean();
		areaBean.setBeginYear(2000);
		AreaBeginDate areaBeginDate = modelMapper.map(areaBean, AreaBeginDate.class);
		assertEquals(areaBean.getBeginYear(), areaBeginDate.getBeginYear());
		
	}
	
	void testAreaEndDate() {
		ModelMapper modelMapper = new ModelMapper();
		AreaBean areaBean  = new AreaBean();
		areaBean.setEndYear(2000);
		AreaEndDate areaEndDate = modelMapper.map(areaBean, AreaEndDate.class);
	}

}
