package com.rest.showroom;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.entities.BrandEntity;

@Path("/showroom")
public class showroom {

	BrandServices services = new BrandServices();
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
	@Path("/getBrands")
	public String print() {
		List<BrandEntity> brands = services.getBrands();
		return "GET method ";
	}
	
	@POST
    @Produces(MediaType.TEXT_PLAIN)
	@Path("/setBrands")
	public String setBrands(BrandEntity brands) {
		services.addBrands();
		return "POST method";
	}
	
	@PUT
    @Produces(MediaType.TEXT_PLAIN)
	@Path("/putBrands")
	public String putBrands(BrandEntity brands) {
		services.updateBrands();
		return "PUT method";
	}
	
	@DELETE
    @Produces(MediaType.TEXT_PLAIN)
	@Path("/deletBrands")
	public String deletBrands() {
		return "DEL method";
	}
	
}


