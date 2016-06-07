package grades;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GUIgrade extends AbstractTableModel 
{
        @SuppressWarnings("rawtypes")
		Vector data;
        @SuppressWarnings("rawtypes")
		Vector columns;
        public static class Mathdoes
    	{
    		public String Name;
    	    public int HW1,HW2,HW3,Midterm,Project,Final;
    	    public Mathdoes(String Name, int HW1, int HW2,int HW3,int Midterm,int Project,int Final)
    	    {
    	        this.Name = Name;
    	        this.HW1 = HW1;
    	        this.HW2 = HW2;
    	        this.HW3 = HW3;
    	        this.Midterm = Midterm;
    	        this.Project = Project;
    	        this.Final = Final;
    	    }
    	    public double getTotal()
    	    {
    	    	int firstd = (HW1+HW2+HW3)/3;
    	    	int firstc= (int) (0.45 * firstd);
    	    	int secondc= (Midterm+Final)/2;
    	        return (firstc + 0.25*Project + 0.30* secondc);
    	    }
    	    public String toString()
    	    {
    	        return "Name: " + Name + "\t" + "HW1: " + HW1 + "\t"
    	                    + "HW2: " + HW2 +"\t"+ "HW3: " + HW3 +"\t"+ 
    	        		"Midterm: " + Midterm +"\t"+ "Project: " + Project
    	                    +"\t" + "Final: " + Final;
    	    }
    	}
        
	public static void Fileparser() throws Exception
	    {
	        List<Mathdoes> gradinglist = new ArrayList<>();
	        FileWriter fw = new FileWriter("grades.txt");
	        int aCount = 0;
            int bCount = 0;
            int cCount = 0;
            int dCount = 0;
            int fCount = 0;
	        try
	        {
	            BufferedReader br = new BufferedReader(new FileReader("student_grades_input.txt"));
	            for(int i=0;i<1; ++i)
	            {
	            	br.readLine();
	            String fileRead = br.readLine();
	            while (fileRead != null)
	            {
	                String[] token = fileRead.split(",");
	                String tempName = token[0];
	                int tempHW1 = Integer.parseInt(token[1]);
	                System.out.println(tempHW1);
	                int tempHW2 = Integer.parseInt(token[2]);
	                int tempHW3 = Integer.parseInt(token[3]);
	                int tempMidterm = Integer.parseInt(token[4]);
	                int tempProject = Integer.parseInt(token[5]);
	                int tempFinal = Integer.parseInt(token[6]);
	                Mathdoes tempObj = new Mathdoes(tempName, tempHW1, tempHW2, tempHW3,tempMidterm,tempProject,tempFinal);	                
	                gradinglist.add(tempObj);
	                fileRead = br.readLine();
	            }
	            br.close();
	        }
	        }
	        catch (FileNotFoundException fnfe)
	        {
	            System.out.println("file not found");
	        }
	        catch (IOException ioe)
	        {
	            ioe.printStackTrace();
	        }
	        fw.write("Name,Final Letter Grade"+System.getProperty("line.separator"));
	        for (Mathdoes each : gradinglist)
	        {
	        	System.out.println();
	            each.getTotal();
	            if(each.getTotal()>90)
	            {
	            	fw.write(each.Name+","+"A"+System.getProperty("line.separator"));
	            	aCount++;
	            }
	            else if(each.getTotal()>80 && each.getTotal()<90)
	            {	  
	            	fw.write(each.Name+","+"B"+System.getProperty("line.separator"));
	            	bCount++;
	            }
	            else if(each.getTotal()>70 && each.getTotal()<80)
	            {
	            	fw.write(each.Name+","+"C"+System.getProperty("line.separator"));
	            	cCount++;
	            }
	            else if(each.getTotal()>60 && each.getTotal()<70)
	            {
	            	fw.write(each.Name+","+"D"+System.getProperty("line.separator"));
	            	dCount++;
	            }
	            else if(each.getTotal()>0 && each.getTotal()<60)
	            {
	            	fw.write(each.Name+","+"F"+System.getProperty("line.separator"));
	            	fCount++;
	            }
	            
	        }
	        fw.write(System.getProperty("line.separator"));
	        fw.write("Total number of each GRADE,"+"COUNT"+ System.getProperty("line.separator"));
	        fw.write(System.getProperty("line.separator"));
	        fw.write("A," +aCount +System.getProperty("line.separator"));
	        fw.write("B," +bCount +System.getProperty("line.separator"));
	        fw.write("C," +cCount +System.getProperty("line.separator"));
	        fw.write("D," +dCount +System.getProperty("line.separator"));
	        fw.write("F," +fCount +System.getProperty("line.separator"));
	        fw.close();
	    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GUIgrade() 
	{
	String line;
	data = new Vector();
	columns = new Vector();
	int count = 0;
	try 
	{
    FileInputStream fis = new FileInputStream("grades.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
    StringTokenizer st1 = new StringTokenizer(br.readLine(), ",");
    while (st1.hasMoreTokens()) 
    {
    columns.addElement(st1.nextToken());
    count++;
    }
    while ((line = br.readLine()) != null) 
    {
    StringTokenizer st2 = new StringTokenizer(line, ",");
    for (int i = 0; i < count; i++) 
    {
        if (st2.hasMoreTokens())
        data.addElement(st2.nextToken());
        else
        data.addElement("");
    }
    }
    br.close();
} catch (Exception e) 
{
    e.printStackTrace();
}
}
        public int getRowCount() 
        {
                return data.size() / getColumnCount();
        }

        public int getColumnCount() 
        {
                return columns.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) 
        {
                return (String) data.elementAt((rowIndex * getColumnCount())
                                + columnIndex);
        }
        public String getColumnName(int i)
        {
        	return (String)columns.get(i);
        }
	public static void main(String s[]) throws Exception 
			{
				Fileparser();
                GUIgrade model = new GUIgrade();
                JTable table = new JTable();
                table.setModel(model);
                JScrollPane scrollpane = new JScrollPane(table);
                JPanel panel = new JPanel();
                panel.add(scrollpane);
                JFrame frame = new JFrame("Table shows each name and their grade");
                frame.setTitle("Table shows each name and their grade with total number of each grade at the bottom");
                frame.add(panel);
                frame.pack();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        }		
	}