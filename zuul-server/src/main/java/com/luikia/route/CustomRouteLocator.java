package com.luikia.route;

import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by suateam on 2017/8/30.
 */
public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private ZuulProperties properties;

    public CustomRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
    }

    @Override
    protected Map<String, ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulRoute> routesMap = new LinkedHashMap<String, ZuulRoute>();
        routesMap.putAll(super.locateRoutes());
        routesMap.putAll(this.mylocateRoutes());
        LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            // Prepend with slash if not already present.
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }

    private Map<String, ZuulRoute> mylocateRoutes(){
        Map<String,ZuulRoute> map = new LinkedHashMap<>();
        ZuulRoute zuulRoute = new ZuulRoute();
        zuulRoute.setId("register");
        zuulRoute.setPath("/register/**");
        zuulRoute.setServiceId("EUREKA-REGISTER");
        map.put("/register/**",zuulRoute);
        return map;
    }

    @Override
    public void refresh() {
        System.out.println("do refresh " + System.currentTimeMillis());
        super.doRefresh();
    }
}
