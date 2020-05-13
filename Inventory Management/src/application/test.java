package application;

import java.util.ArrayList;

public class test {
	
	public static void main(String[] args) {
		Taboo_List ta_list = new Taboo_List();
		 System.out.println(ta_list.getTaboo_word().size());
		@SuppressWarnings("unchecked")
		ArrayList<String> b = (ArrayList<String>) ta_list.getTaboo_word().clone();
		
		String  bad = "fuck232";
	        for(int i1 = 0; i1 < b.size(); i1++) {
	        	
	    		if(bad.contains(b.get(i1)))
	    		{
	    			bad = bad.replaceAll(b.get(i1), "***");
	    			
	    		}
	    	}
	        System.out.println(bad);
		// TODO Auto-generated method stub
/*for(int i1 = 0; i1 < bad_word_list.getTaboo_word().size(); i1++) {
			
    		if(textField.contains(ta_list.getTaboo_word().get(i1)))
    		{
    			textField.setText(textField.getText().replaceAll(bad_word_list.getTaboo_word().get(i1), "***"));
    		}
    	}*/
	}

}
