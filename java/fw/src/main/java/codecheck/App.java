package codecheck;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// test
public class App {
	
	// è©¦è¡Œå›æ•°
	private int n;
<<<<<<< HEAD
	// ’PŒêŒQ
=======
	// å˜èªè»
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	private ArrayList<String> words;
	// ç›´å‰ã®å˜èªã®æœ€å¾Œã®æ–‡å­—
	private char tailChar;
<<<<<<< HEAD
	// ‰ñ“š‚³‚ê‚½’PŒê
=======
	// é¸æŠã•ã‚ŒãŸå˜èª
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	private String selectedWord;
	// å…ˆæ”»AIã®èµ·å‹•ãƒ—ãƒ­ã‚°ãƒ©ãƒ 
	private String firstAI;
	// å¾Œæ”»AIã®èµ·å‹•ãƒ—ãƒ­ã‚°ãƒ©ãƒ 
	private String secondAI;
	
	/**
	 * ã‚³ãƒ³ã‚¹ãƒˆãƒ©ã‚¯ã‚¿
	 * ãƒ•ã‚£ãƒ¼ãƒ«ãƒ‰ã®åˆæœŸåŒ–
	 * 
	 * @param wã€€ã‚³ãƒãƒ³ãƒ‰ãƒ©ã‚¤ãƒ³å¼•æ•°
	 * 			ï¼ˆ0: å…ˆæ”»AIã®èµ·å‹•ãƒ—ãƒ­ã‚°ãƒ©ãƒ , 1: å¾Œæ”»AIã®èµ·å‹•ãƒ—ãƒ­ã‚°ãƒ©ãƒ , 2: é–‹å§‹å˜èª, 3~: å˜èªç¾¤ï¼‰
	 */
	public App(String[] w){
		// s‰ñ”‚Ì‰Šú‰»
		n = 0;
		
		// AI‚Ì‹N“®ƒvƒƒOƒ‰ƒ€‚ÌŠi”[
		firstAI = w[0];
		secondAI = w[1];
		
<<<<<<< HEAD
		// ŠJn’PŒê‚ÌŠi”[
=======
		// åˆã‚ã®æ–‡å­—ã®æ ¼ç´
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
		selectedWord = w[2];
		// ŠJn’PŒê‚ÌÅŒã‚Ì•¶š‚ÌŠi”[
		setTailChar(selectedWord);
		
		// ’PŒêŒQ‚ÌŠi”[
		words = new ArrayList<String>();
		for (int i = 3, l = w.length; i < l; i++) {
			words.add(new String(w[i]));
		}
	}
	
	/**
	 * calledAI
	 * AIã®èµ·å‹•ãƒ—ãƒ­ã‚°ãƒ©ãƒ ã®å®Ÿè¡Œã¨çµæœå–å¾—
	 * 
	 * @throws OutOfMemoryError, IOException, InterruptedException
	 */
	public String calledAI() throws OutOfMemoryError, IOException, InterruptedException{
		ProcessBuilder pb;
		
		// AI‚Ì‹N“®ƒvƒƒOƒ‰ƒ€‚ÌÀsƒRƒ}ƒ“ƒh
		List<String> command = new ArrayList<String>();
		command.add("sh");
		
		// Œ‹‰ÊŠi”[—pƒtƒB[ƒ‹ƒh
		String word = null;

		try{
			//*** AIã®èµ·å‹•ãƒ—ãƒ­ã‚°ãƒ©ãƒ ã®å®Ÿè¡Œ ***//
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

			//*** æ¨™æº–å‡ºåŠ›çµæœã®å–å¾— ***//
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
	 * å¯¾æˆ¦ä¸­ã®å‡ºåŠ›
	 * 
<<<<<<< HEAD
=======
	 * @param judge true: æœ‰åŠ¹ãªå›ç­”, false: ç„¡åŠ¹ãªå›ç­”
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	 */
	public void printPlay(boolean judge){
		// æU/ŒãU‚Ì”»’è
		if(n%2 == 0){
			System.out.print("FIRST ");
		}else{
			System.out.print("SECOND ");
		}
		
		// ‰ñ“š‚Ì—LŒø«”»’è
		if(judge == true){
			System.out.print("(OK): ");
		}else{
			System.out.print("(NG): ");
		}
		
		System.out.println(selectedWord);
	}
	
	/**
	 * prontFinish
	 * å¯¾æˆ¦çµæœã®å‡ºåŠ›
	 */
	public void printFinish(){
		// æU/ŒãU‚Ì”»’è
		if(n%2 == 0){
			System.out.println("WIN - SECOND");
		}else{
			System.out.println("WIN - FIRST");
		}
	}

	/**
<<<<<<< HEAD
	 * isCorrect
	 * ‰ñ“š‚³‚ê‚½’PŒê‚Ì—LŒø«”»’è
=======
	 * isCollect
	 * é¸æŠã•ã‚ŒãŸå˜èªãŒæœ‰åŠ¹ã§ã‚ã‚‹ã‹ã®æ¤œè¨¼
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	 * 
	 * @return (true: æœ‰åŠ¹, false: ç„¡åŠ¹)
	 */
	public boolean isCorrect(){
		// ‰ñ“š‚³‚ê‚½’PŒê‚Ìæ“ª•¶š
		char head = selectedWord.charAt(0);
<<<<<<< HEAD
		
		// ’¼‘O‚Ì‘Šè‚Ì‰ñ“š‚ÌÅŒã‚Ì•¶š‚Ån‚Ü‚ç‚È‚¢’PŒê‚ğ•Ô‚µ‚½
=======
		// ç›´å‰ã®ç›¸æ‰‹ã®å›ç­”ã®æœ€å¾Œã®æ–‡å­—ã§å§‹ã¾ã‚‰ãªã„å˜èªã‚’è¿”ã—ãŸ
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
		if(tailChar != head){
			return false;
		}
		// ç¾æ™‚ç‚¹ã®å˜èªç¾¤ã«å«ã¾ã‚Œãªã„å˜èªã‚’è¿”ã—ãŸ
		if(!words.contains(selectedWord)){
			return false;
		}
		
		return true;
	}

	/**
	 * setTailChar
	 * ç›´å‰ã®å˜èªã®æœ€å¾Œã®æ–‡å­—ã®æ ¼ç´
	 * 
<<<<<<< HEAD
	 * @param w ’¼‘O‚Ì’PŒê
=======
	 * @param w æ ¼ç´ã™ã‚‹æ–‡å­—
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	 */
	public void setTailChar(String w){
		tailChar = w.charAt(w.length()-1);
	}
	
	/**
	 * setSelectedWord
<<<<<<< HEAD
	 * ‰ñ“š‚³‚ê‚½’PŒê‚ÌŠi”[
=======
	 * é¸æŠã•ã‚ŒãŸå˜èªã®æ ¼ç´
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	 * 
	 * @param w æ ¼ç´ã™ã‚‹å˜èª
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
	 * selectedWord‚Ì’l‚Ìæ“¾
	 * 
	 * @return selectedWord
=======
	 * updateN
	 * è©¦è¡Œå›æ•°ã®æ›´æ–°
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	 */
	public String getSelectedWord(){
		return selectedWord;
	}
	
	/**
	 * updateField
	 * ƒtƒB[ƒ‹ƒh‚ÌXVin, tailChar, wordsj
	 */
	public void updateFields(){
		// s‰ñ”‚ÌXV
		n++;
		// ’¼‘O‚Ì’PŒê‚ÌÅŒã‚Ì•¶š‚ÌXV
		setTailChar(getSelectedWord());
		// ’¼‘O‚Ì’PŒê‚Ìíœ
		words.remove(words.indexOf(selectedWord));
	}
	
	/**
	 * ãƒ¡ã‚¤ãƒ³é–¢æ•°
	 * @param args ã‚³ãƒãƒ³ãƒ‰ãƒ©ã‚¤ãƒ³å¼•æ•°
	 * 			ï¼ˆ0: å…ˆæ”»AIã®èµ·å‹•ãƒ—ãƒ­ã‚°ãƒ©ãƒ , 1: å¾Œæ”»AIã®èµ·å‹•ãƒ—ãƒ­ã‚°ãƒ©ãƒ , 2: é–‹å§‹å˜èª, 3~: å˜èªç¾¤ï¼‰
	 */
	public static void main(String[] args) {
		
		// §–ñğŒF@’PŒê”‚ÌÅ‘å‚Í1000’PŒê
		if(args.length > 1000){
			System.out.println("Too many words (Limit is 1000 words)");
			System.exit(-1);
		}
		// §–ñğŒF@‚»‚ê‚¼‚ê‚Ì’PŒê‚ÌÅ‘å’·‚Í1–œ•¶š
		for(int i=0; i<args.length; i++){
			if(args[i].length() > 10000){
				System.out.println("Too long characters (Limit is 10000 characters): " + args[i]);
				System.exit(-1);
			}
		}
		
		// ‰Šú‰»
		App app = new App(args);
		String word;
		

		while(true){
			try{
<<<<<<< HEAD
				
				// ‰ñ“š‚µ‚½’PŒê‚ÌŠi”[
				word = app.calledAI();
				app.setSelectedWord(word);
				// Œ‹‰Êo—Í
				app.printPlay(app.isCorrect());
				
				// I—¹ğŒ
				if(!app.isCorrect()){
=======
				// å›ç­”ã—ãŸå˜èªã®æ ¼ç´
				app.setSelectedWord(app.calledAI());
				// çµæœå‡ºåŠ›
				isFinish = !app.isCollect();
				app.printPlay(app.isCollect());
				
				// ä¸æ­£ãªå›ç­”ã§ã‚ã‚‹
				if(isFinish){
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
					app.printFinish();
					break;
				}
				// ƒtƒB[ƒ‹ƒh‚ÌXV
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
