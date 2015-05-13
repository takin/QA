package module.interfaces;

import java.util.List;
import java.util.Map;

/**
 * Listener untuk memoitor proses tokenisasi dan PPOS Tagging
 * @author syamsul
 */
public interface TokenizerListener {
    
	/**
	 * Jika tokenizer berhasil, maka akan mengembalikan ArrayList 
	 * yang berisi Map<token, kata> dan Map<type, tipe_kata>
	 * 
	 * array list digunakan untuk menjaga urutan kata, karena urutan 
	 * kata diperlukan untuk melakukan proses parsing
	 * 
	 * @param taggedToken
	 */
    void onTokenizeSuccess(List<Map<String,String>> taggedToken);
    void onTokenizerUpdate(String message);
    void onTokenizeFail(String reason);
    
}
