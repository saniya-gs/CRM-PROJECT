//package com.ourdept.crm_software.crm.SampleData.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import hrm.SampleData.Service.SampleDataService;
//import io.swagger.v3.oas.annotations.Operation;
//
//@RestController
//@RequestMapping("/api/admin/sample-data")
//public class SampleDataController {
//
//    @Autowired
//    private SampleDataService sampleDataService;
//
//    @Operation(summary = "Delete all sample data from the application")
//    @DeleteMapping("/delete-all")
//    public ResponseEntity<Void> deleteAllSampleData() {
//        sampleDataService.deleteAllSampleData();
//        return ResponseEntity.ok().build();
//    }
//}
