package dev.nickdala.mcp.azureresourceverifier.tools;

import dev.nickdala.mcp.azureresourceverifier.azure.PostgresFlexibleServer;
import dev.nickdala.mcp.azureresourceverifier.azure.PostgresCapability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostgresMcpTool {

    private final Logger log = LoggerFactory.getLogger(PostgresMcpTool.class);

    private final PostgresFlexibleServer postgresFlexibleServer;

    public PostgresMcpTool(PostgresFlexibleServer postgresFlexibleServer) {
        this.postgresFlexibleServer = postgresFlexibleServer;
    }

    @Tool(
            name = "get-postgres-capabilities",
            description = """
    Get all PostgreSQL flexible server capabilities in the Azure subscription. This tool is used to get a list of all PostgreSQL flexible server capabilities
    in the Azure subscription.  The list of capabilities is used to verify if the resource can be deployed to an Azure region.
    If the command fails because of authentication errors, ask the user to login to Azure CLI with `az login`.
    """
    )
    public List<PostgresCapability> findAllPostgres(@ToolParam(description = "The list of Azure regions") List<String> regions) {
        log.info("Finding all PostgreSQL flexible servers");
        return postgresFlexibleServer.findAll(regions);
    }
}
