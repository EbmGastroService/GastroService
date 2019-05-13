// created on 14.11.2007 at 02:16
//form.html
package egsbh;
public class formhtml{
	public String gen(){
		String str="<html><head><title>EGS Buchhaltung</title></head><body bgcolor='#ccceee' width='640' font face='Times New Roman' font color='#888999'>"+
		"<center>"+
		"<div align=center style='width:640;background:#888999'>"+
		"<h1><font color='#ccc999' >EGS Buchhaltung Programm</font></h1>"+
		" <h3><font color='#ccc777'>Entwickelt von Mourad El Bakry</font></h3>"+
		"<h2><font color='#ccc444' >Einfache geht es wohl nicht mehr!</font></h2></div>"+	
		"<div align='left' style='padding:10px;color:#888990;width:640;background:#fffffe'>"+
		"<p><b>Sie kaufen Ihre Ware ein :</b>"+
		" Sie haben eine Rechnung vom H&auml;ndler bekommen. "+
		" Am Ende der Rechnung nehmen Sie Netto Betr&auml;ge falls m&ouml;glich ist. beim Brutto wird durch meine Programm weiter geholfen. "+
		"<br>Drucken Sie der Bedienung Taste <b>Einkauf</b> dann beantworten Sie die Frageformular"+
		" also Datum, Bar oder mit Schek oder offen geblieben ist!."+
		"Ist das Verbrauchswaren oder Ber&uuml;omaterial, Reinigungsmaterial, LKW Kraftstoff oder Einrechtungsgegenst&auml;nde"+
		". und das war es!"+
		"</p>"+
		"<p><b>Sie verkaufen Ihre Waren : </b>"+
		"Sie haben Ihrer Tageserl&ouml;se bereit addiert, und geteilt in Erl&ouml;se 10%, Erl&ouml;se 20% oder dasgleiche."+		
		"<br>Drucken Sie der Bedienung Taste <b>Verkauf</b> dann beantworten Sie die Frageformular"+
		" also Datum, und Ihre Betr&auml;ge und Steuersatz. und das war es!"+
		"</p>"+
		"<p><b>Sie Bezahlen eine Rechnung : </b>"+
		"Sie haben Strom, Miete, Betriebs Versicherung, oder Telefon Rechnung bezahlt"+		
		"<br>Drucken Sie der Bedienung Taste <b>Bezahle Rechnung</b> dann beantworten Sie die Frageformular"+
		" also &uuml;er Bank oder in Bar, Datum, und Ihre Betr&auml;ge und Steuersatz. und das war es!"+
		"</p>"+
		"<p><b>Sie Bezahlen Ihre Personal : </b>"+
		"Sie haben L&ouml;hne, Geh&auml;lte, GKK Beitr&auml;ge, Finanzamt Abgaben DB DZ Lstr, Gemeinde Kommunalsteuer bezahlt"+		
		"<br>Drucken Sie der Bedienung Taste <b>Personalkosten</b> dann beantworten Sie die Frageformular"+
		" also &uuml;er Bank oder in Bar, Datum, und Ihre Betr&auml;ge. und das war es!"+
		"</p>"+
		"<p><b>Sie lassen eine Lieferanten Rechnung in dieses Monat unbezahlt : </b>"+
		"Sie haben eine Lieferung f&uuml;r Material Dienstleistung oder auch eine Strom Rechnung dieses Monat nicht bezahlt dann"+		
		"<br>Drucken Sie der Bedienung Taste <b>Rechnung bekommen</b> dann beantworten Sie die Frageformular"+
		" also von wem und wann und Betr&auml;ge. und das war es!"+
		"</p>"+
		"<p><b>Sie Verkaufen aber der Kunde hat in dieses Monat noch nicht bezahlt : </b>"+		
		"<br>Drucken Sie der Bedienung Taste <b>Rechnung geben</b> dann beantworten Sie die Frageformular"+
		" also an wen und wann und Betr&auml;ge. und das war es!"+
		"</p>"+	
		"<br>Ich w&uuml;nsche Ihnen viel Vergn&uuml;gen und hoffe damit Ihnen gedient zu haben"+
		"<br>Jeder Zeit stehe ich Ihnen zuverf&uuml;gen<br> unter Tel.:+43 676 354 68 24 oder "+
		"<a href='mailto:elbakry@aon.at'>elbakry@aon.at</a>"+
		"<p align=center>Copywrite &copy;Mourad El Bakry 2007</p>"+
		"</div></center>"+
		"</body></html>";
		return str;	
	}
	public formhtml(){
		new com.units.save().file("egsbh/temp/bhform.html",gen(),false);
	}
}
