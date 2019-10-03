package Main;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.imageio.ImageIO;

import Interface.Image_color;
import Interface.Image_imple;
import Model.Image;

public class Run {
	Image image = new Image();
	String path;

	public static void main(String[] args) {
		new Run().Getimage();
	}

	private void Getimage() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Image full path\n");// get file path
		path = (scanner.next());
		System.out.println("Enter  background image name\n");// get image name
		String oldimg = (scanner.next());
		scanner.close();
		image.setOldimg(new Run().checkFile(oldimg));
		
		if (checkPath(path)) {// check if path is valid

			if ((checkType(image.getPath().concat(image.getOldimg()))) == true) {// check if full image path is valid
				
				Image_color ic = new Image_imple(); // call
				ic.Check_image(image);// call function
			} else {
				System.out.println("no file name " + image.getPath().concat(image.getOldimg()));// return
																												// error
																											// for
																												// file
			}
		} else {
			System.out.println("no file path " + image.getPath()); // return error for path
		}

	}

//check file if it contain extension or not
	private String checkFile(String img) {
		if (!img.contains(".jpg")) {
			img = img.concat(".jpg");
		}
		if (!img.contains("\\")) {
			img = "\\" + img;
		}
		return img;
	}
	// check if file is valid or not

	private boolean checkType(String name) {

		File f = new File(name);
		try {
			return ImageIO.read(f) != null;
		} catch (Exception e) {
			return false;
		}
	}

	// check valid path
	private boolean checkPath(String paths) {
		try {

			paths = paths.replace(image.getOldimg(), "\\");
			Path p = Paths.get(paths);
			image.setPath(paths);

			return Files.exists(p);

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}
}