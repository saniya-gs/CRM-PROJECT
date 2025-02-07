package com.ourdept.crm_software.tenant_Routing_helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantIdentifierResolver extends AbstractRoutingDataSource {

	private static final Logger logger = LoggerFactory.getLogger(TenantContext.class);

	@Override
	protected Object determineCurrentLookupKey() {
		logger.info("determines the current tenant: "+TenantContext.getCurrentTenant()+", from the tenant context");
		return TenantContext.getCurrentTenant();
	}
}
