package br.senai.sp.jandira.gamesapp.upload;

public class FileUpload {

	private String fileName;
	private String base64;
	private String mimeType = "image/png";

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	@Override
	public String toString() {
		return "FileUpload [fileName=" + fileName + ", base64=" + base64 + ", mimeType=" + mimeType + "]";
	}

}
