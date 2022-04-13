package com.trongit.api.admin;


import com.trongit.dto.BuildingDTO;
import com.trongit.dto.request.AssignmentBuildingRequest;
import com.trongit.dto.response.BuildingResponse;
import com.trongit.dto.response.StaffAssignmentResponse;
import com.trongit.service.BuildingService;
import com.trongit.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<BuildingResponse> findAll(@RequestParam(required = false) Map<String, Object> params,
                                          @RequestParam(name = "renttypes", required = false) List<String> rentTypes) {
        return buildingService.findAll(params, rentTypes);
    }

    @GetMapping("/name")
    public List<BuildingResponse> findByNameLike(@RequestParam(required = false, name = "name") String name) {
        return buildingService.findByNameLike(name);
    }

    @PostMapping
    public BuildingDTO save(@RequestBody(required = false) BuildingDTO buildingDTO) {
        return buildingService.save(buildingDTO);
    }

    @GetMapping("/{id}/staff")
    public List<StaffAssignmentResponse> getAllStaffByBuilding(@PathVariable("id") Long id) {
        return userService.getAllStaffAssignmentBuilding(id);
    }

    @PostMapping("/{id}/assignment")
    public AssignmentBuildingRequest assignmentBuilding(@RequestBody(required = false) AssignmentBuildingRequest assignmentBuildingRequest
            , @PathVariable("id") Long buildingId) {
        buildingService.assignmentBuilding(assignmentBuildingRequest, buildingId);
        return assignmentBuildingRequest;
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id) {
        buildingService.delete(id);
        return id;
    }
}
