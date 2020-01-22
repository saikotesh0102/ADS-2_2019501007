public class SeamCarver {

	private Picture picture;
	// create a seam carver object based on the given picture
	public SeamCarver(Picture picture) {
		if(picture == null) { 
			throw new IllegalArgumentException();
		}
		this.picture = new Picture(picture);
	}
 
	// current picture
	public Picture picture() {
		return new Picture(this.picture);
	}
 
	// width of current picture
	public int width() {
		return this.picture.width();
	}
 
	// height of current picture
	public int height() {
		return this.picture.width();
	}
 
	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x < 0 || y < 0 || x > width() - 1 || y > height() - 1) {
            throw new IllegalArgumentException();
        }

        if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) {
            return 1000;
        }

        Color top    = this.picture.get(x, y + 1);
        Color bottom = this.picture.get(x, y - 1);
        Color left   = this.picture.get(x - 1, y);
        Color right  = this.picture.get(x + 1, y);

        return Math.sqrt(squareGradient(top, bottom) + squareGradient(left, right));
	}
 
	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return null;
	}
 
	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return null;
	}
 
	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {


	}
 
	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
 
	//  unit testing (optional)
	public static void main(String[] args) {

	}
 
 }