package codecheck;

import java.util.ArrayList;

public class App {

	// 単語軍
	private ArrayList<String> words;
	// 直前の単語の最後の文字
	private char tailChar;
	
	/**
	 * コンストラクタ
	 * フィールドの初期化
	 * 
	 * @param w　コマンドライン引数
	 * 			（0: 直前の相手の回答, 1~: 単語群）
	 */
	public App(String[] w){
		setTailChar(w[0]);
		
		words = new ArrayList<String>();
		for (int i = 1, l = w.length; i < l; i++) {
			words.add(new String(w[i]));
		}
	}
	
	public String selectWord(){
		char headChar;
		
		if(words.size() == 0){
			return null;
		}
		
		for(int i=0; i<words.size(); i++){
			headChar = words.get(i).charAt(0);
			if(tailChar == headChar){
				return words.get(i);
			}
		}
		return words.get(0);
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
	 * メイン関数
	 * @param args コマンドライン引数
	 * 			（0: 直前の相手の回答, 1~: 単語群）
	 */
	public static void main(String[] args) {

		App app = new App(args);
		System.out.println(app.selectWord());
		System.exit(0);
	}
}
