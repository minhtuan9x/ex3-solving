package com.trongit.controller.admin;

import com.trongit.converter.BuildingConverter;
import com.trongit.dto.BuildingDTO;
import com.trongit.dto.request.BuildingSearchRequest;
import com.trongit.service.BuildingService;
import com.trongit.service.BuildingTypeService;
import com.trongit.service.DistrictService;
import com.trongit.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class BuildingController {

    @Autowired
    private DistrictService districtService;
    @Autowired
    private BuildingTypeService buildingTypeService;
    @Autowired
    private UserService userService;
    @Autowired
    private BuildingService buildingService;


    @GetMapping("/building-list")
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchRequest buildingSearchRequest) {
        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        modelAndView.addObject("modelDistrict", districtService.getAll());
        modelAndView.addObject("modelStaff", userService.getAllStaff());
        modelAndView.addObject("modelBuildingType", buildingTypeService.getAll());
        modelAndView.addObject("modelBuildings", buildingService.findAll(buildingSearchRequest));
        return modelAndView;
    }

    @GetMapping("/building-edit")
    public ModelAndView buildingEdit(@RequestParam(name = "buildingid", required = false) Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("modelDistrict", districtService.findAllWithMap());
        modelAndView.addObject("modelBuildingType", buildingTypeService.findAllWithMap());
        modelAndView.addObject("modelBuilding", buildingService.findById(id));
        return modelAndView;
    }

}
