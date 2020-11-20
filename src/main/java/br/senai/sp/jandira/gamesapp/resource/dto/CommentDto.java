package br.senai.sp.jandira.gamesapp.resource.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.senai.sp.jandira.gamesapp.model.Comment;

public class CommentDto {

	private Long commentId;
	private String commentText;
	private LocalDateTime commentDateTime;
	private String userCommentName;
	private String userPostName;
	private LocalDateTime postDate;
	private String postText;

	public CommentDto(Comment comment) {
		this.commentId = comment.getCommentId();
		this.commentText = comment.getComment();
		this.commentDateTime = comment.getCommentDate();
		this.userCommentName = comment.getUser().getName();
		this.userPostName = comment.getGamePost().getUser().getName();
		this.postDate = comment.getGamePost().getPostDate();
		this.postText = comment.getGamePost().getPostText();
	}

	public Long getCommentId() {
		return commentId;
	}

	public String getCommentText() {
		return commentText;
	}

	public LocalDateTime getCommentDateTime() {
		return commentDateTime;
	}

	public String getUserCommentName() {
		return userCommentName;
	}

	public String getUserPostName() {
		return userPostName;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}

	public String getPostText() {
		return postText;
	}
	
	public static List<CommentDto> convertToDto(List<Comment> comments) {
		return comments.stream().map(CommentDto::new).collect(Collectors.toList());
	}

}
