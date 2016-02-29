import javax.swing.JOptionPane;    
import java.io.* ;

public class TOPPUPIL 
{
    // array of PUPIL objects 
    private PUPIL pupilList [] ;
    //number of members to be calculated after reading file
    int noOfPUPILS ; 
    int topMark ; 

    FILEREADCSV examDataFile ;

    int maxDataPosition ; 

    String fileContent = "" ; 
    
    FILEWRITECSV resultsFile ;
    public TOPPUPIL() throws IOException
    {  
        examDataFile = new FILEREADCSV () ; 
        resultsFile = new FILEWRITECSV() ; 
        topMark = 0 ;
        noOfPUPILS = 49;
    }

    //top level algorithm 
    public void processPUPILS () throws IOException
    {
        setUppupilList () ; 
        counttopMark () ; 
    }

    private void setUppupilList () throws IOException 
    {
        //first user message
        System.out.println("Feeback of marks" ) ;
        System.out.println("** Preparing to read data file.") ;

        // read file, fetch data as String array containing the rows
        String[] datarows = examDataFile.readCSVtable () ;

        //calculate the number of member rows, skip headings
        noOfPUPILS = datarows.length - 1 ;

        // update user with number of rows with membership details
        System.out.println ("** " + noOfPUPILS +  " rows read ." ) ;

        // prepare array for pupils 
        pupilList = new PUPIL[noOfPUPILS] ;

        //create pupil objects and copy data from source
        for (int i = 0 ; i < noOfPUPILS ; i++) {
            pupilList[i] = new PUPIL () ;

            //adjust to skip headings 
            pupilList[i].readPupilDetails(datarows[ i +1]);
        }

    }

    public void counttopMark () throws IOException
    {
        String fileContent = "" ;
        System.out.println( "A report of the pupil with the top mark\n") ;
        int maxDataPosition = 0 ;
        
        for (int i= 0; i< noOfPUPILS; i++) {

            //compare current value with best value
            if (pupilList[i].getMark() > topMark) {
                // update the position of the best value
                topMark = pupilList[i].getMark() ;
                maxDataPosition = i;
                fileContent = fileContent.concat(pupilList[i].writeDetails()) ; 

            }
        }
        System.out.println( "\n Top mark is: " + topMark) ;
        System.out.println( "which belongs to: " + pupilList[maxDataPosition].getfName() + " " + pupilList[maxDataPosition].getsName() ) ;
        System.out.println () ;     
        System.out.println( "** Preparing to write data file") ; 
        resultsFile.writeCSVtable(fileContent) ;
        System.out.println( "** File written and closed.") ; 
    }

   
}