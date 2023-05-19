package org.kcj.messenger.resources;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.kcj.messenger.model.Message;
import org.kcj.messenger.resources.beans.MessageFilterBean;
import org.kcj.messenger.service.MessageService;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
        if (filterBean.getYear()  > 0) {
            return messageService.getAllMessagesForYear(filterBean.getYear());
        }
        
        if (filterBean.getStart() >= 0 && filterBean.getSize() >= 0) {
            return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
        }
        
        return messageService.getAllMessages();
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        System.out.println(uriInfo.getAbsolutePath());
        Message newMessage = messageService.addMessage(message);
        String newId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri)
                .entity(newMessage)
                .build();
        //return messageService.addMessage(message);
    }

    @PUT
    @Path("{messageId}")
    public Message updateMessage(@PathParam("messageId") long id, Message message) {
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("{messageId}")
    public void deleteMessage(@PathParam("messageId") long id) {
        messageService.removeMessage(id);
    }

    @GET
    @Path("{messageId}")
    public Message test(@PathParam("messageId") long id) {
        return messageService.getMessage(id);
    }
    
//    @Path("{messageId}/comments")
//    public CommentResource getCommentResource() {
//        return new CommentResource();
//    }
}
