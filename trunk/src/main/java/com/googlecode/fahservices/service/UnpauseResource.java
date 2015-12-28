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
import com.googlecode.jfold.exceptions.UnpauseException;
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
@Path("/unpause")
@Api(value = "/unpause", description = "Unpause all or one slot(s).")
public class UnpauseResource {

    @Context
    private UriInfo context;
    private Connection connection;

    /**
     * Creates a new instance of UnpauseResource.
     */
    public UnpauseResource() {
        try {
            connection = new ClientConnection("localhost", 36330);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves representation of an instance of
     * com.googlecode.fahservices.service.UnpauseResource.
     *
     * @return an instance of java.lang.String
     */
    @GET
    @ApiOperation(value = "unpause",
            notes = "Unpause.",
            position = 1)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request", response = UnpauseException.class)
    })
    public Response getUnpause() {
        try {
            connection.unpause();
            return Response.status(Status.OK).build();
        } catch (UnpauseException ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex).build();
        }
    }

    /**
     * Retrieves representation of an instance of
     * com.googlecode.fahservices.service.UnpauseResource.
     *
     * @param slot Slot number
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{slot}")
    @ApiOperation(
            value = "unpause",
            notes = "Unpause the specified index.",
            position = 2
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request", response = UnpauseException.class)
    })
    public Response getUnpause(
            @ApiParam(value = "slot number", required = true)
            @DefaultValue("0")
            @PathParam("slot") final int slot) {
        try {
            connection.unpause(slot);
            return Response.status(Status.OK).build();
        } catch (UnpauseException ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex).build();
        }
    }
}
