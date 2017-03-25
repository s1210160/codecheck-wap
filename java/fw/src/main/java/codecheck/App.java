package codecheck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class App {
	
	// 試行回数
	private int n;
	// 単語軍
	private ArrayList<String> words;
	// 直前の単語の最後の文字
	private char tailChar;
	// 選択された単語
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
		n = 0;
		words = new ArrayList<String>();
		firstAI = w[0];
		secondAI = w[1];
		
		// 初めの文字の格納
		selectedWord = w[2];
		setTailChar(selectedWord);
		
		for (int i = 3, l = w.length; i < l; i++) {
			words.add(new String(w[i]));
		}
	}
	
	/**
	 * calledAI
	 * AIの起動プログラムの実行と結果取得
	 * 
	 * @throws Exception
	 */
	public String calledAI() throws Exception{
		ProcessBuilder pb;
		
		List<String> command = new ArrayList<String>();
		command.add("sh");

		try{
			//*** AIの起動プログラムの実行 ***//
			if(n%2 == 0){
				command.add(firstAI);
			}else{
				command.add(secondAI);
			}
			command.addAll(words);
			pb = new ProcessBuilder(command);
			pb.redirectErrorStream(true);
			Process p = pb.start();
			p.waitFor();
			//************************//

			//*** 標準出力結果の取得 ***//
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			return reader.readLine();
			
			//*********************//

		}catch(Exception ex){
			System.out.println(ex.toString());
			ex.printStackTrace();
			throw ex;
		}
	}
	
	/**
	 * printPlay
	 * 対戦中の出力
	 * 
	 * @param judge true: 有効な回答, false: 無効な回答
	 */
	public void printPlay(boolean judge){
		if(n%2 == 0){
			System.out.print("FIRST ");
		}else{
			System.out.print("SECOND ");
		}
		
		if(judge == true){
			System.out.print("(OK): ");
		}else{
			System.out.print("(NG): ");
		}
		System.out.println(selectedWord);
		words.remove(words.indexOf(selectedWord));
	}
	
	/**
	 * prontFinish
	 * 対戦結果の出力
	 */
	public void printFinish(){
		if(n%2 == 0){
			System.out.println("WIN - FIRST");
		}else{
			System.out.println("WIN - SECOND");
		}
	}

	/**
	 * isCollect
	 * 選択された単語が有効であるかの検証
	 * 
	 * @return (true: 有効, false: 無効)
	 */
	public boolean isCollect(){
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
	 * @param w 格納する文字
	 */
	public void setTailChar(String w){
		tailChar = w.charAt(w.length()-1);
	}
	
	/**
	 * setSelectedWord
	 * 選択された単語の格納
	 * 
	 * @param w 格納する単語
	 */
	public void setSelectedWord(String w){
		selectedWord = w;
	}
	
	/**
	 * updateN
	 * 試行回数の更新
	 */
	public void updateN(){
		n++;
	}
	
	/**
	 * メイン関数
	 * @param args コマンドライン引数
	 * 			（0: 先攻AIの起動プログラム, 1: 後攻AIの起動プログラム, 2: 開始単語, 3~: 単語群）
	 */
	public static void main(String[] args) {
		
		App app = new App(args);
		boolean isFinish = false;

		while(true){
			try{
				// 回答した単語の格納
				app.setSelectedWord(app.calledAI());
				// 結果出力
				isFinish = !app.isCollect();
				app.printPlay(app.isCollect());
				
				// 不正な回答である
				if(isFinish){
					app.printFinish();
					break;
				}
				
				app.updateN();
				
			}catch(Exception e){
				System.out.println("Exception!!!!!");
			}
		}

	}
}
