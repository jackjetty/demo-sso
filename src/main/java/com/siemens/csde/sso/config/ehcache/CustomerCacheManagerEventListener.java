package com.siemens.csde.sso.config.ehcache;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;
 


/**
 * ehcache customer cacheManagerEventListener
 * Created by yangjinfeng on 2017/1/5.
 */
@Slf4j
public class CustomerCacheManagerEventListener implements CacheManagerEventListener {

   

    private final CacheManager cacheManager;

    public CustomerCacheManagerEventListener(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void init() throws CacheException {
        log.info("init ehcache...");
    }

    @Override
    public Status getStatus() {
        return null;
    }

    @Override
    public void dispose() throws CacheException {
        log.info("ehcache dispose...");
    }

    @Override
    public void notifyCacheAdded(String s) {
        log.info("cacheAdded... {}", s);
        log.info(cacheManager.getCache(s).toString());
    }

    @Override
    public void notifyCacheRemoved(String s) {

    }
}