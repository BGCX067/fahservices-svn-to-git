package com.googlecode.fahservices.service;

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

import com.googlecode.jfold.Connection;
import com.googlecode.jfold.ClientConnection;
import com.googlecode.jfold.exceptions.SlotInfoException;
import com.googlecode.jfold.slot.Slot;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service.
 *
 * @author Michael Thomas (mikepthomas@outlook.com)
 * @version $Id: $Id
 */
@Path("/slot-info")
@Api(value = "/slot-info", description = "Get slot information.")
public class SlotInfoResource {

    @Context
    private UriInfo context;
    private Connection connection;

    /**
     * Creates a new instance of SlotInfoResource.
     */
    public SlotInfoResource() {
        try {
            connection = new ClientConnection("localhost", 36330);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves representation of an instance of
     * com.googlecode.fahservices.service.SlotInfoResource.
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces({
        MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML,
        MediaType.TEXT_XML
    })
    @ApiOperation(value = "slot-info",
            notes = "Get List of slot information.",
            position = 1,
            response = Slot.class,
            responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Error", response = SlotInfoException.class)
    })
    public Response getSlotInfo() {
        try {
            List<Slot> value = getSlotInfoList();
            return Response.status(Status.OK).entity(new GenericEntity<List<Slot>>(value) { }).build();
        } catch (SlotInfoException ex) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex).build();
        }
    }

    /**
     * Retrieves representation of an instance of
     * com.googlecode.fahservices.service.SlotInfoResource.
     *
     * @param slot Slot number
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{slot}")
    @Produces({
        MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML,
        MediaType.TEXT_XML
    })
    @ApiOperation(
            value = "slot-info",
            notes = "Get slot information at specified index.",
            response = Slot.class,
            position = 2
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Error", response = SlotInfoException.class)
    })
    public Response getSlotInfo(
            @ApiParam(value = "slot number", required = true)
            @DefaultValue("0")
            @PathParam("slot") final int slot) {
        try {
            Slot value = getSlotInfoList().get(slot);
            return Response.status(Response.Status.OK).entity(value).build();
        } catch (SlotInfoException ex) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex).build();
        }
    }

    private List<Slot> getSlotInfoList() throws SlotInfoException {
        return connection.slotInfo();
    }
}
