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
            name = "get-postgres-regional-capabilities",
            description = """
    Determines if a PostgreSQL flexible server can be deployed to an Azure region. If PostgreSQL is restricted in that
    region, the tool gives the reason for the restriction. In addition, the tool gets the capabilities for the PostgreSQL
    server in that region: for example high availability (HA) is only supported in certain regions.
    If the command fails because of authentication errors, ask the user to login to Azure CLI with `az login`.
    """
    )
    public List<PostgresCapability> findAllPostgres(@ToolParam(description = "The list of Azure regions") List<String> regions) {
        log.info("Finding all PostgreSQL flexible servers");
        return postgresFlexibleServer.findAll(regions);
    }
}
