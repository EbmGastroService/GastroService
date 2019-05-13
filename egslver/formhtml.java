// created on 14.11.2007 at 02:16
//form.html
package egslver;
public class formhtml{
	String gen(){
		String str="<html><html><body bgcolor=#fffeee width=480 font face='Times New Roman' font color=#888999><center>"+
		"<table cellpadding=0 cellspacing=0 border=0 width=640><tr><td collspan=5 >"+
		"<b>DN&nbsp;Abrechnung:&nbsp;1207</b>&nbsp;Klient:&nbsp;00000<br>Firma:<b>&nbsp;XXXX & XXXXXX OEG</b>"+
		"<font size=-1>&nbsp;xxxxx xxxxx xxxxx &nbsp;xxxxx xxxxxxxxxxxx&nbsp;Tel.:&nbsp;0xxxxxxxxx</font></td>"+
		"</tr><tr><td collspan=5 >Mitarbeiter:&nbsp;0000&nbsp;&nbsp;Hr&nbsp;xxxxx&nbsp;xxxxxx&nbsp;Vers.:&nbsp;"+
		"xxxxxxxx&nbsp;&nbsp;Angestelte&nbsp;Seit:&nbsp;01.10.200x&nbsp; als Beruf</td>"+
		"</tr></table><table cellpadding=0 cellspacing=0 border= width=640><tr bgcolor=#eeeeee>"+
		"<th align=left width=60>Code</th><th align=left width=300>Bezeichnung</th><th align=right width=80>"+
		"Std</th><th align=right width=100>Std Satz</th><th align=right width=100>Lohn<br>Gehalt</th></tr></table>"+
		"<table cellpadding=0 cellspacing=0 border=0 width=640><tr><td valign=top width=60>1001</td>"+
		"<td align=left width=300>Lohn/Gehalt</td><td align=right width=80>0</td><td align=right width=100>0,00  </td>"+
		"<td align=right width=100><b>750,00  </b></td></tr><tr><td valign=top width=60>1002</td><td align=left width=300>"+
		"Sonder Zahlung WR/UR</td><td align=right width=80>0</td><td align=right width=100>0,00  </td>"+
		"<td align=right width=100><b>750,00  </b></td></tr><tr><td border=0 colspan=5 width=640></td></tr>"+
		"<tr><td border=0 colspan=5 width=640></td></tr><tr><td colspan=5 width=640><hr></td></tr><tr><td colspan=4>"+
		"<b>Gesamt Brutto Bez&uuml;ge</b></td><td align=right><b>1.500,00  </b></td></tr></table><br>"+
		"<table cellpadding=0 cellspacing=0 border=0 width=640><tr bgcolor=#eeeeee><th align=left width=180>"+
		"Bezeichnung</th><th align=right width=115>Jahres<br>Summen</th><th align=right width=115>Soz./Lstr.<br>BMGL</th>"+
		"<th align=right width=115>Beitrags<br>Summen</th><th align=right width=115>Abzug<br>Beitrag</th></tr></table>"+
		"<table cellpadding=0 cellspacing=0 border=0 width=640><tr><td align=left width=180>SV Beitag</td>"+
		"<td align=right width=115>7.380,00  </td><td align=right width=115>750,00  </td><td align=right width=115>1.620,00  </td>"+
		"<th align=right width=115>135,00  </th></tr><tr><td align=left width=180>SZ SV Beitag</td><td align=right width=115>1.245,00  </td>"+
		"<td align=right width=115>750,00  </td><td align=right width=115>255,00  </td><th align=right width=115>127,50  </th></tr>"+
		"<tr><td align=left width=180>SZ Lstr. Beitag</td><td align=right width=115>-0,00  </td><td align=right width=115>622,50  </td>"+
		"<td align=right width=115>0,00  </td><th align=right width=115>0,00  </th></tr><tr><td align=left width=180>"+
		"Lohnsteuer</td><td align=right width=115>-0,00  </td><td align=right width=115>0,00  </td>"+
		"<td align=right width=115>0,00  </td><th align=right width=115>0,00  </th></tr><tr><td colspan=5 width=640><hr></td></tr>"+
		"<tr><tr><td align=left width=180>Summen</td><td align=right width=115>8.625,00  </td><td align=right width=115>MAV&nbsp;160,65  </td>"+
		"<td align=right width=115>1.875,00  </td><th align=right width=115>262,50  </th></tr><tr><td align=left colspan=4>E-Card/Sonstige</td>"+
		"<th align=right>10,00  </th></tr><tr><td colspan=5 width=640><hr></td></tr><tr><th align=left colspan=4>"+
		"Gesamt Netto Bez&uuml;ge</th><th align=right>1.227,50  </th></tr></table>"+
		"<br><br><br>--------------------------------------------------------------<br><br><br>"+
		"<table cellpadding=0 cellspacing=0 border=0"+
		" width=640><tr><td collspan=5 ><b>DN&nbsp;Abrechnung:&nbsp;1207</b>&nbsp;Klient:&nbsp;251401<br>Firma:<b>"+
		"&nbsp;Muster Firmen Bezeichnung </b><font size=-1>&nbsp;Muster Strasse 100 &nbsp;XXXX Ortschaft&nbsp;"+
		"Tel.:&nbsp;00000000000</font></td></tr><tr><td collspan=5 >Mitarbeiter:&nbsp;0000&nbsp;&nbsp;Hr&nbsp;Name"+
		"&nbsp;Vorname&nbsp;Vers.:&nbsp;00000000000000&nbsp;&nbsp;Dienstverh.&nbsp;Seit:&nbsp;01.10.XXXX&nbsp; als Muster Beruf</td></tr></table>"+
		"<table cellpadding=0 cellspacing=0 border= width=640><tr bgcolor=#eeeeee><th align=left width=60>Code</th><th align=left width=300>Bezeichnung</th>"+
		"<th align=right width=80>Std</th><th align=right width=100>Std Satz</th><th align=right width=100>Lohn<br>Gehalt</th></tr></table>"+
		"<table cellpadding=0 cellspacing=0 border=0 width=640><tr><td valign=top width=60>1001</td><td align=left width=300>Lohn/Gehalt</td><td align=right width=80>"+
		"0</td><td align=right width=100>0,00  </td><td align=right width=100><b>750,00  </b></td></tr><tr><td valign=top width=60>1002</td><td align=left width=300>Sonder Zahlung WR/UR</td>"+
		"<td align=right width=80>0</td><td align=right width=100>0,00  </td><td align=right width=100><b>750,00  </b></td></tr><tr><td border=0 colspan=5 width=640></td>"+
		"</tr><tr><td border=0 colspan=5 width=640></td></tr><tr><td colspan=5 width=640><hr></td></tr><tr><td colspan=4><b>Gesamt Brutto Bez&uuml;ge</b></td><td align=right>"+
		"<b>1.500,00  </b></td></tr></table><br><table cellpadding=0 cellspacing=0 border=0 width=640><tr bgcolor=#eeeeee><th align=left width=180>Bezeichnung</th>"+
		"<th align=right width=115>Jahres<br>Summen</th><th align=right width=115>Soz./Lstr.<br>BMGL</th><th align=right width=115>Beitrags<br>Summen</th>"+
		"<th align=right width=115>Abzug<br>Beitrag</th></tr></table><table cellpadding=0 cellspacing=0 border=0 width=640><tr><td align=left width=180>SV Beitag</td>"+
		"<td align=right width=115>7.380,00  </td><td align=right width=115>750,00  </td><td align=right width=115>1.620,00  </td><th align=right width=115>135,00  </th>"+
		"</tr><tr><td align=left width=180>SZ SV Beitag</td><td align=right width=115>1.245,00  </td><td align=right width=115>750,00  </td><td align=right width=115>255,00  </td>"+
		"<th align=right width=115>127,50  </th></tr><tr><td align=left width=180>SZ Lstr. Beitag</td><td align=right width=115>-0,00  </td><td align=right width=115>622,50  </td>"+
		"<td align=right width=115>0,00  </td><th align=right width=115>0,00  </th></tr><tr><td align=left width=180>Lohnsteuer</td><td align=right width=115>-0,00  </td><td align=right width=115>"+
		"0,00  </td><td align=right width=115>0,00  </td><th align=right width=115>0,00  </th></tr><tr><td colspan=5 width=640><hr></td></tr><tr><tr><td align=left width=180>Summen</td><td align=right width=115>"+
		"8.625,00  </td><td align=right width=115>MAV&nbsp;160,65  </td><td align=right width=115>1.875,00  </td><th align=right width=115>262,50  </th></tr><tr><td align=left colspan=4>E-Card/Sonstige</td>"+
		"<th align=right>10,00  </th></tr><tr><td colspan=5 width=640><hr></td></tr><tr><th align=left colspan=4>Gesamt Netto Bez&uuml;ge</th><th align=right>1.227,50  </th></tr></table></center></body></html>";
		return str;
	
	}
	public formhtml(){
		new com.units.save().file("egslv/temp/mform.html",gen(),false);
	}
}
