package com.poc.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.poc.core.Trader;
import com.poc.dto.NewOrderSingleRequestDto;
import com.poc.dto.NewOrderSingleResponseDto;
import com.poc.service.NewOrderSingleService;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.SessionNotFound;

@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@RequestScoped
@Path("/new-order-single")
@Tag(name = "NewOrderSingle")
public class NewOrderSingleRest {

    private static final Logger LOG = LoggerFactory.getLogger(NewOrderSingleRest.class.getName());

    @Inject
    Trader trader;

    @Inject
    NewOrderSingleService newOrderSingleService;

    @POST
    @Operation(summary = "Send New Order Single")
    @APIResponse(responseCode = "200", description = "NewOrderSingleRest", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NewOrderSingleResponseDto.class)) })
    // @APIResponse(responseCode = "422", description = "Negocial error", content =
    // {
    // @Content(mediaType = "application/json", schema = @Schema(implementation =
    // Error.class)) })
    // @APIResponse(responseCode = "500", description = "System error", content = {
    // @Content(mediaType = "application/json", schema = @Schema(implementation =
    // Error.class)) })
    public Response newOrderSingleRest(NewOrderSingleRequestDto request) throws SessionNotFound {

        Gson gson = new Gson();
        String json = gson.toJson(request);
        LOG.debug("Request: " + json);

        NewOrderSingleResponseDto response = newOrderSingleService.newOrderSingle(request);

        json = gson.toJson(response);
        LOG.debug("Response: " + json);

        return Response.status(Response.Status.OK).entity(response).build();
    }
}