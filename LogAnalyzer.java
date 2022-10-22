import java.util.Random;
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * This code contains extra lines that makes it unable to compile.These lines were rquired in order to answer a challenge question.
 * Hence, in order to get a compiled code, you can comment those methods out since there are no methods for the underlined errors.
 
 * @author Frederick Lamptey.
 * @version    2022.10.22
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] countDays;
    private int[] countMonths;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    private Random randomizer;
    private int numEntries;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String fileLog)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        countDays = new int[29];
        countMonths = new int[13];
        randomizer = new Random();
        numEntries = 10 + randomizer.nextInt(90);
        LogfileCreator writer = new LogfileCreator();
        writer.createFile(fileLog, numEntries);
        
        // Create the reader to obtain the data.
        reader = new LogfileReader(fileLog);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        /*
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }*/
        hourCounts = new int[24];
        reader.reset();
        while(reader.hasNext()){
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
     /**
     * Analyze the daily access data from the log file.
     */
    public void analyzeDailyData()
    {
        /*
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }*/
        countDays = new int[29];
        reader.reset();
        while(reader.hasNext()){
            LogEntry entry = reader.next();
            int day = entry.getDay(); // There's no getDay method in logentry
            countDays[day]++;
        }
    }
    
     /**
     * Analyze the monthly access data from the log file.
     */
    public void analyzeMonthlyData()
    {
        /*
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }*/
        countMonths = new int[13];
        reader.reset();
        while(reader.hasNext()){
            LogEntry entry = reader.next();
            int month = entry.getMonth(); //there's no getMonth in logentry
            countMonths[month]++;
        }
    }
    
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        /*
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }*/
        analyzeHourlyData();
        System.out.println("Hr: Count");
        int hour = 0;
        while(hour < hourCounts.length){
            System.out.println(hour + ": " + hourCounts[hour]);
            ++hour;
        }
    }
    
    /**
     * Print the daily counts.
     * These should have been set with a prior
     * call to analyzeDailyData.
     */
    public void printDailyCounts()
    {
        /*
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }*/
        analyzeDailyData();
        System.out.println("Daily: Count");
        int day = 1;
        while(day < countDays.length){
            System.out.println(day + ": " + countDays[day]);
            ++day;
        }
    }
    
    /**
     * Print the monthly counts.
     * These should have been set with a prior
     * call to analyzeDailyData.
     */
        public void totalAccessesPerMonth()
    {
        /*
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }*/
        analyzeDailyData();
        System.out.println("Month: Count");
        int month = 1;
        while(month < countMonths.length){
            System.out.println((month) + ": " + countMonths[month]);
            ++month;
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Tracks the total number of accesses for the year
     */
    public int numberOfAccesses()
    {
        //goes over all lines in the file and add 1 to count for each row
        analyzeHourlyData();
        int count = 0;
        for(int k=0; k<hourCounts.length; k++){
            count += hourCounts[k];
        }
        return count;
    }
    
    /**
     * returns the hour with most traffic. It will print the earliest greatest traffic if two hours happen to have the greatest traffic
     */
    public int busiestHour()
    {
        //goes over all the lines in the file and adds 1 to count for each row.
        analyzeHourlyData();
        int hour = 0;
        int busiest = hourCounts[0];
        for(int k=0; k<hourCounts.length; k++){
            if(hourCounts[k] > busiest){
                busiest = hourCounts[k];
                hour = k;
            }
        }
        return hour;
    }
    
        /**
     * returns the hour with lowest traffic. It will print the earliest lowest traffic if two hours happen to have the lowest traffic
     */
    public int quiestestHour()
    {
        //goes over all the lines in the file and adds 1 to count for each row.
        analyzeHourlyData();
        int hour = 0;
        int quietest = hourCounts[0];
        for(int k=0; k<hourCounts.length; k++){
            if(hourCounts[k] < quietest){
                quietest = hourCounts[k];
                hour = k;
            }
        }
        return hour;
    }
    
        /**
     * returns the day with least traffic. It will print the earliest least traffic if two days happen to have the least traffic
     */
    public int quietestDay()
    {
        //goes over all the lines in the file and adds 1 to count for each row.
        analyzeHourlyData();
        int day = 1;
        int quietest = countDays[1];
        for(int k=1; k<hourCounts.length; k++){
            if(countDays[k] < quietest){
                quietest = countDays[k];
                day = k;
            }
        }
        return day;
    }
    
        /**
     * returns the day with most traffic. It will print the earliest greatest traffic if two days happen to have the greatest traffic
     */
    public int busiestDay()
    {
        //goes over all the lines in the file and adds 1 to count for each row.
        analyzeHourlyData();
        int day = 1;
        int busiest = countDays[1];
        for(int k=1; k<hourCounts.length; k++){
            if(countDays[k] > busiest){
                busiest = countDays[k];
                day = k;
            }
        }
        return day;
    }
    
        /**
     * returns the month with least traffic. It will print the earliest least traffic if two month happen to have the least traffic
     */
    public int quietestMonth()
    {
        //goes over all the lines in the file and adds 1 to count for each row.
        analyzeHourlyData();
        int month = 1;
        int quietest = countMonths[1];
        for(int k=1; k<countMonths.length; k++){
            if(countMonths[k] < quietest){
                quietest = countMonths[k];
                month = k;
            }
        }
        return month;
    }
    
        /**
     * returns the month with most traffic. It will print the earliest greatest traffic if two months happen to have the greatest traffic
     */
    public int busiestMonth()
    {
        //goes over all the lines in the file and adds 1 to count for each row.
        analyzeHourlyData();
        int month = 1;
        int busiest = countMonths[1];
        for(int k=1; k<countMonths.length; k++){
            if(countMonths[k] > busiest){
                busiest = countMonths[k];
                month = k;
            }
        }
        return month;
    }
    
    /**
     * returns the hour with most traffic. It will print the earliest greatest traffic if two hours happen to have the greatest traffic
     */
    public int busiestTwoHour()
    {
        //goes over all the lines in the file and adds 1 to count for each row.
        analyzeHourlyData();
        int hour = 0;
        int busiest = hourCounts[0];
        for(int k=1; k<hourCounts.length-1; k++){
            if((hourCounts[k] + hourCounts[k+1]) > busiest){
                busiest = (hourCounts[k] + hourCounts[k+1]);
                hour = k;
            }
        }
        return hour;
    }
    
    /**
     * returns the average accesses per month.
     */
    public double averageAccessPerMonth()
    {
        //goes over all the lines in the file and adds 1 to count for each row.
        analyzeHourlyData();
        int total = 0;
        double average = 0;
        for(int k=0; k<countMonths.length; k++){
            total += countMonths[k];
            average = ((double)total/countMonths.length);
        }
        return average;
    }
    
}
