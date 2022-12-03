package ftd.com.challenge.ui.datarepository.api;

import java.util.List;

import ftd.com.challenge.ui.datarepository.dao.MobileFoodFacilityDataObject;

public interface IDataRepository {
	
	public List<MobileFoodFacilityDataObject> getData();
}
