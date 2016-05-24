import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ImageScramble{
	public static void main(String args[]){
		
		//Program requires command line peramaters to run
		//First peramater is the image name 'example.jpg'
		//Next is the operation to be performed: -u -s
		//-u is to unscramble an image
		//-s is to scramble an image

		//Current version loses some data and color
		//Need to find a better way to get the pixel data

		String image;

		if(args.length < 2){
			System.out.println("Too few perameters: imagename.extension -u/-s");
		}
		else{
			image = args[0];
			if(args[1].equals("-u")){
				try{
					unscramble(image);
				} catch(IOException e){
					System.out.println("Image does not exist or format is not excepted");
				}
			}
			else if(args[1].equals("-s")){
				try{
					scramble(image);
				} catch(IOException e){
					System.out.println("Image does not exist or format is not excepted");
				}
			}
			else{
				System.out.println("Incorect perameters: imagename.extension -u/-s");
			}
		}
	}

	public static void scramble(String image) throws IOException{
		BufferedImage img = null;
    	img = ImageIO.read(new File(image));

		int x = img.getWidth();
		int y = img.getHeight();

		for(int i = 0; i < y; i++){
			for(int j = 0; j < (x / 2); j++){
				if(j % 2 == 0){
					int leftPixel = img.getRGB(j, i);
					int rightPixel = img.getRGB((x - j) - 1, i);

					img.setRGB(j, i, rightPixel);
					img.setRGB((x - j) - 1, i, leftPixel);
				}
			}
		}

		for(int i = 0; i < x; i++){
			for(int j = 0; j < (y / 2); j++){
				if(j % 2 == 0){
					int topPixel = img.getRGB(i, j);
					int bottomPixel = img.getRGB(i, (y - j) - 1);

					img.setRGB(i, j, bottomPixel);
					img.setRGB(i, ((y - j) - 1), topPixel);
				}
			}
		}	

		File outputfile = new File("scramble.jpg");
		ImageIO.write(img, "jpg", outputfile);
	}

	public static void unscramble(String image) throws IOException{
		BufferedImage img = null;
    	img = ImageIO.read(new File(image));

    	int x = img.getWidth();
		int y = img.getHeight();

		for(int i = 0; i < x; i++){
			for(int j = 0; j < (y / 2); j++){
				if(j % 2 == 0){
					int topPixel = img.getRGB(i, j);
					int bottomPixel = img.getRGB(i, (y - j) - 1);

					img.setRGB(i, j, bottomPixel);
					img.setRGB(i, ((y - j) - 1), topPixel);
				}
			}
		}

		for(int i = 0; i < y; i++){
			for(int j = 0; j < (x / 2); j++){
				if(j % 2 == 0){
					int leftPixel = img.getRGB(j, i);
					int rightPixel = img.getRGB((x - j) - 1, i);

					img.setRGB(j, i, rightPixel);
					img.setRGB((x - j) - 1, i, leftPixel);
				}
			}
		} 	

		File outputfile = new File("unscramble.jpg");
		ImageIO.write(img, "jpg", outputfile);
	}
}
