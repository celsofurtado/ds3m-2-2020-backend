package br.senai.sp.jandira.gamesapp.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.jandira.gamesapp.upload.FileUpload;
import br.senai.sp.jandira.gamesapp.upload.FileUploadURL;
import br.senai.sp.jandira.gamesapp.upload.FirebaseStorageService;

@RestController
@RequestMapping("/api/fileupload")
public class FileUploadResource {
	
	@Autowired
	private FirebaseStorageService firebase;
	
	@PostMapping
	public ResponseEntity<FileUploadURL> saveFile(@RequestBody FileUpload file) {
		FileUploadURL url = new FileUploadURL(firebase.upload(file));
		return ResponseEntity.ok(url);
	}

}
