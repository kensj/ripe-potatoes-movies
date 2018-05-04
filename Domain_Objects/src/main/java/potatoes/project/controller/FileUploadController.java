package potatoes.project.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import potatoes.project.storage.UserIconStorageService;

@Controller
public class FileUploadController {

	private final UserIconStorageService storageService;
	
	@Autowired
    public FileUploadController(UserIconStorageService storageService) {
        this.storageService = storageService;
	}

	@GetMapping("/users/{id}/icon")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable int userID) {
		String filename = userID + ".png";
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
	
	@RequestMapping(value = "/users/{id}/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
	public ResponseEntity<?> handleFormUpload(@RequestPart("file") MultipartFile f) throws IOException {
		Map<String, String> response = new HashMap<String, String>();
		String extension = FilenameUtils.getExtension(f.getOriginalFilename());
		
		InputStream is =  new BufferedInputStream(f.getInputStream());
		BufferedImage image = ImageIO.read(is);
		is.close();
		// Convert multipartfile to image
		
		if(image == null) {
			response.put("success", "false");
			response.put("reason", "notimage");
		}		
		else {
			
			Image scaled = image.getScaledInstance(512, 512, Image.SCALE_SMOOTH);
			BufferedImage bufferedScale = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = bufferedScale.createGraphics();
		    g2d.drawImage(scaled, 0, 0, null);
		    g2d.dispose();
		    // Convert to 512x512

			storageService.store(bufferedScale,f.getOriginalFilename().replace(extension, "png"));
			response.put("success", "true");
		}
		
		return ResponseEntity.ok(response);
    }
	
}