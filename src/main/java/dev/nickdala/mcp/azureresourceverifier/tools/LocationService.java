package dev.nickdala.mcp.azureresourceverifier.tools;

//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import dev.nickdala.mcp.azureresourceverifier.azure.Location;
import dev.nickdala.mcp.azureresourceverifier.azure.LocationFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

//@RestController
//@RequestMapping("/api/v1/azure")
@Service
public class LocationService {

    private static final Logger log = LoggerFactory.getLogger(LocationService.class);

    private final LocationFinder locationFinder;

    public LocationService(LocationFinder locationFinder) {
        this.locationFinder = locationFinder;
    }

    //@GetMapping("/locations")
    @Tool(
            name = "get-azure-regions",
            description = """
    Get all Azure regions in the Azure subscription. This tool is used to get a list of all Azure regions
    in the Azure subscription. The list of locations is used to verify if the resource can be deployed to an Azure region.
    If the command fails because of authentication errors, ask the user to login to Azure CLI with `az login`.
    Show both the name and display name of the location.
    """
    )
    //public List<Location> getAzureLocations(@ToolParam(description = "The Azure subscription id", required = true) String subscriptionId) {
    public List<Location> getAzureLocations() {
        return locationFinder.findAllLocations();
    }
}
