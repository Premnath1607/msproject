package com.tpn.zuulgateway.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.netflix.zuul.ZuulFilter;

public class RouteFilter extends ZuulFilter {

	private static final Logger logger = LogManager.getLogger(RouteFilter.class);

	@Override
	public String filterType() {
		return "route";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		logger.info("Inside Route Filter");

		return null;
	}

}