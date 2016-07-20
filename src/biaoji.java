import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class biaoji {

	public  biaoji(String fil1,String fil2,String fil3) throws Exception{
	{
		File file1=new File(fil1);//"F:/perl/6.23/1.txt" );
		File file2=new File(fil2);//"F:/perl/6.23/2.txt" );
		//写入excel
		  String outputFile=fil3;
			WritableWorkbook wwb = null;
			 WritableSheet ws=null;		
  
	     //首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象  

	     wwb = Workbook.createWorkbook(new File(outputFile));
	     //Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置  
	     ws = wwb.createSheet("sheet1", 0);  
	     ws.setColumnView(0,19 );
	     ws.setColumnView(1,13 );
		    WritableFont font1 = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);  
		    WritableCellFormat cellFormat1 = new WritableCellFormat(font1); 
		    cellFormat1.setBackground(Colour.RED);
		    cellFormat1.setAlignment(Alignment.CENTRE);
		    
		    WritableFont font2 = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);  
		    WritableCellFormat cellFormat2 = new WritableCellFormat(font2); 
		    cellFormat2.setAlignment(Alignment.CENTRE);
		//读取文件一
		BufferedReader brmz=new BufferedReader(new FileReader(file1));
		String sn=null;
		String yih=brmz.readLine();
		HashMap h1=new HashMap();
		HashMap h2=new HashMap();
		while((sn=brmz.readLine())!=null)
		{
			Pattern patternn = Pattern.compile("\t");
			String[] strs = patternn.split(sn);
			h1.put( strs[0]+"/"+strs[1] , strs);
		}
		brmz.close();
		//读取文件二
 		BufferedReader br2=new BufferedReader(new FileReader(file2));
 	String	 s=null;
 		br2.readLine();
 		
 		///开始计算
 			while((s=br2.readLine())!=null){
 				Pattern patternn = Pattern.compile("\t");
 				String[] strs = patternn.split(s);
 				h2.put( strs[0]+"/"+strs[1] , strs);
 				 
 			}
 		br2.close();	
 	 ////提取文件一的每一行
 		   Iterator iter = h1.entrySet().iterator();
 		   int j=1;
 		  while (iter.hasNext()) { 			  
 			Map.Entry entry = (Map.Entry) iter.next();
 			Object key = (String) entry.getKey();
 			String[] val = (String[]) entry.getValue();
 	if(h2.containsKey(key)){
 			String[] val2=(String[]) h2.get(key);
 			   String btzz=(String)key;
 				Pattern patte = Pattern.compile("/");
 				String[] strr = patte.split(btzz);
 			String btz1=strr[0];
 			String btz2=strr[1];
 			Label bt=new Label(0,j,btz1  ,cellFormat2);
 			ws.addCell(bt);
 			Label bt2=new Label(1,j,btz2,cellFormat2);
 			ws.addCell(bt2);
 			for(int i=2;i<31;i++)
   		{      String bj1=val[i];
   		       String bj2=val2[i]; 
   		       ArrayList<Character> b1=new ArrayList<Character>();
   		       b1.add('A');b1.add('T');
   		    ArrayList<Character> b2=new ArrayList<Character>();
   		    b2.add('C');b2.add('G');
   		       if(bj1.length()>2 && bj2.length()>2 ){
 				//if( (bj1.charAt(0)== bj2.charAt(0)  &&  bj1.charAt(2)== bj2.charAt(2))|| (bj1.charAt(0)== bj2.charAt(2)  &&  bj1.charAt(2)== bj2.charAt(0)) )
 				if((b1.contains(bj1.charAt(0)) && b1.contains(bj2.charAt(0))&&b2.contains(bj1.charAt(2)) && b2.contains(bj2.charAt(2)))||  (b1.contains(bj1.charAt(0)) && b1.contains(bj2.charAt(0))&&b1.contains(bj1.charAt(2)) && b1.contains(bj2.charAt(2))) ||  (b2.contains(bj1.charAt(0)) && b2.contains(bj2.charAt(0))&&b1.contains(bj1.charAt(2)) && b1.contains(bj2.charAt(2))||  (b2.contains(bj1.charAt(0)) && b2.contains(bj2.charAt(0))&&b2.contains(bj1.charAt(2)) && b2.contains(bj2.charAt(2)))) || (bj1.charAt(0)== bj2.charAt(2)  &&  bj1.charAt(2)== bj2.charAt(0))   )
   		    	  {
 					Label c1=new Label(i,j, bj1.substring(0,3) ,cellFormat2);  
 					ws.addCell(c1);  
 				}else{
 					Label c1=new Label(i,j, bj1.substring(0,3)+" - "+bj2.substring(0,3) ,cellFormat1);  
 					ws.addCell(c1); 
 				}
   		       }else{
					Label c1=new Label(i,j, bj1+" - "+bj2 ,cellFormat1);  
					ws.addCell(c1); 
				}
 					
 			}
 			
 j++;}
 			}
 			
 		  //写第一行 		
 			Pattern patternn = Pattern.compile("\t");
				String[] yh = patternn.split(yih);
 		 for(int m=0;m<31;m++)
 		 {
 			 Label c=new Label(m,0,yh[m],cellFormat2);
 			ws.addCell(c); 
 		 }
 		  
 		  
 		 try {  
 		     //从内存中写入文件中  
 		     wwb.write();  
 		     //关闭资源，释放内存  
 		     wwb.close();  
 		  } catch (IOException e) {  
 		     e.printStackTrace();  
 		  } catch (WriteException e) {  
 		     e.printStackTrace();  
 		 } 	
 		  
 		  
 		}
	}
}
		
	
	
	

