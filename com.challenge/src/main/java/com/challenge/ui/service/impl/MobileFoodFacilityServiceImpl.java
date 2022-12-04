package com.challenge.ui.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.challenge.ui.datarepository.dao.MobileFoodFacilityDataObject;
import com.challenge.ui.datarepository.service.impl.URLAccessDataRepository;

/**
 * @author Forrest Dai
 */
@Service("com.challenge.ui.service.impl.MobileFoodFacilityServiceImpl")
public class MobileFoodFacilityServiceImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(MobileFoodFacilityServiceImpl.class);
	
	/**
	 * 
	 * @param facilityType
	 * @return
	 */
	public Iterable<MobileFoodFacilityDataObject> getMobileFoodFacilityByFacilityType(String facilityType) {
		logger.info("getMobileFoodFacilityByFacilityType invoke");
		
		// Retrieve data
		URLAccessDataRepository dataRep = new URLAccessDataRepository();
		List<MobileFoodFacilityDataObject> fList = dataRep.getData();
		assert(facilityType != null);
		assert(fList != null);
		fList = fList.stream().filter(row -> facilityType.equalsIgnoreCase(row.getFacilityType())).
				collect(Collectors.toList());
		return fList;
	}
}
