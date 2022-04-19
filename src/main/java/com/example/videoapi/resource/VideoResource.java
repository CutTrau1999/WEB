package com.example.videoapi.resource;

import com.example.videoapi.entity.Video;
import com.example.videoapi.model.VideoModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/videos")

public class VideoResource {
    private VideoModel videoModel;

    public VideoResource() {
        this.videoModel = new VideoModel();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            return Response.status(Response.Status.OK).entity(videoModel.findAll()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.OK).entity(new ArrayList<>()).build();
        }
    }

    @GET()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") int id) {
        try {
            return Response.status(Response.Status.OK).entity(videoModel.findById(id)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Video employee) {
        try {
            Video savedEmployee = videoModel.save(employee);
            return Response.status(Response.Status.CREATED).entity(savedEmployee).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

//    @PUT()
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response update(@PathParam("id") int id, Video video) {
//        try {
//            Video foundEmployee = videoModel.findById(id);
//            if (foundEmployee == null) {
//                return Response.status(Response.Status.BAD_REQUEST).build();
//            }
//            Video updatedEmployee = employeeModel.update(id, employee);
//            return Response.status(Response.Status.OK).entity(updatedEmployee).build();
//        } catch (SQLException e) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
//    }

    @DELETE()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        try {
            Video foundVideo = videoModel.findById(id);
            if (foundVideo == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            boolean isDeleted = videoModel.delete(id);
            if (isDeleted) {
                return Response.status(Response.Status.OK).entity(isDeleted).build();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
