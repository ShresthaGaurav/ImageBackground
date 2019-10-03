package Interface;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import Model.Image;

public class Image_imple implements Image_color {

	@Override
	public void Check_image(Image i) {
	
		
		File input1 = new File((i.getPath().concat(i.getOldimg())));
		try {
			//File input2 = new File(i.getPath().concat(i.getNewimg()));
			//BufferedImage image = ImageIO.read(input1);
			BufferedImage image=new Hiwi().resize(ImageIO.read(input1), 500, 500);
			InputStream in = getClass().getResourceAsStream("/images/1.jpg");
			BufferedImage overlay = new Hiwi().resize(ImageIO.read(in), 500, 500);
		
			BufferedImage tran=makeImageTranslucent(overlay, 0.3);

			// create the new image, canvas size is the max. of both image sizes
			int w = Math.max(overlay.getWidth(), overlay.getWidth());
			int h = Math.max(overlay.getHeight(), overlay.getHeight());
			BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

			// paint both images, preserving the alpha channels
			Graphics g = combined.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.drawImage(tran, 0, 0, null);
			// Save as new image
			ImageIO.write(combined, "png", new File(i.getPath(), "Combined.png"));
			System.out.println("new image is created "+i.getPath()+"Combined.png");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	
		
	}
	public static BufferedImage makeImageTranslucent(BufferedImage source,
		      double alpha) {
		    BufferedImage target = new BufferedImage(source.getWidth(),
		        source.getHeight(), java.awt.Transparency.TRANSLUCENT);
		    // Get the images graphics
		    Graphics2D g = target.createGraphics();
		    // Set the Graphics composite to Alpha
		    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) alpha));
		    // Draw the image into the prepared reciver image
		    g.drawImage(source, null, 0, 0);
		    // let go of all system resources in this Graphics
		    g.dispose();
		    // Return the image
		    return target;
		  }

}
