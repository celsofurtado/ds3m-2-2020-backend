package br.senai.sp.jandira.gamesapp.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;

@Service
public class FirebaseStorageService {
	
	@PostConstruct
	private void init() throws IOException {
		if (FirebaseApp.getApps().isEmpty()) {
			
			// ** Ler o arquivo de configuração da conta
			InputStream serviceAccount = FirebaseStorageService
					.class
					.getResourceAsStream("/instagames-2-2020-firebase-adminsdk-iguvc-8bce40bac4.json");
			
			// ** Definir os dados necessários para acessar o Storage
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setStorageBucket("instagames-2-2020.appspot.com")
					.build();
			
			// ** Inicializar o FirebaseApp
			FirebaseApp.initializeApp(options);
			
		}
	}
	
	public String upload(FileUpload fileUpload) {
		
		// ** PARTE 01 - Criar o acesso ao bucket
		Bucket bucket = StorageClient.getInstance().bucket();
		
		// ** Parte 02 - Pegar o arquivo que está no formato Base64 e converter em bytes novamente
		byte[] bytes = Base64.getDecoder().decode(fileUpload.getBase64());
		
		// ** Parte 03 - Criar um arquivo com o nome e tipo fornecidos
		Blob blob = bucket.create(fileUpload.getFileName(), bytes, fileUpload.getMimeType());
		
		// ** Parte 04 - Tornar a URL de acesso público para quem tiver a URL
		blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
		
		// ** Parte 05 - Obter a URL da imagem gravada
		String imageUrl = String.format("https://storage.googleapis.com/%s/%s", 
				bucket.getName(), 
				fileUpload.getFileName());
		
		// ** Retornar a url da imagem no google Storage
		return imageUrl;
		
	}

}








