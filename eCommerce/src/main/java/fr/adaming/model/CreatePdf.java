package fr.adaming.model;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Header;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.Base64.OutputStream;

import jdk.net.NetworkPermission;

public class CreatePdf {

	Commande co;
	Client cl;
	LigneCommande lc;
	
	 public String writePdf(OutputStream outputStream) throws Exception {
	        Document document = new Document();
	        PdfWriter.getInstance(document, outputStream);
	         
	       
	         
	    PdfPTable table= new PdfPTable(4); 
	    PdfPTable tableCo= new PdfPTable(6);
	    
	    Header header= new Header("Récapitulatif du client" + cl.getNom(), null);
	    
	    try {
	    	PdfWriter.getInstance(document, outputStream);
	    	 document.open();
	         
	        document.addTitle("Récapitulatif");
	        document.addSubject("Récapitulatif commande");
	        document.addKeywords("iText, email");
	        document.addAuthor("ECommerce");
	        document.addCreator("ECommerce");
	   
	        PdfPCell cell;
	        
	        document.add(header);
	        
	        cell= new PdfPCell(new Phrase("Client"));
	        cell.setColspan(4);
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
			cell.setColspan(6);
			tableCo.addCell(cell);
			
			
			cell= new PdfPCell(new Phrase("Id"));
	        tableCo.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase("date commande"));
	        tableCo.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase("produit"));
	        tableCo.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase("quantité"));
	        tableCo.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase("prix final"));
	        tableCo.addCell(cell);
	        
	        String idC= Long.toString(lc.getId());
	        cell= new PdfPCell(new Phrase(id));
	        tableCo.addCell(cell);
	        
	        SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
			String dateC = formatDateJour.format(lc.getCo().getDateCommande());
			cell = new PdfPCell(new Phrase(dateC));
			tableCo.addCell(cell);
			
			 cell= new PdfPCell(new Phrase(lc.getPr().getDesignation()));
		        tableCo.addCell(cell);
		        
		        cell= new PdfPCell(new Phrase(lc.getQuantiteCo()));
		        tableCo.addCell(cell);
		        
		        
		        String pxf= Double.toString(lc.getPrixfinal());
		        cell= new PdfPCell(new Phrase(pxf));
		        tableCo.addCell(cell);
		        
		        document.add(tableCo);
		        
	    } catch (DocumentException de) {
			de.printStackTrace();
		}
	        
	        document.close();
	        
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le PDF a bien été généré"));

			return this.writePdf(outputStream);

	  }   }
	     

