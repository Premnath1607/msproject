package com.tpn.zuulgateway.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.netflix.zuul.ZuulFilter;

public class PostFilter extends ZuulFilter {

	private static final Logger logger = LogManager.getLogger(PostFilter.class);

	@Override
	public String filterType() {
		return "post";
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
		logger.info("Inside Response Filter");

		return null;
	}

}