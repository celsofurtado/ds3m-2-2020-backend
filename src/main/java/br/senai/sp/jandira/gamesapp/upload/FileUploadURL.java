package br.senai.sp.jandira.gamesapp.upload;

public class FileUploadURL {

	private String imageUrl;

	public FileUploadURL(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "FileUploadURL [imageUrl=" + imageUrl + "]";
	}

}
