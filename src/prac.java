/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shrey
 */
public class prac  {
	static int nl,nr;
	static boolean state=false;
	static String s1;
        static double logGamma(double x) {
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 + 76.18009173    / (x + 0)   - 86.50532033    / (x + 1)
                       + 24.01409822    / (x + 2)   -  1.231739516   / (x + 3)
                       +  0.00120858003 / (x + 4)   -  0.00000536382 / (x + 5);
        return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
        }
        static float gamma(double x) { return (float)Math.exp(logGamma(x)); }

	static String calculate(String s){
        try{
        return ""+prac.calcuexp(s);
        }
        catch(Exception e){
            
        return "";
        }
        catch(StackOverflowError t) {
        return "";
        }
        }
        static float calcuexp(String s){
        //    System.out.println(nl+" "+nr);
        String s1=prac.trimb(prac.exbrace(s,0));
		double anstemp=prac.calculine(s1);
		if(nl==0 && nr==0)return (float)anstemp;
                s=s.substring(0,nl)+anstemp+s.substring(nr+1);
		nl=0;nr=0;

		return prac.calcuexp(s);

	}
	static double calculine(String s){
		 if(s.contains("+-"))s=s.substring(0,s.indexOf("+-"))+s.substring(s.indexOf("+-")+1);
		 if(s.contains("-+"))s=s.substring(0,s.indexOf("-+")+1)+s.substring(s.indexOf("-+")+2);
		 if(s.contains("*-"))s=s.substring(0,s.indexOf("*-")+1)+"m"+s.substring(s.indexOf("*-")+2);
		 if(s.contains("/-"))s=s.substring(0,s.indexOf("/-")+1)+"m"+s.substring(s.indexOf("/-")+2);
		 if(s.contains("^-"))s=s.substring(0,s.indexOf("^-")+1)+"m"+s.substring(s.indexOf("^-")+2);
		 int l=s.length();
		 s1=s;
		 s=prac.exbin(s);
         int sp1,sp2,i,j;
         sp1=s.indexOf(" ");
         sp2=s.lastIndexOf(" ");
         i=Integer.parseInt(s.substring(sp1+1,sp2));
         j=Integer.parseInt(s.substring(sp2+1));
         s=s.substring(0,sp1);
         //System.out.println(s+" "+i+" "+j+" "+s1);
        double ans=prac.ansc(s);
         if(j<-5)
        return ans;
         try{
         	//s1.replace("m","-");
         	Double.parseDouble(s1);
         	return ans;
         }
         catch(Exception e){
         	//s1.replace("-","m");
         	}
         String anss=(ans+"");//.replace("-","m");
         
         s=s1.substring(0,i+1)+anss+s1.substring(j);
        //System.out.println(s+" "+i+" "+j+" "+s1);
         
         
         return prac.calculine(s);

	}
        static double parse(String s){
		double ans=0.0;
		s=s.replace("m","-");
		if(s.startsWith("sin")){if(home.degree==false)ans=Math.sin(prac.parse(s.substring(3)));else ans=Math.sin(Math.toRadians(prac.parse(s.substring(3))));}
		else if(s.endsWith("!")){ans=prac.gamma(prac.parse(s.substring(0,s.length()-1))+1);}
 		else if(s.endsWith("%")){ans=prac.parse(s.substring(0,s.length()-1))*0.01;}
 		else if(s.startsWith("cos")){if(home.degree==false)ans=Math.cos(prac.parse(s.substring(3)));else ans=Math.cos(Math.toRadians(prac.parse(s.substring(3))));}
		else if(s.startsWith("tan")){if(home.degree==false)ans=Math.tan(prac.parse(s.substring(3)));else ans=Math.tan(Math.toRadians(prac.parse(s.substring(3))));}
		else if(s.startsWith("ln")){ans=Math.log(prac.parse(s.substring(2)));}
		else if(s.startsWith("log")){ans=Math.log10(prac.parse(s.substring(3)));}
 		else if(s.startsWith("sqrt")){ans=Math.sqrt(prac.parse(s.substring(4)));}
 		else if(s.startsWith("sqp")){ans=Math.pow(prac.parse(s.substring(3)),2);}
 		else if(s.startsWith("asin")){if(home.degree==false)ans=Math.asin(prac.parse(s.substring(4)));else ans=Math.toDegrees(Math.asin(prac.parse(s.substring(4))));}
 		else if(s.startsWith("acos")){if(home.degree==false)ans=Math.acos(prac.parse(s.substring(4)));else ans=Math.toDegrees(Math.acos(prac.parse(s.substring(4))));}
 		else if(s.startsWith("atan")){if(home.degree==false)ans=Math.atan(prac.parse(s.substring(4)));else ans=Math.toDegrees(Math.atan(prac.parse(s.substring(4))));}
 		else if(s.startsWith("exp")){ans=Math.exp(prac.parse(s.substring(3)));}
 		else if(s.startsWith("etn")){ans=Math.pow(10,prac.parse(s.substring(3)));}
 		else if(s.startsWith("ep")){ans=Math.E;}
                else if(s.startsWith("pi")){ans=Math.PI;}
                else ans=Double.parseDouble(s);
	    return ans;
	}
	
	static double ansc(String s){
		int pos=-1;
		double ans=0.0;
		if(s.contains("+"))pos=s.indexOf("+");
		if(s.contains("-"))pos=s.indexOf("-");
		if(s.contains("*"))pos=s.indexOf("*");
		if(s.contains("/"))pos=s.indexOf("/");
		if(s.contains("^"))pos=s.indexOf("^");
		if(pos==-1){
		if(s.startsWith("m"))ans=(-1)*prac.parse(s.substring(1));
		else ans=prac.parse(s);
		return ans;}
		String s1=s.substring(0,pos),s2=s.substring(pos+1);
                if(s.startsWith("m")){}
		else{
		try{
			prac.parse(s1);
		}
		catch(Exception e){
			s=s.replace("-","m");
			return prac.ansc(s);
		}}
		double d1,d2;
		if(s1.startsWith("m")){d1=(-1)*prac.parse(s1.substring(1));}
		else {d1=prac.parse(s1);}
		if(s2.startsWith("m")){d2=(-1)*prac.parse(s2.substring(1));}
		else {d2=prac.parse(s2);}
		
        switch(s.charAt(pos)){
        	case '+':ans=d1+d2;break;
        	case '-':ans=d1-d2;break;
        	case '*':ans=d1*d2;break;
        	case '/':ans=d1/d2;break;
        	case '^':ans=Math.pow(d1,d2);break;
        }
		return ans;
	}
	static String exbin(String s){
		String ans="";
		if(s.contains("+-")){
			s=s.substring(0,s.indexOf("+-"))+s.substring(s.indexOf("+-")+1);
		}
		if(s.charAt(0)=='-'){s="m"+s.substring(1);}
		int pi=s.indexOf("+"),mi=s.indexOf("-"),mui=s.indexOf("*"),di=s.indexOf("/"),ei=s.indexOf("^");
		int i=-1;
		if(!s.contains("+"))pi=Integer.MAX_VALUE;
		if(!s.contains("-"))mi=Integer.MAX_VALUE;
		if(!s.contains("*"))mui=Integer.MAX_VALUE;
		if(!s.contains("/"))di=Integer.MAX_VALUE;
		if(!s.contains("^"))ei=Integer.MAX_VALUE;		
		int j=Math.min(Math.min(Math.min(pi,mi),Math.min(mui,di)),ei);
		int k=j+1;
		//System.out.println(pi+" "+mi+" "+mui+" "+di+" "+ei);
		//System.out.println(i+" "+j+" "+k);
		if(j==Integer.MAX_VALUE)return s+" "+i+" "+k;
		char pos;
		for(;k<s.length();k++){
			pos=s.charAt(k);
			if((pos=='^' || pos=='/' || pos=='*' || pos=='-' || pos=='+')&& k!=j)
			{
				if(s.charAt(j)=='^')break;
				else if(s.charAt(j)=='/' && pos!='^')break;
				else if(s.charAt(j)=='*' && pos!='/' && pos!='^')break;
				else if(s.charAt(j)=='-' && pos!='/' && pos!='^' && pos!='*'){break;}
				else if(s.charAt(j)=='+' && pos!='/' && pos!='^' && pos!='*' && pos!='-')break;
				else {
					i=j;
					j=k;
				}
			}
			ans=s.substring(i+1,k+1);			
		}
		return ans+" "+i+" "+k;
		
	}
	static String trimb(String s){
		if(s.startsWith("(")){
			s=s.replace("(","");
		}
		if(s.endsWith(")")){
			s=s.replace(")","");
		}
                return s;
	}
	static String exbrace(String s,int n){
		if(n==s.length())return s;
		if(s.charAt(n)==('(')){
			nl=n;
			state=true;
		}
		else if(s.charAt(n)==(')')){
		nr=n;
		return s.substring(nl,nr+1);
		}
			return prac.exbrace(s,n+1);
			

	}
	/*public static void main(String args[]){
          System.out.println(calcuexp("2+5"));

	}*/

}
