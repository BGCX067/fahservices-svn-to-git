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
import com.googlecode.jfold.exceptions.PauseException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service.
 *
 * @author Michael Thomas (mikepthomas@outlook.com)
 * @version $Id: $Id
 */
@Path("/pause")
@Api(value = "/pause", description = "Pause all or one slot(s).")
public class PauseResource {

    @Context
    private UriInfo context;
    private Connection connection;

    /**
     * Creates a new instance of PauseResource.
     */
    public PauseResource() {
        try {
            connection = new ClientConnection("localhost", 36330);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves representation of an instance of
     * com.googlecode.fahservices.service.PauseResource.
     *
     * @return an instance of java.lang.String
     */
    @GET
    @ApiOperation(value = "pause",
            notes = "Pause.",
            position = 1)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request", response = PauseException.class)
    })
    public Response getPause() {
        try {
            connection.pause();
            return Response.status(Status.OK).build();
        } catch (PauseException ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex).build();
        }
    }

    /**
     * Retrieves representation of an instance of
     * com.googlecode.fahservices.service.PauseResource.
     *
     * @param slot Slot number
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{slot}")
    @ApiOperation(
            value = "pause",
            notes = "Pause the specified index.",
            position = 2
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request", response = PauseException.class)
    })
    public Response getPause(
            @ApiParam(value = "slot number", required = true)
            @DefaultValue("0")
            @PathParam("slot") final int slot) {
        try {
            connection.pause(slot);
            return Response.status(Status.OK).build();
        } catch (PauseException ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex).build();
        }
    }
}
