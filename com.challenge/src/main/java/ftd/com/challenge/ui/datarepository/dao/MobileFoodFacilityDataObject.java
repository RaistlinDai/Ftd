/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package ftd.com.challenge.ui.datarepository.dao;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Forrest Dai
 */
public class MobileFoodFacilityDataObject {

	@NotEmpty(message = "Location ID is required.")
	private Long locationid;

	private String applicant;

	private String facilityType;

	public Long getLocationid() {
		return this.locationid;
	}

	public void setLocationid(Long locationid) {
		this.locationid = locationid;
	}

	public String getApplicant() {
		return this.applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getFacilityType() {
		return this.facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}
}
