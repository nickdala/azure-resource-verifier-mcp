package dev.nickdala.mcp.azureresourceverifier.tools;

import dev.nickdala.mcp.azureresourceverifier.azure.AppService;
import dev.nickdala.mcp.azureresourceverifier.azure.AppServiceOsEnum;
import dev.nickdala.mcp.azureresourceverifier.azure.AppServicePublishingTypeEnum;
import dev.nickdala.mcp.azureresourceverifier.azure.AppServiceRegionResult;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppServiceService {

    private final AppService appService;

    public AppServiceService(AppService appService) {
        this.appService = appService;
    }


    @Tool(
            name = "get-azure-app-service-regions",
            description = """
    Get all Azure regions where a web app / app service can be deployed in the Azure subscription. 
    - Given the os of 'windows' or 'linux' along
    with the publishing type of 'code' or 'container', this tool will display all the regions that the web app / app service
    can be deployed.
    - If the command fails because of authentication errors, ask the user to login to Azure CLI with `az login`.
    - Show both the name and display name of the location.
    """
    )
    public List<AppServiceRegionResult> getRegions(
            @ToolParam(description = "The os either windows or linux") AppServiceOsEnum os,
            @ToolParam(description = "The type of web app, either code or container") AppServicePublishingTypeEnum publishingType) {
        return appService.getRegions(os, publishingType);
    }
}
