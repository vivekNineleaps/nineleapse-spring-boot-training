package com.nineleaps.springboot.training.config;

import io.micrometer.appoptics.AppOpticsConfig;
import io.micrometer.appoptics.AppOpticsMeterRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.lang.Nullable;

public class MonitoringConfig {
	
	AppOpticsConfig appopticsConfig = new AppOpticsConfig() {
	    @Override
	    public String apiToken() {
	        return "YwbFb4-O2Hyf7szcUnqkb091-sDyRORgCyONYRyuv9HDGMvzYP7DgCqsTV6GM7l7pCIAoNQ";
	    }

	    @Override
	    @Nullable
	    public String get(String k) {
	        return null;
	    }
	};
	MeterRegistry registry = new AppOpticsMeterRegistry(appopticsConfig, Clock.SYSTEM);
}
