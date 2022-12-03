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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * @author Forrest Dai
 */
@Builder
@Value
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileFoodFacilityDataObject {

	@NotEmpty(message = "Object ID is required.")
	@JsonProperty("objectid")
	private Long objectId;

	@JsonProperty("applicant")
	private String applicant;

	@JsonProperty("facilitytype")
	private String facilityType;

	@JsonProperty("cnn")
	private String cnn;

	@JsonProperty("locationdescription")
	private String locationDescription;

	@JsonProperty("address")
	private String address;

	@JsonProperty("blocklot")
	private String blocklot;

	@JsonProperty("block")
	private String block;

	@JsonProperty("lot")
	private String lot;

	@JsonProperty("permit")
	private String permit;

	@JsonProperty("status")
	private String status;

	@JsonProperty("x")
	private double posX;

	@JsonProperty("y")
	private double posY;

	@JsonProperty("latitude")
	private double latitude;

	@JsonProperty("longitude")
	private double longitude;

	@JsonProperty("schedule")
	private String schedule;

	@JsonProperty("received")
	private String received;

	@JsonProperty("priorpermit")
	private String priorPermit;

	@JsonProperty("expirationdate")
	private String expirationDate;
}
