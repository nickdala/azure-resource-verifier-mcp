package dev.nickdala.mcp.azureresourceverifier;

import dev.nickdala.mcp.azureresourceverifier.azure.AppService;
import dev.nickdala.mcp.azureresourceverifier.azure.AppServiceOsEnum;
import dev.nickdala.mcp.azureresourceverifier.azure.AppServicePublishingTypeEnum;
import dev.nickdala.mcp.azureresourceverifier.azure.AppServiceRegionResult;

import java.util.List;

//@RestController
//@RequestMapping("/api/v1/azure")
public class ApiController {

    private final AppService appService;

    public ApiController(AppService appService) {
        this.appService = appService;
    }

    // Add your API endpoints here

    // Example endpoint
    //@RequestMapping("/example")
    public String example() {
        return "This is an example endpoint.";
    }

    // Add more endpoints as needed
    //@RequestMapping("/appservice")
    //public List<AppServiceRegionResult> getAppServiceRegions(@RequestParam String os, @RequestParam String publishingType) {
    public List<AppServiceRegionResult> getAppServiceRegions(String os, String publishingType) {
        // Call the AppServiceService to get the regions
        return appService.getRegions(AppServiceOsEnum.fromString(os), AppServicePublishingTypeEnum.fromString(publishingType));

        //return List.of(new AppServiceRegionResult("example-region", "Example Region"));
    }

}
