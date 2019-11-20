package meme.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import meme.models.Picture;
import meme.repository.PictureImpl;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class IndexController {



	 

	
	@Autowired
	PictureImpl pictures;

//	@RequestMapping(method = RequestMethod.GET)
//	public String index(Model model) {
//
//		model.addAttribute("newImage", new Picture());
//
//		return "index";
//	}

	@RequestMapping(method = RequestMethod.POST)
	public void createMeme(@RequestBody Picture newPicture, HttpServletRequest request) throws IOException {
		
		pictures.save(newPicture);
		
		System.out.println("gavau post requesta");
		System.out.println(newPicture.getText());
		System.out.println(newPicture.getImage());
		
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		
		File input = new File(rootDirectory + "images\\1.jpg");
		System.out.println(rootDirectory + "images\\" + newPicture.getId() + ".jpg");
		BufferedImage image = ImageIO.read(input);

		Graphics g = image.getGraphics();
		  g.setFont(new Font("Arial Black", Font.BOLD, 50));
//		g.setFont(g.getFont().deriveFont(30f));
		g.drawString(newPicture.getText().toUpperCase(), 100, 170);
		g.dispose();

		ImageIO.write(image, "jpg", new File(rootDirectory + "memeimg\\" + newPicture.getId() + ".jpg"));
		newPicture.setUrl("http://localhost:8080/memeimg/" + newPicture.getId() + ".jpg");
		pictures.save(newPicture);
		// Pasiimame nuotrauką iš modelio objekto
//		MultipartFile image = newPicture.getImage();
	

//		System.out.println(image == null);
//		 Priskiriame šakninį servleto katalogą kaip kelią
//		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
//		 Jeigu nuotrauka buvo sėkmingai gauta iš modelio

//		if (image != null && !image.isEmpty()) {
//			try {
//
//				image.transferTo(new File(rootDirectory + "images\\" + newPicture.getId() + ".jpg"));
//			} catch (Exception e) {
//				throw new RuntimeException("Image saving failed", e);
//			}
//		}
}

	@RequestMapping("/addtext")
	public String getProductById(@RequestParam("id") int id, Model model, HttpServletRequest request) {

		Picture picture = pictures.findById(id).get();
		model.addAttribute("newImage", picture);
		return "addtext";

	}
	
	@RequestMapping()
	public List<Picture> allMemes(HttpServletRequest request) {

		List<Picture> allMemes = new ArrayList<>();
		allMemes = pictures.findAll();
		return allMemes;

	}

	@RequestMapping(value = "/addtext", method = RequestMethod.POST)
	public String addComment(@RequestParam("id") int id, @ModelAttribute("newImage") Picture picture, Model model,
			HttpServletRequest request) throws MalformedURLException, IOException {
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		File input = new File(rootDirectory + "images\\1.jpg");
		System.out.println(rootDirectory + "images\\" + id + ".jpg");
		BufferedImage image = ImageIO.read(input);

		Graphics g = image.getGraphics();
		g.setFont(g.getFont().deriveFont(30f));
		g.drawString(picture.getText(), 100, 100);
		g.dispose();

		ImageIO.write(image, "jpg", new File(rootDirectory + "memeimg\\" + id + ".jpg"));

		return "redirect:meme?id=" + id;
	}

	@RequestMapping("/meme")
	public String getMemeById(@RequestParam("id") int id, Model model, HttpServletRequest request) {

		Picture picture = pictures.findById(id).get();
		model.addAttribute("newImage", picture);
		return "meme";

	}


}
