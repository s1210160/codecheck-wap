package codecheck;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class App {
	
	// 試行回数
	private int n;
	// 単語群
	private ArrayList<String> words;
	// 直前の単語の最後の文字
	private char tailChar;
	// 回答された単語
	private String selectedWord;
	// 先攻AIの起動プログラム
	private String firstAI;
	// 後攻AIの起動プログラム
	private String secondAI;
	
	/**
	 * コンストラクタ
	 * フィールドの初期化
	 * 
	 * @param w　コマンドライン引数
	 * 			（0: 先攻AIの起動プログラム, 1: 後攻AIの起動プログラム, 2: 開始単語, 3~: 単語群）
	 */
	public App(String[] w){
		// 試行回数の初期化
		n = 0;
		
		// AIの起動プログラムの格納
		firstAI = w[0];
		secondAI = w[1];
		
		// 開始単語の格納
		selectedWord = w[2];
		// 開始単語の最後の文字の格納
		setTailChar(selectedWord);
		
		// 単語群の格納
		words = new ArrayList<String>();
		for (int i = 3, l = w.length; i < l; i++) {
			words.add(new String(w[i]));
		}
	}
	
	/**
	 * calledAI
	 * AIの起動プログラムの実行と結果取得
	 * 
	 * @throws OutOfMemoryError, IOException, InterruptedException
	 */
	public String calledAI() throws OutOfMemoryError, IOException, InterruptedException{
		ProcessBuilder pb;
		
		// AIの起動プログラムの実行コマンド
		List<String> command = new ArrayList<String>();
		command.add("sh");
		
		// 結果格納用フィールド
		String word = null;

		try{
			//*** AIの起動プログラムの実行 ***//
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

			//*** 標準出力結果の取得 ***//
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
	 * 対戦中の出力
	 * 
	 */
	public void printPlay(boolean judge){
		// 先攻/後攻の判定
		if(n%2 == 0){
			System.out.print("FIRST ");
		}else{
			System.out.print("SECOND ");
		}
		
		// 回答の有効性判定
		if(judge == true){
			System.out.print("(OK): ");
		}else{
			System.out.print("(NG): ");
		}
		
		System.out.println(selectedWord);
	}
	
	/**
	 * prontFinish
	 * 対戦結果の出力
	 */
	public void printFinish(){
		// 先攻/後攻の判定
		if(n%2 == 0){
			System.out.println("WIN - SECOND");
		}else{
			System.out.println("WIN - FIRST");
		}
	}

	/**
	 * isCorrect
	 * 回答された単語の有効性判定
	 * 
	 * @return (true: 有効, false: 無効)
	 */
	public boolean isCorrect(){
		// 回答された単語の先頭文字
		char head = selectedWord.charAt(0);
		
		// 直前の相手の回答の最後の文字で始まらない単語を返した
		if(tailChar != head){
			return false;
		}
		// 現時点の単語群に含まれない単語を返した
		if(!words.contains(selectedWord)){
			return false;
		}
		
		return true;
	}

	/**
	 * setTailChar
	 * 直前の単語の最後の文字の格納
	 * 
	 * @param w 直前の単語
	 */
	public void setTailChar(String w){
		tailChar = w.charAt(w.length()-1);
	}
	
	/**
	 * setSelectedWord
	 * 回答された単語の格納
	 * 
	 * @param w 格納する単語
	 */
	public void setSelectedWord(String w){
		if(w.equals("null")){
			selectedWord = " ";
		}else{
			selectedWord = w;
		}
	}
	
	/**
	 * getSelectedWord
	 * selectedWordの値の取得
	 * 
	 * @return selectedWord
	 */
	public String getSelectedWord(){
		return selectedWord;
	}
	
	/**
	 * updateField
	 * フィールドの更新（n, tailChar, words）
	 */
	public void updateFields(){
		// 試行回数の更新
		n++;
		// 直前の単語の最後の文字の更新
		setTailChar(getSelectedWord());
		// 直前の単語の削除
		words.remove(words.indexOf(selectedWord));
	}
	
	/**
	 * メイン関数
	 * @param args コマンドライン引数
	 * 			（0: 先攻AIの起動プログラム, 1: 後攻AIの起動プログラム, 2: 開始単語, 3~: 単語群）
	 */
	public static void main(String[] args) {
		
		// 制約条件：　単語数の最大は1000単語
		if(args.length > 1000){
			System.out.println("Too many words (Limit is 1000 words)");
			System.exit(-1);
		}
		// 制約条件：　それぞれの単語の最大長は1万文字
		for(int i=0; i<args.length; i++){
			if(args[i].length() > 10000){
				System.out.println("Too long characters (Limit is 10000 characters): " + args[i]);
				System.exit(-1);
			}
		}
		
		// 初期化
		App app = new App(args);
		String word;
		

		while(true){
			try{
				
				// 回答した単語の格納
				word = app.calledAI();
				app.setSelectedWord(word);
				// 結果出力
				app.printPlay(app.isCorrect());
				
				// 終了条件
				if(!app.isCorrect()){
					app.printFinish();
					break;
				}
				// フィールドの更新
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
