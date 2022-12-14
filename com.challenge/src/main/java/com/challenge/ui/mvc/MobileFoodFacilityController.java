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

package com.challenge.ui.mvc;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.challenge.ui.datarepository.dao.MobileFoodFacilityDataObject;
import com.challenge.ui.datarepository.dao.MobileFoodFacilityFilterDataObject;
import com.challenge.ui.service.impl.MobileFoodFacilityServiceImpl;

/**
 * @author Forrest Dai
 */
@Controller("com.challenge.ui.mvc.MobileFoodFacilityController")
@RequestMapping("/")
public class MobileFoodFacilityController {
	public static final java.lang.String APPLICATION_JSON_VALUE = "application/json";
	private static final Logger logger = LoggerFactory.getLogger(MobileFoodFacilityController.class);
	
	private final MobileFoodFacilityServiceImpl mobileFoodFacilityServiceImpl;

	@Autowired
	public MobileFoodFacilityController(MobileFoodFacilityServiceImpl mobileFoodFacilityServiceImpl) {
		this.mobileFoodFacilityServiceImpl = mobileFoodFacilityServiceImpl;
	}

	@RequestMapping
	public ModelAndView viewList(Model model) {
		logger.info("MobileFoodFacilityController???viewList invoke");
		
		Iterable<MobileFoodFacilityDataObject> facilities = new ArrayList<MobileFoodFacilityDataObject>();
	    model.addAttribute("filter", new MobileFoodFacilityFilterDataObject()); 
		return new ModelAndView("facilities/list", "facilities", facilities);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView getList(Model model, @Valid MobileFoodFacilityFilterDataObject filter, BindingResult result, RedirectAttributes redirect) {
		logger.info("MobileFoodFacilityController???getList invoke");
		
		if (result.hasErrors()) {
			return new ModelAndView("facilities/list", "formErrors", result.getAllErrors());
		}
		
		Iterable<MobileFoodFacilityDataObject> facilities = this.mobileFoodFacilityServiceImpl.getMobileFoodFacilityByFacilityType(filter.getFacilityType());
	    model.addAttribute("filter", filter); 
		return new ModelAndView("facilities/list", "facilities", facilities);
	}

	@RequestMapping("foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}

}
