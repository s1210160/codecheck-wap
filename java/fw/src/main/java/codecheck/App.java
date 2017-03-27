package codecheck;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// test
public class App {
	
	// 試行回数
	private int n;
<<<<<<< HEAD
	// �P��Q
=======
	// 単語軍
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	private ArrayList<String> words;
	// 直前の単語の最後の文字
	private char tailChar;
<<<<<<< HEAD
	// �񓚂��ꂽ�P��
=======
	// 選択された単語
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
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
		// ���s�񐔂̏�����
		n = 0;
		
		// AI�̋N���v���O�����̊i�[
		firstAI = w[0];
		secondAI = w[1];
		
<<<<<<< HEAD
		// �J�n�P��̊i�[
=======
		// 初めの文字の格納
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
		selectedWord = w[2];
		// �J�n�P��̍Ō�̕����̊i�[
		setTailChar(selectedWord);
		
		// �P��Q�̊i�[
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
		
		// AI�̋N���v���O�����̎��s�R�}���h
		List<String> command = new ArrayList<String>();
		command.add("sh");
		
		// ���ʊi�[�p�t�B�[���h
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
<<<<<<< HEAD
=======
	 * @param judge true: 有効な回答, false: 無効な回答
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	 */
	public void printPlay(boolean judge){
		// ��U/��U�̔���
		if(n%2 == 0){
			System.out.print("FIRST ");
		}else{
			System.out.print("SECOND ");
		}
		
		// �񓚂̗L��������
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
		// ��U/��U�̔���
		if(n%2 == 0){
			System.out.println("WIN - SECOND");
		}else{
			System.out.println("WIN - FIRST");
		}
	}

	/**
<<<<<<< HEAD
	 * isCorrect
	 * �񓚂��ꂽ�P��̗L��������
=======
	 * isCollect
	 * 選択された単語が有効であるかの検証
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	 * 
	 * @return (true: 有効, false: 無効)
	 */
	public boolean isCorrect(){
		// �񓚂��ꂽ�P��̐擪����
		char head = selectedWord.charAt(0);
<<<<<<< HEAD
		
		// ���O�̑���̉񓚂̍Ō�̕����Ŏn�܂�Ȃ��P���Ԃ���
=======
		// 直前の相手の回答の最後の文字で始まらない単語を返した
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
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
<<<<<<< HEAD
	 * @param w ���O�̒P��
=======
	 * @param w 格納する文字
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	 */
	public void setTailChar(String w){
		tailChar = w.charAt(w.length()-1);
	}
	
	/**
	 * setSelectedWord
<<<<<<< HEAD
	 * �񓚂��ꂽ�P��̊i�[
=======
	 * 選択された単語の格納
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
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
<<<<<<< HEAD
	 * getSelectedWord
	 * selectedWord�̒l�̎擾
	 * 
	 * @return selectedWord
=======
	 * updateN
	 * 試行回数の更新
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
	 */
	public String getSelectedWord(){
		return selectedWord;
	}
	
	/**
	 * updateField
	 * �t�B�[���h�̍X�V�in, tailChar, words�j
	 */
	public void updateFields(){
		// ���s�񐔂̍X�V
		n++;
		// ���O�̒P��̍Ō�̕����̍X�V
		setTailChar(getSelectedWord());
		// ���O�̒P��̍폜
		words.remove(words.indexOf(selectedWord));
	}
	
	/**
	 * メイン関数
	 * @param args コマンドライン引数
	 * 			（0: 先攻AIの起動プログラム, 1: 後攻AIの起動プログラム, 2: 開始単語, 3~: 単語群）
	 */
	public static void main(String[] args) {
		
		// ��������F�@�P�ꐔ�̍ő��1000�P��
		if(args.length > 1000){
			System.out.println("Too many words (Limit is 1000 words)");
			System.exit(-1);
		}
		// ��������F�@���ꂼ��̒P��̍ő咷��1������
		for(int i=0; i<args.length; i++){
			if(args[i].length() > 10000){
				System.out.println("Too long characters (Limit is 10000 characters): " + args[i]);
				System.exit(-1);
			}
		}
		
		// ������
		App app = new App(args);
		String word;
		

		while(true){
			try{
<<<<<<< HEAD
				
				// �񓚂����P��̊i�[
				word = app.calledAI();
				app.setSelectedWord(word);
				// ���ʏo��
				app.printPlay(app.isCorrect());
				
				// �I������
				if(!app.isCorrect()){
=======
				// 回答した単語の格納
				app.setSelectedWord(app.calledAI());
				// 結果出力
				isFinish = !app.isCollect();
				app.printPlay(app.isCollect());
				
				// 不正な回答である
				if(isFinish){
>>>>>>> 6c02ec8521c097d9632a9e51de4f2fc3bf80bdd2
					app.printFinish();
					break;
				}
				// �t�B�[���h�̍X�V
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
