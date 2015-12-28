package com.googlecode.fahservices.config;

/*
 * #%L
 * This file is part of FAHServices.
 * %%
 * Copyright (C) 2014 - 2015 Michael Thomas <mikepthomas@outlook.com>
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.util.Set;
import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

/**
 * <p>ApplicationConfig class.</p>
 *
 * @author Michael Thomas (mikepthomas@outlook.com)
 * @version $Id: $Id
 */
@ApplicationPath("rest")
public class ApplicationConfig extends Application {

    /** {@inheritDoc} */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.googlecode.fahservices.provider.JacksonContextResolver.class);
        resources.add(com.googlecode.fahservices.service.InfoResource.class);
        resources.add(com.googlecode.fahservices.service.OptionsResource.class);
        resources.add(com.googlecode.fahservices.service.PauseResource.class);
        resources.add(com.googlecode.fahservices.service.QueueInfoResource.class);
        resources.add(com.googlecode.fahservices.service.SimulationInfoResource.class);
        resources.add(com.googlecode.fahservices.service.SlotAddResource.class);
        resources.add(com.googlecode.fahservices.service.SlotInfoResource.class);
        resources.add(com.googlecode.fahservices.service.SlotOptionsResource.class);
        resources.add(com.googlecode.fahservices.service.UnpauseResource.class);
    }
}
