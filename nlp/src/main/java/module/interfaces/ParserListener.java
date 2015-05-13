package module.interfaces;

import java.util.Map;

/**
 * interface untuk memonitor proses pembentukan parse tree
 * @author syamsul
 */
public interface ParserListener {
    
    void onPraseSuccess(Map<String, Object> parseResult);
    void onParseFail(String reason);
    
}
