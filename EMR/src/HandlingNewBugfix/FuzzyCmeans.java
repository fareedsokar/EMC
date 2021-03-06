package HandlingNewBugfix;
import java.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FuzzyCmeans {

	
	private final static int MAX_DATA_POINTS=10000;
	final static int MAX_CLUSTER=100;
	private final static int MAX_DATA_DIMENSION=5;
	private int num_data_points;
	private int num_clusters;
	private int num_dimensions;
	private double low_high[][]=new double[MAX_DATA_POINTS][2];
	private double degree_of_memb[][]=new double[MAX_DATA_POINTS][MAX_CLUSTER];
	private double epsilon;
	private double fuzziness;
	private double data_point[][]=new double [MAX_DATA_POINTS][MAX_DATA_DIMENSION];
	private  double cluster_centre[][] =new double [MAX_CLUSTER][MAX_DATA_DIMENSION];
	
	
	
	//Getters & setters for privates var.
	public int getNum_data_points() {
		return num_data_points;
	}
	public void setNum_data_points(int num_data_points) {
		this.num_data_points = num_data_points;
	}
	public int getNum_clusters() {
		return num_clusters;
	}
	public void setNum_clusters(int num_clusters) {
		this.num_clusters = num_clusters;
	}
	public int getNum_dimensions() {
		return num_dimensions;
	}
	public void setNum_dimensions(int num_dimensions) {
		this.num_dimensions = num_dimensions;
	}
	public double[][] getLow_high() {
		return low_high;
	}
	public void setLow_high(double[][] low_high) {
		this.low_high = low_high;
	}
	public double[][] getDegree_of_memb() {
		return degree_of_memb;
	}
	public void setDegree_of_memb(double[][] degree_of_memb) {
		this.degree_of_memb = degree_of_memb;
	}
	public double getEpsilon() {
		return epsilon;
	}
	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}
	public double getFuzziness() {
		return fuzziness;
	}
	public void setFuzziness(double fuzziness) {
		this.fuzziness = fuzziness;
	}
	public double[][] getData_point() {
		return data_point;
	}
	public void setData_point(double[][] data_point) {
		this.data_point = data_point;
	}
	public double[][] getCluster_centre() {
		return cluster_centre;
	}
	public void setCluster_centre(double[][] cluster_centre) {
		this.cluster_centre = cluster_centre;
	}
	public static int getMaxDataPoints() {
		return MAX_DATA_POINTS;
	}
	public static int getMaxCluster() {
		return MAX_CLUSTER;
	}
	public static int getMaxDataDimension() {
		return MAX_DATA_DIMENSION;
	}
	
	//Fuzzy Constructor
	public FuzzyCmeans(String fname)
	{
		int i, j, r, rval;
	    BufferedReader reader = null;
	    String line;
	    double s;
	    try 
	    {
	    	reader = new BufferedReader(new FileReader(fname));
	    	 while((line = reader.readLine()) != null)
	         {
	 	    	Pattern p = Pattern.compile("-?\\d+");
	 	    	Matcher m = p.matcher(line);
	 	    	//Get Data Point
	 	    		setNum_data_points(Integer.parseInt(m.group()));
	 	    	//Get Number of Clusters
	 	    		setNum_clusters(Integer.parseInt(m.group()));
	 	    	//Get Number of Dimensions
	 	    		setNum_dimensions(Integer.parseInt(m.group()));
	 	    	//Get Fuzziness Level
	 	    		setFuzziness(Double.parseDouble(m.group()));
	 	    	//Get Epsilon
	 	    		setEpsilon(Double.parseDouble(m.group()));
	 	        if (getNum_clusters() > MAX_CLUSTER) {
	 	        	System.out.println("Number of clusters should be < "+ MAX_CLUSTER);
	 	        }
	 	        if (getNum_data_points() > MAX_DATA_POINTS) {
	 	        	System.out.println("Number of data points should be < "+ MAX_DATA_POINTS);
	 	        }
	 	        if (getNum_dimensions() > MAX_DATA_DIMENSION) {
	 	        	System.out.println("Number of dimensions should be >= 1.0 and < "+
	 	        			MAX_DATA_DIMENSION);
	 	        }
	 	    
	 	        if (getFuzziness() <= 1.0) {
	 	        	System.out.println("Fuzziness coefficient should be > 1.0");
	 	        }
	 	    
	 	        if (getEpsilon() <= 0.0 || getEpsilon() > 1.0)
	 	        {
	 	        	System.out.println("Termination criterion should be > 0.0 and <= 1.0");
	 	        }  	    
	 	        for (i = 0; i < getNum_data_points(); i++) {
	 	        	for (j = 0; j < getNum_dimensions(); j++) {
	 	        		getData_point()[i][j]=Double.parseDouble(m.group());
	 	        		if (getData_point()[i][j] < getLow_high()[j][0])
	 	        			getLow_high()[j][0] = getData_point()[i][j];
	 	        		if (getData_point()[i][j] > getLow_high()[j][1])
	 	        			getLow_high()[j][1] = getData_point()[i][j];
	 	        	}
	 	        }
	 	        for (i = 0; i < getNum_data_points(); i++) {
	 	        	s = 0.0;
	 	        	r = 100;
	 	        	for (j = 1; j < getNum_clusters(); j++) {
	 	        		rval = ThreadLocalRandom.current().nextInt(0, r + 1);
	 	        		r -= rval;
	 	        		getDegree_of_memb()[i][j] = rval / 100.0;
	 	        		s += getDegree_of_memb()[i][j];
	 	        	}
	 	        	getDegree_of_memb()[i][0] = 1.0 - s;
	 	        }
	         
	         }
	    }catch( FileNotFoundException e)
	    {
	        System.out.println("Input file not found. Exiting.");
	    }
	    catch (IOException e)
	    {
	        System.out.println("Error creating output file. Exiting.");
	    }
	   
	}
	//calculate_centre_vectors
	public int calculate_centre_vectors()
	{
	    int i, j, k;
	    double numerator, denominator;
	    double t[][]=new double[getMaxDataPoints()][getMaxCluster()];
	    for (i = 0; i < getNum_data_points(); i++)
	    {
	        for (j = 0; j < getNum_clusters(); j++)
	        {
	            t[i][j] = Math.pow(getDegree_of_memb()[i][j], getFuzziness());
	        }
	    }
	    for (j = 0; j < getNum_clusters(); j++)
	    {
	        for (k = 0; k < getNum_dimensions(); k++)
	        {
	            numerator = 0.0;
	            denominator = 0.0;
	            for (i = 0; i < getNum_data_points(); i++) 
	            {
	                numerator += t[i][j] * getData_point()[i][k];
	                denominator += t[i][j];
	            }
	            getCluster_centre()[j][k] = numerator / denominator;
	        }
	    }
	    return 0;
	}
	//Get Norm , sum
	public double get_norm(int i, int j) 
	{
		 int k;
		    double sum = 0.0;
		    for (k = 0; k < getNum_dimensions(); k++) 
		    {
		        sum += Math.pow(getData_point()[i][k] - getCluster_centre()[j][k], 2);
		    }
		    return Math.sqrt(sum);
	}
	//Get New Value
	public double get_new_value(int i, int j)
	{
	    int k;
	    double t, p, sum;
	    sum = 0.0;
	    p = 2 / (getFuzziness() - 1);
	    for (k = 0; k < getNum_clusters(); k++) 
	    {
	        t = get_norm(i, j) / get_norm(i, k);
	        t = Math.pow(t, p);
	        sum += t;
	    }
	    return 1.0 / sum;
	}
	//Update the degree of the memberShip
	public double update_degree_of_membership()
	{
	    int i, j;
	    double new_uij;
	    double max_diff = 0.0, diff;
	    for (j = 0; j < getNum_clusters(); j++) 
	    {
	        for (i = 0; i < getNum_data_points(); i++) 
	        {
	            new_uij = get_new_value(i, j);
	            diff = new_uij - getDegree_of_memb()[i][j];
	            if (diff > max_diff)
	                max_diff = diff;
	            getDegree_of_memb()[i][j] = new_uij;
	        }
	    }
	    return max_diff;

	}
	//FCM
	public int fcm(String fname)
	{
	    double max_diff;
	    FuzzyCmeans fcm = new FuzzyCmeans(fname);
	    do {
	        fcm.calculate_centre_vectors();
	        max_diff = fcm.update_degree_of_membership();
	    } while (max_diff > fcm.getEpsilon());
	    return 0;
	}

	////
	/*public int gnuplot_membership_matrix()
	{
	    int i, j, cluster;
	    double highest;
	    String fname;
	    BufferedReader f[] = new BufferedReader[getMaxCluster()]; //FILE * f[MAX_CLUSTER];
	    
	    if (getNum_dimensions() != 2) 
	    {
	        System.out.println("Plotting the cluster only works when then");
	        System.out.println("number of dimensions is two. This will create");
	        System.out.println("a two-dimensional plot of the cluster points.");
	     //   exit(1);
	    }
	    
	    for (j = 0; j < getNum_clusters(); j++)
	    {
	        fname= "cluster."+ j;
	        if ((f[j] = fopen(fname, "w")) == null)
	        {
	            System.out.println("Could not create"+fname);
	            for (i = 0; i < j; i++)
	            {
	               // fclose(f[i]);
	                sprintf(fname, "cluster.%d", i);
	           //     remove(fname);
	            }
	            return -1;
	        }
	        fprintf(f[j], "#Data points for cluster: %d\n", j);
	    }
	    
	    for (i = 0; i < getNum_data_points(); i++)
	    {
	        cluster = 0;
	        highest = 0.0;
	        for (j = 0; j < getNum_clusters(); j++)
	        {
	            if (getDegree_of_memb()[i][j] > highest)
	            {
	                highest = getDegree_of_memb()[i][j];
	                cluster = j;
	            }
	        }
	        
	        fprintf(f[cluster], "%lf %lf\n", getData_point()[i][0], getData_point()[i][1]);
	    }
	    
	    for (j = 0; j < getNum_clusters(); j++) 
	    {
	        fclose(f[j]);
	    }
	    if ((f[0] = fopen("gnuplot.script", "w")) == null) {
	        System.out.println("Could not create gnuplot.script.");
	        
	        for (i = 0; i < j; i++)
	        {
	          //  fclose(f[i]);
	            sprintf(fname, "cluster.%d", i);
	        //    remove(fname);
	        }
	        return -1;
	    }
	    
	    //Write Buffer 
	    fprintf(f[0], "set terminal png medium\n");
	    fprintf(f[0], "set output \"cluster_plot.png\"\n");
	    fprintf(f[0], "set title \"FCM clustering\"\n");
	    fprintf(f[0], "set xlabel \"x-coordinate\"\n");
	    fprintf(f[0], "set ylabel \"y-coordinate\"\n");
	    fprintf(f[0], "set xrange [%lf : %lf]\n", low_high[0][0], low_high[0][1]);
	    fprintf(f[0], "set yrange [%lf : %lf]\n", low_high[1][0], low_high[1][1]);
	    fprintf(f[0],
	            "plot 'cluster.0' using 1:2 with points pt 7 ps 1 lc 1 notitle");
	    for (j = 1; j < num_clusters; j++) {
	        sprintf(fname, "cluster.%d", j);
	        fprintf(f[0],
	                ",\\\n'%s' using 1:2 with points  pt 7 ps 1 lc %d notitle",
	                fname, j + 1);
	    }
	    fprintf(f[0], "\n");
	  
	    return 0;
	}*/
	
}


