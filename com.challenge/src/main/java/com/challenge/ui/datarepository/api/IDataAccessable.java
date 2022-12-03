package com.challenge.ui.datarepository.api;

import java.util.List;

import com.challenge.ui.datarepository.dao.MobileFoodFacilityDataObject;

public interface IDataAccessable {
	
	public List<MobileFoodFacilityDataObject> getData();
}
