package codecheck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class App {
	
	// ���s��
	private int n;
	// �P��R
	private ArrayList<String> words;
	// ���O�̒P��̍Ō�̕���
	private char tailChar;
	// �I�����ꂽ�P��
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
		n = 0;
		words = new ArrayList<String>();
		firstAI = w[0];
		secondAI = w[1];
		
		// ���߂̕����̊i�[
		selectedWord = w[2];
		setTailChar(selectedWord);
		
		for (int i = 3, l = w.length; i < l; i++) {
			words.add(new String(w[i]));
		}
	}
	
	/**
	 * calledAI
	 * AI�̋N���v���O�����̎��s�ƌ��ʎ擾
	 * 
	 * @throws Exception
	 */
	public String calledAI() throws Exception{
		ProcessBuilder pb;
		
		List<String> command = new ArrayList<String>();
		command.add("sh");

		try{
			//*** AI�̋N���v���O�����̎��s ***//
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

			//*** �W���o�͌��ʂ̎擾 ***//
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
	 * �ΐ풆�̏o��
	 * 
	 * @param judge true: �L���ȉ�, false: �����ȉ�
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
	 * �ΐ팋�ʂ̏o��
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
	 * �I�����ꂽ�P�ꂪ�L���ł��邩�̌���
	 * 
	 * @return (true: �L��, false: ����)
	 */
	public boolean isCollect(){
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
	 * @param w �i�[���镶��
	 */
	public void setTailChar(String w){
		tailChar = w.charAt(w.length()-1);
	}
	
	/**
	 * setSelectedWord
	 * �I�����ꂽ�P��̊i�[
	 * 
	 * @param w �i�[����P��
	 */
	public void setSelectedWord(String w){
		selectedWord = w;
	}
	
	/**
	 * updateN
	 * ���s�񐔂̍X�V
	 */
	public void updateN(){
		n++;
	}
	
	/**
	 * ���C���֐�
	 * @param args �R�}���h���C������
	 * 			�i0: ��UAI�̋N���v���O����, 1: ��UAI�̋N���v���O����, 2: �J�n�P��, 3~: �P��Q�j
	 */
	public static void main(String[] args) {
		
		App app = new App(args);
		boolean isFinish = false;

		while(true){
			try{
				// �񓚂����P��̊i�[
				app.setSelectedWord(app.calledAI());
				// ���ʏo��
				isFinish = !app.isCollect();
				app.printPlay(app.isCollect());
				
				// �s���ȉ񓚂ł���
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
