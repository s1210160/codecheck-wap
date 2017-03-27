package codecheck;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class App {
	
	// ���s��
	private int n;
	// �P��Q
	private ArrayList<String> words;
	// ���O�̒P��̍Ō�̕���
	private char tailChar;
	// �񓚂��ꂽ�P��
	private String selectedWord;
	// ��UAI�̋N���v���O����
	private String firstAI;
	// ��UAI�̋N���v���O����
	private String secondAI;
	
	/**
	 * �R���X�g���N�^
	 * �t�B�[���h�̏�����
	 * 
	 * @param w�@�R�}���h���C������
	 * 			�i0: ��UAI�̋N���v���O����, 1: ��UAI�̋N���v���O����, 2: �J�n�P��, 3~: �P��Q�j
	 */
	public App(String[] w){
		// ���s�񐔂̏�����
		n = 0;
		
		// AI�̋N���v���O�����̊i�[
		firstAI = w[0];
		secondAI = w[1];
		
		// �J�n�P��̊i�[
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
	 * AI�̋N���v���O�����̎��s�ƌ��ʎ擾
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
			//*** AI�̋N���v���O�����̎��s ***//
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

			//*** �W���o�͌��ʂ̎擾 ***//
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
	 * �ΐ풆�̏o��
	 * 
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
	 * �ΐ팋�ʂ̏o��
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
	 * isCorrect
	 * �񓚂��ꂽ�P��̗L��������
	 * 
	 * @return (true: �L��, false: ����)
	 */
	public boolean isCorrect(){
		// �񓚂��ꂽ�P��̐擪����
		char head = selectedWord.charAt(0);
		
		// ���O�̑���̉񓚂̍Ō�̕����Ŏn�܂�Ȃ��P���Ԃ���
		if(tailChar != head){
			return false;
		}
		// �����_�̒P��Q�Ɋ܂܂�Ȃ��P���Ԃ���
		if(!words.contains(selectedWord)){
			return false;
		}
		
		return true;
	}

	/**
	 * setTailChar
	 * ���O�̒P��̍Ō�̕����̊i�[
	 * 
	 * @param w ���O�̒P��
	 */
	public void setTailChar(String w){
		tailChar = w.charAt(w.length()-1);
	}
	
	/**
	 * setSelectedWord
	 * �񓚂��ꂽ�P��̊i�[
	 * 
	 * @param w �i�[����P��
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
	 * selectedWord�̒l�̎擾
	 * 
	 * @return selectedWord
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
	 * ���C���֐�
	 * @param args �R�}���h���C������
	 * 			�i0: ��UAI�̋N���v���O����, 1: ��UAI�̋N���v���O����, 2: �J�n�P��, 3~: �P��Q�j
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
				
				// �񓚂����P��̊i�[
				word = app.calledAI();
				app.setSelectedWord(word);
				// ���ʏo��
				app.printPlay(app.isCorrect());
				
				// �I������
				if(!app.isCorrect()){
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
