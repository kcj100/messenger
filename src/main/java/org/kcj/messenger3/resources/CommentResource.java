package org.kcj.messenger3.resources;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import java.util.List;
import org.kcj.messenger3.model.Comment;
import org.kcj.messenger3.model.Message;
import org.kcj.messenger3.resources.beans.MessageFilterBean;
import org.kcj.messenger3.service.CommentService;
import org.kcj.messenger3.service.MessageService;

@Path("/")
public class CommentResource {
    
    private CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
        return commentService.getAllComments(messageId);
    }

    @POST
    public Comment addMessage(@PathParam("messageId")long messageId, Comment comment) {
        return commentService.addComment(messageId, comment);
    }

    @PUT
    @Path("{commentId}")
    public Comment updateMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment) {
        comment.setId(commentId);
        return commentService.updateComment(messageId, comment);
    }

    @DELETE
    @Path("{commentId}")
    public void deleteComment(@PathParam("commentId") long messageId, @PathParam("commentId") long commentId) {
        commentService.removeComment(messageId, commentId);
    }

    @GET
    @Path("{commentId}")
    public Comment getMessage(@PathParam("message") long messageId, long commentId) {
        return commentService.getComment(messageId, commentId);
    }
}
