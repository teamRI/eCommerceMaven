package fr.adaming.model;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Header;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.Base64.OutputStream;

public class CreatePdf {


	LigneCommande lc;
	
	 public void writePdf(Commande co, Client cl, List<LigneCommande> listlc) throws Exception {
	        
		 final String chemin= "C:\\Users\\inti0490\\Desktop\\Formation\\Workspace\\GenerationPDF\\Récapitulatif.pdf";
		 
		 
		 Document document = new Document();
	      
	         
	       
	         
	    PdfPTable table= new PdfPTable(6); 
	    PdfPTable tableCo= new PdfPTable(5);
	    
	   
	    
	    try {
	    	PdfWriter.getInstance(document, new FileOutputStream(chemin));
	    	 document.open();
	         
	        document.addTitle("Récapitulatif");
	        document.addSubject("Récapitulatif commande");
	        document.addKeywords("iText, email");
	        document.addAuthor("Endor online");
	        document.addCreator("Endor online");
	   
	        
	        Chunk chunk = new Chunk("Récapitulatif du client "+cl.getNom(), null);
	      Image image;
	      image= Image.getInstance("C:\\Users\\inti0490\\Desktop\\eCommerce\\logo1.jpg");
	      image.setAbsolutePosition(2, 150);
	      document.add(image);
	        
	        PdfPCell cell;
	        
	        document.add(chunk);
	        
	        cell= new PdfPCell(new Phrase("Client"));
	        cell.setColspan(6);
	        table.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase("Id"));
	        table.addCell(cell);
	        cell= new PdfPCell(new Phrase("Nom"));
	        table.addCell(cell);
	        cell= new PdfPCell(new Phrase("Mail"));
	        table.addCell(cell);
	        cell= new PdfPCell(new Phrase("Téléphone"));
	        table.addCell(cell);
	        cell= new PdfPCell(new Phrase("Adresse"));
	        table.addCell(cell);
	        
	        String id= Long.toString(cl.getId());
	        cell= new PdfPCell(new Phrase(id));
	        table.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase(cl.getNom()));
	        table.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase(cl.getEmail()));
	        table.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase(cl.getTel()));
	        table.addCell(cell);
	      
	        cell= new PdfPCell(new Phrase(cl.getAdresse()));
	        table.addCell(cell);
	        
	        
	        document.add(table);

	        document.add(new Paragraph(
	        
	    "\n-------------------------------------------------------------------------------------------------------------------------------\n"));
	    
	        document.add(new Paragraph("\n"));
	        
	        cell = new PdfPCell(new Phrase("Commande"));
			cell.setColspan(5);
			tableCo.addCell(cell);
			
			
			cell= new PdfPCell(new Phrase("date commande"));
	        tableCo.addCell(cell);
	        
			
			
			for(LigneCommande lc: listlc) {
				
			
			
			cell= new PdfPCell(new Phrase("IdC"));
	        tableCo.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase("produit"));
	        tableCo.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase("quantité"));
	        tableCo.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase("prix final"));
	        tableCo.addCell(cell);
	        
	      
	           SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
			String dateC = formatDateJour.format(lc.getCo().getDateCommande());
			cell = new PdfPCell(new Phrase(dateC));
			tableCo.addCell(cell);
	        
	        
	        
	        String idC= Integer.toString(lc.getId());
	        cell= new PdfPCell(new Phrase(idC));
	        tableCo.addCell(cell);
	        
	     
			
			 cell= new PdfPCell(new Phrase(lc.getPr().getDesignation()));
		        tableCo.addCell(cell);
		        
		        cell= new PdfPCell(new Phrase(lc.getQuantiteCo()));
		        tableCo.addCell(cell);
		        
		        
		        String pxf= Double.toString(lc.getPrixfinal());
		        cell= new PdfPCell(new Phrase(pxf));
		        tableCo.addCell(cell);
		        
		        document.add(tableCo);}
		        
	    } catch (DocumentException de) {
			de.printStackTrace();
		}
	        
	        document.close();
	        
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le PDF a bien été généré"));

		

	  }   }
	     

