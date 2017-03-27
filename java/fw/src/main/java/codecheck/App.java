package codecheck;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// test
public class App {
	
	// è©¦è¡åæ°
	private int n;
<<<<<<< HEAD
	// PêQ
=======
	// åèªè»
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	private ArrayList<String> words;
	// ç´åã®åèªã®æå¾ã®æå­
	private char tailChar;
<<<<<<< HEAD
	// ñ³ê½Pê
=======
	// é¸æãããåèª
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	private String selectedWord;
	// åæ»AIã®èµ·åãã­ã°ã©ã 
	private String firstAI;
	// å¾æ»AIã®èµ·åãã­ã°ã©ã 
	private String secondAI;
	
	/**
	 * ã³ã³ã¹ãã©ã¯ã¿
	 * ãã£ã¼ã«ãã®åæå
	 * 
	 * @param wãã³ãã³ãã©ã¤ã³å¼æ°
	 * 			ï¼0: åæ»AIã®èµ·åãã­ã°ã©ã , 1: å¾æ»AIã®èµ·åãã­ã°ã©ã , 2: éå§åèª, 3~: åèªç¾¤ï¼
	 */
	public App(String[] w){
		// sñÌú»
		n = 0;
		
		// AIÌN®vOÌi[
		firstAI = w[0];
		secondAI = w[1];
		
<<<<<<< HEAD
		// JnPêÌi[
=======
		// åãã®æå­ã®æ ¼ç´
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
		selectedWord = w[2];
		// JnPêÌÅãÌ¶Ìi[
		setTailChar(selectedWord);
		
		// PêQÌi[
		words = new ArrayList<String>();
		for (int i = 3, l = w.length; i < l; i++) {
			words.add(new String(w[i]));
		}
	}
	
	/**
	 * calledAI
	 * AIã®èµ·åãã­ã°ã©ã ã®å®è¡ã¨çµæåå¾
	 * 
	 * @throws OutOfMemoryError, IOException, InterruptedException
	 */
	public String calledAI() throws OutOfMemoryError, IOException, InterruptedException{
		ProcessBuilder pb;
		
		// AIÌN®vOÌÀsR}h
		List<String> command = new ArrayList<String>();
		command.add("sh");
		
		// Êi[ptB[h
		String word = null;

		try{
			//*** AIã®èµ·åãã­ã°ã©ã ã®å®è¡ ***//
			if(n%2 == 0){
				command.add(firstAI);
			}else{
				command.add(secondAI);
			}
			command.add(selectedWord);
			command.addAll(words);
			pb = new ProcessBuilder(command);
			pb.redirectErrorStream(true);
			Process p = pb.start();
			p.waitFor();
			//************************//

			//*** æ¨æºåºåçµæã®åå¾ ***//
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			word = reader.readLine();
			
			//*********************//

		}catch(OutOfMemoryError e){
			e.printStackTrace();
			throw e;
		}catch(IOException e){
			e.printStackTrace();
			throw e;
		}catch(InterruptedException e){
			e.printStackTrace();
			throw e;
		}
		
		return word;
	}
	
	/**
	 * printPlay
	 * å¯¾æ¦ä¸­ã®åºå
	 * 
<<<<<<< HEAD
=======
	 * @param judge true: æå¹ãªåç­, false: ç¡å¹ãªåç­
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	 */
	public void printPlay(boolean judge){
		// æU/ãUÌ»è
		if(n%2 == 0){
			System.out.print("FIRST ");
		}else{
			System.out.print("SECOND ");
		}
		
		// ñÌLø«»è
		if(judge == true){
			System.out.print("(OK): ");
		}else{
			System.out.print("(NG): ");
		}
		
		System.out.println(selectedWord);
	}
	
	/**
	 * prontFinish
	 * å¯¾æ¦çµæã®åºå
	 */
	public void printFinish(){
		// æU/ãUÌ»è
		if(n%2 == 0){
			System.out.println("WIN - SECOND");
		}else{
			System.out.println("WIN - FIRST");
		}
	}

	/**
<<<<<<< HEAD
	 * isCorrect
	 * ñ³ê½PêÌLø«»è
=======
	 * isCollect
	 * é¸æãããåèªãæå¹ã§ãããã®æ¤è¨¼
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	 * 
	 * @return (true: æå¹, false: ç¡å¹)
	 */
	public boolean isCorrect(){
		// ñ³ê½PêÌæª¶
		char head = selectedWord.charAt(0);
<<<<<<< HEAD
		
		// ¼OÌèÌñÌÅãÌ¶ÅnÜçÈ¢PêðÔµ½
=======
		// ç´åã®ç¸æã®åç­ã®æå¾ã®æå­ã§å§ã¾ããªãåèªãè¿ãã
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
		if(tailChar != head){
			return false;
		}
		// ç¾æç¹ã®åèªç¾¤ã«å«ã¾ããªãåèªãè¿ãã
		if(!words.contains(selectedWord)){
			return false;
		}
		
		return true;
	}

	/**
	 * setTailChar
	 * ç´åã®åèªã®æå¾ã®æå­ã®æ ¼ç´
	 * 
<<<<<<< HEAD
	 * @param w ¼OÌPê
=======
	 * @param w æ ¼ç´ããæå­
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	 */
	public void setTailChar(String w){
		tailChar = w.charAt(w.length()-1);
	}
	
	/**
	 * setSelectedWord
<<<<<<< HEAD
	 * ñ³ê½PêÌi[
=======
	 * é¸æãããåèªã®æ ¼ç´
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	 * 
	 * @param w æ ¼ç´ããåèª
	 */
	public void setSelectedWord(String w){
		if(w.equals("null")){
			selectedWord = " ";
		}else{
			selectedWord = w;
		}
	}
	
	/**
<<<<<<< HEAD
	 * getSelectedWord
	 * selectedWordÌlÌæ¾
	 * 
	 * @return selectedWord
=======
	 * updateN
	 * è©¦è¡åæ°ã®æ´æ°
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	 */
	public String getSelectedWord(){
		return selectedWord;
	}
	
	/**
	 * updateField
	 * tB[hÌXVin, tailChar, wordsj
	 */
	public void updateFields(){
		// sñÌXV
		n++;
		// ¼OÌPêÌÅãÌ¶ÌXV
		setTailChar(getSelectedWord());
		// ¼OÌPêÌí
		words.remove(words.indexOf(selectedWord));
	}
	
	/**
	 * ã¡ã¤ã³é¢æ°
	 * @param args ã³ãã³ãã©ã¤ã³å¼æ°
	 * 			ï¼0: åæ»AIã®èµ·åãã­ã°ã©ã , 1: å¾æ»AIã®èµ·åãã­ã°ã©ã , 2: éå§åèª, 3~: åèªç¾¤ï¼
	 */
	public static void main(String[] args) {
		
		// §ñðF@PêÌÅåÍ1000Pê
		if(args.length > 1000){
			System.out.println("Too many words (Limit is 1000 words)");
			System.exit(-1);
		}
		// §ñðF@»ê¼êÌPêÌÅå·Í1¶
		for(int i=0; i<args.length; i++){
			if(args[i].length() > 10000){
				System.out.println("Too long characters (Limit is 10000 characters): " + args[i]);
				System.exit(-1);
			}
		}
		
		// ú»
		App app = new App(args);
		String word;
		

		while(true){
			try{
<<<<<<< HEAD
				
				// ñµ½PêÌi[
				word = app.calledAI();
				app.setSelectedWord(word);
				// ÊoÍ
				app.printPlay(app.isCorrect());
				
				// I¹ð
				if(!app.isCorrect()){
=======
				// åç­ããåèªã®æ ¼ç´
				app.setSelectedWord(app.calledAI());
				// çµæåºå
				isFinish = !app.isCollect();
				app.printPlay(app.isCollect());
				
				// ä¸æ­£ãªåç­ã§ãã
				if(isFinish){
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
					app.printFinish();
					break;
				}
				// tB[hÌXV
				app.updateFields();
				
				
			}catch(OutOfMemoryError e){
				e.printStackTrace();
				System.exit(-1);
			}catch(IOException e){
				e.printStackTrace();
				System.exit(-1);
			}catch(InterruptedException e){
				e.printStackTrace();
				System.exit(-1);
			}

		}
		System.exit(0);
	}
}
