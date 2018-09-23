import java.util.*;
import java.lang.*;

class SingleServer {
  
  public static int rbw(int start, int end) {
    Random rand = new Random();
    return rand.nextInt(end-start+1)+start;
  }

  public static void main(String[] args) {
    
    int[][] server = new int[20][9];

    server[0][0] = 1;
    server[0][1] = 0;
    server[0][2] = 0;
    server[0][3] = rbw(1,6);
    server[0][4] = 0;
    server[0][5] = 0;
    server[0][6] = server[0][3];
    server[0][7] = server[0][3];
    server[0][8] = 0;

    for(int i = 1; i < 20; i++) {
      server[i][0] = i+1;
      server[i][1] = rbw(1,10);
      server[i][2] = server[i-1][2] + server[i][1];
      server[i][3] = rbw(1,6);
      server[i][4] = Math.max(server[i][2],server[i-1][6]);
      server[i][5] = server[i][4] - server[i][2];
      server[i][6] = server[i][4] + server[i][3];
      server[i][7] = server[i][6] - server[i][2];
      server[i][8] = server[i][2] - server[i-1][6];
      if(server[i][8] < 0)
        server[i][8] = 0; 
    }

    System.out.println("   Customer No.\t\tInterarrival Time\t\tArrival Time\t\tService time\t\tService Begin\t\tWaiting Time\t\tService Ends\t\tIn System Time\t\tServer Idle Time");

    for(int i = 0; i < 20; i++) {
      for(int j = 0; j < 9; j++) {
        System.out.print("\t   "+server[i][j]+"    \t");
      }
      System.out.println();
    }

    int awt = 0;
    for(int i = 0; i < 20; i++){
      awt += server[i][5];
    }
    System.out.println("Average delay in queue : " + (float)awt/20);

    int wcount = 0;
    for(int i = 0; i < 20; i++){
      if(server[i][5]>0)
        wcount++;
    }
    System.out.println("Probability. Of Customers waiting : " + (float)wcount/20);

    int icount = 0,idle = 0;
    for(int i = 0; i < 20; i++){
      idle += server[i][8];
      if(server[i][8]>0)
        icount++;
    }
    idle = (server[19][6] - idle)*100/server[19][6];
    System.out.println("Server utilization : " + idle + "%");
    //(100-((float)icount/0.2))
    int iat = 0;
    for(int i = 0; i < 20; i++){
      iat += server[i][1];
    }
    System.out.println("Average Time between Arrival : " + (float)iat/19);

    System.out.println("Average Waiting Time of Those Who Wait : " + (float)awt/wcount);

    int ist = 0;
    for(int i = 0; i < 20; i++){
      ist += server[i][7];
    }
    System.out.println("Average Time Customers Spends In System : " + (float)ist/20);

  }

}
