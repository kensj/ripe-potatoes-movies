package potatoes.project.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import potatoes.project.domain_objects.User;
import potatoes.project.storage.UserIconStorageService;

@Controller
public class FileUploadController {
	
	@Autowired
    private HttpSession session;

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
	
	@RequestMapping(value = "/uploadicon", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String handleFormUpload(@RequestPart("file") MultipartFile f, RedirectAttributes redirectAttributes) throws IOException {
		//Map<String, String> response = new HashMap<String, String>();
		User u = (User) session.getAttribute("user");
		
		InputStream is =  new BufferedInputStream(f.getInputStream());
		BufferedImage image = ImageIO.read(is);
		is.close();
		
		//if(image == null) {
			//response.put("success", "false");
			//response.put("reason", "notimage");
		//}		
		//else {
		if(image != null) {
			Image scaled = image.getScaledInstance(512, 512, Image.SCALE_SMOOTH);
			BufferedImage bufferedScale = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = bufferedScale.createGraphics();
		    g2d.drawImage(scaled, 0, 0, null);
		    g2d.dispose();

			storageService.store(bufferedScale,Integer.toString(u.getUserID())+".png");
			//response.put("success", "true");
		}
		return "redirect:/users/"+u.getUserID();
		//return ResponseEntity.ok(response);
    }
	
	@RequestMapping(value = "/deleteicon", method = RequestMethod.POST)
	public ResponseEntity<?> iconDelete() throws IOException {
		Map<String, String> response = new HashMap<String, String>();
		User u = (User) session.getAttribute("user");
		storageService.delete(Integer.toString(u.getUserID())+".png");
		response.put("success", "true");
		return ResponseEntity.ok(response);
    }

}