package gpa_cal;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class gpa_calc {

    public static void main(String[] args) throws Exception {
        
            Scanner sc = new Scanner(System.in);
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Sudhy1234");
            
            while (true) {
            	System.out.println("enter name");
                String ch_name = sc.next();
                System.out.println("welcome to gpa calculator "+ch_name);
                System.out.println("enter your choice\n1.enter details and calculating gpa\n2.updating grades\n3.deleting grades\n4.displaying data\n ");
                int choice = sc.nextInt();
                switch (choice) 
                { 
                    case 1:
                        PreparedStatement pst = con.prepareStatement("INSERT INTO grade VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                        System.out.println("enter student name");
                        String sname = sc.next();
                        System.out.println("enter telugu subject gpa ");
                        int tel_gpa = sc.nextInt();
                        System.out.println("enter hindi subject gpa ");
                        int hin_gpa = sc.nextInt();
                        System.out.println("enter eng subject gpa ");
                        int eng_gpa = sc.nextInt();
                        System.out.println("enter maths subject gpa ");
                        int mat_gpa = sc.nextInt();
                        System.out.println("enter physics subject gpa ");
                        int phy_gpa = sc.nextInt();
                        System.out.println("enter che subject gpa ");
                        int che_gpa = sc.nextInt();
                        float cal_gpa = (tel_gpa + hin_gpa + eng_gpa + mat_gpa + phy_gpa + che_gpa) / 6;
                        String cal_gra = "";
                        if (cal_gpa >= 9) {
                            cal_gra = "A+";
                        } else if (cal_gpa >= 8 && cal_gpa < 9) {
                            cal_gra = "A";
                        } else if (cal_gpa >= 7 && cal_gpa < 8) {
                            cal_gra = "B";
                        } else if (cal_gpa < 7) {
                            cal_gra = "C";
                        }
                        pst.setString(1, sname);
                        pst.setInt(2, tel_gpa);
                        pst.setInt(3, hin_gpa);
                        pst.setInt(4, eng_gpa);
                        pst.setInt(5, mat_gpa);
                        pst.setInt(6, phy_gpa);
                        pst.setInt(7, che_gpa);
                        pst.setFloat(8, cal_gpa);
                        pst.setString(9, cal_gra);
                        pst.executeUpdate();
                        break;
                      
                    case 2:
                              while (true) 
                              {
                                 
                                 
                                  System.out.println("updating details\n1.change name\n2.change telugu gpa\n3.change hindi gpa\n4.change english gpa\n5.change maths gpa\n6.change physics gpa\n7.change chemistry gpa\n8.displaying changed gpa after updating");
                                  int ch_data = sc.nextInt();
                                 switch (ch_data) 
                                {
                                  case 1:
                                    PreparedStatement ch1_pst = con.prepareStatement("update grade set sname=?");
                                    ch1_pst.setString(1, ch_name);
                                    ch1_pst.executeUpdate();
                                    break;
                                   case 2:
                                    System.out.println("enter gpa of telugu subject  to change");
                                    int ch_tel = sc.nextInt();
                                    PreparedStatement ch2_pst = con.prepareStatement("update grade set TEL_GRA=?");
                                    ch2_pst.setInt(1, ch_tel);
                                    ch2_pst.executeUpdate();
                                    break;
                                    case 3:
                                    System.out.println("enter gpa of hindi subject  to change");
                                    int ch_hin = sc.nextInt();
                                    PreparedStatement ch3_pst = con.prepareStatement("update grade set HIN_GRA=?");
                                    ch3_pst.setInt(1, ch_hin);
                                    ch3_pst.executeUpdate();
                                    break;
                                     case 4:
                                      System.out.println("enter gpa of english subject  to change");
                                      int ch_eng = sc.nextInt();
                                      PreparedStatement ch4_pst = con.prepareStatement("update grade set ENG_GRA=?");
                                      ch4_pst.setInt(1, ch_eng);
                                      ch4_pst.executeUpdate();
                                       break;
                                     case 5:
                                    System.out.println("enter gpa of maths subject  to change");
                                    int ch_mat = sc.nextInt();
                                    PreparedStatement ch5_pst = con.prepareStatement("update grade set MAT_GRA=?");
                                    ch5_pst.setInt(1, ch_mat);
                                    ch5_pst.executeUpdate();
                                    break;
                                    case 6:
                                    System.out.println("enter gpa of physics subject  to change");
                                    int ch_phy = sc.nextInt();
                                    PreparedStatement ch6_pst = con.prepareStatement("update grade set PHY_GRA=?");
                                    ch6_pst.setInt(1, ch_phy);
                                    ch6_pst.executeUpdate();
                                    break;
                                    case 7:
                                    System.out.println("enter gpa of chemistry subject  to change");
                                    int ch_che = sc.nextInt();
                                    PreparedStatement ch7_pst = con.prepareStatement("update grade set CHE_GRA=?");
                                    ch7_pst.setInt(1, ch_che);
                                    ch7_pst.executeUpdate();
                                    break;
                                    case 8:
                                	System.out.println("Displaying changed gpa  after updating");
                                	Statement edit_st = con.createStatement();
                                	ResultSet rs = edit_st.executeQuery("SELECT * FROM grade WHERE sname='" + ch_name + "'");
                                    String edit_gra="";
                                	float edit_gpa = 0;
                                	while (rs.next()) 
                                	{
                                	    edit_gpa = (float) (rs.getInt("tel_gra") + rs.getInt("hin_gra") + rs.getInt("eng_gra") + rs.getInt("mat_gra") + rs.getInt("phy_gra") + rs.getInt("che_gra")) / 6;
                                	    System.out.println(edit_gpa);
                                	}
                                	if (edit_gpa >= 9) 
                                	{
                                        edit_gra = "A+";
                                    } 
                                	else if (edit_gpa >= 8 && edit_gpa < 9) 
                                    {
                                        edit_gra = "A";
                                    } 
                                	else if (edit_gpa >= 7 && edit_gpa < 8)
                                    {
                                        edit_gra = "B";
                                    } 
                                	else if (edit_gpa < 7) 
                                	{
                                        edit_gra = "C";
                                    }
                                	Statement edi_gra_st=con.createStatement();
                                	edi_gra_st.executeUpdate("UPDATE grade SET AVG_GPA='" + edit_gpa + "', AVG_GRADE='" + edit_gra + "'");
                                	break;
                                	
                                }               
                              
                                 System.out.println("do you want to edit grades again [Y|N]"); 
                                 String edit_check = sc.next();
                                   if (edit_check.equalsIgnoreCase("Y")) 
                                   {
                                      break;
                                  }
                                
                         
                             } 
                             
                    case 3: System.out.println("delete data in database");
                            System.out.println("enter name to delete record in database");
                            String del_name=sc.next();
                            Statement del_st=con.createStatement();
                            del_st.executeUpdate("delete from grade where sname='"+del_name+"'");
                            break;
                    case 4: System.out.println("displaying data");
                            System.out.println("enter name to display record from database");
                             String dis_name = sc.next();
                             Statement dis_st = con.createStatement();
                             ResultSet dis_rs = dis_st.executeQuery("SELECT * FROM grade WHERE sname='" + dis_name + "'");
                             boolean dis_b = false;
                             while (dis_rs.next()) {
                              dis_b = true; // Set to true if at least one record is found
                               System.out.println("-----------------------------------------------------------------------------------------");
                               System.out.println("name            \ttelugu\thindi\tenglish\tmaths phyiscs chemistry\tgpa\tgrade");
                               System.out.println("-----------------------------------------------------------------------------------------");
                               System.out.print(dis_rs.getString("SNAME") + "\t");
                               System.out.print(dis_rs.getInt("TEL_GRA") + "\t");
                               System.out.print(dis_rs.getInt("HIN_GRA") + "\t");
                               System.out.print(dis_rs.getInt("ENG_GRA") + "\t");
                               System.out.print(dis_rs.getInt("MAT_GRA") + "\t");
                               System.out.print(dis_rs.getInt("PHY_GRA") + "\t");
                               System.out.print(dis_rs.getInt("CHE_GRA") + "\t");
                               System.out.print(dis_rs.getFloat("AVG_GPA") + "\t");
                               System.out.print(dis_rs.getString("AVG_GRADE") + "\t");
                               System.out.println();
                               System.out.println("------------------------------------------------------------------------------------------");
                               System.out.println();
                            }
                             if (!dis_b) {
                          System.out.println("user name doesn't exist");
                            }
                             break;
                                   
                }
                
                System.out.println("do you want to edit this page  [Y|N]");
                String an_check = sc.next();
                if (an_check.equalsIgnoreCase("Y")) 
                 {
                        break;
                 }
            }
    }
}

                

            
        
    


