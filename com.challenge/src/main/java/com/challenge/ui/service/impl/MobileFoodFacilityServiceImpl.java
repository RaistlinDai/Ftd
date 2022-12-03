package com.challenge.ui.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.challenge.ui.datarepository.dao.MobileFoodFacilityDataObject;
import com.challenge.ui.datarepository.service.impl.URLAccessDataRepository;

@Service("com.challenge.ui.service.impl.MobileFoodFacilityServiceImpl")
public class MobileFoodFacilityServiceImpl {
	
	/**
	 * 
	 * @param facilityType
	 * @return
	 */
	public List<MobileFoodFacilityDataObject> getMobileFoodFacilityByFacilityType(String facilityType) {
		// Retrieve data
		URLAccessDataRepository dataRep = new URLAccessDataRepository();
		List<MobileFoodFacilityDataObject> fList = 
				dataRep.getData().stream().filter(row -> row.getFacilityType().equals(facilityType)).
				collect(Collectors.toList());
		return fList;
	}
}
