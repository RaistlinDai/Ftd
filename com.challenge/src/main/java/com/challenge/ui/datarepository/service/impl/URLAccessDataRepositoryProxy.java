package com.challenge.ui.datarepository.service.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.challenge.ui.datarepository.api.IDataAccessable;
import com.challenge.ui.datarepository.dao.MobileFoodFacilityDataObject;

/**
 * @author Forrest Dai
 * Proxy class of URLAccessDataRepository
 */
@Component
public class URLAccessDataRepositoryProxy implements IDataAccessable {

	private static final Logger logger = LoggerFactory.getLogger(URLAccessDataRepository.class);
	
	private URLAccessDataRepository urlAccessDataRepository;
	
	private final String KEY_URL_DATASOURCE = "URL";
	private ConcurrentHashMap<String, List<MobileFoodFacilityDataObject>> urlDataCache = 
			new ConcurrentHashMap<String, List<MobileFoodFacilityDataObject>>(256);
	
	// Cache
	private ScheduledThreadPoolExecutor executor;
	private Future<?> refreshFuture;
	private final String THREAD_URL_CACHE_NAME = "URL_DATASOURCE_CACHE";
	private final int THREAD_URL_CACHE_REFRESH_INTERVAL = 30;
	
	@Override
	public List<MobileFoodFacilityDataObject> getData() {

		logger.info("proxy.getData invoke");
		
		if (urlAccessDataRepository == null)
			urlAccessDataRepository = URLAccessDataRepository.getInstance();
		
		if (urlDataCache.isEmpty() || !urlDataCache.containsKey(KEY_URL_DATASOURCE)) 
			this.runCacheRefresher();

		return urlDataCache.get(KEY_URL_DATASOURCE);
	}
	
    private void runCacheRefresher() {
        if (executor != null) {
            return;
        }
        synchronized (urlDataCache) {
            if (executor != null) {
                return;
            }
            logger.info("URLAccessDataRepositoryProxy.runCacheRefresher: Startup cache");
            // Initialize cache
            urlDataCache.put(KEY_URL_DATASOURCE, urlAccessDataRepository.getData());
            // Get instance of the scheduled thread pool
            executor = new ScheduledThreadPoolExecutor(1, r -> new Thread(r, THREAD_URL_CACHE_NAME));
            // Schedule refresher
            refreshFuture = executor.scheduleAtFixedRate(() -> {
            	logger.info("URLAccessDataRepositoryProxy.runCacheRefresher: Refresh cache");
            	// Refresh cache
                urlDataCache.put(KEY_URL_DATASOURCE, urlAccessDataRepository.getData());
            }, THREAD_URL_CACHE_REFRESH_INTERVAL, THREAD_URL_CACHE_REFRESH_INTERVAL, TimeUnit.SECONDS);
        }
    }

    @PreDestroy
    public void shutdownCache(){
        logger.info("URLAccessDataRepositoryProxy.runCacheRefresher: Close cache");
        
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
        if (refreshFuture != null) {
            refreshFuture.cancel(true);
        }
    }
}
