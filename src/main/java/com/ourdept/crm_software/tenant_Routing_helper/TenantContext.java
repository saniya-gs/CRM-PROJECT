package com.ourdept.crm_software.tenant_Routing_helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantContext {

	private static final Logger logger = LoggerFactory.getLogger(TenantContext.class);

	private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

	public static void setCurrentTenant(String tenant) {
//		logger.info("sets the tenant context"+tenant);
		currentTenant.set(tenant);
	}

	public static String getCurrentTenant() {
//		logger.info("gets the tenant context"+currentTenant.get());
		return currentTenant.get();
	}

	public static void clear() {
		currentTenant.remove();
	}
}
