package importCSVAutomatedMail;



import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.opencsv.CSVReader;

public class SendMail {

	
	public static StringBuilder ResetEmail(StringBuilder emailBody) {
		
		emailBody = new StringBuilder();
        emailBody.append("<style type=\"text/css\">\n" + 
        		".tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #729ea5;border-collapse: collapse;}\n" + 
        		".tftable th {font-size:12px;background-color:#acc8cc;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;text-align:left;}\n" + 
        		".tftable tr {background-color:#d4e3e5;}\n" + 
        		".tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;}\n" + 
        		".tftable tr:hover {background-color:#ffffff;}\n" + 
        		"</style>\n" + 
        		"\n" + 
        		"<table class=\"tftable\" border=\"1\">\n" + 
        		"<tr><th>Booking Date<th>Venue</th><th>Street</th><th>City</th><th>State</th><th>Zip</th><th>Artist</th><th>Email</th>"
        		+ "<th>Cost</th><th>Amt Due</th><th>Start Time</th><th>End Time</th><th>Load-In Time</th><th>Production</th>"
        		+ "<th>Sound</th><th>Additional Terms</th><th>Open Artist</th><th>Open Start</th><th>Open End</th></tr>");
        
        
		
	//	emailBody.append("Starting new email...\n");
        return emailBody;
		
	}
	
	public static StringBuilder AppendEmailBody(GigInformation gig, StringBuilder emailBody) {
		
		
		
		emailBody.append("<tr><td>");
    	emailBody.append(gig.getDate() + "</td><td>");
    	emailBody.append(gig.getVenue() + "</td><td>");
    	emailBody.append(gig.getStreet() + "</td><td>");
    	emailBody.append(gig.getCity() + "</td><td>");
    	emailBody.append(gig.getState() + "</td><td>");
    	emailBody.append(gig.getZip() + "</td><td>");
    	emailBody.append(gig.getArtist() + "</td><td>");
      	emailBody.append(gig.getEmail() + "</td><td>");
    	emailBody.append(gig.getCost() + "</td><td>");
    	emailBody.append(gig.getAmountDue() + "</td><td>");
    	emailBody.append(gig.getStartTime() + "</td><td>");
    	emailBody.append(gig.getEndTime() + "</td><td>");
    	emailBody.append(gig.getLoadInTime() + "</td><td>");
    	emailBody.append(gig.getProduction() + "</td><td>");
    	emailBody.append(" "+ "</td><td>");
    	emailBody.append(gig.getAdditionalTerms() + "</td><td>");
    	emailBody.append(gig.getOpenArtist() + "</td><td>");
    	emailBody.append(gig.getOpenStart() + "</td><td>");
    	emailBody.append(gig.getOpenEnd() + "</td><td>");
    	
		
		
		
		//emailBody.append(gig.getArtist() + " : ");
	  //   emailBody.append(gig.getVenue() + " : ");
	  //   emailBody.append(gig.getEmail());
	  //   emailBody.append("\n");
		
		return emailBody;
		
	}
    public static void main(String[] args) {

        String csvFile = "C:\\lce\\lce3.csv";

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            reader.readNext();
            String[] line;
            List <GigInformation> gigs = new ArrayList<GigInformation>();
            while ((line = reader.readNext()) != null) {
              //  System.out.println(line[0] + line[1] +  line[2]);
                GigInformation gig = new GigInformation();
                gig.setDate(line[0]);
                gig.setVenue(line[2]);
                gig.setStreet(line[25]);
                gig.setCity(line[26]);
                gig.setState(line[27]);
                gig.setZip(line[28]);
                gig.setArtist(line[1]);
                gig.setCost(line[14]);
                gig.setAmountDue(line[5]);
                gig.setStartTime(line[3]);
                gig.setEndTime(line[4]);
                gig.setLoadInTime(line[17]);
                gig.setProduction(line[15]);
                gig.setAdditionalTerms(line[16]);
                gig.setOpenArtist(line[22]);
                gig.setOpenStart(line[24]);
                gig.setOpenEnd(line[23]);
                gig.setEmail(line[7]);
                
                gigs.add(gig);
            }
            
            
            StringBuilder emailBody = new StringBuilder();
            String currentArtist = gigs.get(0).getArtist();
            String previousArtist = gigs.get(0).getArtist();
        //    emailBody.append(gigs.get(0).getArtist() + " : ");	
   	    //    emailBody.append(gigs.get(0).getVenue());
   	   //     emailBody.append("\n");
            String previousRecipients = "";
            emailBody = ResetEmail(emailBody);
            ListIterator<GigInformation> it = gigs.listIterator();
              
            for (GigInformation gig: gigs)
            {
            	if (gig.getArtist().equals(currentArtist)) 
            	{
            
            		emailBody = AppendEmailBody(gig, emailBody);
            	} else
            	
            	{
            		// then new artist. send the email, set the current artist to current.
            		
            	//	System.out.print(emailBody.toString());
            	
            		
            		
                    SendEmail(previousRecipients, emailBody, currentArtist);
            		//System.out.println("Sending email to" + gig.getArtist());
            		emailBody = ResetEmail(emailBody);
            		currentArtist = gig.getArtist();
            		
            		// get new values
            		emailBody = AppendEmailBody(gig, emailBody);
            	
            	}
    		
            	// previous 
        		previousRecipients = gig.getEmail().replaceAll(";", ", ");
        		previousArtist = gig.getArtist();

	            	 
            }
            
            SendEmail(previousRecipients, emailBody, currentArtist);

            
         //  it.nextIndex();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

       
        
       

    }

	private static void SendEmail(String recipients, StringBuilder emailBody, String artist) throws UnsupportedEncodingException  {
		Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("davidthongmusic@gmail.com", "goof1981");
            }
        });
        try {
            MimeMessage msg = new MimeMessage(session);
            
            emailBody.append("</table> <p> Please do not reply to this email directly. If you have any questions, please contact amber_foster@LastCallEnt.com </p>");
            msg.setContent(emailBody.toString(), "text/html; charset=utf-8");
           
            InternetAddress replyTo = new InternetAddress("amber_foster@LastCallEnt.com", "Amber Foster");
            
            InternetAddress [] replyTos  = { replyTo };
            msg.setReplyTo(replyTos);
            msg.setFrom(replyTo);
           // InternetAddress ad = new Intern
            
            //Storing the comma seperated values to email addresses
            String to = recipients;
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
           InternetAddress[] address = InternetAddress.parse(to, false);
            //Setting the recepients from the address variable
            
      //      Address 
    //        msg.addRecipient(Message.RecipientType.TO, InternetAddress.parse("davidthongmusic@gmail.com"));
       //     msg.addRecipient(Message.RecipientType.TO, InternetAddress.parse("davidthonglastcall@gmail.com"));
        //    msg.addRecipient
            
            msg.setRecipients(Message.RecipientType.TO, address);
            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
            msg.setSubject("Weekly Event Reminder from Last Call Entertainment for 8/16/2018 to 8/30/2018 - TEST - " + artist);
            msg.setSentDate(new Date());
           // msg.setText(text.toString());
            
            msg.setText(emailBody.toString(), "utf-8", "html");
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            System.out.println("Mail has been sent successfully to " + artist + " 's  email:  " + recipients);
        } catch (MessagingException mex) {
            System.out.println("Unable to send an email" + mex);
        }
		
	}

}