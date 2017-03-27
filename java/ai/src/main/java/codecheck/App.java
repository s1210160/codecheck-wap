package codecheck;

import java.util.ArrayList;

public class App {

	// �P��R
	private ArrayList<String> words;
	// ���O�̒P��̍Ō�̕���
	private char tailChar;
	
	/**
	 * �R���X�g���N�^
	 * �t�B�[���h�̏�����
	 * 
	 * @param w�@�R�}���h���C������
	 * 			�i0: ���O�̑���̉�, 1~: �P��Q�j
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
	 * ���O�̒P��̍Ō�̕����̊i�[
	 * 
	 * @param w ���O�̒P��
	 */
	public void setTailChar(String w){
		tailChar = w.charAt(w.length()-1);
	}
	
	/**
	 * ���C���֐�
	 * @param args �R�}���h���C������
	 * 			�i0: ���O�̑���̉�, 1~: �P��Q�j
	 */
	public static void main(String[] args) {

		App app = new App(args);
		System.out.println(app.selectWord());
		System.exit(0);
	}
}
