// import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class SeamCarver {

	private static final boolean HORIZONTAL = true;
    private static final boolean VERTICAL   = false;

	private Picture picture;
	private double[] distTo;
	private int[][] edgeTo;
	
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
		return this.picture.height();
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
		// System.out.println(Math.sqrt(squareGradient(top, bottom) + squareGradient(left, right)));
        return Math.sqrt(squareGradient(top, bottom) + squareGradient(left, right));
	}

	private double squareGradient(Color first, Color second) {
		// System.out.println(Math.pow(first.getRed() - second.getRed(), 2) + Math.pow(first.getGreen() - second.getGreen(), 2) + Math.pow(first.getBlue()  - second.getBlue(), 2));
		return Math.pow(first.getRed() - second.getRed(), 2) + Math.pow(first.getGreen() - second.getGreen(), 2) + Math.pow(first.getBlue()  - second.getBlue(), 2);
    }
 
	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return seam(HORIZONTAL);
	}
 
	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return seam(VERTICAL);
	}

	private int[] seam(boolean direction) {
		this.distTo = (direction == VERTICAL) ? new double[this.width()] : new double[this.height()];
		// if(direction == VERTICAL) {
		// 	this.distTo = new double[this.width()];
		// } else {
		// 	this.distTo = new double[this.height()];
		// }
		this.edgeTo = new int[this.width()][this.height()];

		for (int i = 0; i < this.distTo.length; i++) {
			this.distTo[i] = 1000;
		}

		int maxI = (direction == VERTICAL) ? this.height() : this.width();
		int maxJ = (direction == VERTICAL) ? this.width() : this.height();
		
		// if(direction == VERTICAL) {
		// 	int maxI = this.height();
		// }else {
		// 	int maxI = this.width();
		// }

		// if(direction == VERTICAL) {
		// 	int maxJ = this.width();
		// }else {
		// 	int maxj = this.height();
		// }

		for (int i = 1; i < maxI; i++) {
            double[] lastDistTo = this.distTo.clone();
            for (int k = 0; k < this.distTo.length; k++) {
                this.distTo[k] = Double.POSITIVE_INFINITY;
            }

            for (int j = 1; j < maxJ; j++) {
				int x = (direction == VERTICAL) ? j : i;
				// if(direction == VERTICAL) {
				// 	int x = j;
				// } else {
				// 	int x = i;
				// }
				int y = (direction == VERTICAL) ? i : j;
				// if(direction == VERTICAL) {
				// 	int y = i;
				// } else {
				// 	int y = j;
				// }

                double energy = energy(x, y);

                relax(j - 1, x, y, energy, lastDistTo, direction);
                relax(j, x, y, energy, lastDistTo, direction);
                relax(j + 1, x, y, energy, lastDistTo, direction);
            }
		}
		
		double minWeight = Double.POSITIVE_INFINITY;
        int min = 0;
        
        for (int i = 0; i < this.distTo.length; i++) {
            double weight = this.distTo[i];
            if (weight < minWeight) {
                min = i;
                minWeight = weight;
            }
		}
		
		int[] seam = (direction == VERTICAL) ? new int[this.height()] : new int[this.width()];

		// if(direction == VERTICAL) {
		// 	int[] seam = new int[this.height()];
		// } else {
		// 	int[] seam = new int[this.width()];
		// }

        if (direction == VERTICAL) {
            for (int y = this.height() - 1; y >= 0; y--) {
                seam[y] = min;
                min = edgeTo[min][y];
            }
        } else {
            for (int x = this.width() - 1; x >= 0; x--) {
                seam[x] = min;
                min = edgeTo[x][min];
            }
        }
        return seam;
	}

	private void relax(int prev, int x, int y, double energy, double[] lastDistTo, boolean direction) {
        if (prev < 0 || prev >= lastDistTo.length) {
            return;
        }

        double weight = lastDistTo[prev];

		int index = (direction == VERTICAL) ? x : y;
		// if(direction == VERTICAL) {
		// 	int index = x;
		// } else {
		// 	int index = y;
		// }
        if (this.distTo[index] > weight + energy) {
            this.distTo[index] = weight + energy;
            this.edgeTo[x][y] = prev;
        }
    }

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {
		if (seam == null || this.width() <= 1 || seam.length != this.height()) {
            throw new IllegalArgumentException();
		}
		
		Picture newPicture = new Picture(this.width() - 1, this.height());

		int prevSeam = seam[0];

		for (int x = 0; x < this.width(); x++) {
            if (Math.abs(seam[x] - prevSeam) > 1 || seam[x] < 0 || seam[x] >= this.height()) {
                throw new IllegalArgumentException();
            }
            prevSeam = seam[x];

            for (int y = 0; y < this.height(); y++) {
                if (seam[x] == y) {
					continue;
				}

                Color color = this.picture.get(x, y);
                newPicture.set(x, seam[x] > y ? y : y - 1, color);
            }
        }
        this.picture = newPicture;
	}
 
	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {
		if (seam == null || this.width() <= 1 || seam.length != this.height()) {
            throw new IllegalArgumentException();
        }

        Picture newPicture = new Picture(this.width() - 1, this.height());

        int prevSeam = seam[0];

        for (int y = 0; y < this.height(); y++) {
            if (Math.abs(seam[y] - prevSeam) > 1 || seam[y] < 0 || seam[y] >= this.width()) {
                throw new IllegalArgumentException();
            }
            prevSeam = seam[y];

            for (int x = 0; x < this.width(); x++) {
                if (seam[y] == x) {
					continue;
				}

                Color color = this.picture.get(x, y);
                newPicture.set(seam[y] > x ? x : x - 1, y, color);
            }
        }
        this.picture = newPicture;
	}
 
	//  unit testing (optional)
	public static void main(String[] args) {
	// 	Picture picture = new Picture(args[0]);
    //     System.out.printf("image is %d pixels wide by %d pixels high.\n", picture.width(), picture.height());
        
    //     SeamCarver sc = new SeamCarver(picture);
        
	// 	System.out.printf("Printing energy calculated for each pixel.\n");        

    //     for (int row = 0; row < sc.height(); row++) {
	// 		for (int col = 0; col < sc.width(); col++) {
	// 			System.out.printf("%9.0f ", sc.energy(col, row));
	// 		}
	// 		System.out.println();
    //     }
	// }
	}
}